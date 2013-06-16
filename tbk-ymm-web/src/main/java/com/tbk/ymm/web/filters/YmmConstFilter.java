package com.tbk.ymm.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.tbk.ymm.data.catcher.commons.consts.YmmDataConsts;


/**
 * @author David (qidawei@xiaomi.com)
 */
public class YmmConstFilter implements Filter {

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		request.setAttribute("STATIC_ROOT", "/static");
		request.setAttribute("YMM_DOMAIN", "http://www.yunmama123.com");
		request.setAttribute("APP_KEY", YmmDataConsts.YMM_APP_KEY);
		//
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
	}
}
