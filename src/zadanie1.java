class LowArray {
    private long[] a;
    private int nElems;

    public LowArray(int size) {
        a = new long[size];
    }
    //    public void setElem(int index, long value) {
//        a[index] = value;
//    }
//    public long getElem(int index) {
//        return a[index];
//    }
//    public boolean find(long searchKey) {
//        int j;
//        for(j=0; j<nElems; j++)
//            if(a[j] == searchKey)
//                break;
//        if(j == nElems)
//            return false;
//        else
//            return true;
//    }
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
//    public void insert(long value) {
//        a[nElems] = value;
//        nElems++;
//    }
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
//    public boolean delete(long value) {
//        int j;
//        for(j=0; j<nElems; j++)
//            if(value == a[j])
//                break;
//            if(j==nElems)
//                return false;
//            else {
//                for(int k=j; k<nElems; k++)
//                    a[k] = a[k+1];
//                nElems--;
//                return true;
//            }
//    }
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
    public void vivod() {
        for(int j=0; j<a.length; j++)
            System.out.println(a[j]);
    }
}

public class zadanie1 {
    public static void main(String[] args) {
        LowArray aLowArray = new LowArray(10);
        aLowArray.insert(1);
        aLowArray.insert(10);
        aLowArray.insert(3);
//        aLowArray.vivod();
        aLowArray.delete(1);
        System.out.println(aLowArray.find(10));
        aLowArray.vivod();
//        aLowArray.setElem(1, 322);
//        System.out.println(aLowArray.getElem(1));
    }
}
