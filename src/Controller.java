import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    LineChart<String,Number> ekgplot;
    @FXML
    TextField CPR;



   // public void setEkgplot(XYChart.Series lineChart){ekgplot2.getData().add(lineChart);}

    public void startmaal() {

        if(Plot.getPlotOBJ().CPRCheck(Plot.getPlotOBJ().getCPR())) {
            Threadhandler.getThreadhandlerOBJ().setLinechart(ekgplot);
            Plot.getPlotOBJ().setupChart(ekgplot);
        Threadhandler.getThreadhandlerOBJ().makeNewThreadIfClosed(Threadhandler.getThreadhandlerOBJ().getMainThread());
        }
        else{
            Plot.getPlotOBJ().textBox("Indtast venligst CPR");
        }



       /* Threadhandler.getThreadhandlerOBJ().setShouldIRun(true);
        if(Plot.getPlotOBJ().CPRCheck(Plot.getPlotOBJ().getCPR())) {
            while (Threadhandler.getThreadhandlerOBJ().getShouldIRun()) {

                if (Sensor.getSensorOBJ().getIsportclosed()) {
                    SQL.getSqlOBJ().makeConnectionSQL();
                    Sensor.getSensorOBJ().setIsportclosed(false);
                    Sensor.getSensorOBJ().open();
                }
                Sensor.getSensorOBJ().maal();
                if (Filter.getFilterOBJ().getAorB()) {
                    System.out.println("maaling 2");
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().maaling1);
                    SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling1());
                   // Plot.getPlotOBJ().clearData(ekgplot);
                    Plot.getPlotOBJ().plotdata(ekgplot, Filter.getFilterOBJ().getMaaling1());
                    Filter.getFilterOBJ().setAorB(false);
                } else {
                    System.out.println("maaling 1");
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().maaling2);
                    SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling2());
                    Plot.getPlotOBJ().clearData(ekgplot);
                    Plot.getPlotOBJ().plotdata(ekgplot, Filter.getFilterOBJ().getMaaling2());
                    Filter.getFilterOBJ().setAorB(true);
                }
            }
        }

        else{
            Plot.getPlotOBJ().textBox("Indtast korrekt CPR");
        }
        //Threadhandler.getThreadhandlerOBJ().run();*/
    }


    public void stopmaal() {
      Threadhandler.getThreadhandlerOBJ().setShouldIRun(false);
      /*
        Sensor.getSensorOBJ().close();
        SQL.getSqlOBJ().removeConnectionSQL();
        Sensor.getSensorOBJ().setIsportclosed(true);*/
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

    public void vismaal(){
        Plot.getPlotOBJ().populateChart(getEkgplot(),Filter.getFilterOBJ().getMaaling2());
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

