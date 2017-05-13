package press.turngeek.todos;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class DeleteToDoServlet
 */
@WebServlet({ "/DeleteToDoServlet", "/delete" })
public class DeleteToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ToDoService service;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteToDoServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
		DataSource ds = (DataSource)config.getServletContext().getAttribute("datasource");
		service = new ToDoService(ds);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Reset button
		try {
			service.deleteAllToDos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//redirect
		response.sendRedirect("/todos");
	}
}
