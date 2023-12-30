package utils;

public class Point{
    private int row;
    private int col;
    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){
        return row;
    }


    public int getCol(){
        return col;
    }

    public boolean compareTo(Point other){
        return col == other.col && row == other.row;
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    @Override
    // override hashcode is necessary when create new Point() in map to achieve structural equivalence
    public int hashCode() {
        return (int) (Math.pow(2, row)*Math.pow(3,col));
    }

    // Overriding equals() to compare two Complex objects
    @Override
    public boolean equals(Object o) {
        return o.hashCode() == hashCode();
    }

}
