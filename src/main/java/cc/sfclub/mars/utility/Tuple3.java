package cc.sfclub.mars.utility;

public class Tuple3<T, U, V> {
    private T l;
    private U m;
    private V r;

    public Tuple3(T left, U middle, V right) {
        l = left;
        m = middle;
        r = right;
    }

    public T getLeft() {
        return l;
    }

    public U getMiddle() {
        return m;
    }

    public V getRight() {
        return r;
    }
}
