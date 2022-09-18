import java.util.Arrays;

class disorderArray {
    private long[] a;
    private int nElems;

    public disorderArray(int size) {
        a = new long[size];
    }

    public long[] ret() {
        return a;
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
}

class orderArray {
    private long[] a;
    private int nElems;

    public orderArray(int size) {
        a = new long[size];
    }

    public long[] ret() {
        return a;
    }

    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems-1;
        int curln;

        while(true) {
            curln = (lowerBound+upperBound)/2;

            if(a[curln]==searchKey)
                return curln;
            else if(lowerBound>upperBound)
                return -1;
            else {
                if(a[curln]<searchKey)
                    lowerBound=curln+1;
                else
                    upperBound=curln-1;
            }
        }
    }

    public void insert(long value) {
        int j;

        for(j=0;j<nElems;j++)
            if(a[j]>value)
                break;
        for(int k=nElems; k>j; k--)
            a[k] = a[k-1];

        a[j] = value;
        nElems++;
    }
    public boolean delete(long value) {
        int j=find(value);

        if(j==nElems)
            return false;
        else {
            for(int k=j; k<nElems; k++)
                a[k] = a[k+1];

            nElems--;
            return true;
        }
    }
}

public class zadanie1 {
    public static void main(String[] args) {
        disorderArray disArray = new disorderArray(10);
        System.out.println(Arrays.toString(disArray.ret()));
        disArray.insert(10);
        System.out.println(Arrays.toString(disArray.ret()));
        disArray.insert(1);
        System.out.println(Arrays.toString(disArray.ret()));
        disArray.delete(1);
        System.out.println(Arrays.toString(disArray.ret()));
        System.out.println(disArray.find(10));
        System.out.println(disArray.find(1));

        orderArray orArray = new orderArray(10);
        System.out.println(Arrays.toString(orArray.ret()));

        orArray.insert(10);
        System.out.println(Arrays.toString(orArray.ret()));
        orArray.insert(1);
        System.out.println(Arrays.toString(orArray.ret()));
        orArray.delete(1);
        System.out.println(Arrays.toString(orArray.ret()));
        System.out.println(orArray.find(10));
        System.out.println(orArray.find(1));
    }
}
