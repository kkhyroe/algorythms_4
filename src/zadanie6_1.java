// Открытая адресация, линейное пробирование

class ValueEntry2 {
    String key;
    int value;

    ValueEntry2(String key, int value)
    {
        this.key = key;
        this.value = value;
    }
}

class HashTable2 {
    private int HASH_TABLE_SIZE;
    private int size;
    private ValueEntry2[] table;

    public HashTable2(int ts)
    {
        size = 0;
        HASH_TABLE_SIZE = ts;
        table = new ValueEntry2[HASH_TABLE_SIZE];

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
    }

    public void insert(String key, int value)
    {
        // если таблица заполнена
        if (size == HASH_TABLE_SIZE) {
            System.out.println("Таблица заполнена");
            return;
        }

        long startTime = System.nanoTime();

        int hashing1 = myHash(key);

        System.out.println("");
        System.out.println("Скорость линейного пробирования");
        System.out.println((System.nanoTime() - startTime));
        System.out.println("");

        table[hashing1] = new ValueEntry2(key, value);
        size++;
    }

    public void remove(String key)
    {
        int hash1 = myHash(key);

        table[hash1] = null;
        size--;
    }

    public void search(String key)
    {
        int hash1 = myHash(key);
        System.out.println(table[hash1].value);
    }

    // Метод умножения (√5-1) / 2 - константа
    private int myHash(String y)
    {
        int myHashVal1 = y.hashCode();

        myHashVal1 = (int) Math.round(HASH_TABLE_SIZE * (myHashVal1 * (((Math.sqrt(5) - 1) / 2)) % 1));

        if (myHashVal1 < 0)
            myHashVal1 += HASH_TABLE_SIZE;

        return myHashVal1;
    }

    public void printHashTable()
    {
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key + " " + table[i].value);
    }
}

public class zadanie6_1 {
    public static void main(String[] args)
    {
        HashTable2 ht = new HashTable2(100);
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