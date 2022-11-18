package com.alok.accounts.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class TraceFilter implements Filter {

    public static final String CORRELATION_ID = "eazybank-correlation-id";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String correlationId = request.getHeader(CORRELATION_ID);
        log.info(" CORRELATION_ID : {} ", correlationId);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
