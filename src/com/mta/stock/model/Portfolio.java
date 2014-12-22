package com.mta.stock.model;
import java.util.Date;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Portfolio { //a portfolio of up to 5 stocks
	public String title;
	private int portfolio_size;
	final private int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	public StockStatus[] StocksStatus;
	float balance;
	public void addstock(Stock stock){ //adds new stock to portfolio
		if (this.portfolio_size<this.MAX_PORTFOLIO_SIZE)
		{
			this.stocks[this.portfolio_size]=stock;
			this.StocksStatus[this.portfolio_size].currentask=stock.getAsk();
			this.StocksStatus[this.portfolio_size].currentbid=stock.getBid();
			this.StocksStatus[this.portfolio_size].date=stock.getDate();
			this.StocksStatus[this.portfolio_size].currentask=stock.getAsk();
			this.portfolio_size++;
			
		}
		else
		{
			System.out.println();
		}
	}
	public void conStocks(){ //constructor for portfolio
		this.stocks=new Stock[MAX_PORTFOLIO_SIZE];
		this.StocksStatus= new StockStatus[MAX_PORTFOLIO_SIZE];
		this.portfolio_size=0;
		this.balance=0;
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
		private int StockQuantity;
		private ALGO_RECOMMENDATION recommend;
		
	}
	private enum ALGO_RECOMMENDATION {

	    DO_NOTHING(0),
	    BUY(1),
	    SELL(2);

	    private final int value;

	    private ALGO_RECOMMENDATION(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return this.value;
	    }
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
	public Portfolio(){
		this.conStocks();
		this.title=new String();
		
	}
	public Portfolio (Portfolio original){ //replicates a portfolio
		
		this.conStocks();
		String strtool=new String(original.title);
		this.title=strtool;
		int i;
		for(i=0;i<original.portfolio_size;i++){
			this.stocks[i]=new Stock(original.stocks[i]);
		}
		
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void updateBalance(float change){
		this.balance=this.balance+change;
	}
	public void removestock(String Symbol){ //removes specified stock from specified portfolio
		Date date;
		int stocknumber=-1,i;
		for (i = 0; i < this.portfolio_size; i++) {
			date=this.stocks[i].getDate();
			if (date.equals(Symbol)){
				stocknumber=i;
			}
			
		}
		if (i>(-1)){
			this.updateBalance(this.StocksStatus[i].currentbid*this.StocksStatus[i].StockQuantity);
			if ((i+1)<this.portfolio_size){
				for (int j = (i+1); j < StocksStatus.length; j++) {
					this.stocks[j-1]=this.stocks[j];
					this.StocksStatus[j-1]=this.StocksStatus[j];
				}
			}
			this.stocks[this.portfolio_size-1]=null;
			this.StocksStatus[this.portfolio_size-1]=null;
			this.portfolio_size--;			
		}
	}
}
