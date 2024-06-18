package org.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initializer class for configuring the DispatcherServlet in a Spring MVC application.
 * This class extends AbstractAnnotationConfigDispatcherServletInitializer and overrides methods
 * to specify the configuration classes and servlet mappings for the DispatcherServlet.
 *
 * @author Klezovich Ivan
 * @version 1.0
 */
public class DispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specifies the root configuration classes for the application context.
     * In this case, no root configuration classes are specified.
     *
     * @return An array of root configuration classes.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * Specifies the servlet configuration classes for the DispatcherServlet.
     * In this case, the SpringConfig class is specified as the servlet configuration.
     *
     * @return An array of servlet configuration classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    /**
     * Specifies the URL mappings for the DispatcherServlet.
     * In this case, the DispatcherServlet is mapped to the root URL ("/").
     *
     * @return An array of servlet mappings.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
