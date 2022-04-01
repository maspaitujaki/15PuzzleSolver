/**
 * Pair.java
 */
public class Pair<T,B> {
    T key;
    B value;

    public Pair() {
        super();
        this.key = null;
        this.value = null;
    }

    public Pair(T key, B value) {
        super();
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public B getValue() {
        return value;
    }
}
