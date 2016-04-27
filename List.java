import java.util.Iterator;

/**
 * Created by cubemaster on 4/25/16.
 */
public class List<T> implements Iterable<T> {
    private Node<T> head;
    private int size = 0;
    public List() {
        head = null;
    }

    public void add(T t) {
        if(head == null) {
            head = new Node<>(t);
            size++;
        }
        Node<T> current = head;
        while(current.getNext() != null)
            current = current.getNext();
        current.setNext(new Node<T>(t));
        size++;
    }

    public void remove(T t) {
        if(head == null)
            throw new IndexOutOfBoundsException();
        if(t.equals(head.getValue())) {
            head = head.getNext();
            size--;
        }
        Node<T> current = head;
        while(current.getNext() != null && !current.getNext().getValue().equals(t))
            current = current.getNext();
        if(current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            size--;
        }
    }

//    public T get(int i) {
//        if(i >= size || head == null)
//            throw new IndexOutOfBoundsException();
//        Node<T> current = head;
//        int count = 0;
//        while(current.getNext() != null) {
//            current = current.getNext();
//            count++;
//            if(count == i)
//                break;
//        }
//        if(count < i)
//            throw new IndexOutOfBoundsException();
//        return current.getValue();
//    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null && current.next != null;
            }

            @Override
            public T next() {
                return (current = current.next).getValue();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> it = iterator();
        if(it.hasNext())
            sb.append(it.next());
        else
            return "[]";
        while(it.hasNext()) {
            sb.append(", ");
            sb.append(it.next());
        }
        return sb.append("]").toString();
    }

    private static class Node<T> {
        private Node<T> next;
        private T t;
        public Node(T t) {
            next = null;
            this.t = t;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public T getValue() {
            return t;
        }

        @Override
        public String toString() {
            return t.toString();
        }
    }
}
