package com.mta.stock.service;


import java.util.Date;

import com.mta.stock.model.Portfolio;
import com.mta.stock.model.Stock;
import com.mta.stock.model.StockStatus;

public class PortfolioService {
	@SuppressWarnings("deprecation")
	public Portfolio getPortfolio(){ //sets initial value for portfolio
		Portfolio myportfolio= new Portfolio(); 
		myportfolio.conStocks();
		myportfolio.title= "Exercise 8 portfolio";
		myportfolio.updateBalance(10000); //initial value
		//add 3 stocks to portfolio
		Date date=new Date(114,11,15);
		StockStatus stock=new StockStatus("PIH",10,(float) 8.5,date);		
		myportfolio.addstock(stock);
				
		date=new Date(114,11,15);		
		stock=new StockStatus("AAL",30,(float) 25.5,date);		
		myportfolio.addstock(stock);
				
		date=new Date(114,11,15);		
		stock=new StockStatus("CAAS",20,(float) 15.5,date);		
		myportfolio.addstock(stock);
		//buy stocks
		myportfolio.buystock("PIH", 20); 
		myportfolio.buystock("AAL", 30);
		myportfolio.buystock("CAAS", 40);
		
		myportfolio.sellstock("AAL", -1, false); //keep stock afterwards
		myportfolio.removestock("CAAS"); //remove stock from portfolio
		return myportfolio;
	}

}
