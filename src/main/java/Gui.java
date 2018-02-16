//import java.awt.Font;
//import java.awt.Label;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Pane;
import javafx.stage.Stage; 
import javafx.scene.paint.Color;

//import javafx.animation.Interpolator;
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.Timeline;
//import javafx.event.ActionEvent;
//import javafx.event.Event;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//import javafx.scene.input.MouseEvent;
//import javafx.util.Duration;



//@SuppressWarnings("restriction")
public class Gui extends Application {
	File cardsDir = new File("src/main/resources");
	private ImageView imgView;
	private Image[] cardsImgFoe; 
	private Image[] cardsImgWeapon;
	private Image[] cardsImgAlly;
	private Image[] cardsImgTest;
	private Image[] cardsImgAmour;
	private Image[] deckImg;
	private Image[] cardsImgRank;
	
	private ImageView activeRankView;
	
	private boolean firstTurn = true;
	private int height = 1000; 
	private int width = 1000;
	
	ArrayList<Player> players = new ArrayList<Player>();
	private Deck deck = new Deck();
	private Pane canvas = new Pane();
	int numberOfCardsTotal = 125;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		//builds the adventure deck
		adventureDeck();
	
        FilenameFilter imgFilterRank = new FilenameFilter() {
			
			@Override 
			public boolean accept(File dir, String name) {
				
				return name.toLowerCase().startsWith("r ");
			}
		};
		
		
		File[] cardsFileRanks = cardsDir.listFiles(imgFilterRank);
		cardsImgRank = new Image[cardsFileRanks.length];
		
		System.out.println(cardsFileRanks.length);
		
		int i = 0; 
		
