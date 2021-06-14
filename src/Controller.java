import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

import java.awt.*;

public class Controller {
    @FXML
    LineChart ekgplot;
    @FXML
    TextField CPR;




    public void startmaal() {

        SQL.getSqlOBJ().makeConnectionSQL();
       Sensor.getSensorOBJ().open();
        Sensor.getSensorOBJ().maal();
        Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().maaling1);
        SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling1());
        Plot.getPlotOBJ().plotdata(ekgplot,Filter.getFilterOBJ().maaling1);
        //Threadhandler.getThreadhandlerOBJ().run();
    }


    public void stopmaal() {
        //Threadhandler.getThreadhandlerOBJ().stoprunning();
    }
    public void setCPR(String CPR) {
        this.CPR.setText(CPR);
    }
    public TextField getCPR() {
        return CPR;
    }
}

