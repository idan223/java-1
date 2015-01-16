package com.mta.stock.servlet;

import java.io.IOException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.stock.exceptions.*;
import com.mta.stock.model.Portfolio;
import com.mta.stock.service.PortfolioService;


@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet{ //initiates the program and prints the output of the program in html code
	Portfolio portfolio;
	String str=" ";
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PortfolioService portfolioService = new PortfolioService(); //creates portfolio
		
		
		try 
		{		
			 this.portfolio = portfolioService.getPortfolio(); //fills portfolio and performs tasks on it
			 resp.getWriter().print(this.portfolio.getHtmlString()); //prints out the result in html coding.
		} catch (ArithmeticException negativenumber)
		{
			str=str+negativenumber.getMessage();
		}catch (BalanceException  badgiven)
		{
			str=str+badgiven.getMessage();
		}catch (PortfolioFullException portfull)
		{
			str=str+portfull.getMessage();
		}catch (StockAlreadyExistsException stockexists)
		{
			str=str+stockexists.getMessage();
		}catch (StockNotExistException nostock)
		{
			str=str+nostock.getMessage();
		}
		resp.getWriter().print("<h1></h1>"+str);
		
		  
		
		
	}	
}
