package stack;

import array.Array;

/**
 * 数组实现的栈
 */
public class ArrayStack<E> implements Stack<E> {

    //底层实现数组
    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }


    public int getCapacity() {
        return array.getCapacity();
    }

    //进栈 O(1)
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    //出栈 O(1)
    @Override
    public E pop() {
        return array.removeLast();
    }

    //查看栈顶 O(1)
    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack:[");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize() - 1) {
                sb.append(",");
            }
        }
        sb.append("] top");
        return sb.toString();
    }

    public static void main(String[] args) {
        //..
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
    }
}
