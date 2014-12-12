package com.mta.stock;

import java.util.Date;

public class Stock { //signifies a stock and it's values for a certain date
	private String symbol;
	private float Ask;
	private float Bid;
	private Date date;
	
	
	
	public String getSymbol(){
		return symbol;
	}
	
	public float getAsk() {
		return Ask;
	}
	
	public String getHtmlDescription(){ //returns the values of a stock in html coding
		String result = symbol +"<td>"+ Ask +"</td><td>"+Bid+"</td><td>"+date.toString();
		return result;
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
	
	public Date getDate() {
		return date;
	}
	
	public void setAsk(float ask) {
		Ask = ask;
	}
	
	public void setBid(float bid) {
		Bid = bid;
	}
	public Stock stockcopy(Stock original){ //copies a given stock into a new identical instance
		Stock replica=new Stock();
		replica.setAsk(original.getAsk());
		replica.setBid(original.getBid());
		replica.setDate(original.getDate());
		replica.setSymbol(original.getSymbol());
		return replica;
	}

}
