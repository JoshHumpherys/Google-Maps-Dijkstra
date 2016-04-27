// Josh Humpherys
// Asg2 -- Hash Tables

import java.util.Iterator;

public class HashTable<K, V> implements Iterable<HashPair<K, V>> {
    private K[] keys;
    private V[] values;
    
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        keys = (K[])new Object[size];
        values = (V[])new Object[size];
    }

    public void put(K key, V value) {
        int hash = getHashCode(key);
        int firstHash = hash;
        while(keys[hash] != null && !key.equals(keys[hash])) {
            hash = getDoubleHashCode(key, hash);
            if(hash == firstHash)
                return;
        }
        keys[hash] = key;
        values[hash] = value;
    }

    public V get(K key) {
        int hash = getHashCode(key);
        int firstHash = hash;
        if(keys[hash] == null)
            return null;
        while(keys[hash] == null || !key.equals(keys[hash])) {
            hash = getDoubleHashCode(key, hash);
            if(hash == firstHash)
                return null;
        }
        return values[hash];
    }
    
    private int getHashCode(K key) {
        return ((key.hashCode() % keys.length) + keys.length) % keys.length;
    }
    
    private int getDoubleHashCode(K key, int hash) {
        return (((hash + 3 - (key.hashCode() % 3)) % keys.length + keys.length)) % keys.length;
    }

    public void dump() {
        for(HashPair<K, V> p : this)
            System.out.println(p);
    }
    
    @Override
    public String toString() {
        Iterator<HashPair<K, V>> it = iterator();
        if(!it.hasNext())
            return "[]";
        StringBuilder sb = new StringBuilder("[" + it.next());
        while(it.hasNext()) {
            sb.append(", ");
            sb.append(it.next());
        }
        sb.append("]");
        return sb.toString();
    }

    public Iterator<HashPair<K, V>> iterator() {
        return new Iterator<HashPair<K, V>>() {
            private int cursor = -1;
            
            @Override
            public boolean hasNext() {
                for(int i = cursor + 1; i < keys.length; i++)
                    if(keys[i] != null)
                        return true;
                return false;
            }
            
            @Override
            public HashPair<K, V> next() {
                cursor++;
                for(;cursor < keys.length; cursor++)
                    if(keys[cursor] != null)
                        return new HashPair<K, V>(keys[cursor], values[cursor]);
                return null;
            }
        };
    }
}
