package com.app.mymovieserver.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author aghil
 *
 */
@WebFilter("/*")
public class ApplicationFilter implements Filter {

	private static final Logger log = LoggerFactory.getLogger(ApplicationFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
	ServletException {
		try {

			SecurityContext context = SecurityContextHolder.getContext();
			if (context != null) {
				Authentication authentication = context.getAuthentication();
				if (authentication != null && authentication.getPrincipal() != null
						&& authentication.getPrincipal() instanceof Integer) {
					Integer sid = (Integer) authentication.getPrincipal();
					MDC.put("SID", sid.toString());
					log.info("SID:"+sid);
				}
			}
		} catch (Throwable e) {
			log.error("Exception ", e);
		}
		chain.doFilter(request, response);
		MDC.clear();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}