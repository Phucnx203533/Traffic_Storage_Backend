package aithings.camAI.controlpanel.utils.constant;

import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public enum EActiveStatus {
    ACTIVE(1, "Hoạt động", "Active"),
    INACTIVE(0, "Khóa", "Lock");

    private final Integer value;
    final String nameVi;
    private final String nameEn;

    public String getText() {
        return this.nameVi;
    }

    EActiveStatus(Integer value, String nameVi, String nameEn) {
        this.nameVi = nameVi;
        this.value = value;
        this.nameEn = nameEn;
    }

    public static EActiveStatus findByValue(Integer value) {
        EActiveStatus[] statuses = EActiveStatus.values();
        for (EActiveStatus status : statuses) {
            if (Objects.equals(status.getValue(), value)) {
                return status;
            }
        }
        return null;
    }

    public static String getName(Integer value, String lang) {
        if (StringUtils.hasText(lang) && lang.length() == 2) {
            if (lang.toLowerCase().equals("vn")) {
                lang = "Vi";
            } else {
                lang = lang.substring(0, 1).toUpperCase() + lang.substring(1).toLowerCase();
            }
        } else {
            lang = "Vi";
        }
        EActiveStatus status = findByValue(value);
        return Objects.isNull(status) ? "" : (lang.equals("Vi") ? status.getNameVi() : status.getNameEn());
    }

    public static List<EActiveStatus> getAll() {
        return Arrays.asList(EActiveStatus.values());
    }

    public static EActiveStatus getActiveStatus(Integer value) {
        return getAll().stream().filter(item -> item.getValue().equals(value)).findFirst().orElse(null);
    }
}
