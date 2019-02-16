package nguyen.hw.waregame;

import java.util.ArrayList;
import java.util.Random;


public class Deck {

    ArrayList<Card> cards = new ArrayList<Card>();
    String[] values = {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
    String[] suits = {"C","H","D","H"};
    private static Random random = new Random();

public Deck()
{
    for (int i = 0; i <suits.length; i++)
    {
        for (int j = 0; j<values.length; j++) {
            this.cards.add(new Card(suits[i],Integer.valueOf(values[j])));
        }
    }


}
public ArrayList<Card> getDeck(){
    return cards;
}
public static  ArrayList<Card> EmptyDeck() {
    ArrayList<Card> nocards = new ArrayList<Card>();
    return nocards;
    }
// Fisher Yates algorithm
public void shuffle() {
    for (int i = 0; i < this.cards.size(); i++) {
        int randomIndex = i + random.nextInt(this.cards.size() - i);
        Card randomCard = this.cards.get(randomIndex);
        this.cards.set(randomIndex, this.cards.get(i));
        this.cards.set(i, randomCard);
    }
}


public static void main (String [] args)
{
    Deck test = new Deck();
    test.shuffle();
    System.out.println("HI");
    System.out.println(test.getDeck());




}


}
