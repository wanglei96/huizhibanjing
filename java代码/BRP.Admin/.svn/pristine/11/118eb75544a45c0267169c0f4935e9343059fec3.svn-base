package strosoft.app.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 获取request,response对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		// 登陆页面无需过滤
		if (path.indexOf("login") > -1 || path.indexOf("VerifyImage") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		// System.out.println(path);
		// 获取Cookie中的登陆信息
		Cookie[] cookies = servletRequest.getCookies();
		String loginName = null;
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("loginName".equals(cookie.getName())) {
					loginName = cookie.getValue();
				}
			}
		}
		// 判断如果没有取到员工信息,就跳转到登陆页面
		if (loginName == null || loginName.equals("")) {
			// 跳转到登陆页面
			String rootUrl = servletRequest.getContextPath();
			servletResponse.sendRedirect(rootUrl);
		} else {
			// 登陆成功,继续此次请求
			chain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
