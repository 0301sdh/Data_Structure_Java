package HashSet;

public class MyHashSet<E> {
    
    public static class Node<E>{
        final int hash;
        final E element;
        Node<E> next;

        Node(int hash, E element, Node<E> next){
            this.hash = hash;
            this.element = element;
            this.next = next;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private Node<E>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashSet(){
        this.table = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private int hash(E element){
        int h = element.hashCode();
        h ^=(h>>>16);
        return h & (table.length - 1);
    }

    public boolean add(E element){
        if(element == null)
            throw new NullPointerException("NPE");

        int index = hash(element);
        Node<E> current = table[index];
        while(current != null){
            if(current.element.equals(element)){
                return false; // 이미 데이터가 있다
            }
            current = current.next;
        }

        table[index] = new Node<>(index, element, table[index]);
        this.size ++;

        if(size > table.length*LOAD_FACTOR)
            resize();

        return true;
    }



    public boolean remove(E element){
        if(element == null)
            throw new NullPointerException("NPE");

        int index = hash(element);
        Node<E> current = table[index];
        Node<E> prev = null;
        while(current != null){
            if(current.element.equals(element)){
                if(prev == null){
                    table[index] = current.next;
                }
                else{
                    prev.next = current.next;
                }
                this.size --;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    public boolean contains(E element){
        if(element == null)
            throw new NullPointerException("NPE");
        int index = hash(element);
        Node<E> current = table[index];
        while(current != null){
            if(current.element.equals(element))
                return true;
            current = current.next;
        }
        return false;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(){
        Node<E> oldTable[] = table;
        table = new Node[oldTable.length*2];
        size = 0;

        for(int i = 0; i<oldTable.length; i++){
            Node<E> current = oldTable[i];
            while(current != null){
                add(current.element);
                current = current.next;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        int check = 0;

        for(int i = 0; i<table.length; i++){
            Node<E> current = table[i];
            while(current != null){
                sb.append(current.element);
                if(check < size - 1){
                    sb.append(", ");
                }
                check ++;
                current = current.next;
            }
        }
        sb.append("]");
        return sb.toString();
    }


    // public void put(E element){
    //     if(element == null)
    //         throw new NullPointerException("NPE");
    //     int index = hash(element);
    //     Node<E> current = table[index];
    //     while(current != null){
    //         if(current.element.equals(element))
    //             return;
    //         current = current.next;
    //     }
    //     table[index] = new Node<>(index, element, table[index]);
    //     this.size ++;

    //     if(this.size > table.length * LOAD_FACTOR)
    //         resize();
    // }

    
    // public void remove(E element){
    //     if(element == null)
    //         throw new NullPointerException("NPE");
    //     int index = hash(element);
    //     Node<E> current = table[index];
    //     Node<E> prev = null;
    //     while(current != null){
    //         if(current.element.equals(element)){
    //             if(prev == null){
    //                 table[index] = current.next;
    //             }
    //             else{
    //                 prev.next = current.next;
    //             }
    //             this.size --;
    //             return;
    //         }
    //         prev = current;
    //         current = current.next;
    //     }
    // }


}
