package utils;

public class Pair <T,P>{
    public T first;
    public P second;
    public Pair(T first, P second){
        this.first = first;
        this.second = second;
    }



    public int hashCode() {
        return (int) (2*first.hashCode() + 3*second.hashCode());
    }

    // Overriding equals() to compare two Complex objects
    @Override
    public boolean equals(Object o) {
        return o.hashCode() == hashCode();
    }



}
