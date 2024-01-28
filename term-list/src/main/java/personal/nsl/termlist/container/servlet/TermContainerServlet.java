package personal.nsl.termlist.container.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import personal.nsl.termlist.config.ContainerServletStringConstants;
import personal.nsl.termlist.config.ContextConstants;
import personal.nsl.termlist.config.ViewNames;
import personal.nsl.termlist.container.dto.TermContainerCreationResponseDTO;
import personal.nsl.termlist.container.dto.TermContainerResponseDTO;
import personal.nsl.termlist.container.service.TermContainerService;
import personal.nsl.termlist.util.NLogger;
import personal.nsl.termlist.util.ViewUriResolver;

public class TermContainerServlet extends HttpServlet {
    private static final long serialVersionUID = -5769267923607623575L;

    private final NLogger log = NLogger.getLogger();
    private TermContainerService termContainerService;

    public TermContainerServlet() {
        this.termContainerService = TermContainerService.getInstance();
    }

    private String getContainerName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String errorMessage = ContainerServletStringConstants.WRONG_PARAM_KEY.get();
        String param = request
                .getParameter(ContainerServletStringConstants.REQUEST_PARAM_KEY.get());

        log.log(param, getClass());

        if (param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, String.format("%s: %s", errorMessage, param));
        }
        return param;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.log(request.getRequestURL() + ", params size=" + request.getParameterMap().size(), super.getClass());

        if (request.getParameterMap().size() == 0) {
            // TODO: if length of params is 0, then return all containers
            throw new UnsupportedOperationException("Not implemented");
        }

        String reqContainerName = getContainerName(request, response);
        if (reqContainerName != null) {
            sendContainerInfo(reqContainerName, request, response);
        }
    }

    private void sendContainerInfo(String reqContainerName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TermContainerResponseDTO dto = this.termContainerService.getContainer(reqContainerName);
        log.log(dto);

        request.setAttribute(ContextConstants.DTO_KEY.get(), dto);
        response.setContentType("text/html");

        String viewUri = resolveViewUri(ViewNames.CONTAINER.get());

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewUri);
        dispatcher.forward(request, response);
    }

    private String resolveViewUri(String containerNameKey) {
        String viewLocation = ContextConstants.VIEW_LOCATION.get();
        String viewUri = viewLocation + containerNameKey;
        log.log(viewUri, super.getClass());
        return viewUri;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.log(request.getRequestURL() + ", params size=" + request.getParameterMap().size(), super.getClass());

        if (request.getParameterMap().size() == 0) {
            // TODO: if length of params is 0, then return all containers
            throw new UnsupportedOperationException("Not implemented");
        }

        String reqContainerName = getContainerName(request, response);
        if (reqContainerName != null) {
            addContainer(reqContainerName, request, response);
        }
    }

    private void addContainer(String reqContainerName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TermContainerCreationResponseDTO dto = this.termContainerService.addContainer(reqContainerName);
        
        request.setAttribute(ContextConstants.DTO_KEY.get(), dto);

        String viewUri = resolveViewUri(ViewNames.CONTAINER_CREATION_SUCCESS.get());

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewUri);
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
                                    throws ServletException, IOException {
        log.log(request.getRequestURL() + ", params size=" + request.getParameterMap().size(), super.getClass());
        
        if (request.getParameterMap().size() == 0) {
            // TODO: if length of params is 0, then return all containers
            throw new UnsupportedOperationException("Not implemented");
        }
        
        String reqContainerName = getContainerName(request, response);
        if (reqContainerName != null) {
            deleteContainer(reqContainerName, request, response);
        }
    }
    
    private void deleteContainer(String reqContainerName,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                                         throws ServletException, IOException {
        boolean removed = this.termContainerService.removeContainer(reqContainerName);
        
        request.setAttribute(ContextConstants.DTO_KEY.get(), removed);
        
        response.sendRedirect("/");
    }
}
