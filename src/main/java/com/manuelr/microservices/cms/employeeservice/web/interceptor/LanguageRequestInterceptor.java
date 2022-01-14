package com.manuelr.microservices.cms.employeeservice.web.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

@Component
public class LanguageRequestInterceptor implements RequestInterceptor {
    private static final String X_FORWARDED_HOST = "X-Forwarded-Host";
    private static final String HOST = "localhost:8080";

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes))
            return;
        template.header(X_FORWARDED_HOST, HOST);
    }
}
