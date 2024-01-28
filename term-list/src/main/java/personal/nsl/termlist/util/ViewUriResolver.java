package personal.nsl.termlist.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

public class ViewUriResolver {
    private final HttpServlet servlet;
    private final String viewLocation;
    
    public ViewUriResolver(HttpServlet servlet) {
        this.servlet = servlet;
        
        ServletContext context = servlet.getServletContext();
        this.viewLocation = context.getInitParameter("view-location");
    }
    
    public String getUri(String paramName) {
        String viewName = this.servlet.getInitParameter(paramName);
        return this.viewLocation + viewName;
    }
}
