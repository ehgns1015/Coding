package Poker;

import java.security.spec.RSAOtherPrimeInfo;
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
    private int imsimoney;
    private int fieldmoney;
    private boolean turn;
    private int bet;

    /**
     * Game 생성자 player 두명으로 시작
     */
    Game(Player player1,Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getImsimoney() {
        return imsimoney;
    }

    public void setImsimoney(int imsimoney) {
        this.imsimoney = imsimoney;
    }

    public int getFieldmoney() {
        return fieldmoney;
    }

    public void setFieldmoney(int fieldmoney) {
        this.fieldmoney = fieldmoney;
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
     * 
     * @return player2
     */
    public Player getPlayer2() {
        return player2;
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

            list.add(deck.get(q.poll()));


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
        Game game = new Game(player1,player2);
        Poker.Deck deck = new Poker.Deck();
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
            game.distribute(game.getPlayer1().getMyDeck(), order, deck.getDeck());
            game.distribute(game.getPlayer2().getMyDeck(), order, deck.getDeck());
        }
        for (int i = 0; i < 3; i++) {
            game.distribute(game.getPlayer1().getField(), order, deck.getDeck());
            game.distribute(game.getPlayer2().getField(), order, deck.getDeck());
        }
        System.out.println("Player1 Deck: " + game.getPlayer1().getMyDeck());
        System.out.println("Player1: " + game.getPlayer1().getField());
        System.out.println("Player2: " + game.getPlayer2().getField());

            playingbet(scan, game.getPlayer1(), game.getPlayer2(),game);
        if (game.player1.isOneMore() && game.player2.isOneMore()&&this.player1.isDie()==false && this.player2.isDie()==false) {
            game.distribute(game.getPlayer1().getField(), order, deck.getDeck());
            game.distribute(game.getPlayer2().getField(), order, deck.getDeck());
            System.out.println("Player1 Deck: " + game.getPlayer1().getMyDeck());
            System.out.println("Player1: " + game.getPlayer1().getField());
            System.out.println("Player2: " + game.getPlayer2().getField());
            playingbet(scan, game.getPlayer1(), game.getPlayer2(),game);
        } else {
            game.whoIsWinner(game.getPlayer1(), game.getPlayer2());
        }


        if (game.player1.isOneMore() && game.player2.isOneMore()&&this.player1.isDie()==false && this.player2.isDie()==false) {
            game.distribute(game.getPlayer1().getMyDeck(), order, deck.getDeck());
            game.distribute(game.getPlayer2().getMyDeck(), order, deck.getDeck());
            System.out.println("Player1 Deck: " + game.getPlayer1().getMyDeck());
            System.out.println("Player1: " + game.getPlayer1().getField());
            System.out.println("Player2: " + game.getPlayer2().getField());
            playingbet(scan, game.getPlayer1(), game.getPlayer2(),game); //
            game.whoIsWinner(game.getPlayer1(), game.getPlayer2());
        } else {
            game.whoIsWinner(game.getPlayer1(), game.getPlayer2());
        }


        if (this.getPlayer1().isWin()) {
            this.getPlayer1().setMymoney(this.getFieldmoney() + this.getPlayer1().getMymoney());
            System.out.println("Player1 Win");
        } else {
            this.getPlayer2().setMymoney(this.getFieldmoney() + this.getPlayer2().getMymoney());
            System.out.println("Player2 Win");
        }

        System.out.println(this.getFieldmoney());
        System.out.println("player1 : " + game.getPlayer1().getMymoney() +"player2 : " + game.getPlayer2().getMymoney());

        System.out.println("Player1 Deck: " + game.getPlayer1().getMyDeck());
        System.out.println("Player1: " + game.getPlayer1().getField());
        System.out.println("Player2: " + game.getPlayer2().getField());

        System.out.println(game.getPlayer1().getMyDeck());
        System.out.println(game.getPlayer2().getMyDeck());

    }

    public void playingbet(Scanner scan,Player player1,Player player2,Game game) {
        int p1=0;
        int p2=0;
        int count =0;

        while (count < 4 || player1.isDie() == false || player2.isDie() == false || (player1.getMymoney() >0 && player2.getMymoney()>0)) {
            System.out.println("What do you want");
            System.out.println("1.call  2.half  3.full  4.die 5.basic");

            bet = scan.nextInt();
            p1 = player1.betting(bet,p2,this);
            if (bet == 4) {
                player1.setDie(true);
                break;
            } else if (count == 3) {
                bet = 1;
                System.out.println("count == 3 you are select call");
            } else count++;

            bet = scan.nextInt();
            p2 = player2.betting(bet,p1,this);
            if (bet == 4) {
                player1.setDie(true);
                break;
            } else if (count == 3) {
                bet = 1;
                System.out.println("count == 3 you are select call");
            } else count++;

            if (p1 == -1 || p2 == -1) {
                System.out.println("죽었습니다");
                return;
            }
            if (player1.isDie() == true || player2.isDie() == true) {
                whoIsWinner(player1,player2);
                break;
            }
            if (p1 == p2 && this.getFieldmoney()>200) {
                p1 =0;
                p2=0;
                player1.setOneMore(true);
                player2.setOneMore(true);
                break;
            }


        }





    }
    public static void main(String[] args) {
        Game game = new Game(new Player(),new Player());
        Scanner scan = new Scanner(System.in);
        String ans = "yes";
        while (ans.equalsIgnoreCase("yes")) {
            game.playGame(scan);
            System.out.println("regame? (yes)");
            ans = scan.next();
        }
    }
}
