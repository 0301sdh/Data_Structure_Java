package Heap;

public class MaxHeap<T extends Comparable<T>> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] heap;
    private int size;
    private int capacity;

    public MaxHeap() {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.heap = new Object[capacity];
    }

    public MaxHeap(T[] arr) {
        this.capacity = arr.length;
        this.size = arr.length;
        this.heap = new Object[capacity];

        for (int i = 0; i < size; i++) {
            heap[i] = arr[i];
        }

        for (int i = parent(size - 1); i >= 0; i--) {
            siftDown(i);
        }

    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return i * 2 + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        Object tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return (T) heap[0];
    }

    public void insert(T value) {
        if (size == capacity) {
            resize();
        }
        heap[size] = value;
        size++;
        siftUp(size - 1);
    }

    @SuppressWarnings("unchecked")
    private void siftUp(int i) {
        while (i > 0 && ((T) heap[i]).compareTo((T) heap[parent(i)]) > 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void resize() {
        capacity = capacity * 2;
        Object[] newHeap = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    @SuppressWarnings("unchecked")
    public T poll() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        T max = (T) heap[0];
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;
        siftDown(0);
        return max;
    }

    @SuppressWarnings("unchecked")
    private void siftDown(int i) {
        while (true) {
            int largest = i;
            int left = leftChild(i);
            int right = rightChild(i);

            if (left < size && ((T) heap[left]).compareTo((T) heap[largest]) > 0) {
                largest = left;
            }
            if (right < size && ((T) heap[right]).compareTo((T) heap[largest]) > 0) {
                largest = right;
            }
            if (largest == i) {
                break;
            }

            swap(i, largest);
            i = largest;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        MaxHeap<Integer> heap = new MaxHeap<>();

        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(9);
        heap.insert(2);
        heap.insert(20);
        heap.insert(21);
        heap.insert(25);
        heap.insert(62);
        heap.insert(24);
        heap.insert(25);
        heap.insert(200);
        heap.insert(72);

        System.out.print("정렬 결과 : ");
        while (!heap.isEmpty()) {
            System.out.print(heap.poll() + " ");
        }
        System.out.println();

        MaxHeap<String> words = new MaxHeap<>();
        words.insert("apple");
        words.insert("cherry");
        words.insert("banana");
        System.out.println("last word : " + words.peek());

        Integer[] arr = { 3, 1, 9, 5, 2, 8, 7, 4 };
        MaxHeap<Integer> built = new MaxHeap<>(arr);
        System.out.print("build heap result : ");
        while (!built.isEmpty()) {
            System.out.print(built.poll() + " ");
        }
    }

}
