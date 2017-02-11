import java.util.*;

public class Deck {
  private final List<Card> cards;
  private int indexOfDeck;
  public Deck() {
    cards = new ArrayList<Card>();
    for (int i = 1; i <= 13; i++) {
      for (Suit s : Suit.values()) {
        cards.add(new Card(i, s));
      }
    }
  }
  
  //before start every round, we should shuffle the deck to a random order. We can use an array to record the order
  public void shuffle() {
    for (int i = cards.size() - 1; i > 0; i--) {
      int index = (int) Math.random() * (i + 1);
      Card c1 = cards.get(index);
      Card c2 = cards.get(i);
      cards.set(index, c2);
      cards.set(i, c1);
    }
  }
  
  private int remainingCard() {
    return cards.size() - indexOfDeck;
  }
  
  public List<Card> dealHand(int number) {
    if (number > remainingCard()) {
      return null;
    }
    List<Card> C = new ArrayList<>();
    for (int i = 0; i < number; i++) {
      C.add(dealCard());
    }
    return C;
  }
  
  public Card dealCard() {
    if (remainingCard() <= 0) {
      return null;
    }
    return cards.get(indexOfDeck++);
  }
  
}
