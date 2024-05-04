package aithings.camAI.controlpanel.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import aithings.camAI.controlpanel.utils.constant.BEConstant;

import java.time.LocalDateTime;

public class CriteriaUtils {

    public static Criteria criteriaRangeTime(String from, String to, String fieldName) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime fromDate;
        LocalDateTime toDate;
        if (StringUtils.isEmpty(from) || StringUtils.isEmpty(to)) {
            fromDate = currentDate.minusDays(BEConstant.DEFAULT_SEARCH_DAYS);
            toDate = currentDate;
        } else {
            fromDate = DateTimeUtils.convertStringToDate(from);
            toDate = DateTimeUtils.convertStringToDate(to);
        }
        fromDate = fromDate.withHour(0).withMinute(0).withSecond(0);
        toDate = toDate.withHour(23).withMinute(59).withSecond(59);
        return Criteria.where(fieldName).gte(fromDate).lte(toDate);
    }
}
