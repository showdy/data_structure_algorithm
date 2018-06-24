package queue;

/**
 * 链表实现的队列: 由于队列的操作只是在对头和队尾, 就不需要虚拟结点了,
 * 另外对头插入和删除方便, 而队尾不方便, 为了使得队尾插入方便, 保存一个队尾
 * 结点变量, 这样子可以链表头作为对头, 链表尾作为队尾插入元素.
 *
 * @param <E>
 */
public class LinkedListQueue<E> implements Queue<E> {

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

    private Node<E> head;

    private Node<E> tail;

    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node<>(e);
            head = tail;
        } else {
            tail.next = new Node<>(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Empty Queue cannot dequeue");
        }
        Node<E> node = head;
        head = head.next;
        node.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return node.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Empty Queue cannot dequeue");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front ");
        for (Node<E> cur = head; cur != null; cur = cur.next) {
            sb.append(cur + "->");
        }
        sb.append("NULL tail");
        return sb.toString();
    }

    public static void main(String[] args){
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
