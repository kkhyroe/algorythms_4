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
    public boolean find(long searchKey) {
        int j;
        for(j=0; j<nElems; j++)
            if(a[j] == searchKey)
                break;
        if(j == nElems)
            return false;
        else
            return true;
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

public class zadanie1 {
    public static void main(String[] args) {
        LowArray aLowArray = new LowArray(100);
        aLowArray.insert(1);
        aLowArray.insert(2);
        aLowArray.delete(1);
        System.out.println(aLowArray.find(2));
//        aLowArray.setElem(1, 322);
//        System.out.println(aLowArray.getElem(1));
    }
}
