class disorderArray2 {
    private long[] a;
    private int nElems;

    public disorderArray2(int size) {
        a = new long[size];
    }

    public boolean find(long searchKey) {
        int j;

        for(j=0; j<nElems; j++)
            if(a[j] == searchKey)
                break;

        return j != nElems;
    }
    public void insert(long value) {
        a[nElems] = value;
        nElems++;
    }
    public boolean delete(long value) {
        int j;

        for(j=0; j<nElems; j++)
            if(value == a[j])
                break;
        if(j==nElems)
            return false;
        else {
            for(int k=j; k<nElems; k++)
                a[k] = a[k+1];

            nElems--;
            return true;
        }
    }
    public void vivod() {
        for(int k=0; k<a.length; k++)
            System.out.println(a[k]);
    }
    public void bubbleSort() {
        int out, in;
        for (out=nElems-1; out>1; out--)
            for(in=0; in<out; in++)
                if(a[in]>a[in+1])
                    swap(in, in+1);
    }
    private void swap(int one, int two) {
        long temp = a[one];
        a[one] = a[two];
        a[two] = temp;
    }
    public void choiceSort() {
        System.out.println(System.currentTimeMillis());
        long max = 0, c;
        int Nmax, i, k;

        for(i=0; i<nElems; i++) {
            max = a[i];
            Nmax = i;

            for(k=0; k<nElems; k++)
                if(a[k]>max) {
                    max=a[k];
                    Nmax=k;
                }
            c=a[i];
            a[i]=max;
            a[Nmax]=c;
        }

        System.out.println(System.currentTimeMillis());
    }
    public void insertSort() {
        System.out.println(System.currentTimeMillis());
        int i, j;
        long temp;

        for(i=1;i<nElems-1;i++) {
            temp=a[i];
            j=i;

            if((a[j-1]>temp)&(j>0)) {
                a[j]=a[j-1];
                j=j-1;
            }

            a[j]=temp;
        }

        System.out.println(System.currentTimeMillis());
    }
}

public class zadanie2 {
    public static void main(String[] args) {
        disorderArray2 newArray = new disorderArray2(10);
        newArray.insert(10);
        newArray.insert(1);
        newArray.insert(3);
        newArray.insert(7);
        newArray.insert(14);
        newArray.vivod();
        System.out.println("Sorted");
//        newArray.bubbleSort();
        newArray.choiceSort();
        System.out.println("Sorted");
        newArray.insertSort();
        newArray.vivod();
    }
}
