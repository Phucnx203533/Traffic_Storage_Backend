package aithings.camAI.controlpanel.dto.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Getter
@Setter
public class BaseRequest {

    private String requestId;
    private String fromDate;
    private String toDate;
    private String timeSearch;

    public void setTimeSearch(String timeSearch) {
        this.timeSearch = timeSearch;
        if (StringUtils.hasText(timeSearch)) {
            String[] arrTime = timeSearch.split(" - ");
            this.fromDate = arrTime[0];
            this.toDate = arrTime[1];
        }
    }

    public String getRequestId() {
        if(StringUtils.hasText(requestId)) {
            requestId = UUID.randomUUID().toString();
        }
        return requestId;
    }
}
