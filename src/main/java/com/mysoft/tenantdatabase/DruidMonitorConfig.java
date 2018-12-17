package com.mysoft.tenantdatabase;


import com.alibaba.druid.support.http.*;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.web.servlet.*;
import org.springframework.context.annotation.*;

@SpringBootConfiguration
public class DruidMonitorConfig {
 
	private static final Logger logger = LoggerFactory.getLogger(DruidMonitorConfig.class);
 
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		logger.info("init Druid Monitor Servlet ...");
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		// IP白名单
//		servletRegistrationBean.addInitParameter("allow", "192.168.0.101,127.0.0.1");
		// IP黑名单(共同存在时，deny优先于allow)
//		servletRegistrationBean.addInitParameter("deny", "192.168.1.100");
		// 控制台管理用户
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		// 是否能够重置数据 禁用HTML页面上的“Reset All”功能
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}
 
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
 
}
