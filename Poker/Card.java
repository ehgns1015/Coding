package src.Poker;

/**
 * Card class compareTo 구현을 위해 Comparable<Card> implements
 * 
 */
public class Card implements Comparable<Card>{
    private String shape;
    private String number;
    /**
     * Card 초기 생성자
     * 매개변수 shape와 number를 초기값으로 함
     */
    public Card(String shape, String number) {
        this.shape = shape;
        this.number = number;
    }

    /**
     * getter for number
     * 
     * @return 카드 숫자
     */
    public String getNumber() {
        return number;
    }

    /**
     * getter for shape
     * 
     * @return 카드 모양
     */
    public String getShape() {
        return shape;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub        
        return "{ " + shape + ", " + number + " }";
    }

    @Override
    public int compareTo(Card o) {
        // TODO Auto-generated method stub
        int myNum = compareNum(this.number);
        int otherNum = compareNum(o.number);
        if (myNum == otherNum) {
            if ((this.shape.equals("space") && !o.shape.equals("space")) || (this.shape.equals("diamond") && !o.shape.equals("space")) || (this.shape.equals("diamond") && !o.shape.equals("diamond")) || (this.shape.equals("heart") && o.shape.equals("clover"))) {
                return myNum;
            } else if((this.shape.equals("diamond") && o.shape.equals("space")) || (this.shape.equals("heart") && !o.shape.equals("heart")) || (this.shape.equals("heart") && !o.shape.equals("clover")) || (this.shape.equals("clover") && !o.shape.equals("clover"))) {
                return myNum * -1;
            } else {
                return 0;
            }
        } else {
            return myNum - otherNum;
        }
    }

    /**
     * Helper Method for Card Number
     * 카드 숫자를 int형으로 바꿔주는 method
     * 
     * @param number 카드 숫자
     * @return int 카드 숫자
     */
    private int compareNum(String number) {
        switch (number) {
            case "A":
                return 14;
            case "K":
                return 13;
            case "Q":
                return 12;
            case "J":
                return 11;
            default:
                return Integer.parseInt(number);
        }
    }
}