		for(File cardFileRank : cardsFileRanks) {
			try {
				cardsImgRank[i] = new Image(new FileInputStream(cardFileRank.getPath()));
				i++;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		//currently just makes up the players but will be used to rig later
		initPlayers(cardsFileRanks);
		
		//hands out the cards to all players on the first turn
		firstTurnHandOutCards(primaryStage);
		
		//shows the GUI
	    initGUI(primaryStage);
	    
		//this sets up the rest of the active players display
		setActivePlayerDisplay(primaryStage);
	}
	
	
	public void initPlayers(File[] file) {
		Player player1 = new Player("Player 1", 0);
		Player player2 = new Player("Player 2", 0);
		Player player3 = new Player("Player 3", 0);
		Player player4 = new Player("Player 4", 0);
		
		
		try {
			player1.setRankImage(new Image(new FileInputStream(file[2].toString())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			player2.setRankImage(new Image(new FileInputStream(file[2].toString())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			player3.setRankImage(new Image(new FileInputStream(file[2].toString())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			player4.setRankImage(new Image(new FileInputStream(file[2].toString())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		players.add(player1); 
		players.add(player2);
		players.add(player3); 
		players.add(player4);
		
		
		
	}

	//gives back the appropriate cardWidth to display based on
	//the number of cards the player has, the drawing space available to the GUI
	//and the buffer that the developer wants between the cards and the edges of GUI
	public int calculateCardWidth(int numCards, int drawingSpace, int buffer) {
		int divided = drawingSpace / numCards; 
		int width = divided - buffer; 
		return width; 
	}
	

	public void storyDeck() {
		FilenameFilter imgFilterQuest = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				
				return name.toLowerCase().startsWith("q ");
			}
		};
		
		
		
	}
	
	public void adventureDeck() {

		
		//filter for the Foe cards
	    FilenameFilter imgFilterFoe = new FilenameFilter() {
	    	
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		
	    		return name.toLowerCase().startsWith("f ");
	    	}
	    };
	    
	    
	    FilenameFilter imgFilterWeapon = new FilenameFilter() {
	    	
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		
	    		return name.toLowerCase().startsWith("w ");
	    	}
	    };
	    
	    FilenameFilter imgFilterAlly = new FilenameFilter() {
	    	
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		
	    		return name.toLowerCase().startsWith("a ");
	    	}
	    };
	    
	    /*
	    FilenameFilter imgFilterAmour = new FilenameFilter() {
	    	
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		
	    		return name.toLowerCase().startsWith("amour");
	    	}
	    };
	   */ 
	   
	    FilenameFilter imgFilterTest = new FilenameFilter() {
	    	
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		
	    		return name.toLowerCase().startsWith("t ");
	    	}
	    };
	    
	    FilenameFilter imgFilterAmour = new FilenameFilter() {
	    	
	    	@Override
	    	public boolean accept(File dir, String name) {
	    		
	    		return name.toLowerCase().startsWith("amour");
	    	}
	    };
	    
	    //filter for the Amour card here
	    File[] cardsFileAmours = cardsDir.listFiles(imgFilterAmour);
	    cardsImgAmour = new Image[cardsFileAmours.length];
	    
        int i = 0;
	    
	    //instantiate
		for (File cardFileAmour : cardsFileAmours) {
			try {
				
				cardsImgAmour[i] = new Image(new FileInputStream(cardFileAmour.getPath()));
				i++;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	    
	    //filter for the Foe cards here
	    File[] cardsFileFoes = cardsDir.listFiles(imgFilterFoe);
	    cardsImgFoe = new Image[cardsFileFoes.length];
	    //System.out.println("Length of Foe deck: " + cardsFileFoes.length);
	    i = 0;
	    
	    //instantiate
		for (File cardFileFoe : cardsFileFoes) {
			try {
				
				cardsImgFoe[i] = new Image(new FileInputStream(cardFileFoe.getPath()));
				i++;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		//filter for the weapon cards here
		File[] cardsFileWeapons =  cardsDir.listFiles(imgFilterWeapon);
		cardsImgWeapon = new Image[cardsFileWeapons.length];
		//System.out.println("Length of Weapon deck: " + cardsFileWeapons.length);
		
		i = 0; 
		
		for(File cardFileWeapon : cardsFileWeapons) {
			try {
				cardsImgWeapon[i] = new Image(new FileInputStream(cardFileWeapon.getPath()));
				i++;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	    
		//filter for the ally cards here
		File[] cardsFileAllies =  cardsDir.listFiles(imgFilterAlly);
	    cardsImgAlly = new Image[cardsFileAllies.length];
		//System.out.println("Length of Ally deck: " + cardsFileAllies.length);
				
		i = 0; 
				
		for(File cardFileAlly : cardsFileAllies) {
			try {
				cardsImgAlly[i] = new Image(new FileInputStream(cardFileAlly.getPath()));
				i++;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		//filter for the test cards here
		File[] cardsFileTests =  cardsDir.listFiles(imgFilterTest);
	    cardsImgTest = new Image[cardsFileTests.length];
		//System.out.println("Length of Test deck: " + cardsFileTests.length);
						
		i = 0; 
						
		for(File cardFileTest : cardsFileTests) {
			try {
				//System.out.println(cardFileTest.getPath());
				cardsImgTest[i] = new Image(new FileInputStream(cardFileTest.getPath()));
				i++;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		
		try {
			buildFoeFrequency(cardsFileFoes);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		try {
			buildWeaponFrequency(cardsFileWeapons);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			buildTestFrequency(cardsFileTests);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			buildAllyFrequency(cardsFileAllies);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			buildAmourFrequency(cardsFileAmours);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Size of deck after adding Foes, Weapons, Tests and Allies: " + deck.size());
		//deck.printDeck();
		deck.shuffle();
		//deck.printDeck();
		
    }
	
	private void buildAmourFrequency(File[] file) throws FileNotFoundException {
		
		if(file[0].toString().endsWith("mour.jpg")) {
			//System.out.println("Amour found");
			for(int c = 0; c < 8; c++) {
				deck.add(new Card(new Image(new FileInputStream(file[0].toString())), "Amour"));
			}
		}
		
		
	}
	
	private void buildAllyFrequency(File[] file) throws FileNotFoundException {
		for(int i = 0; i < file.length; i++) {
			
			if(file[i].toString().endsWith("Galahad.jpg")) {
				//System.out.println("Found Sir Galahad");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Sir Galahad"));
			} else if (file[i].toString().endsWith("Lancelot.jpg")) {
				//System.out.println("Found Sir Lancelot");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Sir Lancelot"));
			} else if (file[i].toString().endsWith("Arthur.jpg")) {
				//System.out.println("Found King Arthur");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "King Arthur"));
			} else if (file[i].toString().endsWith("Tristan.jpg")) {
				//System.out.println("Found Sir Tristan");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Sir Tristan"));
			} else if (file[i].toString().endsWith("Pellinore.jpg")) {
				//System.out.println("Found Sir Pellinore");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Sir Pellinore"));
			} else if (file[i].toString().endsWith("Gawain.jpg")) {
				//System.out.println("Found Sir Gawain");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Sir Gawain"));
			} else if (file[i].toString().endsWith("Percival.jpg")) {
				//System.out.println("Found Sir Percival");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Sir Percival"));
			} else if (file[i].toString().endsWith("Guinevere.jpg")) {
				//System.out.println("Found Queen Guinevere");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Queen Guievere"));
			} else if (file[i].toString().endsWith("Iseult.jpg")) {
				//System.out.println("Found Queen Iseult");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Queen Iseult"));
			} else if (file[i].toString().endsWith("Merlin.jpg")) {
				//System.out.println("Found Merlin");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())), "Merlin"));
			}
			else {
				System.out.println("Bad Card");
			}
			
		}
		
	}
	
	
	private void buildTestFrequency(File[] file) throws FileNotFoundException{
		
	
	   for(int i = 0; i < file.length; i++) {
		
		  if(file[i].toString().endsWith("Test of Valor.jpg")) {
			  //System.out.println("Found the Test of Valor");
			  for(int c = 0; c < 2; c++) {
				  deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Test of Valor"));
			  }
		  } else if (file[i].toString().endsWith("Test of Temptation.jpg")) {
			  //System.out.println("Found the Test of Temptation");
              for(int c = 0; c < 2; c++) {
			    deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Test of Temptation"));
              }
		  } else if (file[i].toString().endsWith("Test of Morgan Le Fey.jpg")) {
		      //System.out.println("Found the Test of Morgan Le Fey");
		      for(int c = 0; c < 2; c++) {
			     deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Test of Morgan Le Fey"));
			  }
		  } else if (file[i].toString().endsWith("Test of the Questing Beast.jpg")) {
			  //System.out.println("Found the Teset of the Questing Beast");
			  for(int c = 0; c < 2; c++) {
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Test of the Questing Beast"));
			  }
		  }
	   }
	}


	public void buildWeaponFrequency(File[] file) throws FileNotFoundException {
         
		 
		for(int i = 0; i < file.length; i++) {
			
			if(file[i].toString().endsWith("Excalibur.jpg")) {
				//System.out.println("Found the Excalibur");
				for(int c = 0; c < 2; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Excalibur"));
				}
			} else if (file[i].toString().endsWith("Lance.jpg")) {
				//System.out.println("Found the Lance");
	            for(int c = 0; c < 6; c++) {
				  deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Lance"));
	            }
			} else if (file[i].toString().endsWith("Battle-ax.jpg")) {
				//System.out.println("Found the Battle-ax");
				for(int c = 0; c < 8; c++) {
				  deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Battle-ax"));
				}
			} else if (file[i].toString().endsWith("Sword.jpg")) {
				//System.out.println("Found the sword");
				for(int c = 0; c < 16; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Sword"));
				}
			} else if (file[i].toString().endsWith("Horse.jpg")) {
				//System.out.println("Found the Horse");
				for (int c = 0; c < 11; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Horse"));
				}
			} else if (file[i].toString().endsWith("Dagger.jpg")) {
				//System.out.println("Found the Dagger");
				for (int c = 0; c < 6; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Dagger"));
				}
			} else {
				//System.out.println("bad card");
			}
		}
	}
	
	
	public void buildFoeFrequency(File[] file) throws FileNotFoundException {
		//System.out.println(file.length);
		//System.out.println(file[0].toString());
		
		for(int i = 0; i < file.length; i++) {
			
			if(file[i].toString().endsWith("Dragon.jpg")) {
				//System.out.println("Found the dragon");
				deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Dragon"));
			} else if (file[i].toString().endsWith("Giant.jpg")) {
				//System.out.println("Found the giant");
	            for(int c = 0; c < 2; c++) {
				  deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Giant"));
	            }
			} else if (file[i].toString().endsWith("Mordred.jpg")) {
				//System.out.println("Found the mordred");
				for(int c = 0; c < 4; c++) {
				  deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Mordred"));
				}
			} else if (file[i].toString().endsWith("Green Knight.jpg")) {
				//System.out.println("Found the green knight");
				for(int c = 0; c < 2; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Green Knight"));
				}
			} else if (file[i].toString().endsWith("Black Knight.jpg")) {
				//System.out.println("Found the black knight");
				for (int c = 0; c < 3; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Black Knight"));
				}
			} else if (file[i].toString().endsWith("Evil Knight.jpg")) {
			//	System.out.println("Found the evil knight");
				for (int c = 0; c < 6; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Evil Knight"));
				}
			} else if (file[i].toString().endsWith("Saxon Knight.jpg")) {
				//System.out.println("Found the saxon knight");
				for (int c = 0; c < 8; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Saxon Knight"));
				}
			} else if (file[i].toString().endsWith("Robber Knight.jpg")) {
				//System.out.println("Found the robber knight");
				for (int c = 0; c < 7; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Robber Knight"));
				}
			} else if (file[i].toString().endsWith("Saxons.jpg")) {
				//System.out.println("Found the saxons");
				for (int c = 0; c < 5; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Saxons"));
				}
			} else if (file[i].toString().endsWith("Boar.jpg")) {
				//System.out.println("Found the boar");
				for (int c = 0; c < 4; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Boar"));
				}
			} else if (file[i].toString().endsWith("Thieves.jpg")) {
				//System.out.println("Found the thieves");
				for (int c = 0; c < 8; c++) {
					deck.add(new Card(new Image(new FileInputStream(file[i].toString())),"Thieves"));
				}
			} else {
				System.out.println("bad card");
			}
		}
		
		
	}
	
	
	public void dealHand(Player p) {
		
		ArrayList<Card> hand = new ArrayList<Card>();
		
		for (int i = 0; i < 12; i++) {
			hand.add(deck.getDeck().get(0));
			deck.getDeck().remove(0);
		}
		
		Hand h = new Hand(hand);
		p.setHand(h);
		
	}
	
	
	public void firstTurnHandOutCards(Stage primaryStage) {
		System.out.println("Dealing all players their first hand");
		//System.out.println("# of players: " + players.size());
		
		for(int i = 0; i < players.size(); i++) {
		    dealHand(players.get(i));
		}
		
		System.out.println("Deck size after drawing hands: " + deck.size());
		
	}
	//this section of code is very much from /tochix the TA who demo'd JUnit. 
	//https://github.com/tochix/junit-demo/blob/master/src/main/java/core/HomeScreen.java
	//sets up the active player in the hotseat's display
	public void setActivePlayerDisplay(Stage primaryStage) {
	
		//the label for the active playersName
		Label activePlayerName = new Label(players.get(0).getName());
		
		activePlayerName.setTextFill(Color.web("#f4a460"));
		activePlayerName.relocate(width-200, height-300);
	    activePlayerName.setFont(Font.font("Serif", FontWeight.BOLD, 30));

		
	    activeRankView = new ImageView();
	    activeRankView.setImage(players.get(0).getRankImage());
	    activeRankView.relocate(width-200,  height-250);
	    activeRankView.setFitWidth(200);
	    activeRankView.setFitHeight(200);
	    activeRankView.setPreserveRatio(true);
		
		canvas.getChildren().addAll(activePlayerName);
		canvas.getChildren().addAll(activeRankView);
		
		showActiveHand(primaryStage, players.get(0), 0);
		
		showOpponentStatus(primaryStage, 0);
		
	}
	
	private void showOpponentStatus(Stage primaryStage, int playerPos) {
		
		for(int i = 0; i < players.size(); i++) {
			if(i == playerPos) {
				//do nothing
				continue;
			} else {
				
				//set up the players name label
				Label tempLabelName = new Label(players.get(i).getName());
				tempLabelName.setTextFill(Color.web("#f4a460"));
				
				int labelX = (width - 580) + (i * 150);
				
				tempLabelName.relocate(labelX, height-980);
				tempLabelName.setFont(Font.font("Serif", FontWeight.BOLD, 20));

				//set up the players number of shields label
				String tempNumShields = Integer.toString(players.get(i).getShields());
				Label tempLabelShield = new Label(tempNumShields);
				tempLabelShield.setTextFill(Color.web("#f4a460"));
				
				tempLabelShield.relocate(labelX,  height-960);
				
				Label tempShieldsLabel = new Label("Shields");
				tempShieldsLabel.setTextFill(Color.web("#f4a460"));
				tempShieldsLabel.relocate(labelX+20,  height-960);
				
				
				//set up the players number of cards label
				String tempNumCards = Integer.toString(players.get(i).getHand().size());
				Label tempLabelCard = new Label(tempNumCards);
				tempLabelCard.setTextFill(Color.web("#f4a460"));
				
				tempLabelCard.relocate(labelX,  height-940);
				
				Label tempCardsLabel = new Label("Cards");
				tempCardsLabel.setTextFill(Color.web("#f4a460"));
				tempCardsLabel.relocate(labelX+20,  height-940);
				
				
				//set up the players rank image
				ImageView tempImgRank = new ImageView();
				tempImgRank.setImage(players.get(i).getRankImage());
				tempImgRank.relocate(labelX, height-920);
				tempImgRank.setFitWidth(100);
			    tempImgRank.setFitHeight(100);
			    tempImgRank.setPreserveRatio(true);
				
				
				//adding all the labels for this player
				canvas.getChildren().addAll(tempLabelName);
				canvas.getChildren().addAll(tempLabelShield);
				canvas.getChildren().addAll(tempShieldsLabel);
				canvas.getChildren().addAll(tempLabelCard);
				canvas.getChildren().addAll(tempCardsLabel);
				canvas.getChildren().addAll(tempImgRank);
			}
		}
		
	}


	//accomodates up to 21 cards 
	private void showActiveHand(Stage primaryStage, Player p, int playerPos) {
		
		
		for(int i = 0; i < players.get(playerPos).getHand().size(); i++) {
			ImageView tempImage = new ImageView();
			tempImage.setImage(p.getHand().getHand().get(i).getImg());
			
			int variable = i;
			if(i > 6) {
				variable = i-7;
			}
			int cardWidth = (width - 990) + (variable * 100);
			
			//first row of cards
			int cardHeight = height-600;
			//second row of cards
			if(i > 6) {
				cardHeight = height-400;
			}
			//third row of cards
			if(i > 12) {
				cardHeight = height-200;
			}
			tempImage.relocate(cardWidth, cardHeight);
			tempImage.setFitWidth(175);
			tempImage.setFitHeight(175);
			tempImage.setPreserveRatio(true);
			
			canvas.getChildren().addAll(tempImage);
		}
	}


	private void initGUI(Stage primaryStage) {
		// TODO Auto-generated method stub
		canvas.setStyle("-fx-background-color: black");
		
		//making a new label
		//Label title = new Label("Knights Team77");
		//setting the labels font
		//title.setFont(Font.font("Serif", FontWeight.NORMAL, 20));
		//title.setTextFill(Color.web("#f4a460"));
		//specifying label location
		//title.relocate(350,10);
		
		//adding the label to the canvas
		//canvas.getChildren().addAll(title);
		//setting and showing the stage
		Scene scene = new Scene(canvas);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Knights Of The Round Table");
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.show();
	}

}

