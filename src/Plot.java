import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Plot {

    public Plot() { }
    private static Plot plotOBJ = new Plot();
    public static Plot getPlotOBJ(){return plotOBJ;}

    String vali;


    XYChart.Series<String, Number> ekg = new XYChart.Series<>();

    public void clearData(LineChart ekgplot){
        ekgplot.getData().clear();
        ekg.getData().clear();
        ekg.setName("EKG");
        ekgplot.getData().add(ekg);
    }

    public void plotdata(LineChart ekgplot, int[] ekgarray){
        ekgplot.getData().clear();
        for (int i = 0; i < (ekgarray.length - 1); i++) {
            ekg.getData().add(new XYChart.Data<>(vali, ekgarray[i]));
        }
    }

    public void textBox(String message) {
        Label alertLabel = new Label();
        StackPane allertLayout = new StackPane();
        Stage allertStage = new Stage();
        Button allertButton = new Button();

        allertButton.setText("OK");
        alertLabel.setText(message);
        allertStage.setTitle("Alert");

        allertButton.setOnAction(p -> allertStage.close());
        allertLayout.getChildren().addAll(allertButton, alertLabel);
        Scene allertScene = new Scene(allertLayout, 200, 100);
        alertLabel.setTranslateY(-25);

        allertStage.setScene(allertScene);
        allertStage.initModality(Modality.APPLICATION_MODAL);
        allertStage.show();
    }
}
