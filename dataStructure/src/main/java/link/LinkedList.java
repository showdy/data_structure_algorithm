package link;

/**
 * 链表--动态线性数据结构,不能随机访问
 * <p>
 * +++++++      +++++++++
 * | null |     |   0   |
 * | node |---> |  node |----> ....--->NULL
 * +++++++      ++++++++
 *
 * @param <E>
 */
public class LinkedList<E> {
    /**
     * 单链表
     *
     * @param <E>
     */
    private static class Node<E> {
        public E e;
        public Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

//    private Node<E> head; //链表头,第一个节点

    private Node<E> dummyHead; //虚拟头结点--链表中始终存在的结点, 不存储数据

    private int size;

    public LinkedList() {
        dummyHead = new Node<>(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    //非常规操作
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index illegal!");
        }
        Node<E> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            //找到插入索引前一个节点
            pre = pre.next;
        }
//            Node<E> node = new Node<>(e);
//            node.next = pre.next;
//            pre.next = node;
        pre.next = new Node<>(e, pre.next);
        size++;

    }

    public void addFirst(E e) {
//        Node<E> node= new Node<>(e);
//        node.next=head;
//        head=node;
//        head = new Node<>(e, head);
//        size++;
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }
    //O(n)
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index illegal!");
        }

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }
    //O(n)
    //非常规操作
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index illegal!");
        }

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }
    //O(n)
    public boolean contains(E e) {
        Node<E> cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            } else {
                cur = cur.next;
            }
        }
        return false;
    }

    //O(n/2) == O(n)
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index illegal!");
        }
        Node<E> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node<E> node = pre.next;
        pre.next = node.next;
        node.next = null;
        size--;
        return node.e;
    }
    //O(1)
    public E removeFirst() {
        return remove(0);
    }
    //O(n)
    public E removeLast() {
        return remove(size-1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        Node cur = dummyHead.next;
//        while (cur != null) {
//            sb.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node<E> cur = dummyHead.next; cur != null; cur = cur.next) {
            sb.append(cur + "->");
        }

        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 666);
        System.out.println(linkedList);
        linkedList.set(3, 99);
        System.out.println(linkedList);
        linkedList.remove(1);
        System.out.println(linkedList);
        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
