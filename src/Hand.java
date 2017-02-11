import java.util.*;

public abstract class Hand {
  protected final List<Card> cards = new ArrayList<>();
  
  public Hand() {
    //empty constructor
  }
  
  //define a score calculation method
  //this can make sure that the subclass will implement it.
  public abstract int score();
  
  public int size() {
    return cards.size();
  }
  
  
}
