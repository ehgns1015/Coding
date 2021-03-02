package src.Poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Game class
 * 실제 게임 구현
 */
public class Game {
    private Player player1;
    private Player player2;

    /**
     * Game 생성자
     * player 두명으로 시작
     */
    Game() {
        player1 = new Player();
        player2 = new Player();
    }

    /**
     * getter for player1
     * 
     * @return player1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * getter for player2
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Helper method for distributing cards
     * 카드를 나눠주는 method 구현
     * 
     * @param list 받을 사람의 deck
     * @param time 횟수
     * @param q 모든 카드 list의 index
     * @param deck 모든 카드 list
     */
    private void distribute(List<Card> list, int time, Queue<Integer> q, List<Card> deck) {
        for (int i = 0; i < time; i++) {
            list.add(deck.get((int)q.poll()));
        }
    }

    /**
     * 누가 이겼는지 판단하는 method
     * 
     * @param player1
     * @param player2
     */
    public void whoIsWinner(Player player1, Player player2) {
        if (player1.isDie()) {
            System.out.println("Player2 wins");
        } else if (player2.isDie()) {
            System.out.println("Player1 wins");
        } else {
            player1.setMyDeck(whatYouGot(player1.getMyDeck(), player1.getField()));
            player2.setMyDeck(whatYouGot(player2.getMyDeck(), player2.getField()));
            if (isRSF(player1.getMyDeck()) || isRSF(player2.getMyDeck())) {
                if (isRSF(player1.getMyDeck()) && isRSF(player2.getMyDeck())) {
                    if (player1.getMyDeck().get(player1.getMyDeck().size() - 1).getShape().compareTo(player2.getMyDeck().get(player2.getMyDeck().size() - 1).getShape()) > 0) {
                        player1.setWin(true);
                    } else {
                        player2.setWin(true);
                    }
                }
            } else if (!(isSF(player1.getMyDeck()) == null && isSF(player2.getMyDeck()) == null)) {
                if (!(isSF(player1.getMyDeck()) == null || isSF(player2.getMyDeck()) == null)) {
                    if (isSF(player1.getMyDeck()).compareTo(isSF(player2.getMyDeck())) > 0) {
                        System.out.println("SF1");
                        player1.setWin(true);
                    } else {
                        System.out.println("SF2");
                        player2.setWin(true);
                    }
                } else if (isSF(player2.getMyDeck()) == null) {
                    System.out.println("SF3");
                    player1.setWin(true);
                } else {
                    System.out.println("SF4");
                    player2.setWin(true);
                }
            } else if (!(isFourCard(player1.getMyDeck()) == null && isFourCard(player2.getMyDeck()) == null)) {
                if (!(isFourCard(player1.getMyDeck()) == null || isFourCard(player2.getMyDeck()) == null)) {
                    if (isFourCard(player1.getMyDeck()).compareTo(isFourCard(player2.getMyDeck())) > 0) {
                        System.out.println("41");
                        player1.setWin(true);
                    } else {
                        System.out.println("42");
                        player2.setWin(true);
                    }
                } else if (isFourCard(player2.getMyDeck()) == null) {
                    System.out.println("43");
                    player1.setWin(true);
                } else {
                    System.out.println("44");
                    player2.setWin(true);
                }
            } else if (!(isFullHouse(player1.getMyDeck()) == null && isFullHouse(player2.getMyDeck()) == null)) {
                if (!(isFullHouse(player1.getMyDeck()) == null || isFullHouse(player2.getMyDeck()) == null)) {
                    if (isFullHouse(player1.getMyDeck()).compareTo(isFullHouse(player2.getMyDeck())) > 0) {
                        System.out.println("Full1");
                        player1.setWin(true);
                    } else {
                        System.out.println("Full2");
                        player2.setWin(true);
                    }
                } else if (isFullHouse(player2.getMyDeck()) == null) {
                    System.out.println("Full3");
                    player1.setWin(true);
                } else {
                    System.out.println("Full4");
                    player2.setWin(true);
                }
            } else if (!(isFlush(player1.getMyDeck()) == null && isFlush(player2.getMyDeck()) == null)) {
                if (!(isFlush(player1.getMyDeck()) == null || isFlush(player2.getMyDeck()) == null)) {
                    if (isFlush(player1.getMyDeck()).compareTo(isFlush(player2.getMyDeck())) > 0) {
                        System.out.println("Flu1");
                        player1.setWin(true);
                    } else {
                        System.out.println("Flu2");
                        player2.setWin(true);
                    }
                } else if (isFlush(player2.getMyDeck()) == null) {
                    System.out.println("Flu3");
                    player1.setWin(true);
                } else {
                    System.out.println("Flu4");
                    player2.setWin(true);
                }
            } else if (!(isStraight(player1.getMyDeck()) == null && isStraight(player2.getMyDeck()) == null)) {
                if (!(isStraight(player1.getMyDeck()) == null || isStraight(player2.getMyDeck()) == null)) {
                    if (isStraight(player1.getMyDeck()).compareTo(isStraight(player2.getMyDeck())) > 0) {
                        System.out.println("Tri1");
                        player1.setWin(true);
                    } else {
                        System.out.println("Tri2");
                        player2.setWin(true);
                    }
                } else if (isStraight(player2.getMyDeck()) == null) {
                    System.out.println("Tri3");
                    player1.setWin(true);
                } else {
                    System.out.println("Tri4");
                    player2.setWin(true);
                }
            } else if (!(isTriple(player1.getMyDeck()) == null && isTriple(player2.getMyDeck()) == null)) {
                if (!(isTriple(player1.getMyDeck()) == null || isTriple(player2.getMyDeck()) == null)) {
                    if (isTriple(player1.getMyDeck()).compareTo(isTriple(player2.getMyDeck())) > 0) {
                        System.out.println("Tri1");
                        player1.setWin(true);
                    } else {
                        System.out.println("Tri2");
                        player2.setWin(true);
                    }
                } else if (isTriple(player2.getMyDeck()) == null) {
                    System.out.println("Tri3");
                    player1.setWin(true);
                } else {
                    System.out.println("Tri4");
                    player2.setWin(true);
                }
            } else if (!(isTwoPair(player1.getMyDeck()) == null && isTwoPair(player2.getMyDeck()) == null)) {
                if (!(isTwoPair(player1.getMyDeck()) == null || isTwoPair(player2.getMyDeck()) == null)) {
                    if (isTwoPair(player1.getMyDeck()).compareTo(isTwoPair(player2.getMyDeck())) > 0) {
                        System.out.println("Two1");
                        player1.setWin(true);
                    } else {
                        System.out.println("Two2");
                        player2.setWin(true);
                    }
                } else if (isTwoPair(player2.getMyDeck()) == null) {
                    System.out.println("Two3");
                    player1.setWin(true);
                } else {
                    System.out.println("Two4");
                    player2.setWin(true);
                }
            } else if (!(isOnePair(player1.getMyDeck()) == null && isOnePair(player2.getMyDeck()) == null)) {
                if (!(isOnePair(player1.getMyDeck()) == null || isOnePair(player2.getMyDeck()) == null)) {
                    if (isOnePair(player1.getMyDeck()).compareTo(isOnePair(player2.getMyDeck())) > 0) {
                        System.out.println("One1");
                        player1.setWin(true);
                    } else {
                        System.out.println("One2");
                        player2.setWin(true);
                    }
                } else if (isOnePair(player2.getMyDeck()) == null) {
                    System.out.println("One3");
                    player1.setWin(true);
                } else {
                    System.out.println("One4");
                    player2.setWin(true);
                }
            } else {
                if (player1.getMyDeck().get(player1.getMyDeck().size() - 1).compareTo(player2.getMyDeck().get(player2.getMyDeck().size() - 1)) > 0) {
                    System.out.println("Top1");
                    player1.setWin(true);
                } else {
                    System.out.println("Top2");
                    player2.setWin(true);
                }
            }
        }
    }

