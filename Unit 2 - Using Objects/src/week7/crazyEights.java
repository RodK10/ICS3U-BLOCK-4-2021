package week7;

import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

public class crazyEights {
    private static final int NUM_SUITS = 4;
    private static final String HEARTS = "H";
    private static final String DIAMONDS = "D";
    private static final String CLUBS = "C";
    private static final String SPADES = "S";
    private static final int CARDS_PER_SUIT = 13;
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";

    public static void main(String[] args) {
        int userPoints = 0, computer1Points = 0, computer2Points = 0;
        Scanner in = new Scanner(System.in);
        String playerHand = "";
        String computer1Hand = "";
        String computer2Hand = "";
        String topCard = "";
        /**
         * This while loop prints out the total points of the two computers and the player. It
         * first parse ints the string into an integer in order to add on the numbers to each other after each round
         * because you cant add string numbers together. The in the loop, it prints out the total number of points each computer and player
         * have after each round. 
         */
        while (!gameOver(userPoints, computer1Points, computer2Points)) {
            String result = playRound(playerHand, topCard);
            int firstDash = result.indexOf("-");
            int secondDash = result.lastIndexOf("-");
            userPoints += Integer.parseInt(result.substring(0, firstDash));
            computer1Points += Integer.parseInt(result.substring(firstDash + 1, secondDash));
            computer2Points += Integer.parseInt(result.substring(secondDash + 1));
            System.out.println("Players current total points: " + userPoints);
            System.out.println("Computer 1 current total points: " + computer1Points);
            System.out.println("Computer 2 current total points: " + computer2Points);

            // System.out.println(userPoints + " ");
        }
        if (userPoints < computer1Points && userPoints < computer2Points) {
            System.out.println("Player Wins!");
        }
        if (computer1Points < userPoints && computer1Points < computer2Points) {
            System.out.println("Computer 1 Wins!");
        }
        if (computer2Points < userPoints && computer2Points < userPoints) {
            System.out.println("Computer 2 Wins!");
        }

    }
/**
 * This method basically loops through the hands of the computers and everytime it see's a number of a letter, it replaces it with an X.
 */
    private static String maskComputerHand(String hand) {
        String result = "";
        while (hand.length() > 1) {
            int temp = hand.indexOf(" ");
            hand = hand.substring(temp + 1, hand.length());
            result += "XX ";

        }
        return result;

    }
    /**
     * This method sets point values for each card and goes through each and every card value and goes through the players
     * ranks and outputs them as points for the system print lines to execute. 
     * 
     * 
     */
    
    private static int countingPoints(String hand) {
        int point = 0;
        while (hand.length() > 1) {
            int temp = hand.indexOf(" ");
            String card = hand.substring(0, temp);
            String rank = card.substring(0, card.length() - 1);
            hand = hand.substring(temp + 1, hand.length());
            if (rank.equals("A")) {
                point += 1;
            }
            if (rank.equals("2")) {
                point += 2;
            }
            if (rank.equals("3")) {
                point += 3;
            }
            if (rank.equals("4")) {
                point += 4;
            }
            if (rank.equals("5")) {
                point += 5;
            }
            if (rank.equals("6")) {
                point += 6;
            }
            if (rank.equals("7")) {
                point += 7;
            }
            if (rank.equals("8")) {
                point += 50;
            }
            if (rank.equals("9")) {
                point += 9;
            }
            if (rank.equals("10")) {
                point += 10;
            }
            if (rank.equals("J")) {
                point += 10;
            }
            if (rank.equals("Q")) {
                point += 10;
            }
            if (rank.equals("K")) {
                point += 10;
            }

        }
        return point;
    }
    /**
     * This method playRound goes and first distributes the players and computers card in a for loop. Next, it displays these
     * cards. The while loop checks if the top card is 8 and to call the getCard() method to change the top card. Next, it sees if
     * either the computer or the player as a playable deck and if not, the if statements use break so that when they one of them
     * has no cards left, it leaves the while loop and checks which hand got to 0 first and displays who wins and the points they
     * accumulated that specific round.
     */

