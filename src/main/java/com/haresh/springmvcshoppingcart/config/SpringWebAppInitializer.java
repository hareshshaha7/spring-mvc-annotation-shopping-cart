/**
 * Aauthor: Haresh Shaha
 * Date: 21-11-2020
 */

package com.haresh.springmvcshoppingcart.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class SpringWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(ApplicationContextConfig.class);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"SpringDispatcher", new DispatcherServlet(applicationContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		ContextLoaderListener loaderListener = new ContextLoaderListener(
				applicationContext);
		servletContext.addListener(loaderListener);

		// Filter
		FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", CharacterEncodingFilter.class);
		fr.setInitParameter("encoding", "UTF-8");
		fr.setInitParameter("forceEncoding", "true");
		fr.addMappingForUrlPatterns(null, true, "/*");
	}

}
