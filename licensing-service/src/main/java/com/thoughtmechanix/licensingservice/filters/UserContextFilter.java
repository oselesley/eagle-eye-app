package com.thoughtmechanix.licensingservice.filters;

import com.thoughtmechanix.licensingservice.utils.UserContext;
import com.thoughtmechanix.licensingservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserContextFilter implements Filter {
    private Logger log = LoggerFactory.getLogger(UserContextFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        UserContextHolder.getContext().setCorrelationId(req.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setAuthToken(req.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getContext().setUserId(req.getHeader(UserContext.USER_ID));
        UserContextHolder.getContext().setOrgId(req.getHeader(UserContext.ORG_ID));

        log.info("UserContextFilter CorrelationId {}:", UserContextHolder.getContext().getCorrelationId());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
