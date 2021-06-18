import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;


public class Controller {


    @FXML
    LineChart<String,Number> ekgplot;
    @FXML
    LineChart<String,Number> ekgplot2;
    @FXML
    TextField CPR;




    public void startmaal() {

        if(Plot.getPlotOBJ().CPRCheck(Plot.getPlotOBJ().getCPR())) {
            Threadhandler.getThreadhandlerOBJ().setLinechart(ekgplot);
            Plot.getPlotOBJ().setupChart(ekgplot);
        Threadhandler.getThreadhandlerOBJ().makeNewThreadIfClosed(Threadhandler.getThreadhandlerOBJ().getMainThread());
        }
        else{
            Plot.getPlotOBJ().textBox("Indtast venligst CPR");
        }
    }


    public void stopmaal() {
      Threadhandler.getThreadhandlerOBJ().setShouldIRun(false);
    }

    public void gemCPR(){
       if(Plot.getPlotOBJ().CPRCheck(getCPR().getText())) {
           Plot.getPlotOBJ().setCPR(getCPR().getText());
           System.out.println("ok");
           Plot.getPlotOBJ().textBox("CPR Godkendt");
       }
       else{
           Plot.getPlotOBJ().textBox("Forkert CPR");
       }
    }


    public void setCPR(String CPR) {
        this.CPR.setText(CPR);
    }
    public TextField getCPR() {
        return CPR;
    }

    public void setEkgplot(XYChart.Series lineChart){ekgplot.getData().add(lineChart);}
    public LineChart getEkgplot(){return ekgplot;}
}

