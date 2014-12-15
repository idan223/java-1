package com.mta.stock.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.stock.model.Portfolio;
import com.mta.stock.service.PortfolioService;


@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet{ //initiates the program and prints the output of the program in html code
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PortfolioService portfolioService = new PortfolioService();
		Portfolio portfolio = portfolioService.getPortfolio();
		Portfolio portfolio2=new Portfolio(portfolio);
		portfolio2.setTitle("portfolio#2");
		resp.getWriter().print(portfolio.getHtmlString()+"<br>");
		resp.getWriter().print(portfolio2.getHtmlString());
		
		

	}

}
