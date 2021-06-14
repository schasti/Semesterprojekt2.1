import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Plot {

    public Plot() { }
    private static Plot plotOBJ = new Plot();
    public static Plot getPlotOBJ(){return plotOBJ;}

    String vali;


    XYChart.Series<String, Number> ekg = new XYChart.Series<>();

    public void plotdata(LineChart ekgplot, int[] ekgarray){
        ekgplot.getData().clear();
        for (int i = 0; i < (ekgarray.length - 1); i++) {
            vali = String.valueOf(i);
            ekg.getData().add(new XYChart.Data<>(vali, ekgarray[i]));

        }ekgplot.getData().add(ekg);
    }
}
