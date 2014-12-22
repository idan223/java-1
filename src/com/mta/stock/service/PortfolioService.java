package com.mta.stock.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.stock.*;
import com.mta.stock.model.Portfolio;
import com.mta.stock.model.Stock;

public class PortfolioService {
	public Portfolio getPortfolio(){ //sets initial value for portfolio
		Portfolio myportfolio= new Portfolio(); 
		myportfolio.conStocks();
		myportfolio.title= "first port";
		Stock stock1=new Stock(); 
		float Ask=(float) 12.4;
		float Bid=(float) 13.1;
		String symbol="PIH";
		Calendar cal = Calendar.getInstance();
		cal.set(2014, Calendar.NOVEMBER, 15);
		Date date = cal.getTime();
		stock1.setAsk(Ask);
		stock1.setBid(Bid);
		stock1.setDate(date);
		stock1.setSymbol(symbol);
		myportfolio.addstock(stock1);
		Stock stock2=new Stock();
		Ask=(float) 5.5;
		Bid=(float) 5.78;
		symbol="AAL";
		stock2.setAsk(Ask);
		stock2.setBid(Bid);
		stock2.setSymbol(symbol);
		stock2.setDate(date);
		myportfolio.addstock(stock2);
		Stock stock3=new Stock();
		Ask=(float) 31.5;
		Bid=(float) 31.2;
		symbol="CAAS";
		stock3.setAsk(Ask);
		stock3.setBid(Bid);
		stock3.setSymbol(symbol);
		stock3.setDate(date);
		myportfolio.addstock(stock3);
		return myportfolio;
		
		
	
	}

}
