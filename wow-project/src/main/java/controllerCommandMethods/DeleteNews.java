package controllerCommandMethods;

import java.io.IOException;
import java.util.List;

import bean.News;
import controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.NewsService;
import service.ServiceException;
import service.ServiceProvider;

public class DeleteNews implements Command {
	private static final ServiceProvider PROVIDER = ServiceProvider.getInstance();
	private static final NewsService NEWS_SERVICE = PROVIDER.getNewService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String lastCommandName = "GO_TO_MAIN_PAGE";
		String path;
		News newsDelete = null;
		Integer choosenNewsId = Integer.parseInt(request.getParameter("choosenNewsId"));
		Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
		if (choosenNewsId == null || choosenNewsId < 1) {
			path = "/WEB-INF/jsp/unknownPage.jsp";
			lastCommandName = "UNKNOWN_COMMAND";
			session.setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + path);
			return;
		}

		try {
			NEWS_SERVICE.deleteNews(choosenNewsId);
			lastCommandName = "GO_TO_MAIN_PAGE&currentPage=" + currentPage;
			session.setAttribute("lastURL", lastCommandName); // for redirect in localization
			response.sendRedirect("Controller?commandToController=" + lastCommandName);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
