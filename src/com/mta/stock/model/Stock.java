package com.mta.stock.model;

import java.util.Date;

public class Stock { //signifies a stock and it's values for a certain date
	//variables
	protected String symbol;
	protected float Ask;
	protected float Bid;
	protected Date date;
	
	public Stock(){
		
	}
	
	public Stock(String Symbol, float ask, float bid, Date date){   //constructor
		this.symbol=Symbol;
		this.Ask=ask;
		this.Bid=bid;
		this.date=date;
	}
	
	
	public Stock(Stock original){ //Copy C'tor
		this.setAsk(original.getAsk());
		this.setBid(original.getBid());
		this.setDate(original.getDate());
		this.setSymbol(original.getSymbol());
	}
	
	
	//getters/setters
	public String getSymbol(){
		return symbol;
	}	
	public float getAsk() {
		return Ask;
	}	
	public float getBid() {
		return Bid;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate(){
		Date date=new Date(this.date.getDate());
		return date;
	}
	
	public void setAsk(float ask) {
		Ask = ask;
	}
	
	public void setBid(float bid) {
		Bid = bid;
	}
	//end of getters/setters
	
	
	public String getHtmlDescription(){ //returns the values of a stock in html coding
		String result = symbol +"<td>"+ Ask +"</td><td>"+Bid+"</td><td>"+date.toString();
		return result;
	}
	

}
