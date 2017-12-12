package presentationClass;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import databaseClass.DivisionDB;

/**
 * Servlet implementation class deleteDivisionServlet
 */
@WebServlet("/DeleteDivisionServlet")
public class DeleteDivisionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/DeleteDivisions.jsp";
		String divisionNumber = request.getParameter("Number");
		DivisionDB.deleteDivision(divisionNumber);
		url = "/AllDivisionsServlet";

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
