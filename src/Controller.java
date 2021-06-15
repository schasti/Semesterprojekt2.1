import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.*;
import javafx.scene.control.TextField;


public class Controller {
    @FXML
    LineChart ekgplot;
    @FXML
    TextField CPR;


    public void startmaal() {
        if(Plot.getPlotOBJ().CPRCheck(Plot.getPlotOBJ().getCPR())) {
        Threadhandler.getThreadhandlerOBJ().startThread();
        }
        else{
            Plot.getPlotOBJ().textBox("Forkert CPR");
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
      Threads.getThreadsOBJ().setShouldIRun(false);/*
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
    public void setCPR(String CPR) {
        this.CPR.setText(CPR);
    }
    public TextField getCPR() {
        return CPR;
    }
}

