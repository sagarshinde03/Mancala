import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Implementation {
	boolean turn=true;
	void play(){
		while(!gameFinished()){
			displayCurrentGamePosition();
			int deckNumber=askUserForDeck();
			Deck deck=Deck.deck1.getDeckFromNumber(deckNumber);
			int currentSum=deck.numberOfPebbles;
			deck.numberOfPebbles=0;
			if(deckNumber==6){
				if(currentSum==1){
					Rack.rack1.numberOfPebles++;
					continue;
				}
				Rack.rack1.numberOfPebles++;
				updateDecksAndRacks(currentSum-1, 7);
				continue;
			}
			if(deckNumber==12){
				if(currentSum==1){
					Rack.rack2.numberOfPebles++;
					continue;
				}
				Rack.rack2.numberOfPebles++;
				updateDecksAndRacks(currentSum-1, 1);
				continue;
			}
			updateDecksAndRacks(currentSum,getNextDeckNumber(deckNumber));
		}
		displayWinner();
	}
	void displayCurrentGamePosition(){
		System.out.println("Rack 1: "+Rack.rack1.numberOfPebles+"\t"+"Rack 2: "+Rack.rack2.numberOfPebles);
		System.out.println();
		System.out.println(Deck.deck6.numberOfPebbles+"\t"+Deck.deck5.numberOfPebbles+"\t"+Deck.deck4.numberOfPebbles+"\t"+Deck.deck3.numberOfPebbles+"\t"+Deck.deck2.numberOfPebbles+"\t"+Deck.deck1.numberOfPebbles);
		System.out.println(Deck.deck7.numberOfPebbles+"\t"+Deck.deck8.numberOfPebbles+"\t"+Deck.deck9.numberOfPebbles+"\t"+Deck.deck10.numberOfPebbles+"\t"+Deck.deck11.numberOfPebbles+"\t"+Deck.deck12.numberOfPebbles);
		System.out.println();
	}
	boolean gameFinished(){
		if(Deck.deck1.numberOfPebbles==0 && Deck.deck2.numberOfPebbles==0 && Deck.deck3.numberOfPebbles==0 && Deck.deck4.numberOfPebbles==0 && Deck.deck5.numberOfPebbles==0 && Deck.deck6.numberOfPebbles==0){
			return true;
		}
		if(Deck.deck7.numberOfPebbles==0 && Deck.deck8.numberOfPebbles==0 && Deck.deck9.numberOfPebbles==0 && Deck.deck10.numberOfPebbles==0 && Deck.deck11.numberOfPebbles==0 && Deck.deck12.numberOfPebbles==0){
			return true;
		}
		return false;
	}
	void displayWinner(){
		if(Rack.rack1.numberOfPebles>Rack.rack2.numberOfPebles){
			System.out.println("Player 1 is the winner");
		}else if(Rack.rack2.numberOfPebles>Rack.rack1.numberOfPebles){
			System.out.println("Player 2 is the winner");
		}else{
			System.out.println("Game tied");
		}
	}
	int askUserForDeck(){
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			if(turn){
				System.out.println("Player 1, please enter the Deck number you want to select");
				int deckNumber=Integer.parseInt(br.readLine());
				Deck deck=Deck.deck1.getDeckFromNumber(deckNumber);
				while(deckNumber<1 || deckNumber>6 || deck.numberOfPebbles==0){
					System.out.println("Please enter a valid Deck number with at least one pebble");
					deckNumber=Integer.parseInt(br.readLine());
					deck=Deck.deck1.getDeckFromNumber(deckNumber);
				}
				return deckNumber;
			}else{
				System.out.println("Player 2, please enter the Deck number you want to select");
				int deckNumber=Integer.parseInt(br.readLine());
				Deck deck=Deck.deck1.getDeckFromNumber(deckNumber);
				while(deckNumber<7 || deckNumber>12 || deck.numberOfPebbles==0){
					System.out.println("Please enter a valid Deck number with at least one pebble");
					deckNumber=Integer.parseInt(br.readLine());
					deck=Deck.deck1.getDeckFromNumber(deckNumber);
				}
				return deckNumber;
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	int getNextDeckNumber(int deckNumber){
		if(deckNumber==12){
			return 1;
		}
		return deckNumber+1;
	}
	void updateDecksAndRacks(int currentSum,int currentDeckNumber){
		Deck deck=Deck.deck1.getDeckFromNumber(currentDeckNumber);
		if(currentSum==1){
			deck.numberOfPebbles++;
			if(turn && deck.numberOfPebbles==1 && currentDeckNumber>=1 && currentDeckNumber<=6){
				int oppositeDeckNumber=(13-currentDeckNumber);
				Deck oppositeDeck=Deck.deck1.getDeckFromNumber(oppositeDeckNumber);
				int oppositeDeckPebbles=oppositeDeck.numberOfPebbles;
				if(oppositeDeckPebbles!=0){
					oppositeDeck.numberOfPebbles=0;
					deck.numberOfPebbles=0;
					Rack.rack1.numberOfPebles+=oppositeDeckPebbles+1;
				}
			}
			if(!turn && deck.numberOfPebbles==1 && currentDeckNumber>=7 && currentDeckNumber<=12){
				int oppositeDeckNumber=(13-currentDeckNumber);
				Deck oppositeDeck=Deck.deck1.getDeckFromNumber(oppositeDeckNumber);
				int oppositeDeckPebbles=oppositeDeck.numberOfPebbles;
				if(oppositeDeckPebbles!=0){
					oppositeDeck.numberOfPebbles=0;
					deck.numberOfPebbles=0;
					Rack.rack2.numberOfPebles+=oppositeDeckPebbles+1;
				}
			}
			turn=!turn;
			return;
		}
		if(currentDeckNumber==6){
			if(currentSum==2){
				deck.numberOfPebbles++;
				Rack.rack1.numberOfPebles++;
				if(turn) return;
				else{
					turn=!turn;
					return;
				}
			}
			deck.numberOfPebbles++;
			Rack.rack1.numberOfPebles++;
			updateDecksAndRacks(currentSum-2, 7);
			return;
		}
		if(currentDeckNumber==12){
			if(currentSum==2){
				deck.numberOfPebbles++;
				Rack.rack2.numberOfPebles++;
				if(!turn) return;
				else{
					turn=!turn;
					return;
				}
			}
			deck.numberOfPebbles++;
			Rack.rack2.numberOfPebles++;
			updateDecksAndRacks(currentSum-2, 1);
			return;
		}
		deck.numberOfPebbles++;
		updateDecksAndRacks(currentSum-1, currentDeckNumber+1);
		return;
	}
}
