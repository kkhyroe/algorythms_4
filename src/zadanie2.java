import java.util.Arrays;

interface arr {
    int size = 15;
}

class disorderArray2 implements arr {
    private long[] a;

    public disorderArray2() {
        a = new long[size];
        for(int i=0; i<size; i++)
            a[i] = (int) (Math.random()*size);
    }
    public long[] get() {
        return a;
    }
    public void choiceSort() {
        long min, c;
        int Nmin, i, k;

        for(i=0; i<a.length; i++) {
            min = a[i];
            Nmin = i;

            for(k=i+1; k<a.length; k++)
                if(a[k]<min) {
                    min=a[k];
                    Nmin=k;
                }

            c=a[i];
            a[i]=min;
            a[Nmin]=c;
        }
    }
    public void insertSort() {
        int i, j;
        long temp;

        for(i=1;i<size;i++) {
            temp=a[i];
            j=i-1;
            for(; j>=0; j--) {
                if(a[j]>temp) {
                    a[j+1]=a[j];
                } else {
                    break;
                }
            }

            a[j+1]=temp;
        }
    }
    public void fastSort() {
        quickSort(0, size-1);
    }
    private void quickSort(int low, int high) {
        int middle = low+(high-low)/2, i = low, j = high;
        long opora = a[middle], temp;

        while(i<=j) {
            while(a[i] < opora) {
                i++;
            }
            while(a[j] > opora) {
                j--;
            }
            if(i<=j) {
                temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                i++;
                j--;
            }
        }
        if(j>low)
            quickSort(low, j);
        if(high>i)
            quickSort(i, high);
    }
    public long[] mergeSort() {
        return mergeSortSort(a);
    }
    private long[] mergeSortSort(long[] a) {
        if(a == null) {
            return null;
        }
        if(a.length<2) {
            return a;
        }
        long[] a2 = new long[a.length/2];
        System.arraycopy(a, 0, a2, 0, a.length/2);
        long[] a3 = new long[a.length - a.length/2];
        System.arraycopy(a, a.length/2, a3, 0, a.length - a.length/2);
        a2 = mergeSortSort(a2);
        a3 = mergeSortSort(a3);

        return mergeMergeSort(a2, a3);
    }
    private long[] mergeMergeSort(long[] a, long[] a2) {
        long[] a3 = new long[a.length + a2.length];
        int posA = 0, posB = 0;

        for(int i=0;i<a3.length;i++) {
            if (posA > a.length-1) {
                long aa = a2[posB];
                a3[i] = aa;
                posB++;
            }
            else if (posB > a2.length-1) {
                long aa = a[posA];
                a3[i] = aa;
                posA++;
            }
            else if (a[posA] < a2[posB]) {
                long aa = a[posA];
                a3[i] = aa;
                posA++;
            }
            else {
                long b = a2[posB];
                a3[i] = b;
                posB++;
            }
        }
        return a3;
    }
}

public class zadanie2 {
    public static void main(String[] args) {
        disorderArray2 arrayForIns = new disorderArray2();

        System.out.println("Vstavka");
        arrayForIns.insertSort();
        System.out.println(Arrays.toString(arrayForIns.get()));

        System.out.println("Vibor");
        arrayForIns.choiceSort();
        System.out.println(Arrays.toString(arrayForIns.get()));

        System.out.println("Sliyanie");
        System.out.println(Arrays.toString(arrayForIns.mergeSort()));

        System.out.println("Bistraya");
        arrayForIns.fastSort();
        System.out.println(Arrays.toString(arrayForIns.get()));
    }
}
