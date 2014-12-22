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
			this.StocksStatus[this.portfolio_size]=new StockStatus();
			this.StocksStatus[this.portfolio_size].setCurrentask(stock.getAsk());
			this.StocksStatus[this.portfolio_size].setCurrentbid(stock.getBid());
			this.StocksStatus[this.portfolio_size].date=stock.getDate();
			this.StocksStatus[this.portfolio_size].setSymbol(stock.getSymbol());
			this.StocksStatus[this.portfolio_size].init();
			this.portfolio_size++;
			
		}
		else
		{
			System.out.println("Can’t add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stocks");
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
		public int getStockQuantity() {
			return StockQuantity;
		}
		public float getCurrentask() {
			return currentask;
		}
		public float getCurrentbid() {
			return currentbid;
		}
		public String getSymbol() {
			return symbol;
		}
		public ALGO_RECOMMENDATION getRecommend() {
			return recommend;
		}public Date getDate() {
			return date;
		}
		public void setCurrentask(float currentask) {
			this.currentask = currentask;
		}
		public void setCurrentbid(float currentbid) {
			this.currentbid = currentbid;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public void updateStockQuantity(int change){
			this.StockQuantity=this.StockQuantity+change;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		private void init()
		{
			this.StockQuantity=0;
		}
		
		
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
		String str = "<h1>"+this.title+"</h1><table border=1><tr><td>The total value of the portfolio is</td><td>"+this.getTotalValue()+"$</tr>";
		str= str.concat("<tr><td>The stock value of the portfolio is</td><td>"+this.getStocksValue()+"$</tr>");
		str= str.concat("<tr><td>The balance of the portfolio is</td><td>"+this.getBalance()+"$</tr>");		
//				+ "<tr><td>symbol</td><td>ask</td><td>bid</td><td>date</td></tr>";   // keeping for internal testing reasons!!
//		for (int i=0;i<this.portfolio_size;i++)
//		{
//		str = str.concat("<tr><td>"+this.stocks[i].getHtmlDescription()+"</td></tr>");
//		}
//		str = str.concat("</table>");
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
	public void updateBalance(float amount){
		this.balance=this.balance+amount;
	}
	public boolean removestock(String Symbol){ //removes specified stock from specified portfolio
			boolean check=this.sellstock(Symbol,-1);
			if (check){
				return true;
			}
			
			else
			{
				System.out.println("The specified stock cannot be found");
				return false;
			}
	}
	public boolean sellstock(String Symbol,int amount){
		int stocknumber=-1,i;
		String Symbol1;
		for (i = 0; i < this.portfolio_size; i++) {
			Symbol1=this.stocks[i].getSymbol();
			if (Symbol1.equals(Symbol)){
				stocknumber=i;
			}
			
		}
		if (stocknumber>(-1))
		{
			if ((amount==-1) || (amount>this.StocksStatus[stocknumber].getStockQuantity()))
			{
				this.balance=this.balance+(this.StocksStatus[stocknumber].getCurrentbid()*this.StocksStatus[stocknumber].getStockQuantity());
				if (stocknumber<(this.portfolio_size-1))
				{
					for (i = (stocknumber+1); i < StocksStatus.length; i++) 
					{
						this.stocks[i-1]=this.stocks[i];
						this.StocksStatus[i-1]=this.StocksStatus[i];
						
					}
					
					
				}
				this.stocks[this.portfolio_size-1]=null;
				this.StocksStatus[this.portfolio_size-1]=null;
				this.portfolio_size--;	
			}
			else
			{
				this.balance=this.balance+(this.StocksStatus[stocknumber].currentbid*amount);
				this.StocksStatus[stocknumber].updateStockQuantity((-1)*amount);
			}
			
			return true;
		}
		else
		{
			return false;
		}

	}
	public boolean buystock(String Symbol,int amount)
	{
		int stocknumber=-1,i;
		String Symbol1;
		for (i = 0; i < this.portfolio_size; i++)
		{
			Symbol1=this.stocks[i].getSymbol();
			if (Symbol1.equals(Symbol))
			{
				stocknumber=i;
			}			
		}
		if (stocknumber>(-1))
		{
			if ((amount<0) || ((amount*this.StocksStatus[stocknumber].getCurrentask())>this.balance))
			{
				int sum=((int) (balance/this.StocksStatus[stocknumber].getCurrentask()));
				this.StocksStatus[stocknumber].updateStockQuantity(this.StocksStatus[stocknumber].getStockQuantity()+(sum));
				this.balance=this.balance-(sum*this.StocksStatus[stocknumber].getCurrentask()) ;
			}
			else
			{
				this.StocksStatus[stocknumber].updateStockQuantity(amount);
				this.balance=this.balance-amount*this.StocksStatus[stocknumber].getCurrentask();
			}
			return true;
		}
		else
		{
			return false;
		}
		
	}
	public float getBalance() 
	{
		return balance;
	}
	public float getStocksValue()
	{
		float sum=0;
		if (this.stocks[0] == null)
		{
			return sum;
		}
		else
		{
			for (int i=0;i<this.portfolio_size;i++)
				
			{
				sum=sum+this.stocks[i].getAsk()*this.StocksStatus[i].getStockQuantity();
			}
		}
		return sum;
	}
	public float getTotalValue()
	{
		float sum=getBalance()+getStocksValue();
		return sum;
	}
	
}
