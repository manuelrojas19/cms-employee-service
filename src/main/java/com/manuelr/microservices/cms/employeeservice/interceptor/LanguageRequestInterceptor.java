package com.manuelr.microservices.cms.employeeservice.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class LanguageRequestInterceptor implements RequestInterceptor {
    private static final String X_FORWARDED_HOST = "X-Forwarded-Host";

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(requestAttributes))
            return;
        HttpServletRequest request = requestAttributes.getRequest();
        if (Objects.isNull(request))
            return;
        template.header(X_FORWARDED_HOST, "localhost:8080");

    }
}