    /**
     * Helper method for rearranging cards
     * 받은 카드를 합쳐서 sort해주는 method
     * 
     * @param deck player deck
     * @param field player field
     * @return 종합되어 정렬된 deck
     */
    private List<Card> whatYouGot(List<Card> deck, List<Card> field) {
        List<Card> temp = new ArrayList<>();
        for (Card card : deck) {
            temp.add(card);
        }
        for (Card card : field) {
            temp.add(card);
        }
        Collections.sort(temp);
        return temp;
    }

    /**
     * Royal Back Straight Flush인지 확인하는 method
     * 
     * @param deck player deck
     * @return Royal Back Straight Flush라면 그 중 가장 높은 카드
     */
    public boolean isRBSF(List<Card> deck) {
        int count = 0;
        String shape = "";
        int i = 0;
        while (i < deck.size() - 1) {
            if (deck.get(i).getShape().equals(deck.get(i + 1).getShape())) {
                switch(deck.get(i).getNumber()) {
                    case "5":
                        count++;
                        break;
                    case "4":
                        count++;
                        break;
                    case "3":
                        count++;
                        break;
                    case "2":
                        count++;
                        break;
                    default:
                        continue;
                }
            }
            if (count == 3) {
                shape = deck.get(i).getShape();
                break;
            }
        }
        if (i < deck.size() - 1) {
            for (int j = deck.size() - 1; j > i; j--) {
                if (deck.get(i).getShape().equals(shape) && deck.get(i).getNumber().equals("A")) {
                    return true;
                }
            }
        } 
        return false;
    }

