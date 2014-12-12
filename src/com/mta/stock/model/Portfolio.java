package com.mta.stock.model;
import java.util.Date;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.stock.Stock; 
public class Portfolio { //a portfolio of up to 5 stocks
	public String title;
	private int portfolio_size;
	final private int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	public StockStatus[] StocksStatus;
	final public int DO_NOTHING=0;
	final public int BUY=1;
	final public int SELL=2;
	public void addstock(Stock stock){ //adds new stock to portfolio
		stocks[this.portfolio_size]=stock;
		this.portfolio_size++;
	}
	public void conStocks(){ //constructor for portfolio
		this.stocks=new Stock[MAX_PORTFOLIO_SIZE];
		this.StocksStatus= new StockStatus[MAX_PORTFOLIO_SIZE];
		this.portfolio_size=0;
	}

	public Stock[] getStocks() {
		return stocks;
	}
	public int getPortfolio_size() {
		return portfolio_size;
	}
	

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {	
	}
	public class StockStatus{ //holds certain values for stock class
		private String symbol;
		private float currentbid;
		private float currentask;
		private Date date;
		private int recommendation;
		private int StockQuantity;
		
		
	}
	public String getHtmlString(){ //prints the values in a portfolio
		String str = "<h1>"+this.title+"</h1><table border=1>"
				+ "<tr><td>symbol</td><td>ask</td><td>bid</td><td>date</td></tr>";
		for (int i=0;i<this.portfolio_size;i++)
		{
		str = str.concat("<tr><td>"+this.stocks[i].getHtmlDescription()+"</td></tr>");
		}
		str = str.concat("</table>");
		return str;
		
	}
	public Portfolio PortCopy(Portfolio original){ //replicates a portfolio
		Portfolio replica1=new Portfolio();
		replica1.conStocks();
		String strtool=new String(original.title);
		replica1.title=strtool;
		int i;
		for(i=0;i<original.portfolio_size;i++){
			replica1.stocks[i]=new Stock();
			replica1.addstock(original.stocks[i].stockcopy(original.stocks[i]));
		}
		return replica1;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Portfolio removestock(Portfolio handled,int stocknumber){ //removes specified stock from specified portfolio
		Portfolio newport=handled;
		int i;
		for (i=stocknumber;i<newport.portfolio_size;i++)
		{
			newport.stocks[i-1]=newport.stocks[i];
			
		}
		newport.stocks[newport.portfolio_size]=null;
		newport.portfolio_size--;
		return newport;
	}
	public void editBid(Portfolio handled,int stocknumber,float value){ //edits bid value for the desired stock
		handled.stocks[stocknumber-1].setBid(value);
	}
}
