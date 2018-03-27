package wschun.shop.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.naming.Name;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理全站乱码优化版
 */
@WebFilter(value = "/*")
public class EncodingFilter implements Filter {
	private String encoding; // 处理请求参数的编码表
	private String contentType; // 响应的网页编码

	/**
	 * 加载初始化参数：编码表和响应的网页编码
	 */
	public void init(FilterConfig config) throws ServletException {
		encoding = config.getInitParameter("encoding");
		if (encoding == null) {
			encoding = "UTF-8";
		}
		contentType = config.getInitParameter("contentType");
		if (contentType == null) {
			contentType = "text/html;charset=UTF-8";
		}
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		// 强制转换为与协议相关的对象
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		// 解决POST方式乱码问题
		request.setCharacterEncoding(encoding);
		// 处理客户端响应乱码
		response.setContentType(contentType);
		
		// 如果是get方式请求，需要特殊处理
		String method = request.getMethod();
		if ( method.equalsIgnoreCase("get") ) {
			// 对request进行包装、增强
			request = new MyRequest(request);
		}
		// 放行
		chain.doFilter(request, response);
	}

	/**
	 * 重写需要增强的方法
	 */
	private class MyRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request;
		private boolean encoded = false; // 是否已编码过

		public MyRequest(HttpServletRequest request) throws UnsupportedEncodingException {
			super(request);
			this.request = request;
		}
		
		public String getParameter(String name) {
			String[] values = getParameterValues(name);
			if (values != null) {
				return values[0];
			}
			return null;
		}
		
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> map = request.getParameterMap();
			if (!encoded) {
				try {
					for (Map.Entry<String, String[]> me : map.entrySet()) {
						String[] values = me.getValue();
						for (int i = 0; i < values.length; i++) {
							values[i] = new String(values[i].getBytes("ISO-8859-1"), encoding);
						}
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				encoded = true;
			}
			return map;
		}
		
		public String[] getParameterValues(String name) {
			Map<String, String[]> map = getParameterMap();
			if (map != null) {
				String[] values = map.get(name);
				if (values != null) {
					return values;
				}
			}
			return null;
		}
	}
	
	public void destroy() {

	}
}