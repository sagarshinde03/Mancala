
public enum Deck {
	deck1(1), deck2(2), deck3(3), deck4(4),deck5(5), deck6(6), deck7(7), deck8(8), deck9(9), deck10(10), deck11(11), deck12(12);
	int numberOfPebbles;
	int deckNumber;
	Deck(int deckNumber){
		this.deckNumber=deckNumber;
		this.numberOfPebbles=4;
	}
	int getDeckNumber(){
		return deckNumber;
	}
	public Deck getDeckFromNumber(int deckNumber){
		if(deckNumber==1){
			return Deck.deck1;
		}else if(deckNumber==2){
			return Deck.deck2;
		}else if(deckNumber==3){
			return Deck.deck3;
		}else if(deckNumber==4){
			return Deck.deck4;
		}else if(deckNumber==5){
			return Deck.deck5;
		}else if(deckNumber==6){
			return Deck.deck6;
		}else if(deckNumber==7){
			return Deck.deck7;
		}else if(deckNumber==8){
			return Deck.deck8;
		}else if(deckNumber==9){
			return Deck.deck9;
		}else if(deckNumber==10){
			return Deck.deck10;
		}else if(deckNumber==11){
			return Deck.deck11;
		}else if(deckNumber==12){
			return Deck.deck12;
		}else{
			return null;
		}
	}
}
