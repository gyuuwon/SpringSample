package net.koreate.test.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	// 설계정보에 대한 위치정보
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}

	//initpram 설정정보
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfig.class};
	}

	// 어떻게 mapping 할것인가
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}
