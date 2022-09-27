class ValueEntry {

    // Member variables of the class
    String key;
    int value;

    // Constructor of this class
    // Parameterized constructor
    ValueEntry(String key, int value)
    {
        // This keyword refers to current object
        // for assigning values to same object itself
        this.key = key;

        // this operator is pointer which contains location
        // of  that container that have key and value pairs
        this.value = value;
    }
}

// Class 2
// Helper Class
//  HashTable
class HashTable {

    // Member variable of this class
    private int HASH_TABLE_SIZE;
    private int size;
    private ValueEntry[] table;
    private int totalprimeSize;

    // Constructor of this class
    // Parameterized constructor
    public HashTable(int ts)
    {
        // Initializing the member variables
        size = 0;
        HASH_TABLE_SIZE = ts;
        table = new ValueEntry[HASH_TABLE_SIZE];

        // Iterating using for loop over table
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
        totalprimeSize = getPrime();
    }

    // Method 1
    // To check for the prime number
    public int getPrime()
    {
        // Iterating using for loop in reverse order
        for (int i = HASH_TABLE_SIZE - 1; i >= 1; i--) {

            // Initially assigning count to zero
            int cnt = 0;

            // Now, iterating from 2 upto the desired number
            // to be checked by dividing it with all no
            // in between [2 - no]
            for (int j = 2; j * j <= i; j++)

                // If number is divisible
                // means not a prime number
                if (i % j == 0)

                    // So simply move to next number
                    // to check for divisibility by
                    // incrementing the count variable
                    cnt++;

            // By now number is not divisible
            // hence count holds 0 till last
            if (cnt == 0)

                // It means it is a prime number so
                // return the number as it is a prime number
                return i;
        }

        // Returning a prime number
        return 3;
    }

    // Method 2
    // To get number of key-value pairs
    public int getSize() { return size; }
    public boolean isEmpty() { return size == 0; }

    //
    /* Function to clear hash table */
    public void makeEmpty()
    {
        size = 0;
        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            table[i] = null;
    }

    // Method 3
    // To get value of a key
    public int getKey(String key)
    {
        int hash1 = myHash1(key);
        int hash2 = myHash2(key);

        while (table[hash1] != null
                && !table[hash1].key.equals(key)) {
            hash1 += hash2;
            hash1 %= HASH_TABLE_SIZE;
        }
        return table[hash1].value;
    }

    // Method 4
    // To insert a key value pair
    public void insert(String key, int value)
    {
        // checking the size of table and
        //  comparing it with users input value
        if (size == HASH_TABLE_SIZE) {

            // Display message
            System.out.println("Table is full");
            return;
        }

        int hashing1 = myHash1(key);
        int hashing2 = myHash2(key);
        while (table[hashing1] != null) {
            hashing1 += hashing2;
            hashing1 %= HASH_TABLE_SIZE;
        }

        table[hashing1] = new ValueEntry(key, value);
        size++;
    }

    // Method 5
    // To remove a key
    public void remove(String key)
    {
        int hash1 = myHash1(key);
        int hash2 = myHash2(key);
        while (table[hash1] != null
                && !table[hash1].key.equals(key)) {
            hash1 += hash2;
            hash1 %= HASH_TABLE_SIZE;
        }
        table[hash1] = null;
        size--;
    }

    // Method 6
    // Function gives a hash value for a given
    // string basically it is linear probing
    private int myHash1(String y)
    {
        int myHashVal1 = y.hashCode();
        myHashVal1 %= HASH_TABLE_SIZE;
        if (myHashVal1 < 0)
            myHashVal1 += HASH_TABLE_SIZE;
        return myHashVal1;
    }

    // Method 7
    // Remember that the above function namely 'myhash'
    // is used for double hashing

    // Now after linear probing, quadratic probing
    //  is used in which two myhash functions are used
    //  as it is double chaining
    private int myHash2(String y)
    {
        int myHashVal2 = y.hashCode();
        myHashVal2 %= HASH_TABLE_SIZE;
        if (myHashVal2 < 0)
            myHashVal2 += HASH_TABLE_SIZE;
        return totalprimeSize - myHashVal2 % totalprimeSize;
    }

    // Method 8
    // To print the hash table
    public void printHashTable()
    {
        // Display message
        System.out.println("\nHash Table");

        for (int i = 0; i < HASH_TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key + " "
                        + table[i].value);
    }
}