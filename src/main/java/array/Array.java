package array;

/**
 * 动态数组: 增删改查
 */
public class Array<E> {

    /*静态初始数组*/
    private E[] data;

    /*数组长度*/
    private int size;


    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //O(1)
    public void addLast(E element) {
        add(size, element);
    }

    //O(n)
    public void addFirst(E element) {
        add(0, element);
    }

    //O(n/2)==O(n)
    public void add(int index, E element) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add last element failed, index illegal!");
        }

//        if (size == data.length) {
//            throw new IllegalArgumentException("Add last element failed, Arrays is full!");
//        }

        //使用动态数组--java实现中采用1.5倍
        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index illegal!");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }


    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index illegal!");
        }
        data[index] = element;
    }

    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param element 元素
     * @return index 元素的索引,-1表示不存在
     */
    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    //删除
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index illegal!");
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;//loitering objects !=memory leak

        //缩容
        if (size == data.length / 2) {
            resize(data.length / 2);
        }

        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E element) {
        int index = find(element);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array:size=%d,capacity=%d\n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    public Array() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //===========================私有辅助方法=============

    //策略: 在于扩容大小的抉择
    private void resize(int capacity) {
        //使用新数组,将所有元素复制过去
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(20);
        for (int i = 0; i < 10; i++) {
            arr.addFirst(i);
        }
        arr.addLast(-1);
        arr.add(2, 100);
        System.out.println(arr);
        arr.remove(2);
        System.out.println(arr);
        arr.removeElement(10);
        System.out.println(arr);
        arr.removeLast();
        System.out.println(arr);

        Array<Integer> arrs = new Array<>();
        for (int i = 0; i < 20; i++) {
            arrs.addLast(i);
        }
        System.out.println(arrs);

        for (int i = 0; i < 11; i++) {
            arrs.removeLast();
        }
        System.out.println(arrs);
    }
}
