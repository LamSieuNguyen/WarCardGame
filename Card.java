package nguyen.hw.waregame;

public class Card {

    private String suit;
    private int value;

    public Card(String suit, int val) {

        this.suit = suit;
        this.value = val;
    }

    public String getSuit() {
        return this.suit;
    }

    public int getValue() {
        return this.value;
    }

    public void setSuit(String newsuit){
        this.suit = newsuit;
    }

    public void setValue(int newValue){
        this.value = newValue;
    }

    public String toString()
    {
        String newval =  String.valueOf(this.value);
        String thecard = this.suit + this.value;
        return thecard;

    }
    public static String compareTo(Card one, Card two)
    {
        int value1 = one.getValue();
        int value2 = two.getValue();
        if(value1 > value2)
        {
            return "True";
        }
        if(value2 > value1) {
            return "False";
        }
        else {
            return "Equal";
        }
    }

    public static void main (String [] args)
    {
        Card spades1 = new Card("S",1);
        Card spades2 = new Card("S",2);
        String comp = compareTo(spades1,spades1);
        System.out.println(comp);
        System.out.println(spades1.toString());

    }

}


