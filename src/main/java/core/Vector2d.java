package core;

import java.util.Objects;

public class Vector2d {
    private final int x;
    private final int y;


    public Vector2d(int x,int y) {
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean precedes(Vector2d other){
        return (this.x <= other.x) && (this.y <= other.y);

    }
    public boolean follows(Vector2d other){
        return (this.x >= other.x) && (this.y >= other.y);

    }
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x,this.y + other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x,this.y - other.y);
    }
    //Dodaj math
    public Vector2d upperRight(Vector2d other){
        int max_x = 0;
        int max_y = 0;
        if (this.x > other.x) max_x = this.x;
        else max_x = other.x;
        if (this.y > other.y) max_y = this.y;
        else max_y = other.y;

        return new Vector2d(max_x,max_y);
    }
    public Vector2d lowerLeft(Vector2d other)
    {
        int min_x = 0;
        int min_y = 0;
        if (this.x < other.x) min_x = this.x;
        else min_x = other.x;
        if (this.y < other.y) min_y = this.y;
        else min_y = other.y;

        return new Vector2d(min_x,min_y);
    }
    public Vector2d opposite()
    {
        return new Vector2d(-this.x,-this.y);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x && y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")" ;
    }
}
