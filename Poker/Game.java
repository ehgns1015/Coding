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
 * Game class 실제 게임 구현
 */
public class Game {
    private Player player1;
    private Player player2;
    private int fieldMoney;

    /**
     * Game 생성자 player 두명으로 시작
     */
    Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        fieldMoney = 0;
    }

    /**
     * Helper method for distributing cards 카드를 나눠주는 method 구현
     * 
     * @param list 받을 사람의 deck
     * @param time 횟수
     * @param q    모든 카드 list의 index
     * @param deck 모든 카드 list
     */
    private void distribute(List<Card> list, Queue<Integer> q, List<Card> deck) {
        list.add(deck.get((int) q.poll()));
    }

    /**
     * 누가 이겼는지 판단하는 method
     * 
     * @param player1
     * @param player2
     */
    public void whoIsWinner(Player player1, Player player2) {
        if (!(player1.isDie() && player2.isDie())) {
            player1.setMyDeck(whatYouGot(player1.getMyDeck(), player1.getField()));
            player2.setMyDeck(whatYouGot(player2.getMyDeck(), player2.getField()));
            if (isRSF(player1.getMyDeck()) || isRSF(player2.getMyDeck())) {
                if (isRSF(player1.getMyDeck()) && isRSF(player2.getMyDeck())) {
                    if (player1.getMyDeck().get(player1.getMyDeck().size() - 1).getShape()
                            .compareTo(player2.getMyDeck().get(player2.getMyDeck().size() - 1).getShape()) > 0) {
                        player1.setWin(true);
                    } else {
                        player2.setWin(true);
                    }
                }
            }
            Card p1 = null;
            Card p2 = null;
            String what = "";
            for (int i = 8; i > 0; i--) {
                if (player1.isWin() || player2.isWin()) {
                    break;
                }
                switch (i) {
                case 8:
                    p1 = isSF(player1.getMyDeck());
                    p2 = isSF(player2.getMyDeck());
                    what = "Straight Flush";
                    break;
                case 7:
                    p1 = isFourCard(player1.getMyDeck());
                    p2 = isFourCard(player2.getMyDeck());
                    what = "Four Card";
                    break;
                case 6:
                    p1 = isFullHouse(player1.getMyDeck());
                    p2 = isFullHouse(player2.getMyDeck());
                    what = "Full House";
                    break;
                case 5:
                    p1 = isFlush(player1.getMyDeck());
                    p2 = isFlush(player2.getMyDeck());
                    what = "Flush";
                    break;
                case 4:
                    p1 = isStraight(player1.getMyDeck());
                    p2 = isStraight(player2.getMyDeck());
                    what = "Straight";
                    break;
                case 3:
                    p1 = isTriple(player1.getMyDeck());
                    p2 = isTriple(player2.getMyDeck());
                    what = "Triple";
                    break;
                case 2:
                    p1 = isTwoPair(player1.getMyDeck());
                    p2 = isTwoPair(player2.getMyDeck());
                    what = "Two pairs";
                    break;
                case 1:
                    p1 = isOnePair(player1.getMyDeck());
                    p2 = isOnePair(player2.getMyDeck());
                    what = "One pair";
                    break;
                default:
                    break;
                }
                if (p1 != null || p2 != null) {
                    if (p1 != null && p2 != null) {
                        if (p1.compareTo(p2) > 0) {
                            System.out.println(what + "1");
                            player1.setWin(true);
                        } else {
                            System.out.println(what + "2");
                            player2.setWin(true);
                        }
                    } else if (p2 == null) {
                        System.out.println(what + "3");
                        player1.setWin(true);
                    } else {
                        System.out.println(what + "4");
                        player2.setWin(true);
                    }
                }
                p1 = null;
                p2 = null;
                what = "";
            }
            if (!(player1.isWin() || player2.isWin())) {
                if (player1.getMyDeck().get(player1.getMyDeck().size() - 1)
                        .compareTo(player2.getMyDeck().get(player2.getMyDeck().size() - 1)) > 0) {
                    System.out.println("Top1");
                    player1.setWin(true);
                } else {
                    System.out.println("Top2");
                    player2.setWin(true);
                }
            }
        } else if (player1.isDie()) {
            player2.setWin(true);
        } else {
            player1.setWin(true);
        }
    }

    /**
     * Helper method for rearranging cards 받은 카드를 합쳐서 sort해주는 method
     * 
     * @param deck  player deck
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
            if (deck.get(i).getNumber().equals("10")) {
                shape = deck.get(i).getShape();
                break;
            }
            i++;
        }
        while (i < deck.size() - 1) {
            if (deck.get(i).getShape().equals(shape)) {
                switch (deck.get(i).getNumber()) {
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
                    break;
                }
            }
            if (count == 4) {
                break;
            }
            i++;
        }
        if (i < deck.size() - 1) {
            for (int j = deck.size() - 1; j > i; j--) {
                if (deck.get(j).getShape().equals(shape) && deck.get(j).getNumber().equals("A")) {
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
            if (deck.get(i).getShape().equals(deck.get(i + 1).getShape())
                    && deck.get(i).getNumber().compareTo(deck.get(i + 1).getNumber()) == -1) {
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
        int count = 0;
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
        int count = 0;
        Card max = null;
        for (int i = deck.size() - 1; i > 0; i--) {
            if (deck.get(i).compareTo(deck.get(i - 1)) == 1) {
                if (max == null) {
                    max = deck.get(i);
                }
                count++;
            } else {
                count = 0;
            }
            if (count == 4) {
                return max;
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
        for (int i = 0; i < deck.size() - 2; i++) {
            if (deck.get(i).getNumber().equals(deck.get(i + 1).getNumber())
                    && deck.get(i).getNumber().equals(deck.get(i + 2).getNumber())) {
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
                if (i < deck.size() - 1 && deck.get(i + 1).getNumber().equals(deck.get(i - 1).getNumber())) {
                    count--;
                }
                if (count == 2) {
                    return max;
                }
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
        for (int i = deck.size() - 1; i > 0; i--) {
            if (deck.get(i).getNumber().equals(deck.get(i - 1).getNumber())) {
                return deck.get(i);
            }
        }
        return null;
    }

    /**
     * 실제 game을 실행하는 method
     */
    public void playGame(Scanner scan) {
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
        for (int i = 0; i < 2; i++) {
            distribute(player1.getMyDeck(), order, deck.getDeck());
            distribute(player2.getMyDeck(), order, deck.getDeck());
        }
        for (int i = 0; i < 3; i++) {
            distribute(player1.getField(), order, deck.getDeck());
            distribute(player2.getField(), order, deck.getDeck());
        }
        player1.setMoney(player1.getMoney() - 500);
        player2.setMoney(player2.getMoney() - 500);
        fieldMoney += 1000;
        System.out.println("Player1 Deck: " + player1.getMyDeck());
        System.out.println("Player1: " + player1.getField());
        System.out.println("Player2: " + player2.getField());
        betting(scan, player1, player2);
        if (!(player1.isDie() || player1.isDie())) {
            distribute(player1.getField(), order, deck.getDeck());
            distribute(player2.getField(), order, deck.getDeck());
            System.out.println("Player1 Deck: " + player1.getMyDeck());
            System.out.println("Player1: " + player1.getField());
            System.out.println("Player2: " + player2.getField());
            betting(scan, player1, player2);
        }
        if (!(player1.isDie() || player1.isDie())) {
            distribute(player1.getMyDeck(), order, deck.getDeck());
            distribute(player2.getMyDeck(), order, deck.getDeck());
            System.out.println("Player1 Deck: " + player1.getMyDeck());
            System.out.println("Player1: " + player1.getField());
            System.out.println("Player2: " + player2.getField());
            betting(scan, player1, player2);
        }
        whoIsWinner(player1, player2);
        if (player1.isWin()) {
            System.out.println("Player1 Win");
            player1.setMoney(fieldMoney + player1.getMoney());
        } else {
            System.out.println("Player2 Win");
            player2.setMoney(fieldMoney + player2.getMoney());
        }
        System.out.println(player1.getMyDeck());
        System.out.println(player2.getMyDeck());
        System.out.println(player1.getMoney());
        System.out.println(player2.getMoney());
        player1.setMyDeck(new ArrayList<>());
        player2.setMyDeck(new ArrayList<>());
        player1.setField(new ArrayList<>());
        player2.setField(new ArrayList<>());
        player1.setDie(false);
        player2.setDie(false);
        fieldMoney = 0;
    }

    public void betting(Scanner scan, Player player1, Player player2) {
        int pl1 = 1;
        int pl2 = 0;

        while (!(player1.isDie() || player2.isDie())) {
            System.out.println(fieldMoney);
            if (pl2 != 0) {
                System.out.println("Other player raised: " + pl2);
            }
            if (!(player2.isDie() || pl1 == pl2)) {
                pl1 = player1.betting(scan, this, pl2);
            }
            if (!player1.isDie() && (pl1 > pl2 || pl1 == 0)) {
                pl2 = player2.betting(scan, this, pl1);
            }
            if (pl1 == pl2) {
                break;
            }
        }
    }

    /**
     * getter for fieldMoney
     * 
     * @return fieldMoney
     */
    public int getFieldMoney() {
        return fieldMoney;
    }

    /**
     * setter for fieldMoney
     * 
     * @param fieldMoney 새로 설정할 fieldmoney
     */
    public void setFieldMoney(int fieldMoney) {
        this.fieldMoney = fieldMoney;
    }

    public static void main(String[] args) {
        Game game = new Game(new Player(), new Player());
        Scanner scan = new Scanner(System.in);
        String ans = "yes";
        while (ans.equalsIgnoreCase("yes")) {
            game.playGame(scan);
            System.out.println("다시하기? (yes)");
            ans = scan.next();
        }
    }
}
