package src.Poker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Player class
 */
public class Player {
    private boolean win;
    private List<Card> myDeck;
    private List<Card> field;
    private boolean die;
    private int money;

    /**
     * 생성될때 자신의 덱, 필드, 한번더를 초기값으로 아래처럼 가짐
     */
    Player() {
        myDeck = new ArrayList<>();
        field = new ArrayList<>();
        money = 20000;
    }

    public int betting(Scanner scan, Game game, int diff) {
        int ans = 0;
        while (true) {
            if (money <= 0) {
                System.out.println("You don't have money");
                this.setDie(true);
                return 0;
            } else {
                System.out.println("Choose how much you want to bet");
                System.out.println("[1] call, [2] half, [3] raise, [4] die");
                ans = scan.nextInt();
                switch (ans) {
                case 1:
                    game.setFieldMoney(game.getFieldMoney() + diff);
                    money -= diff;
                    return diff;
                case 2:
                    int half = game.getFieldMoney() / 2;
                    game.setFieldMoney(game.getFieldMoney() + half);
                    money -= half;
                    return half;
                case 3:
                    System.out.println("How much you want to raise");
                    int raise = scan.nextInt();
                    if (raise <= diff || raise > game.getFieldMoney() * 2) {
                        System.out.println(
                                "You should bet more than other's betting or less than 2 times of field money");
                    } else {
                        game.setFieldMoney(game.getFieldMoney() + raise);
                        money -= raise;
                        return raise;
                    }
                    break;
                case 4:
                    setDie(true);
                    return 0;
                default:
                    break;
                }
            }
        }
    }

    /**
     * getter for win
     * 
     * @return win
     */
    public boolean isWin() {
        return win;
    }

    /**
     * getter for field
     * 
     * @return field list
     */
    public List<Card> getField() {
        return field;
    }

    /**
     * getter for deck
     * 
     * @return deck list
     */
    public List<Card> getMyDeck() {
        return myDeck;
    }

    /**
     * getter for die
     * 
     * @return 다이
     */
    public boolean isDie() {
        return die;
    }

    /**
     * setter for die
     * 
     * @param die player die 상태
     */
    public void setDie(boolean die) {
        this.die = die;
    }

    /**
     * setter for deck
     * 
     * @param myDeck 새로운 deck
     */
    public void setMyDeck(List<Card> myDeck) {
        this.myDeck = myDeck;
    }

    /**
     * setter for field
     * 
     * @param field 새로운 field
     */
    public void setField(List<Card> field) {
        this.field = field;
    }

    /**
     * setter for win
     * 
     * @param win 새로 설정할 win
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    /**
     * getter for money
     * 
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     * setter for money
     * 
     * @param money 새로 설정할 money
     */
    public void setMoney(int money) {
        this.money = money;
    }
}
