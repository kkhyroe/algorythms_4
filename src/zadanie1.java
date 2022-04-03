class LowArray {
    private long[] a;
    public LowArray(int size) {
        a = new long[size];
    }
    public void setElem(int index, long value) {
        a[index] = value;
    }
    public long getElem(int index) {
        return a[index];
    }
}

public class zadanie1 {
    public static void main(String[] args) {
        LowArray aLowArray = new LowArray(100);
        aLowArray.setElem(1, 322);
        System.out.println(aLowArray.getElem(1));
    }
}
