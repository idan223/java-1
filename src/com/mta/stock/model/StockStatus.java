package com.mta.stock.model;

import java.util.Date;


public class StockStatus extends Stock{
	
	private int StockQuantity;
	private ALGO_RECOMMENDATION recommend;
	
	private enum ALGO_RECOMMENDATION {

	    DO_NOTHING(0),
	    BUY(1),
	    SELL(2);

	    private final int value;

	    private ALGO_RECOMMENDATION(int value) {
	        this.value = value;
	    }

	    public int getValue() 
	    {
	        return this.value;
	    }
	}
	
	
	
	public int getStockQuantity() {
		return StockQuantity;
	}
	

	public ALGO_RECOMMENDATION getRecommend() {
		return recommend;
	}

	public void updateStockQuantity(int change){
		this.StockQuantity=this.StockQuantity+change;
	}
	private void init()
	{
		this.StockQuantity=0;
	}
	
	public StockStatus(StockStatus original){  //Copy C'tor
		super(original);
		this.StockQuantity=original.StockQuantity;
		this.recommend=original.recommend;	
	}
	
	public StockStatus(){  //C'tor
		super();
		this.init();
	}
	
	public StockStatus(String Symbol, float ask, float bid, Date date){
		super(Symbol,ask,bid,date);
		this.init();
	}
	
	

}
