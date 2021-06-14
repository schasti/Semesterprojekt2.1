import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

public class Controller {
    @FXML
    LineChart ekgplot;

    public void startmaal(){

        Sensor.getSensorOBJ().open();
        Sensor.getSensorOBJ().maal();
        Filter.getFilterOBJ().filtrering();
        SQL.getSqlOBJ().makeConnectionSQL();
        SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling1());
        //Plot.getPlotOBJ().plotdata(ekgplot,Filter.getFilterOBJ().maaling1);
            }


    }

