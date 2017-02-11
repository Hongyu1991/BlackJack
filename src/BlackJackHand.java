public class BlackJackHand extends Hand{
  private int numOfHand;
  public BlackJackHand() {
    // TODO Auto-generated constructor stub
  }
  public BlackJackHand(int numOfHand) {
    this.numOfHand = numOfHand;
  }
  
  public int getHandNum() {
    return numOfHand;
  }
  
  @Override
  public int score() {
    // TODO Auto-generated method stub
    int numOfA = 0;
    int sum = 0;
    for (Card c : cards) {
      int v = c.getFaceValue();
      sum += v;
      if (v == 1) numOfA++;
    }
    
    for (int i = 0; i < numOfA; i++) {
      if (sum <= 11) {
        sum += 10;
      }
    }
    return sum;
  }
  
  public int getValue(Card c) {
    if (c.getFaceValue() >= 11 && c.getFaceValue() <= 13) {
      return 10;
    }
    return c.getFaceValue();
  }
  
  
  public boolean busted() {
    return score() > 21;
  }
  
  public boolean isBlackJack() {
    if (cards.size() != 2) {
      return false;
    }
    return score() == 21;
  }

}
