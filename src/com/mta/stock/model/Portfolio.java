package com.mta.stock.model;

import com.mta.stock.exceptions.*;

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
	
	
	
	public void addstock(StockStatus stock) throws StockAlreadyExistsException, PortfolioFullException{ //adds new stock to portfolio
		if (this.portfolio_size<this.MAX_PORTFOLIO_SIZE)
		{
			int stocknumber=-1;
			for (int i = 0; i < this.portfolio_size; i++) { //finds if the stock is in the portfolio
				String Symbol1=this.stocks[i].getSymbol();
				if (Symbol1.equals(stock.getSymbol())){
					stocknumber=i;
				}
				
			}
			if (stocknumber==-1) //stock wasn't found, can add it
			{
				this.stocks[this.portfolio_size]=stock;
				this.portfolio_size++;
			}
			else
			{
				throw new StockAlreadyExistsException("Sorry, stock already exist in portfolio<br>");
			}
		}
		else  //portfolio is full
		{
			
			throw new PortfolioFullException("Portfolio is already full<br>");
			
		}
	}
	
	
	
	public void removestock(String Symbol) throws StockNotExistException{ //removes specified stock from portfolio
		int stocknumber=-1,i;
		String Symbol1;
		for (i = 0; i < this.portfolio_size; i++) { //finds if the stock is in the portfolio
			Symbol1=this.stocks[i].getSymbol();
			if (Symbol1.equals(Symbol)){
				stocknumber=i;
			}
			
		}
		if (stocknumber>-1)
		{ //stock found
			this.balance=this.balance+(this.stocks[stocknumber].getBid()*this.stocks[stocknumber].getStockQuantity());
			this.stocks[stocknumber].updateStockQuantity(this.stocks[stocknumber].getStockQuantity()*(-1));
			//sell that stock
		}
		else //stock not found
		{
			throw new StockNotExistException("Stock by that symbol isn't in profile<br>");
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
	
	
	
	public void sellstock(String Symbol,int amount) throws BalanceException, StockNotExistException{ //sells a stock from portfolio for the amount specified
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
			if (amount>-2) //amount is applicable
			{
				if (amount==(-1)) //selling all stock
				{				
					this.balance=this.balance+(this.stocks[stocknumber].getBid()*this.stocks[stocknumber].getStockQuantity());
					this.stocks[stocknumber].updateStockQuantity(this.stocks[stocknumber].getStockQuantity()*(-1));				
				}				
				else //only part of the stock amount was sold
				{
					if (amount<=this.stocks[stocknumber].getStockQuantity())
					{
						this.balance=this.balance+(this.stocks[stocknumber].getBid()*amount);				
						this.stocks[stocknumber].updateStockQuantity((-1)*amount);
					}
					else  //amount is higher than stock quantity
					{
						throw new BalanceException("amount is higher than quantity in stock<br>");
					}
				}						
			}
			else  //negative number entered
			{
				throw new ArithmeticException("negative number given<br>");	
			}
		}
		else //stock not found
		{
			throw new StockNotExistException("Stock by that symbol isn't in profile<br>");
		}				
	}
	
	
	
	public void buystock(String Symbol,int amount) throws BalanceException  , StockNotExistException//buys a stock for the amount specified
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
			if (amount>-2)
			{
				if (amount==-1)
				{
					int sum=((int) (balance/this.stocks[stocknumber].getAsk()));
					this.stocks[stocknumber].updateStockQuantity(this.stocks[stocknumber].getStockQuantity()+(sum));
					this.balance=this.balance-(sum*this.stocks[stocknumber].getAsk()) ;
				}
				else
				{				
					if ((amount*this.stocks[stocknumber].getAsk())>this.balance)//balance is negative
					{
						throw new BalanceException("Portfolio balance will be negative<br>");
					}
					else //balance is fine
					{
						this.stocks[stocknumber].updateStockQuantity(amount);
						this.balance=this.balance-amount*this.stocks[stocknumber].getAsk();
					}
				}
			}
			else
			{
				throw new ArithmeticException("negative number given<br>");				
			}
			
		}
		else //stock not found
		{
			throw new StockNotExistException("Stock by that symbol isn't in profile<br>");
		}
		

		
	}
	
}
