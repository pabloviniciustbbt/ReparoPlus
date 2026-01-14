package com.pabloleal.ReparoPlus.utils;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AvisoContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            AvisoContext.limparAvisos();
            filterChain.doFilter(servletRequest,servletResponse);
        } finally {
            AvisoContext.limparAvisos();
        }
    }
}