    /**
     * Royal Straight Flush인지 확인하는 method
     * 
     * @param deck player deck
     * @return Royal Straight Flush라면 그 중 가장 높은 카드
     */
    public boolean isRSF(List<Card> deck) {
        int count = 0;
        String shape = "";
        int i = 0;
        while (i < deck.size() - 1) {
            if (deck.get(i).getShape().equals(deck.get(i + 1).getShape())) {
                switch(deck.get(i).getNumber()) {
                    case "K":
                        count++;
                        break;
                    case "Q":
                        count++;
                        break;
                    case "J":
                        count++;
                        break;
                    case "10":
                        count++;
                        break;
                    default:
                        continue;
                }
            }
            if (count == 3) {
                shape = deck.get(i).getShape();
                break;
            }
        }
        if (i < deck.size() - 1) {
            for (int j = deck.size() - 1; j > i; j--) {
                if (deck.get(i).getShape().equals(shape) && deck.get(i).getNumber().equals("A")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Straight Flush인지 확인하는 method
     * 
     * @param deck player deck
     * @return Straight Flush라면 그 중 가장 높은 카드
     */
    public Card isSF(List<Card> deck) {
        int count = 0;
        for (int i = 0; i < deck.size() - 1; i++) {
            if (deck.get(i).getShape().equals(deck.get(i + 1).getShape()) && deck.get(i).getNumber().compareTo(deck.get(i + 1).getNumber()) == -1) {
                count++;
            }
            if (count == 4) {
                return deck.get(i + 1);
            }
        }
        return null;
    }

    /**
     * FourCard인지 확인하는 method
     * 
     * @param deck player deck
     * @return FourCard라면 그 중 가장 높은 카드
     */
    public Card isFourCard(List<Card> deck) {
        int count = 0;
        for (int i = 0; i < deck.size() - 1; i++) {
            if (deck.get(i).getNumber().equals(deck.get(i + 1).getNumber())) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) {
                return deck.get(i + 1);
            }
        }
        return null;
    }

    /**
     * FullHouse인지 확인하는 method
     * 
     * @param deck player deck
     * @return FullHouse라면 Triple 중 가장 높은 카드
     */
    public Card isFullHouse(List<Card> deck) {
        Card temp = isTriple(deck);
        if (temp != null && isTwoPair(deck) != null) {
            return temp;
        }
        return null;
    }

    /**
     * Flush인지 확인하는 method
     * 
     * @param deck player deck
     * @return Flush라면 그 중 가장 높은 카드
     */
    public Card isFlush(List<Card> deck) {
        int count = 4;
        for (int i = 0; i < deck.size() - 1; i++) {
            if (deck.get(i).getShape().equals(deck.get(i + 1).getShape())) {
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return deck.get(i + 1);
            }
        }
        return null;
    }

    /**
     * Straight인지 확인하는 method
     * 
     * @param deck player deck
     * @return Straight라면 그 중 가장 높은 카드
     */
    public Card isStraight(List<Card> deck) {
        int count = 4;
        for (int i = 0; i < deck.size() - 1; i++) {
            if (deck.get(i).getNumber().compareTo(deck.get(i + 1).getNumber()) == -1) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) {
                return deck.get(i + 1);
            }
        }
        return null;
    }

    /**
     * Triple인지 확인하는 method
     * 
     * @param deck player deck
     * @return Triple이라면 그 중 가장 높은 카드
     */
    public Card isTriple(List<Card> deck) {
        int count = 0;
        for (int i = 0; i < deck.size() - 1; i++) {
            if (deck.get(i).getNumber().equals(deck.get(i + 1).getNumber())) {
                count++;
            } else {
                count = 0;
            }
            if (count == 2) {
                return deck.get(i + 1);
            }
        }
        return null;
    }

    /**
     * TwoPair인지 확인하는 method
     * 
     * @param deck player deck
     * @return TwoPair라면 그 중 가장 높은 카드
     */
    public Card isTwoPair(List<Card> deck) {
        int count = 0;
        Card max = null;
        for (int i = deck.size() - 1; i > 0; i--) {
            if (deck.get(i).getNumber().equals(deck.get(i - 1).getNumber())) {
                if (max == null) {
                    max = deck.get(i);
                }
                count++;
            }
            if (count == 2 && !deck.get(i - 1).getNumber().equals(deck.get(i + 1).getNumber())) {
                return max;
            }
        }
        return null;
    }

    /**
     * OnePair인지 확인하는 method
     * 
     * @param deck player deck
     * @return OnePair라면 그 중 가장 높은 카드
     */
    public Card isOnePair(List<Card> deck) {
        int count = 0;
        for (int i = 0; i < deck.size() - 1; i++) {
            if (deck.get(i).getNumber().equals(deck.get(i + 1).getNumber())) {
                return deck.get(i + 1);
            }
        }
        return null;
    }

    /**
     * 실제 game을 실행하는 method
     */
    public void playGame() {
        Game game = new Game();
        Deck deck = new Deck();
        Set<Integer> set = new HashSet<>();
        while (set.size() < 14) {
            set.add((int) (Math.random() * 52));
        }
        Iterator<Integer> iter = set.iterator();
        Queue<Integer> order = new LinkedList<>();
        while (iter.hasNext()) {
            order.add(iter.next());
        }
        game.distribute(game.getPlayer1().getMyDeck(), 2, order, deck.getDeck());
        game.distribute(game.getPlayer2().getMyDeck(), 2, order, deck.getDeck());
        game.distribute(game.getPlayer1().getField(), 3, order, deck.getDeck());
        game.distribute(game.getPlayer2().getField(), 3, order, deck.getDeck());
        System.out.println("Player1 Deck: " + game.getPlayer1().getMyDeck());
        System.out.println("Player1: " + game.getPlayer1().getField());
        System.out.println("Player2: " + game.getPlayer2().getField());
        if (game.player1.isOneMore() && game.player2.isOneMore()) {
            game.distribute(game.getPlayer1().getField(), 1, order, deck.getDeck());
            game.distribute(game.getPlayer2().getField(), 1, order, deck.getDeck());
        } else {
            game.whoIsWinner(game.getPlayer1(), game.getPlayer2());
        }
        System.out.println("Player1 Deck: " + game.getPlayer1().getMyDeck());
        System.out.println("Player1: " + game.getPlayer1().getField());
        System.out.println("Player2: " + game.getPlayer2().getField());
        if (game.player1.isOneMore() && game.player2.isOneMore()) {
            game.distribute(game.getPlayer1().getMyDeck(), 1, order, deck.getDeck());
            game.distribute(game.getPlayer2().getMyDeck(), 1, order, deck.getDeck());
        }
        game.whoIsWinner(game.getPlayer1(), game.getPlayer2());
        if (game.getPlayer1().isWin()) {
            System.out.println("Player1 Win");
        } else {
            System.out.println("Player2 Win");
        }
        System.out.println(game.getPlayer1().getMyDeck());
        System.out.println(game.getPlayer2().getMyDeck());
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scan = new Scanner(System.in);
        String ans = "yes";
        while(ans.equalsIgnoreCase("yes")) {
            game.playGame();
            System.out.println("다시하기? (yes)");
            ans = scan.next();
        }
    }    
}
