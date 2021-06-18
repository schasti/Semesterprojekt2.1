import java.sql.*;
import java.util.ArrayList;

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
                String write = "INSERT INTO ekgmaalinger (EKG,CPR)"+ " values(?,?)"+
                        ",(?,?)"+
                        ",(?,?)"+
                        ",(?,?)"+
                        ",(?,?)"+
                        ",(?,?)"+
                        ",(?,?)"+
                        ",(?,?)"+
                        ",(?,?)"+
                        ",(?,?)";
                PreparedStatement PP = myConn.prepareStatement(write);
                PP.setInt(1, maaling[i]);
                PP.setString(2,Plot.getPlotOBJ().getCPR());
                PP.setInt(3, maaling[i+1]);
                PP.setString(4,Plot.getPlotOBJ().getCPR());
                PP.setInt(5, maaling[i+2]);
                PP.setString(6,Plot.getPlotOBJ().getCPR());
                PP.setInt(7, maaling[i+3]);
                PP.setString(8,Plot.getPlotOBJ().getCPR());
                PP.setInt(9, maaling[i+4]);
                PP.setString(10, Plot.getPlotOBJ().getCPR());
                PP.setInt(11, maaling[i+5]);
                PP.setString(12,Plot.getPlotOBJ().getCPR());
                PP.setInt(13, maaling[i+6]);
                PP.setString(14,Plot.getPlotOBJ().getCPR());
                PP.setInt(15, maaling[i+7]);
                PP.setString(16,Plot.getPlotOBJ().getCPR());
                PP.setInt(17, maaling[i+8]);
                PP.setString(18,Plot.getPlotOBJ().getCPR());
                PP.setInt(19, maaling[i+9]);
                PP.setString(20,Plot.getPlotOBJ().getCPR());


                PP.execute();
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
            removeConnectionSQL();
        }
        System.out.println("Done med at sende data");
    }
}