    private static String playRound(String playerHand, String topCard) {
        System.out.println("The round begins!");
        System.out.println();
        String c1Hand = "";
        String c2Hand = "";

        for (int i = 0; i < 5; i++) {
            playerHand += getCard() + " ";
            c1Hand += getCard() + " ";
            c2Hand += getCard() + " ";

        }
        

        System.out.println("Player: " + playerHand);
        System.out.println("Computer 1: XX XX XX XX XX");
        System.out.println("Computer 2: XX XX XX XX XX");
        topCard = getCard();
        while (topCard.substring(0, 1).equals("8")) {
            topCard = getCard();
        }

        System.out.println("Top Card: " + topCard);

        while (playerHand.length() > 1 && c1Hand.length() > 1 && c2Hand.length() > 1) {
            

            String temp = processPlayer(playerHand, topCard);
            playerHand = temp.substring(0, temp.indexOf("-"));
            topCard = temp.substring(temp.indexOf("-") + 1);
            if (playerHand.length() < 3) {
                break;// skips the while loop
            }
            temp = processComputer(c1Hand, topCard, playerHand, c2Hand, "1");
            c1Hand = temp.substring(0, temp.indexOf("-"));
            topCard = temp.substring(temp.indexOf("-") + 1);
            if (c1Hand.length() < 3) {
                break;
            }

            temp = processComputer(c2Hand, topCard, playerHand, c1Hand, "2");
            c2Hand = temp.substring(0, temp.indexOf("-"));
            topCard = temp.substring(temp.indexOf("-") + 1);

        }

        int playerPoints = countingPoints(playerHand);
        int c1Points = countingPoints(c1Hand);
        int c2Points = countingPoints(c2Hand);
        if (playerPoints == 0) {
            System.out.println("Round is over, player has no cards!");
        }
        if (c1Points == 0) {
            System.out.println("Round is over, Computer has no cards!");
        }
        if (c2Points == 0) {
            System.out.println("Round is over, Computer 2 has no cards!");
        }

        
        System.out.println("This round, player got: " + playerPoints);
        System.out.println("This round, computer 1 got: " + c1Points);
        System.out.println("This round, computer 2 got: " + c2Points);
        return playerPoints + "-" + c1Points + "-" + c2Points;

    }
    /**
     * In User Choice, it prompts the user on which card it wants to play in a while loop. It checks to see if what the player put in
     * is valid. It uses substring to set the cardRank and cardSuit and only lets the player choose a card that is equal to what has been
     * set in the loop. 
     * 
     */
    private static String userChoice(Scanner in) {
        boolean validInput = false;
        String cardRank = "";
        String cardSuit = "";
        String answer = "";

        while (!validInput) {
            System.out.print("Which card do you want to Play: ");
            answer = in.nextLine().toUpperCase();
            if (answer.length() == 2) {
                cardRank = answer.substring(0, 1);
                if (cardRank.equals("A") || cardRank.equals("2") || cardRank.equals("3") || cardRank.equals("4")
                        || cardRank.equals("5") || cardRank.equals("6") || cardRank.equals("7") || cardRank.equals("8")
                        || cardRank.equals("9") || cardRank.equals("J") || cardRank.equals("Q")
                        || cardRank.equals("K")) {
                    cardSuit = answer.substring(1, 2);
                    if (cardSuit.equals("D") || cardSuit.equals("C") || cardSuit.equals("S") || cardSuit.equals("H")) {
                        validInput = true;
                    }

                }

            } else if (answer.length() == 3) {
                if (answer.substring(0, 2).equals("10")) {
                    cardSuit = answer.substring(2, 3);
                    if (cardSuit.equals("D") || cardSuit.equals("C") || cardSuit.equals("S") || cardSuit.equals("H")) {
                        validInput = true;
                    }
                }

            }
            if (!validInput) {
                System.out.println("You must enter a valid card!");
            }
        }
        return answer;
    }
    /**
     * First in process computer, it checks if the when the computer does not have a valid card and they have not
     * already drawn 5 times, to add a card to their deck and print out that they are drawing. Next, it looks through
     * if the suit of the card that the computer played and the suit of the top card are the same and if it is not an
     * 8 card. It also checks if opposite hand 1 and 2 is on its last card and if it has a valid suit which lets the computer know
     * to try and place an 8 and to change the suit. Next, it does the same with the rank where it checks if the card rank that the computer played is
     * equal to the top card rank. It then checks if the opposite hand 1 and 2 are on their last card and if they have a valid rank which 
     * lets the computer know to try and play a different rank than what they have. Next, it checks each opposite hands suit and sees which one that does not 
     * match up with the computers hand and switches it to the suit where the computer can most benefit from and where the player is forced to draw.
     * Finally, there are if and else if statements to determine which suit the computer will choose to go with the 8. If this entire code cannot be played,
     * the computers turn is skipped.
     * 
     * 
     */
    private static String processComputer(String hand, String topCard, String oppHand1, String oppHand2,
            String computerName) {
        int numDrawn = 0;

        while (!validHand(hand, topCard) && numDrawn < 5) {

            numDrawn++;

            hand = hand + getCard() + " ";

            System.out.println("Computer " + computerName + " is drawing a card....");

        }
        System.out.println("Computer " + computerName + " hand: " + maskComputerHand(hand));
        try {

            Thread.sleep(500);

        } catch (InterruptedException e) {

        }

        // a bunch of if elses that give the computer set instructions, NO questions
        // find rank and suit of top card and check if it equals to any cards of
        // computer
        String newCard = "";
        String topCardRank = topCard.substring(0, topCard.length() - 1);
        String topCardSuit = topCard.substring(topCard.length() - 1, topCard.length());
        String tempHand = hand;
        while (tempHand.length() > 1) {
            int temp = tempHand.indexOf(" ");
            if (temp == -1) {
                newCard = tempHand;
            } else {
                newCard = tempHand.substring(0, temp);
            }
            tempHand = tempHand.substring(temp + 1, tempHand.length());
            String newCardRank = newCard.substring(0, newCard.length() - 1);
            String newCardSuit = newCard.substring(newCard.length() - 1, newCard.length());

            if (!newCardRank.equals("8") && topCardSuit.equals(newCardSuit)) {
                if(oppHand1.length() < 4 && oppHand1.indexOf(newCardSuit) > -1){

                     
                }else if(oppHand2.length() < 4 && oppHand2.indexOf(newCardSuit) > -1){

                }else{

                
                
                topCard = newCard;
                hand = playACard(newCard, hand);

                System.out.println("Computer" + computerName + " picking a card.....");
                System.out.println("Top Card:" + topCard);
                System.out.println();
                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) {

                }
                return hand + "-" + topCard;
            }
            }
            if (!newCardRank.equals("8") && topCardRank.equals(newCardRank) && tempHand.indexOf(topCardSuit) == -1) {
                if(oppHand1.length() < 4 && oppHand1.indexOf(newCardSuit) > -1 && tempHand.indexOf(newCardRank) > -1){
                    
                }else if(oppHand2.length() < 4 && oppHand2.indexOf(newCardSuit) > -1 && tempHand.indexOf(newCardRank) > -1){

                }
                else{
                topCard = newCard;
                hand = playACard(newCard, hand);
                System.out.println("Computer" + computerName + " picking a card.....");
                System.out.println("Top Card:" + topCard);
                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) {

                }
                System.out.println();
                return hand + "-" + topCard;
            }
            }
            if (newCardRank.equals("8") && tempHand.indexOf(topCardRank) == -1 && tempHand.indexOf(topCardSuit) == -1) {
                
                hand = playACard(newCard, hand);
                try {

                    Thread.sleep(500);

                } catch (InterruptedException e) {

                }
                System.out.println("The computer played: " + newCard);
                if(oppHand1.length() < 4 || oppHand2.length() < 4){
                    String trgtSuit = "";
                    for(int i = 0;i < 4; i++){//checks each opposite hands suit and sees which one that does not match up with the computers hand
                        
                        if(i==0){
                            trgtSuit = "D";
                        }
                        else if(i==1){
                            trgtSuit = "C";
                        }
                        else if(i==2){
                            trgtSuit = "S";
                        }
                        else if(i==3){
                            trgtSuit = "H";
                        }
                        if(!(oppHand1.length() < 4 && oppHand1.indexOf(trgtSuit) > -1) && !(oppHand2.length() < 4 && oppHand2.indexOf(trgtSuit) > -1)){
                            break;
                            
                        }
                        

                    }
                    topCard = "8" + trgtSuit;
                    System.out.println("Computer switched suits: " + topCard);
                    
                }
                else{
                
                int findSuit = hand.indexOf(" ");
                if (hand.strip().length() < 3) {//Strip is used to get rid of the extra space because if there was an extra space, the substrings would get messed causing an error.
                    topCard = newCard;

                } else if (findSuit == -1) {
                    topCard = "8" + hand.substring(hand.length() - 1, hand.length());
                    try {

                        Thread.sleep(500);

                    } catch (InterruptedException e) {

                    }
                    System.out.println("The Computer chose the top card to be: " + topCard);
                } else {
                    topCard = "8" + hand.substring(findSuit - 1, findSuit);
                    System.out.println("The Computer chose the top card to be: " + topCard);
                }
            }
                return hand + "-" + topCard;

            }

        }

        System.out.println("Computer " + computerName + " cannot play. Skipping turn");
        try {

            Thread.sleep(500);

        } catch (InterruptedException e) {

        }
        return hand + "-" + topCard;

    }
    /**
     * This method Eight Suit prompts the user for which suit they will play for the 8 card in a while loop. It takes in an
     * answer but it uses the toUpperCase() class so if they user puts in a lower case, it automatically puts it as upper case. It then
     * says that the answers should only be these four letters which are the suits and if they dont choose those for, it goes to the else
     * stating they they can only input valid suits.
     *  
     * 
     */
    private static String eightSuit(Scanner in) {
        boolean validInput = false;
        String answer = "";
        while (!validInput) {
            System.out.println("What suit will you choose? [♠] [♥] [♣️] [⟡]");
            answer = in.nextLine().toUpperCase();
            if (answer.equals("D") || answer.equals("C") || answer.equals("S") || answer.equals("H")) {
                validInput = true;
            } else {
                System.out.println("You can only input valid Suits!");
            }
        }
        return answer;

    }
    /**
     * In processPlayer, It first displays that it is the players turn and displays their deck. Then, it only allows the player to pick up
     * 5 cards if they do not have a valid hand to play. Next, it checks if it is not valid hand, it skips the players turn. Now, it asks
     * if the cardPlayed is -1 or non-existent in the playerHand, to display that this card is not in your hand by calling the userChoice
     * and looking at what card the user played. Next, it sets the top and card suits and ranks to the substring of the card. Next, it sees
     * if the player inputted an 8 and then it sets temp as the eight suit method. Then, is sets the top card as the cardPlay substring from 
     * 0 to the cardPlay length -1 which is the rank + temp which was the suit that the player chose from the eight suit method. Finally,
     * it sees if the cardPlay suit and rank and equal to the topCard Suit and rank and if that is true, it will replace top card with the
     * card play. If none of this is true, it displays that your play is against the rules.
     * 
     */
    private static String processPlayer(String playerHand, String topCard) {
        System.out.println("It is the player turns");
        System.out.println("Player Hand: " + playerHand);
        System.out.println("New Top Card " + topCard);
        try {

            Thread.sleep(500);

        } catch (InterruptedException e) {

        }
        // create code for placing card on top of card and then taking the card away and
        // changing top card to card just mentioned. play only if an 8, same suit or
        // same rank
        // use Scanner in class and us .replace to replace topCard function P
        int numDrawn = 0;

        while (!validHand(playerHand, topCard) && numDrawn < 5) {

            numDrawn++;

            playerHand = playerHand + getCard() + " ";

            System.out.println("You must draw!");

            System.out.println("New hand: " + playerHand);

            System.out.println("Top card: " + topCard);
            try {

                Thread.sleep(500);

            } catch (InterruptedException e) {

            }

        }
        if (!validHand(playerHand, topCard)) {
            System.out.println("Cannot play. Skipping turn...");
            try {

                Thread.sleep(500);

            } catch (InterruptedException e) {

            }
            return playerHand + "-" + topCard;
        }

        boolean validPlay = false;
        while (!validPlay) {

            String cardPlay = userChoice(new Scanner(System.in));
            if (playerHand.indexOf(cardPlay) == -1) {
                System.out.println("This card is not in your hand!");
                continue; // The command "continue" once reached, will skip everything in the current loop
                          // and continue to the next loop
            }
            String cardSuit = "";
            String cardRank = "";
            if (cardPlay.length() == 2) {
                cardRank = cardPlay.substring(0, 1);
                cardSuit = cardPlay.substring(1, 2);
            } else {
                cardRank = cardPlay.substring(0, 2);
                cardSuit = cardPlay.substring(2, 3);
            }
            String topSuit = "";
            String topRank = "";
            if (topCard.length() == 2) {
                topRank = topCard.substring(0, 1);
                topSuit = topCard.substring(1, 2);
            } else {
                topRank = topCard.substring(0, 2);
                topSuit = topCard.substring(2, 3);
            }
            if (cardRank.equals("8")) {
                playerHand = playACard(cardPlay, playerHand);
                String temp = eightSuit(new Scanner(System.in));
                topCard = cardPlay.substring(0, cardPlay.length() - 1) + temp;
                System.out.println("New Top card is: " + topCard);

                validPlay = true;

            }

            else if (cardRank.equals(topRank) || cardSuit.equals(topSuit)) {
                playerHand = playACard(cardPlay, playerHand);
                topCard = cardPlay;
                validPlay = true;

            } else {
                System.out.println("Your play is against the rules!");
            }

        }

        return playerHand + "-" + topCard;

    }
    /**
     * This method basically sorts out the card played and replaces the extra space with one space so the substrings
     * in the other methods will not mess and for example, if we wanted to loop through the hand, we would have to account
     * for two spaces which makes everything complicated.
     * 
     */
    private static String playACard(String cardPlay, String myHand) {
        String temp = myHand.substring(0, myHand.indexOf(cardPlay));// from 0, to the index of the character before the
                                                                    // second card
        temp += myHand.substring(myHand.indexOf(cardPlay) + cardPlay.length(), myHand.length());// first character of card played, index of
                                                                                                // cardplay + cardplay.length will give us the last position in my hand
                                                                                               
        temp = temp.replace("  ", " ");
        if (temp.substring(0, 1).equals(" ")) {
            temp = temp.substring(1, temp.length());
        }

        return temp;

    }
