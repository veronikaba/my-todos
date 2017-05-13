package press.turngeek.todos;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ToDoServlet
 */
@WebServlet({ "/ToDoServlet", "/todos" })
public class ToDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ToDoService service;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDoServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        DataSource ds = (DataSource)config.getServletContext().getAttribute("datasource");
        service = new ToDoService(ds);
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//generate output
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get post parameter
		String todoDescr = request.getParameter("todo");
		if (todoDescr!=null&&todoDescr.length()>0) {
			//Save button
			//create todo
			ToDo todo = new ToDo(todoDescr, new Date());
            try {
                service.addToDo(todo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		//generate output
		processRequest(request, response);
	}
		

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
        response.getOutputStream().print(generateOutput());
	}
	
	private String generateTable() {
        StringBuffer table = new StringBuffer();

        try {
            List<ToDo> todos = service.getAllTodos();

            for (ToDo todo : todos) {
                table.append("<tr><td>"+todo.getDescription()+"</td>");
                table.append("<td>"+todo.getCreated()+"</td></tr>");
            }
            if (table.length()==0) table.append("<tr><td>Currently, there are no TODOs for you!</td></tr>");

        } catch (SQLException se) {
            table.append("Fehler beim Lesen der Datenbank");
        }
        return table.toString();
	}
	
	private String generateOutput() {
		StringBuffer site = new StringBuffer();
		
		site.append("<!DOCTYPE html>" +
		"<html>" +
		"<head>" +
			"<meta charset=\"UTF-8\">" +
			"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" +
			"<title>My TODOs</title>" +
			"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css\">" +
		"</head>" +
		"<body>" +
		"<div class=\"container\"" +
			"<section>" +
				"<h1>Enter TODO</h1>" +
				"<form role=\"form\" method=\"POST\" action=\"\">" +
		  			"<div class=\"form-group\">" +
						"<input type=\"text\" name=\"todo\" class=\"form-control\" size=\"50\" placeholder=\"Enter todo\" />" + 
						"<button type=\"submit\" id=\"save\" class=\"btn btn-default\">Save</button>" +
					"</div>" +
				"</form>" +
			"</section>" +
			"<section>" +
				"<h1>My TODOs</h1>" +
				"<table class=\"table table-striped\">" +
					generateTable() +
				"</table>" +
				"<form role=\"form\" method=\"POST\" action=\"delete\">" +
					"<div class=\"form-group\">" + 
						"<button type=\"submit\" id=\"reset\" class=\"btn btn-default\">Reset</button>"+
					"</div>" +
				"</form>" +
			"</section>" +
			"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js\"></script>" +
			"</div" +
			"</body>" +
		"</html>");
		
		
		return site.toString();
		
	}
}
