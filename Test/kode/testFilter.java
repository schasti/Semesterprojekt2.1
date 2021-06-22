package kode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testFilter {

    @Test
    public void filtrering(){
        int[] a = new int[3125];
        Filter.getFilterOBJ().filtrering(a);
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }

    }

}
