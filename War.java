package nguyen.hw.waregame;

import java.util.Scanner;

public class War {





    private static void printTotalCards(int enemy, int player, boolean tab) {
        if(tab) {
            System.out.print("\t");
        }
        System.out.println("CPU now has " + enemy + " cards. You now have " + player + " cards.");
    }
    private static void OutOfCards(Deck eDeck, Deck eWin, Deck pDeck, Deck pWin, boolean tab) {
        printSizes(eDeck.cards.size(), eWin.cards.size(), pDeck.cards.size(), pWin.cards.size(), tab);

        if(tab) {
            System.out.print("\t");
        }
        System.out.println("Out of cards!");

        eDeck.cards.addAll(eWin.cards);
        eWin.cards.clear();
        eDeck.shuffle();
        pDeck.cards.addAll(pWin.cards);
        pWin.cards.clear();
        pDeck.shuffle();

        printTotalCards(eDeck.cards.size(), pDeck.cards.size(), tab);
    }

    private static String draw() {
        System.out.println("Press enter to DRAW a card!");
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static void printDrawnCards(Card enemy, Card player, boolean tab) {
        if(tab) {
            System.out.print("\t");
        }
        System.out.println("(enemy)\t" + enemy + " <> " + player + "\t(you)");
    }

    private static void printSizes(int eDeck, int eWin, int pDeck, int pWin, boolean tab) {
        if(tab) {
            System.out.print("\t");
        }
        System.out.println("CPU deck: " + eDeck + " CPU winnings: " + eWin + " Player deck: " + pDeck + " Player winnings: " + pWin);
    }

    private static void printWin(int eSize, int pSize) {
        System.out.println("Computer Winnings: " + eSize + " cards\tYour Winnings: " + pSize + " cards");
    }

    private static void printStake(int stakeSize, Card c1, Card c2) {
        System.out.println("\t(there are " + stakeSize + " cards at stake, plus " + c1 + " and " + c2 + ")");
    }

    public static void main(String[] args) {

        Deck origDeck = new Deck();
        origDeck.shuffle();
        Deck eDeck = new Deck();
        Deck pDeck = new Deck();
        for (int i =0; i< origDeck.cards.size()/2; i++) {
            eDeck.cards.add(origDeck.cards.get(i));
        }
        for (int i = origDeck.cards.size()/2; i< origDeck.cards.size(); i++) {
            pDeck.cards.add(origDeck.cards.get(i));
        }
        Deck eWin  = new Deck();
        Deck pWin = new Deck();
        eWin.cards = eWin.EmptyDeck();
        pWin.cards = pWin.EmptyDeck();

        Deck Fightcards = new Deck();
        Fightcards.cards = Fightcards.EmptyDeck();

        boolean war = false;

        System.out.println("---- Welcome to WAR! ----");
        System.out.println("You and the computer will each draw a card, whoever has the higher card wins the pair!");
        System.out.println("You can quit any time by entering \"Q\"");

        while(!eDeck.cards.isEmpty() && !pDeck.cards.isEmpty()) {
            String userInput = draw();

            if(userInput.equals("q") || userInput.equals("Q")) {
                break;
            }
            Card eCard = eDeck.cards.remove(eDeck.cards.size() - 1);
            Card pCard = pDeck.cards.remove(pDeck.cards.size() - 1);

            printDrawnCards(eCard, pCard, false);

            while(eCard.compareTo(eCard,pCard) == "Equal") {

                war = true;
                System.out.println("\t!!! This means WAR !!!");

                if(!Fightcards.cards.isEmpty()) {
                    printStake(Fightcards.cards.size(), eCard, pCard);
                }

                Fightcards.cards.add(eCard);
                Fightcards.cards.add(pCard);

                if(eDeck.cards.isEmpty() || pDeck.cards.isEmpty()) {
                    OutOfCards(eDeck, eWin, pDeck, pWin, true);
                }


                if(eDeck.cards.isEmpty() || pDeck.cards.isEmpty()) {
                    break;
                }

                Fightcards.cards.add(eDeck.cards.remove(eDeck.cards.size() - 1));
                Fightcards.cards.add(pDeck.cards.remove(pDeck.cards.size() - 1));

                if(eDeck.cards.isEmpty() || pDeck.cards.isEmpty()) {
                    OutOfCards(eDeck, eWin, pDeck, pWin, true);
                }


                if(eDeck.cards.isEmpty() || pDeck.cards.isEmpty()) { // game ends
                    break;
                }

                eCard = eDeck.cards.remove(eDeck.cards.size() - 1);
                pCard = pDeck.cards.remove(pDeck.cards.size() - 1);
                printDrawnCards(eCard, pCard, true);
            }
            if((eDeck.cards.isEmpty() && eWin.cards.isEmpty())
                    || (pDeck.cards.isEmpty() && pWin.cards.isEmpty())) { // game ends
                break;
            }

            if(!Fightcards.cards.isEmpty()) {
                printStake(Fightcards.cards.size(), eCard, pCard);
            }


            if(eCard.compareTo(eCard,pCard) == "True") {

                eWin.cards.add(eCard);
                eWin.cards.add(pCard);

                if(war) {
                    war = false;
                    System.out.println("You lost the war!");
                    eWin.cards.addAll(Fightcards.cards);
                    Fightcards.cards.clear();
                } else {
                    System.out.println("You lose!");
                }
            } else {
                // Player wins
                pWin.cards.add(eCard);
                pWin.cards.add(pCard);

                if(war) {
                    war = false;
                    System.out.println("You won the war!");
                    pWin.cards.addAll(Fightcards.cards);
                    Fightcards.cards.clear();
                } else {
                    System.out.println("You win!");
                }
            }

            printWin(eWin.cards.size(), pWin.cards.size());

            if(eDeck.cards.isEmpty() || pDeck.cards.isEmpty()) {
                OutOfCards(eDeck, eWin, pDeck, pWin, false);
            }
        }


        if((eDeck.cards.isEmpty() && eWin.cards.isEmpty()) || (pDeck.cards.isEmpty() && pWin.cards.isEmpty())) {
            System.out.println("GAME OVER!!!");
            System.out.print("The winner is: ");

            if((eDeck.cards.isEmpty() && eWin.cards.isEmpty()) && (pDeck.cards.isEmpty() && pWin.cards.isEmpty())) {
                System.out.println("IT'S A TIE!");
            }
            else if(eDeck.cards.isEmpty() && eWin.cards.isEmpty()) {
                System.out.println("YOU!!!");
            } else {
                System.out.println("CPU!!!");
            }
        }
        System.out.println("Thanks for playing!");
    }
}

