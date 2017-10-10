package edu.tridenttech.cpt237.cards.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.tridenttech.cpt237.cards.model.Card;
import edu.tridenttech.cpt237.cards.model.Card.SpadeSortComparator;
import edu.tridenttech.cpt237.cards.model.Deck;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HandDisplayWindow
{
	private final int SUIT_SIZE = 13;
	private Image[] cardImages = new Image[4*SUIT_SIZE];
	private Stage myStage;
	
	private ListView<Card> listView = new ListView<>();
	private ArrayList<Card> handList;
	private ArrayList<Card> clubsInHand = new ArrayList<>();
	private ArrayList<Card> diamondsInHand = new ArrayList<>();
	private ArrayList<Card> heartsInHand = new ArrayList<>();
	private ArrayList<Card> spadesInHand = new ArrayList<>();
	private ArrayList<ArrayList<Card>> handList3d = new ArrayList<>();
	private Button dealBtn = new Button("Deal Cards");
	private Button fishBtn = new Button("Fish Sort");
	private Button spadeBtn = new Button("Spade Sort");
	private Button bridgeBtn = new Button("Bridge Sort");
	private CheckBox reverseBox = new CheckBox("Reverse Cards");
	private HBox buttonBox = new HBox();
	Deck deck;
	int handSize;

	//clears 3d array of suit array lists and deals a new hand
	public class Redealer implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event) {
			handList3d.clear();			
			clubsInHand.clear();
			diamondsInHand.clear();
			heartsInHand.clear();
			spadesInHand.clear();
			dealNewHand();			
			myStage.show();
			
		}
	}
	
	public HandDisplayWindow(Stage stage, int size)
	{
		handSize = size;
		myStage = stage;
		myStage.setWidth(1200);
		myStage.setTitle("Card Hand");
		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);
		myStage.setScene(scene);
		pane.setPadding(new Insets(15,15,15,15));
				
		listView.setCellFactory(param -> new ListCell<Card>() {
			private ImageView imageView = new ImageView();

			@Override
			public void updateItem(Card card, boolean empty)
			{
				super.updateItem(card, empty);
				if (empty) {
					setGraphic(null);
				} else {
					// determine the index of the card
					int index = card.getSuit() * SUIT_SIZE + card.getRank();
					imageView.setImage(cardImages[index]);
					imageView.setPreserveRatio(true);
					imageView.setFitWidth(48);
					setGraphic(imageView);
				}
			}
		});

		listView.setOrientation(Orientation.HORIZONTAL);
		pane.setCenter(listView);

		myStage.setHeight(250);
		myStage.setWidth(70 * handSize);

		loadImages();
		
		//set button locations and functions
		buttonBox.getChildren().addAll(dealBtn,fishBtn, spadeBtn,bridgeBtn,reverseBox);
		pane.setBottom(buttonBox);
		
		//deals a new hand to the display
		dealBtn.setOnAction(new Redealer());
		
		//sorts by go fish sort, checks for reverseBox selection
		fishBtn.setOnAction(e -> {			
			
			
			Collections.sort(handList);
			if(reverseBox.isSelected()) {
				Collections.sort(handList,Collections.reverseOrder());	
			}			
			listView.getItems().setAll(handList);
			myStage.show();
		});
		
		//sorts by spade sort, checks for reverseBox selection
		spadeBtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				if(reverseBox.isSelected())
				{					
					Collections.sort(handList, new SpadeSortComparator());					
					listView.getItems().setAll(handList);
					myStage.show();
				}
				else 
				{					
					Collections.sort(handList, new SpadeSortComparator());
					Collections.reverse(handList);
					listView.getItems().setAll(handList);
					myStage.show();
				}
			}
		});
		bridgeBtn.setOnAction(new EventHandler<ActionEvent>()
		{
			
			@Override
			public void handle(ActionEvent event) 
			{
				
				//sorts suits
				Collections.sort(clubsInHand);
				Collections.sort(diamondsInHand);
				Collections.sort(heartsInHand);
				Collections.sort(spadesInHand);
				
				Collections.sort(handList3d, new Comparator<ArrayList<Card>>() {

					@Override
					public int compare(ArrayList<Card> list1, ArrayList<Card> list2) {
						if(reverseBox.isSelected()) 
						{
							return list1.size() - list2.size();
						}
						else
						{
							return list2.size() - list1.size();
						}
					}					
				});		
				listView.getItems().clear();
				if(!reverseBox.isSelected()) 
				{				
					//reverse suit display to show highest card first if reverseBox isn't selected
					Collections.reverse(clubsInHand);
					Collections.reverse(diamondsInHand);
					Collections.reverse(heartsInHand);
					Collections.reverse(spadesInHand);						
				}
				
				//fill listView
				for (ArrayList<Card> list: handList3d) {
					listView.getItems().addAll(list);
				}				
				myStage.show();				
			}
			
		});
	
	}

	private void loadImages()
	{
		String resourceDir = "file:resources/cardspng/";
		char[] suits = { 'c', 'd', 'h', 's' };
		char[] rank = { '2', '3', '4', '5', '6', '7', '8', '9', '0', 'j', 'q', 'k', 'a' };
		int slot = 0;
		// load images
		for (int s = 0; s < 4; s++) {
			for (int r = 0; r < SUIT_SIZE; r++) {
				String path = resourceDir + suits[s] + rank[r] + ".png";
				cardImages[slot] = new Image(path);
				slot++;
			}
		}
	}

	public void show(Deck deck)
	{
		this.deck = deck;
		dealNewHand();		
		myStage.show();
	}
	
	//deals new hand, shows stage.
	public void dealNewHand() {
		
		
		if (deck != null)
		{
			
			handList = deck.deal(handSize);	
			//sorts cards into separate suit lists
			for(Card card: handList) 
			{
				if (card.getSuit() == 0) {clubsInHand.add(card);} 
				if (card.getSuit() == 1) {diamondsInHand.add(card);}
				if (card.getSuit() == 2) {heartsInHand.add(card);}
				if (card.getSuit() == 3) {spadesInHand.add(card);}
			}
			
			//add suit lists to a 3d array to sort by size(line 170)
			handList3d.add(clubsInHand);
			handList3d.add(diamondsInHand);
			handList3d.add(heartsInHand);
			handList3d.add(spadesInHand);			
			listView.getItems().setAll(handList);
		}
		myStage.show();
	}
}
