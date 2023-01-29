package co.gv.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.gv.service.GreetingService;

@WebServlet("/greet")
public class GreetingController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// responsibility as controller
		// 1. read input from request
		String name = req.getParameter("name");
		// 2. invoke a model method to get model data
		GreetingService service = new GreetingService();
		String msg = service.getMessage(name);
		// 3. store model data in a scope or storage location accessible to view
		req.setAttribute("message", msg);
		// 4. forward the request to view (in our case JSP)
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/greet.jsp");
		rd.forward(req, resp);
	}

	private static final long serialVersionUID = 1L;

}
