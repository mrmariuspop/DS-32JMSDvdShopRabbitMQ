package edu.assign3_2.producer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.assign3_2.entities.DVD;
import edu.assign3_2.producer.service.MessageSender;

@WebServlet("/")
public class AddDVDController extends HttpServlet {

	private static final long serialVersionUID = 1;
	private MessageSender sender = new MessageSender();
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("addDVD.jsp").forward(request, response);
        return;
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("add") != null) {
			String title = request.getParameter("dvd_title");
			int year = Integer.parseInt(request.getParameter("dvd_year"));
			double price = Double.parseDouble(request.getParameter("dvd_price"));
			
			DVD dvd = new DVD(title, year, price);
			
			sender.sendDVD(dvd);
			
			request.getRequestDispatcher("addDVD.jsp").forward(request, response);
		}
		return;
	}
}
