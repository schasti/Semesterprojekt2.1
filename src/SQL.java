import java.sql.*;

public class SQL {

    private SQL() { }
    static private SQL sqlOBJ = new SQL();
    static public SQL getSqlOBJ() {
        return sqlOBJ;
    }

    static String url = "jdbc:mysql://localhost:1404/semesterprojekt2";
    static String user = "root";
    static String password = "1234";
    static Connection myConn;
    static Statement myStatement;

    public void makeConnectionSQL(){
        try {
            myConn = DriverManager.getConnection(url, user, password);
            myStatement = myConn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeConnectionSQL(){
        try {
            if (!myConn.isClosed()){
                myConn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void writetoDB(int[] maaling){

        try {
            makeConnectionSQL();
            for (int i = 0; i < maaling.length-11 ; i+=10) {
                String write = "INSERT INTO ekgmaalinger (EKG)"+ " values(?)"+
                        ",(?)"+
                        ",(?)"+
                        ",(?)"+
                        ",(?)"+
                        ",(?)"+
                        ",(?)"+
                        ",(?)"+
                        ",(?)"+
                        ",(?)";
                PreparedStatement PP = myConn.prepareStatement(write);
                PP.setInt(1, maaling[i]);
                PP.setInt(2, maaling[i+1]);
                PP.setInt(3, maaling[i+2]);
                PP.setInt(4, maaling[i+3]);
                PP.setInt(5, maaling[i+4]);
                PP.setInt(6, maaling[i+5]);
                PP.setInt(7, maaling[i+6]);
                PP.setInt(8, maaling[i+7]);
                PP.setInt(9, maaling[i+8]);
                PP.setInt(10, maaling[i+9]);

                PP.execute();
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
            removeConnectionSQL();
        }
        System.out.println("Done med at sende data");
    }
}

