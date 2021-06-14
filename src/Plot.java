import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Plot {

    public Plot() { }
    private static Plot plotOBJ = new Plot();
    public static Plot getPlotOBJ(){return plotOBJ;}

    XYChart.Series ekg = new XYChart.Series<>();

    public void plotdata(LineChart ekgplot, int[] ekgarray){
        ekgplot.getData().clear();
        for (int i = 0; i < (ekgarray.length - 1); i++) {
            ekg.getData().add(new XYChart.Data<>(i, ekgarray[i]));
            ekgplot.getData().add(ekg);
        }
    }
}
