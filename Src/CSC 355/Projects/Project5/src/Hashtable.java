import java.util.Objects;

public class Hashtable<K, V> {
    private static final int DEFAULT_CAPACITY = 11; // Default initial capacity
    private static final double ALPHA_LOW = 0.125; // Lower threshold for resizing
    private static final double ALPHA_HIGH = 0.5; // Upper threshold for resizing
    
    private Pair<K, V>[] table; // Array to store key-value pairs
    private int size; // Number of key-value pairs in the table
    
    public Hashtable() {
        this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
    public Hashtable(int initialCapacity) {
        table = new Pair[getNextNum(initialCapacity)];
        size = 0;
    }

    public Pair<K, V>[] getTable() {
        return table;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = findIndex(hash, key);
        if (index != -1)
            return table[index].getValue();
        return null;
    }

    public void put(K key, V val) {
        int hash = hash(key);
        System.out.println("Hash for key " + key + ": " + hash);
        int index = findIndex(hash, key);
    
        if (index != -1) {
            table[index].setValue(val); // Update value if key exists
            return;
        }
    
        if ((double)(size + 1) / table.length > ALPHA_HIGH) {
            resize(getNextNum(2 * table.length));
            hash = hash(key); // Recalculate hash after resizing
            System.out.println("Resized table. New hash for key " + key + ": " + hash);
        }
    
        int probe = 1;
        index = (hash + probe) % table.length;
    
        while (table[index] != null && !table[index].isDeleted()) {
            probe++;
            index = (hash + probe) % table.length;
        }
    
        table[index] = new Pair<>(key, val);
        size++;
        System.out.println("Inserted key " + key + " at index " + index);
    }
    
    public V delete(K key) {
        int hash = hash(key);
        int index = findIndex(hash, key);

        if (index != -1) {
            V deletedValue = table[index].getValue();
            table[index].setDeleted(true);
            size--;

            if (table.length >= 11 && (double)size / table.length < ALPHA_LOW)
                resize(getNextNum(table.length / 2));

            return deletedValue;
        }

        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private int hash(K key) {
        return Math.abs(Objects.hashCode(key)) % table.length;
    }

    private int findIndex(int hash, K key) {
        int probe = 0;
        int index = hash;

        while (table[index] != null) {
            if (table[index].getKey().equals(key) && !table[index].isDeleted())
            return index;
            probe++;
            index = (hash + probe) % table.length;
        }
        
        return -1;
    }
    
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Pair<K, V>[] oldTable = table;
        table = new Pair[newSize];
        size = 0;
    
        for (Pair<K, V> pair : oldTable) {
            if (pair != null && !pair.isDeleted())
                put(pair.getKey(), pair.getValue());
        }
    }
    

    private int getNextNum(int n) {
        int num = n + 1;
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n <= 3)
            return true;
        if (n % 2 == 0 || n % 3 == 0)
            return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }
}
