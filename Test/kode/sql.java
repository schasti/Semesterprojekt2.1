package kode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class sql {
    int[] a = {1,2,3,4,5,56,4,42,2,};

    @Test
    public void read(){
        SQL.getSqlOBJ().makeConnectionSQL();
        SQL.getSqlOBJ().readfromDB("1234567890");
        SQL.getSqlOBJ().removeConnectionSQL();

    }

    @Test
    public void send(){
        SQL.getSqlOBJ().makeConnectionSQL();
        SQL.getSqlOBJ().writetoDB(a);
        SQL.getSqlOBJ().removeConnectionSQL();
    }
}
