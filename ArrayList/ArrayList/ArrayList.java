package src.ArrayList;

/**
 * ArrayList 구현.
 *
 * @author Dohun Yeo
 * @userid dyeo8
 * @version 1.0
 */
public class ArrayList<T> {

    // instance변수 수정 및 추가 금지.
    private T[] backingArray;
    private int size;

    /**
     * ArrayList생성자 호출시 backingArray용량 초기설정.
     *
     * 변수 수정 금지!!!!!!!!!!!!!!!!!!
     */
    public static final int INITIAL_CAPACITY = 9;

    /**
     * ArrayList생성자 정의.
     *
     * Java는 일반적으로 generic타입 array생성을 지원하지 않는다 그래서 Object array생성 후 T[]타입으로 cast해주어라
     */
    public ArrayList() {

    }

    /**
     * element를 특정 index에 추가하라
     *
     * 기억해야 될 것은 이 method는 elements들을 추가시에는 필요하다면 이동시켜주어야 한다.
     *
     * index {@code size}에 추가할시에는 O(1), 다른 모든경우는 O(n).
     *
     * @param index 새로운 element를 추가할 index
     * @param data  새로 list에 추가할 data
     * @throws IndexOutOfBoundsException index가 음수이거나 size보다 클때
     * @throws IllegalArgumentException  data가 null일때
     */
    public void addAtIndex(int index, T data) {

    }

    /**
     * 주어진 data를 list 앞에 추가.
     *
     * 기억해야 될 것은 이 method는 elements들을 필히 이동시켜주어야 한다.
     * 
     * 효율성 O(n).
     *
     * @param data list에서 추가할 data
     * @throws IllegalArgumentException data가 null이면
     */
    public void addToFront(T data) {
    }

    /**
     * 주어진 data를 list 뒤에 추가.
     *
     * 기억해야 될 것은 이 method는 elements들을 이동시킬 필요가 없다.
     * 
     * 효율성 O(1).
     *
     * @param data list에서 추가할 data
     * @throws IllegalArgumentException data가 null이면
     */
    public void addToBack(T data) {
    }

    /**
     * index{@code index}에 위치한 data를 지우고 반환하라
     *
     * 기억해야 될 것은 이 method는 elements들을 추가시에는 필요하다면 이동시켜주어야 한다.
     *
     * index {@code size - 1}에서 삭제할시에는 O(1), 다른 모든경우는 O(n).
     *
     * @param index 접근할 index
     * @return index에 삭제하기 전의 data
     * @throws IndexOutOfBoundsException index가 음수이거나 size이상일때
     */
    public T removeAtIndex(int index) {
        return null;
    }

    /**
     * list 맨 앞의 data를 지우고 반환하라
     *
     * 기억해야 될 것은 이 method는 elements들을 필히 이동시켜주어야 한다.
     *
     * 효율성 O(n).
     *
     * @return list 맨 앞의 data, 만약 list가 비었다면 null
     */
    public T removeFromFront() {
        return null;
    }

    /**
     * list 맨 뒤의 data를 지우고 반환하라
     *
     * 기억해야 될 것은 이 method는 elements들을 이동시킬 필요가 없다.
     *
     * 효율성 O(1).
     *
     * @return list 맨 뒤의 data, 만약 list가 비었다면 null
     */
    public T removeFromBack() {
        return removeAtIndex(size - 1);
    }

    /**
     * 주어진 index의 data를 반환하라
     *
     * 효율성 O(1).
     *
     * @param index 접근할 index
     * @return index에 위치한 data
     * @throws IndexOutOfBoundsException index가 음수이거나 size이상일때
     */
    public T get(int index) {
        return null;
    }

    /**
     * 선택사항!!!!!!!!!!!!!!!!!!!! 주어진 data가 list의 어느 index에 위치한지 찾고 반환하라
     *
     * 만약 중복되는 data가 있을 시, 가장 뒤에 위치한 index를 반환하라.
     *
     * 값 비교시 신중히 생각해야 할 것은 Data type이 무엇인지 꼭 확인 후 이행할 것
     *
     * 효율성 O(n), 고려해야할 사항은 list의 어느 시작점 부터 찾아야 하는것 이다
     *
     * @param data 찾을 data
     * @return 가장 뒤에 있는 data, data가 없을 시 -1
     * @throws IllegalArgumentException data가 null이면
     */
    public int lastIndexOf(T data) {
        return 0;
    }

    /**
     * list가 비어있는지 확인하라
     *
     * 효율성 O(1).
     *
     * @return 비었다면 true, 아니면 false
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * list초기화. 초기화 후 초기설정값으로 backingArray 설정
     *
     * 효율성O(1).
     */
    public void clear() {

    }

    /**
     * list의 size 반환
     *
     *
     * @return list의 size
     */
    public int size() {
        // method 수정 금지!
        return size;
    }

    /**
     * list의 backingArray 반환
     *
     *
     * @return backingArray
     */
    public Object[] getBackingArray() {
        // method 수정 금지!
        return new Object[5];
    }
}