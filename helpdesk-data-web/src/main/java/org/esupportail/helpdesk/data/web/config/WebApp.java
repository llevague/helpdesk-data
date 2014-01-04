/**
 *
 */
package org.esupportail.helpdesk.data.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

import static java.lang.String.format;

@Slf4j
public class WebApp implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        WebApplicationContext rootContext = createRootContext(servletContext);

        configureSpringMvc(servletContext, rootContext);
    }

    private WebApplicationContext createRootContext(ServletContext servletContext) {
        servletContext.setInitParameter("log4jConfigLocation", "classpath:log4j.properties");
        
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(ContextConfig.class);
        root.refresh();

        servletContext.addListener(new ContextLoaderListener(root));
        servletContext.addListener(new Log4jConfigListener());
        servletContext.setInitParameter("defaultHtmlEscape", "true");

        return root;
    }

    private void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(MvcConfig.class);
        mvcContext.setParent(rootContext);

        ServletRegistration.Dynamic appServlet = servletContext.addServlet(
                "dispatcher", new DispatcherServlet(mvcContext));
        appServlet.setLoadOnStartup(1);
        Set<String> mappingConflicts = appServlet.addMapping("/");

        if (!mappingConflicts.isEmpty()) {
            for (String s : mappingConflicts) {
                WebApp.log.error(format("Mapping conflict: %s", s));
            }
            throw new IllegalStateException("'dispatcher' cannot be mapped to '/'");
        }
    }

}
