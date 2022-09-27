// Открытая адресация, квадратичное пробирование

class ValueEntry {
    String key;
    int value;

    ValueEntry(String key, int value)
    {
        this.key = key;
        this.value = value;
    }
}

class HashTable {
    private int HASH_TABLE_SIZE;
    private int size;
    private ValueEntry[] table;
    private int totalPrimeSize;

    public HashTable(int ts)
    {
        size = 0;
        HASH_TABLE_SIZE = ts;
        table = new ValueEntry[HASH_TABLE_SIZE];

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;

        totalPrimeSize = getPrime();
    }

    public int getPrime()
    {
        for (int i = HASH_TABLE_SIZE - 1; i >= 1; i--) {
            int cnt = 0;

            for (int j = 2; j * j <= i; j++)
                if (i % j == 0)
                    cnt++;

            if (cnt == 0)
                return i;
        }

        return 3;
    }

    public void search(String key)
    {
        int hash1 = myHash1(key);
        int hash2 = myHash2(key);

        while (table[hash1] != null && !table[hash1].key.equals(key)) {
            hash1 += hash2;
            hash1 %= HASH_TABLE_SIZE;
        }

        System.out.println(table[hash1].value);
    }

    public void insert(String key, int value)
    {
        // если таблица заполнена
        if (size == HASH_TABLE_SIZE) {
            System.out.println("Table is full");
            return;
        }

        long startTime = System.nanoTime();

        int hashing1 = myHash1(key);
        int hashing2 = myHash2(key);

        System.out.println("");
        System.out.println("Скорость квадратичного пробирования");
        System.out.println((System.nanoTime() - startTime));
        System.out.println("");

        while (table[hashing1] != null) {
            hashing1 += hashing2;
            hashing1 %= HASH_TABLE_SIZE;
        }

        table[hashing1] = new ValueEntry(key, value);
        size++;
    }

    public void remove(String key)
    {
        int hash1 = myHash1(key);
        int hash2 = myHash2(key);

        while (table[hash1] != null && !table[hash1].key.equals(key)) {
            hash1 += hash2;
            hash1 %= HASH_TABLE_SIZE;
        }

        table[hash1] = null;
        size--;
    }

    // сначала просто линейное пробирование
    private int myHash1(String y)
    {
        int myHashVal1 = y.hashCode();
        myHashVal1 %= HASH_TABLE_SIZE;

        if (myHashVal1 < 0)
            myHashVal1 += HASH_TABLE_SIZE;

        return myHashVal1;
    }

    // после первой функции, используя вторую, создается квадратичное пробирование
    private int myHash2(String y)
    {
        int myHashVal2 = y.hashCode();
        myHashVal2 %= HASH_TABLE_SIZE;

        if (myHashVal2 < 0)
            myHashVal2 += HASH_TABLE_SIZE;

        return totalPrimeSize - myHashVal2 % totalPrimeSize;
    }

    public void printHashTable()
    {
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key + " " + table[i].value);
    }
}

public class zadanie6 {
    public static void main(String[] args)
    {
        HashTable ht = new HashTable(100);
        ht.insert("first", 80);
        ht.insert("second", 20);
        ht.insert("third", 32);
        ht.insert("fourth", 99);

        System.out.println("Поиск");
        ht.search("second");

        System.out.println("Таблица");
        ht.printHashTable();

        System.out.println("Таблица после удаления");
        ht.remove("fourth");
        ht.printHashTable();
    }
}