package edu.tridenttech.cpt237.cards.model;

import java.util.Comparator;

/**
 * Represents a single playing card.  The suits are arranged in order of a typical bridge ordering.
 * The rank ranges from 0 - 12 where 0 is a two and 12 is an ace.  This gives the correct ordering of
 * rank though it may be a little disconcerting.
 * 
 * @author andersonjackson
 *
 */
public class Card implements Comparable<Card>
{ 
	public final int SUIT_SIZE = 13;
	public static final int CLUB = 0;
	public static final int DIAMOND = 1;
	public static final int HEART = 2;
	public static final int SPADE = 3;

    private int suit;      // clubs = 0, diamonds = 1, hearts = 2, spades = 3
    private int rank;      // deuce = 0, three = 1, four = 2, ..., king = 11, ace = 12
    private boolean isFaceUp = true; // not used for our program
    
    public static class SpadeSortComparator implements Comparator<Card>{

		@Override
		public int compare(Card c1, Card c2) {
			if(c1.suit - c2.suit == 0) {
				return c1.rank - c2.rank;
			}
			else return c1.suit - c2.suit;
		}}
    
    // create a new card based on integer 0 = 2C, 1 = 3C, ..., 51 = AS
    public Card(int card) {
        rank = card % SUIT_SIZE;
        suit = card / SUIT_SIZE;
    }

    public int getRank() {
    	return rank;
    }

    public int getSuit() {
    	return suit;
    }
    
    public boolean isFaceUp()
	{
		return isFaceUp;
	}

	public void flip()
    {
    	isFaceUp = !isFaceUp;
    }

    // represent cards like "2H", "9C", "JS", "AD"
    public String toString() {
        String ranks = "23456789TJQKA";
        String suits = "CDHS";
        return ranks.charAt(rank) +  "" + suits.charAt(suit);
    }


@Override
	public int compareTo(Card o) {		

			if (this.rank - o.rank == 0) 
			{
			return this.suit - o.suit;
			} 
			else
			return this.rank - o.rank;
		}
}