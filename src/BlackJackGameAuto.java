import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlackJackGameAuto {
  private Deck deck;
  private List<BlackJackHand> hands;
  private static final int SCORE_STOP = 16; //when our score hit 16 points, we stop asking for more cards. 
  private Set<BlackJackHand> winners;
  
  public BlackJackGameAuto(int numOfPlayers) {
    // TODO Auto-generated constructor stub
    hands = new ArrayList<BlackJackHand>();
    for (int i = 0; i < numOfPlayers; i++) {
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
    for (BlackJackHand h : hands) {
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
      playHand(hand);
      show(hand);
    }
    return true;
  }
  
  //All players that are not busted in the end are winners.
  Set<BlackJackHand> getWinner() {
    winners = new HashSet<>();
    for (BlackJackHand hand : hands) {
      if (!hand.busted()) {
        winners.add(hand);
      }
    }
    return winners;
  }
  
  //print out the results
  void showResult() {
    for (BlackJackHand hand : hands) {
      if (winners != null && winners.contains(hand)) {
        System.out.println("Hand"+hand.getHandNum() + "'s score is " + hand.score() + ". Win!");
      } else {
        System.out.println("Hand"+hand.getHandNum() + "'s score is " + hand.score() + ". Busted!");
      }
    }
  }
  
  void show(BlackJackHand hand) {
    System.out.print("Hand"+hand.getHandNum() + "'s current cards are:");
    for (Card card : hand.cards) {
      if (card.getFaceValue() == 1) {
        System.out.print(" A ");
      } else if (card.getFaceValue() == 11) {
        System.out.print(" J ");
      } else if (card.getFaceValue() == 12) {
        System.out.print(" Q ");
      } else if (card.getFaceValue() == 13) {
        System.out.print(" K ");
      } else {
        System.out.print(" " + card.getFaceValue() + " ");
      }
    }
    
    System.out.print("   ");
    System.out.println("Hand"+hand.getHandNum() + "'s current score is " + hand.score());
    System.out.println(" ");
  }
  
  //
  public void run() {
    initDeck();
    System.out.println("Deck initial and shuffle");
    System.out.println(" ");
    
    initDeal();
    System.out.println("Init 2 Cards");
    System.out.println(" ");
    for (BlackJackHand hand : hands) {
      show(hand);
    }
    
    System.out.println("Auto play all!!!");
    System.out.println(" ");
    playAll();
    
    getWinner();
    
    showResult();

  }
  
  
}
