package collection;

import java.util.List;
import java.util.Vector;

public class VectorEx {
    public static void initVector(){
        List<String> vector = new Vector<>();
        Vector<String> vector1 = new Vector<>();
        vector.add("bmw");
        System.out.println(vector);
    }

    public static void main(String[] args) {
        initVector();
    }

}



