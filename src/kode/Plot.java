package kode;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Plot {

    public Plot() {
    }

    private static Plot plotOBJ = new Plot();

    public static Plot getPlotOBJ() {
        return plotOBJ;
    }

    String CPR = "";


    XYChart.Series<Number, Number> ekg = new XYChart.Series<>();
    XYChart.Series<Number, Number> ekggemt = new XYChart.Series<>();

    public void setupChart(LineChart lineChart, XYChart.Series<Number, Number> serie) {
        serie.getData().clear();
        lineChart.getData().clear();
        serie.setName("ECG");
        lineChart.getData().add(serie);
    }

    public void populateChart(int[] array) {
        ekg.getData().clear();
        for (int i = 0; i < (array.length - 1); i++) {
            ekg.getData().add(new XYChart.Data(i, array[i]));
        }
    }

    public void populateChartArraylist(ArrayList<Integer> arraylist) {
        ekggemt.getData().clear();
        for (int i = 0; i < (arraylist.size() - 1); i++) {
            ekggemt.getData().add(new XYChart.Data(i, arraylist.get(i)));
        }
    }


    public Boolean CPRCheck(String string) {
        if (string != "" && string.length() == 10 && string.matches("[0-9]+")) {
            return true;
        } else {
            return false;
        }
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public String getCPR() {
        return CPR;
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
