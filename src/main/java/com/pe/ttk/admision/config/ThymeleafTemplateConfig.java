package com.pe.ttk.admision.config;

import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafTemplateConfig {
	
	public ClassLoaderTemplateResolver templateResolver() {
		
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		resolver.setPrefix("templates/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCharacterEncoding("UTF-8");
		resolver.setCheckExistence(true);
		
		return resolver;
		
	}

}
