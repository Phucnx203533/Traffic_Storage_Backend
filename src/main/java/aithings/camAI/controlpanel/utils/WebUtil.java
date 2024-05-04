package aithings.camAI.controlpanel.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import aithings.camAI.controlpanel.config.security.CustomerUserDetails;
import aithings.camAI.controlpanel.dto.SubFunctionDTO;
import aithings.camAI.controlpanel.entity.SAUserEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class WebUtil {
    private WebUtil() {
    }

    public static CustomerUserDetails getUserDetail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        if (auth instanceof AnonymousAuthenticationToken) {
            log.info("user is anonymousAuthentication");
        }
        if (auth.getPrincipal() instanceof CustomerUserDetails) {
            return (CustomerUserDetails) auth.getPrincipal();
        } else {
            return (CustomerUserDetails) auth.getDetails();
        }
    }

    public static List<SubFunctionDTO> getSubFunction() {
        CustomerUserDetails userDetail = getUserDetail();
        return userDetail != null ? userDetail.getSubFunctions() : new ArrayList<>();
    }

    public static SAUserEntity getCurrentUser() {
        CustomerUserDetails userDetail = getUserDetail();
        return userDetail != null ? userDetail.getUser() : new SAUserEntity();
    }

    public static String getCurrentUsername() {
        CustomerUserDetails userDetail = getUserDetail();
        return userDetail != null ? (userDetail.getUser().getId() != null ? userDetail.getUser().getId() : "") : "";
    }

    public static String getCurrentRole() {
        CustomerUserDetails userDetail = getUserDetail();
        return userDetail != null ? (userDetail.getUser().getRole() != null ? userDetail.getUser().getRole() : "") : "";
    }

    public static String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static String buildUrlForPaging(HttpServletRequest request, String baseUrl) {
        String urlQuery = request.getQueryString();
        if (StringUtils.isEmpty(urlQuery)) {
            return baseUrl;
        }
        return baseUrl + "?" + urlQuery.replaceAll("&page=\\d+", StringUtils.EMPTY)
                .replaceAll("page=\\d+", StringUtils.EMPTY);
    }

    public static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    public static String getBody(HttpServletRequest request) {
        if (HttpMethod.POST.toString().equals(request.getMethod())) {
            try {
                return request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }
}
