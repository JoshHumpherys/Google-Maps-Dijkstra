/**
 * Created by cubemaster on 4/26/16.
 */
public class HashPair<K, V> {
    private K k;
    private V v;
    HashPair(K k, V v) {
        this.k = k;
        this.v = v;
    }
    public K getKey() {
        return k;
    }
    public V getValue() {
        return v;
    }
    public void setKey(K k) {
        this.k = k;
    }
    public void setValue(V v) {
        this.v = v;
    }
    @Override public String toString() {
        return "(" + k + ", " + v + ")";
    }
}
