package com.mta.stock.model;

public class Portfolio { //a portfolio of up to 5 stocks
	//variables
	public String title; // name of portfolio
	private int portfolio_size; //holds current size
	final private int MAX_PORTFOLIO_SIZE = 5;
	private StockStatus[] stocks; //holds all the stocks 
	float balance; //sum left in portfolio
	
	
	
	
	public Portfolio(){ //base C'tor
		this.conStocks();
		this.title=new String();
		
	}
	
	
	
	public Portfolio (Portfolio original){ //Copy C'tor
		
		this.conStocks();
		String strtool=new String(original.title);
		this.title=strtool;
		int i;
		for(i=0;i<original.portfolio_size;i++){
			this.stocks[i]=new StockStatus(original.stocks[i]);
		}
		
	}
	
	
	public void conStocks(){ //constructor tool for portfolio
		this.stocks= new StockStatus[MAX_PORTFOLIO_SIZE];
		this.portfolio_size=0;
		this.balance=0;
	}
	
	
	
	public void addstock(StockStatus stock){ //adds new stock to portfolio
		if (this.portfolio_size<this.MAX_PORTFOLIO_SIZE)
		{
			this.stocks[this.portfolio_size]=stock;
			this.portfolio_size++;
			
		}
		else  //portfolio is full
		{
			System.out.println("Can’t add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stocks");
		}
	}
	
	
	
	public boolean removestock(String Symbol){ //removes specified stock from portfolio
		
		boolean check=this.sellstock(Symbol,-1,true); //sell that stock, true for remove it afterwards
		if (check){  
			return true; //success
		}
		
		else //failure
		{
			System.out.println("The specified stock cannot be found");
			return false;
		}
}

	
	//setters/getters/updaters
	public Stock[] getStocks() {  
		return stocks;
	}
	public int getPortfolio_size() {
		return portfolio_size;
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
	public float getBalance() 
	{
		return balance;
	}
	public float getStocksValue() //returns all stocks' value
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
				sum=sum+this.stocks[i].getBid()*this.stocks[i].getStockQuantity();
			}
		}
		return sum;
	}
	public float getTotalValue()
	{
		float sum=getBalance()+getStocksValue();
		return sum;
	}
	//end of getters/setters/updaters
	
		
		
	
	public String getHtmlString(){ //prints the values in a portfolio
		String str = "<h1>"+this.title+"</h1><table border=1><tr><td>The total value of the portfolio is</td><td>"+this.getTotalValue()+"$</tr>";
		str= str.concat("<tr><td>The stock value of the portfolio is</td><td>"+this.getStocksValue()+"$</tr>");
		str= str.concat("<tr><td>The balance of the portfolio is</td><td>"+this.getBalance()+"$</tr>");		
		str=str.concat("</table><table border=1><tr><td>symbol</td><td>ask</td><td>bid</td><td>date</td></tr>");  
		for (int i=0;i<this.portfolio_size;i++)
		{
		str = str.concat("<tr><td>"+this.stocks[i].getHtmlDescription()+"</td></tr>");
		}
		str = str.concat("</table>");
		return str;
	}
	
	
	
	public boolean sellstock(String Symbol,int amount,boolean remove){ //sells a stock from portfolio for the amount specified
		int stocknumber=-1,i;
		String Symbol1;
		for (i = 0; i < this.portfolio_size; i++) { //finds if the stock is in the portfolio
			Symbol1=this.stocks[i].getSymbol();
			if (Symbol1.equals(Symbol)){
				stocknumber=i;
			}
			
		}
		if (stocknumber>(-1)) //stock was found 
		{
			if ((amount==-1) || (amount>this.stocks[stocknumber].getStockQuantity())) //if sum exceeds amount of current stock
			{
				
				this.balance=this.balance+(this.stocks[stocknumber].getBid()*this.stocks[stocknumber].getStockQuantity());
				this.stocks[stocknumber].updateStockQuantity(this.stocks[stocknumber].getStockQuantity()*(-1));
				if (remove)
				{				
					if (stocknumber<(this.portfolio_size-1)) //stock isn't last
					{
						for (i = (stocknumber+1); i < stocks.length; i++) 	//cut back stocks 1 in place, last one is a duplicate						
						{
							this.stocks[i-1]=this.stocks[i];				
						}										
					}				
					this.stocks[this.portfolio_size-1]=null; //remove last stock
					this.portfolio_size--;
				}
		
			}
			else //only part of the stock amount was sold
			{
				this.balance=this.balance+(this.stocks[stocknumber].getBid()*amount);
				this.stocks[stocknumber].updateStockQuantity((-1)*amount);
			}
			
			return true; //success
		}
		else
		{
			return false; //stock was not found, returning false for failure
		}

	}
	
	
	
	public boolean buystock(String Symbol,int amount)  //buys a stock for the amount specified
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
			if ((amount<0) || ((amount*this.stocks[stocknumber].getAsk())>this.balance))
			{
				int sum=((int) (balance/this.stocks[stocknumber].getAsk()));
				this.stocks[stocknumber].updateStockQuantity(this.stocks[stocknumber].getStockQuantity()+(sum));
				this.balance=this.balance-(sum*this.stocks[stocknumber].getAsk()) ;
			}
			else
			{
				this.stocks[stocknumber].updateStockQuantity(amount);
				this.balance=this.balance-amount*this.stocks[stocknumber].getAsk();
			}
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
}
