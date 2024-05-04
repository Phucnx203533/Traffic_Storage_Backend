package aithings.camAI.controlpanel.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Slf4j
public class DateTimeUtils {

    private static final String JSON_DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String DDMMYYYY = "ddMMyyyy";

    public static Long convertStringToTimestamp(String value, String pattern, int addDay) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            Date date = dateFormat.parse(value);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, addDay);
            return calendar.getTime().getTime();
        } catch (ParseException px) {
            log.error(px.getMessage(), px);
            throw px;
        }
    }

    public static Date endOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        return calendar.getTime();
    }
    public static Date copyHHmmss(Date destDate, Date sourceDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(destDate);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(sourceDate);
        calendar.set(Calendar.HOUR_OF_DAY, calendar2.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE,calendar2.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND,calendar2.get(Calendar.SECOND));
        return calendar.getTime();
    }

    public static Date startOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int addDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, addDay < 0 ? 0 : 23);
        calendar.set(Calendar.MINUTE, addDay < 0 ? 0 : 59);
        calendar.set(Calendar.SECOND, addDay < 0 ? 0 : 59);
        calendar.add(Calendar.DATE, addDay);
        return calendar.getTime();
    }

    public static Date convertTimestampToDate(Long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        return calendar.getTime();

    }
    public static String convertDateToStringYYYYMMDD(Date date,String cameraId){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String result = "";
        result+= "/year-"+calendar.get(Calendar.YEAR)+"/month-"+(calendar.get(Calendar.MONTH)+1)%12+"/day-"+calendar.get(Calendar.DAY_OF_MONTH)+"/"+cameraId+"/";
        return result;
    }

    public static String convertDate(String value) {

        String[] arr = value.split("/");

        String day = arr[0];
        String month = arr[1];
        String year = arr[2];
        if (Integer.parseInt(day) < 10) {
            day = "0" + Integer.parseInt(day);
        }
        if (Integer.parseInt(month) < 10) {
            month = "0" + Integer.parseInt(month);
        }


        return year + month + day;
    }

    public static List<String> getDays(int i, String pattern) {
        List<String> result = new ArrayList<>();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE,1);
        for(int j = 0; j < i; j++) {
            calendar.add(Calendar.DATE, -1);
            String date = simpleDateFormat.format(calendar.getTime());
            result.add(date);
        }
        return result;
    }


    public static List<String> getDays(Date fromDate, Date toDate,String pattern) {
        List<String> result = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        while (calendar.getTime().before(toDate)) {
            String date = simpleDateFormat.format(calendar.getTime());
            result.add(date);
            calendar.add(Calendar.DATE, 1);
        }
        return result;
    }

    public Date addDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, day);

        return calendar.getTime();
    }

    public static Date convertStringToDate(String date, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(date);
        } catch (Exception ex){
            return new Date();
        }
    }

    public static LocalDateTime convertStringToDate(String source) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(source).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime convertDateToLocalDateTime(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime dateToConvert) {
        return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String convertDateToString(Date datetime) {
        String s = null;
        if (datetime != null) {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy");
            s = formatter.format(datetime);
        }
        return s;
    }

    public static String convertDateToStringDDMMYYYYHHMISS(Date datetime) {
        String s = null;
        if (datetime != null) {
            Format formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            s = formatter.format(datetime);
        }
        return s;
    }

    public static String convertDateToString(Date datetime, String pattern) {
        String s = null;
        if (datetime != null) {
            Format formatter = new SimpleDateFormat(pattern);
            s = formatter.format(datetime);
        }
        return s;
    }

    public static String convertDateToStringDDMM(Date datetime) {
        String s = null;
        if (datetime != null) {
            Format formatter = new SimpleDateFormat("dd/MM");
            s = formatter.format(datetime);
        }
        return s;
    }

    public static String convertLongToStringDDMMYYY(Integer days){
        LocalDate date = LocalDate.of(1899, Month.DECEMBER, 30).plusDays(days);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatters);
    }

    /**
     * Chuyen doi du lieu tim kiem fromDate, toDate
     */
    public static void formatDateFilter(Map<String, Object> filter, String fromDate, String toDate) {
        try {
            if (fromDate != null && !fromDate.isEmpty()) {
                filter.put("fromDate", formatJsonDate(fromDate + " 00:00:00"));
            }
            if (toDate != null && !toDate.isEmpty()) {
                filter.put("toDate", formatJsonDate(toDate + " 23:59:59"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Date formatJsonDate(String jsonDate) throws ParseException {
        return (new SimpleDateFormat(JSON_DATE_FORMAT)).parse(jsonDate);
    }

    public static String formatNumber(Double percent) {
        NumberFormat formatter = new DecimalFormat("#0.00");

        return formatter.format(percent);
    }

    public static String formatDate(Date date) {
        return (new SimpleDateFormat(JSON_DATE_FORMAT)).format(date);
    }

    public static boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }
}
