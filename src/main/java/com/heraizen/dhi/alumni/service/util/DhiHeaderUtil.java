package com.heraizen.dhi.alumni.service.util;

import org.springframework.http.HttpHeaders;

public class DhiHeaderUtil {
    public static HttpHeaders createAlert(String applicationName, String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-alert", message);
        headers.add("X-" + applicationName + "-params", param);
        return headers;
    }
}
