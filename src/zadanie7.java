class Heap {
    private int[] Heap;
    public int size;
    private int maxsize;

    public Heap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        Heap = new int[this.maxsize];
    }

    private int parent(int pos) { return (pos - 1) / 2; }

    private int leftChild(int pos) { return (2 * pos) + 1; }

    private int rightChild(int pos) { return (2 * pos) + 2; }

    private boolean isLeaf(int pos) { return pos > (size / 2) && pos <= size; }

    private void swap(int fPos, int sPos)
    {
        int tmp;
        tmp = Heap[fPos];
        Heap[fPos] = Heap[sPos];
        Heap[sPos] = tmp;
    }

    private void maxHeapify(int pos)
    {
        if (isLeaf(pos))
            return;

        if (Heap[pos] < Heap[leftChild(pos)] || Heap[pos] < Heap[rightChild(pos)]) {
            if (Heap[leftChild(pos)] > Heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    public void insert(int element)
    {
        Heap[size] = element;
        int current = size;

        while (Heap[current] > Heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }

        size++;
    }

    public void find(int key)
    {
        for (int i = 0; i < size; i++) {
            if (Heap[i] == key) {
                System.out.println("Нашел: " + Heap[i]);
                return;
            }
        }

        System.out.println("Не нашел");
    }

    public int remove()
    {
        int temp = Heap[0];
        Heap[0] = Heap[--size];

        maxHeapify(0);
        return temp;
    }

    public void print()
    {
        for (int i = 0; i < size; i++) {
            System.out.println(Heap[i]);
        }

        for (int i = 0; i < size / 2; i++) {


            System.out.print("Родитель: " + Heap[i]);

            if (leftChild(i) < size)
                System.out.print(" Левый потомок: " + Heap[leftChild(i)]);

            if (rightChild(i) < size)
                System.out.print(" Правый потомок: " + Heap[rightChild(i)]);

            System.out.println();
        }
    }
}


public class zadanie7 {
    // Method 10
    // main dri er method
    public static void main(String[] arg)
    {
        Heap maxHeap = new Heap(15);

        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);

        maxHeap.find(9);
        maxHeap.remove();
        maxHeap.remove();


        // Calling maxHeap() as defined above
        maxHeap.print();



        int[] anArray = new int[]{5, 3, 17, 10, 84, 19, 6, 22, 9};

        maxHeap = new Heap(anArray.length);

        for (int i : anArray) maxHeap.insert(i); // Из несортированного массива
        for ( int k = 0; k < anArray.length; k++){
            anArray[k] = maxHeap.remove(); // В отсортированный массив
        }

        System.out.println("Отсортировано:");
        for (int j : anArray) System.out.print(j + " ");
    }
}