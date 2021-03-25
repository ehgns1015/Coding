package Poker;


import java.util.ArrayList;
import java.util.List;

/**
 * Player class
 */
public class Player {
    private boolean win;
    private List<Card> myDeck;
    private List<Card> field;
    private boolean oneMore;
    private boolean die;
    private int mymoney;
    private boolean turn;






    
    /**
     * 생성될때 자신의 덱, 필드, 한번더를 초기값으로 아래처럼 가짐
     */
    Player() {
        myDeck = new ArrayList<>();
        field = new ArrayList<>();
        oneMore = true;
        mymoney = 10000;
    }

    public int getMymoney() {
        return mymoney;
    }

    public void setMymoney(int mymoney) {
        this.mymoney = mymoney;
    }


    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int betting(int bet,int othermoney, Game game) {
        int imsi=othermoney;

        switch (bet) {
            case 1 :
                System.out.println("call");
                mymoney -= othermoney;
                game.setFieldmoney(game.getFieldmoney() + othermoney);
                System.out.println(game.getFieldmoney());
                return imsi;
            case 2 :
                System.out.println("half");
                mymoney -= (game.getFieldmoney() / 2);
                imsi = game.getFieldmoney()/2;
                game.setFieldmoney(game.getFieldmoney()+ game.getFieldmoney() / 2);
                System.out.println(game.getFieldmoney());
                return imsi;
            case 3 :
                System.out.println("full");
                mymoney -= game.getFieldmoney();
                imsi = game.getFieldmoney();
                game.setFieldmoney(game.getFieldmoney() * 2);
                System.out.println(game.getFieldmoney());
                return imsi;
            case 4 :
                System.out.println("die");

                return -1;
            case 5 :
                System.out.println("basic");
                mymoney -= 100;
                game.setFieldmoney(game.getFieldmoney()+ 100 );
                System.out.println(game.getFieldmoney());
                return 100;

            default:
                System.out.println("숫자를 다시 입력하세여");
                return 0;

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
     * getter for oneMore
     * 
     * @return 한번 더
     */
    public boolean isOneMore() {
        return oneMore;
    }

    public void setOneMore(boolean oneMore) {
        this.oneMore = oneMore;
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
     * setter for deck
     * 
     * @param myDeck 새로운 deck
     */
    public void setMyDeck(List<Card> myDeck) {
        this.myDeck = myDeck;
    }

    /**
     * setter for win
     * 
     * @param win 새로 설정할 win
     */
    public void setWin(boolean win) {
        this.win = win;
    }

    public void setDie(boolean die) {
        this.die = die;
    }
}
