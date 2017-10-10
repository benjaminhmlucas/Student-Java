package edu.tridenttech.cpt237.lucas;
//Creates Stock Objects
class Stock {
	String symbol;
	double purchasePrice;
	double currentPrice;
	
	Stock(String symbol, double purchasePrice)
	{
		this.symbol = symbol;
		this.purchasePrice = purchasePrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}
	
	
}