/**
 * This method simply checks if the card played is valid and if topCard suit and rank are
 * existent in the playerHand
 * 
 */
    private static boolean validHand(String playerHand, String topCard) {
        boolean canPlay = false;

        String topCardSuit = topCard.substring(topCard.length() - 1);

        String topCardRank = topCard.substring(0, topCard.length() - 1);

        if (playerHand.indexOf("8") > -1) {

            canPlay = true;

        }

        if (playerHand.indexOf(topCardSuit) > -1) {

            canPlay = true;

        }

        if (playerHand.indexOf(topCardRank) > -1) {

            canPlay = true;

        }

        return canPlay;

    }
    
    /**
     * This method lets the game know that the entire game is over when a computer or players reaches 100 or greater
     */
    private static boolean gameOver(int userPoints, int computer1Points, int computer2Points) {
        return userPoints >= 100 || computer1Points >= 100 || computer2Points >= 100;
    }
    /**
     * This method creates a card by putting together the getFace method and the getSuit method.
     * 
     */
    private static String getCard() {
        String card = getFace() + getSuit();
        return card;
    }
    /**
     * This method lists out all the possible valid suit options and randomizes through them
     * for the get card function so you do not always get the same suit
     * @return
     */
    private static String getSuit() {
        int suit = (int) (Math.random() * NUM_SUITS);

        if (suit == 0)
            return HEARTS;
        else if (suit == 1)
            return DIAMONDS;
        else if (suit == 2)
            return CLUBS;
        else
            return SPADES;

    }
    /**
     * This method getFace lists out all the valid face cards and randomizes between them
     * for the getCard method so that it will not always get the same face value.
     */
    private static String getFace() {
        int suit = (int) (Math.random() * CARDS_PER_SUIT);
        if (suit >= 2 && suit <= 10)
            return suit + "";
        else if (suit == 1)
            return ACE;
        else if (suit == 11)
            return JACK;
        else if (suit == 12)
            return QUEEN;
        else
            return KING;

    }

}
