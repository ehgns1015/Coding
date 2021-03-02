package src.Poker;


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
    
    /**
     * 생성될때 자신의 덱, 필드, 한번더를 초기값으로 아래처럼 가짐
     */
    Player() {
        myDeck = new ArrayList<>();
        field = new ArrayList<>();
        oneMore = true;
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

}
