package com.mta.firsttask;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Myname extends HttpServlet { //prints the program for first week assignement
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		resp.getWriter().print("<h1>Hello Idan Heber</h1>"); 
		}
}
