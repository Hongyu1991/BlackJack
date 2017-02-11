import java.util.ArrayList;
import java.util.List;

public class BlackJackGameAuto {
  private Deck deck;
  private List<BlackJackHand> hands;
  private static final int SCORE_STOP = 16; //when our score hit 16 points, we stop asking for more cards. 
  
  public BlackJackGameAuto(int numOfPlayers) {
    // TODO Auto-generated constructor stub
    hands = new ArrayList<BlackJackHand>();
    for (int i = 0; i < hands.size(); i++) {
      hands.add(new BlackJackHand(i));
    }
  }
  
  //before every game, we init one new deck and shuffle it.
  boolean initDeck() {
    deck = new Deck();
    deck.shuffle();
    return true;
  }
  
  //give every player 2 cards at first
  boolean initDeal() {
    //each player should have two cards in hand at first
    for (Hand h : hands) {
      List<Card> newCards = deck.dealHand(2);
      h.cards.addAll(newCards);
    }
    return true;
  }
  
  //check which players get Black Jack in the first place.
  List<BlackJackHand> getBlackJack() {
    List<BlackJackHand> BJwinners = new ArrayList<>();
    for (BlackJackHand p : hands) {
      if (p.isBlackJack()) {
        BJwinners.add(p);
      }
    }
    return BJwinners;
  }
  
  //auto-play
  boolean playHand(BlackJackHand hand) {
    while (hand.score() <= SCORE_STOP) {
      Card newCard = deck.dealCard();
      if (newCard == null) {
        return false;
      }
      hand.cards.add(newCard);
    }
    return true;
  }
  
  //auto-play all players
  boolean playAll() {
    for (BlackJackHand hand : hands) {
      if(!playHand(hand)) {
        return false;
      }
    }
    return true;
  }
  
  
  
  
  
}
