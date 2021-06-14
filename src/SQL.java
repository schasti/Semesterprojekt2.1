import java.sql.*;

public class SQL {

    private SQL() { }
    static private SQL sqlOBJ = new SQL();
    static public SQL getSqlOBJ() {
        return sqlOBJ;
    }

    static String url = "jdbc:mysql://localhost:1404/SemesterProjekt2";
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
            for (int i = 0; i < maaling.length - 1; i++) {
                String write = "INSERT INTO ekgmaalinger (EKG) values(?);";
                PreparedStatement PP = myConn.prepareStatement(write);
                PP.setInt(1, maaling[i]);
                PP.execute();
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
            removeConnectionSQL();
        }
    }
}

