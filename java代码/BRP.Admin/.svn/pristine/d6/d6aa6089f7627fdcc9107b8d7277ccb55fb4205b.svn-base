package strosoft.app.util;

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
import javax.servlet.http.HttpSession;

public class FilterPages implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 获取request,response对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
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
		// 打印用户名
		// System.out.println(" loginName== " + loginName);
		// 登陆页面无需过滤
		if (path.indexOf("") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		// 判断如果没有取到员工信息,就跳转到登陆页面
		if (loginName == null || loginName.equals("")) {
			// 跳转到登陆页面
			servletResponse.sendRedirect("");		
		} else {
			// 登陆成功,继续此次请求
			chain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
