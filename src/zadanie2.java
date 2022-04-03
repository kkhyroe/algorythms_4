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
    public void vivod() {
        for(int k=0; k<a.length; k++)
            System.out.println(a[k]);
    }
}

public class zadanie2 {
    public static void main(String[] args) {
        disorderArray2 newArray = new disorderArray2(10);
        newArray.insert(10);
        newArray.insert(1);
        newArray.insert(3);
        newArray.vivod();
        System.out.println("Sorted");
        newArray.bubbleSort();
        newArray.vivod();
    }
}
