package edu.tridenttech.cpt237.lucas;
/*Tracks total number of recommendations and compares purchase stock price to new stock 
price to determine recommendation value.*/
class StockRecommender {
	static final int BUY = 1;
	static final int SELL = 10;
	static final int HOLD = 5;
	int numBuys = 0;
	int numSells = 0;
	int numHolds = 0;
		
	StockRecommender()
	{
		
	}
	
	public static int getBuy() {
		return BUY;
	}
	
	public static int getSell() {
		return SELL;
	}
	
	public static int getHold() {
		return HOLD;
	}
	
	/*Uses passed in value to determine recommendation value, returns said value.
	*Tracks number of recommendations by type.*/
	public int getRecommendation(Stock stock)
	{
		if(stock.currentPrice > stock.purchasePrice + (stock.purchasePrice * 0.07))
		{
			numSells++;
			return SELL;
		}
		else if(stock.currentPrice < stock.purchasePrice - (stock.purchasePrice * 0.07))
			{
				numBuys++;
				return BUY;
			}
			else
			{
				numHolds++;
				return HOLD;
			}
	}		
}	

