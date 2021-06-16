// import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threadhandler extends Thread  {

    private static Threadhandler threadhandlerOBJ = new Threadhandler();
    private Threadhandler(){}
    public static Threadhandler getThreadhandlerOBJ(){return threadhandlerOBJ; }

    private LineChart linechart;

    private final ExecutorService SqlHandler = Executors.newSingleThreadExecutor();



    private final Thread mainThread = new Thread(new Runnable() {
        @Override
        public void run() {
          //Platform.runLater(() -> Plot.getPlotOBJ().setupChart(linechart));
            Sensor.getSensorOBJ().open();
            SQL.getSqlOBJ().makeConnectionSQL();
            Threads.getThreadsOBJ().setShouldIRun(true);
            while (Threads.getThreadsOBJ().getShouldIRun()) {
                if(Filter.getFilterOBJ().getAorB()) {
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().getMaaling1());
                    System.out.println("Filter A");
                    Filter.getFilterOBJ().setAorB(false);
                    Platform.runLater(plotThread);
                    getSqlHandler().execute(sqlThread);
                }
                else {
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().getMaaling2());
                    System.out.println("Filter b");
                    Filter.getFilterOBJ().setAorB(true);
                    Platform.runLater(plotThread);
                   getSqlHandler().execute(sqlThread);
                }
            }
            SQL.getSqlOBJ().removeConnectionSQL();
            Sensor.getSensorOBJ().close();
        }
    });
    private final Thread plotThread = new Thread(() -> {
        if (Filter.getFilterOBJ().getAorB()) {
            Plot.getPlotOBJ().populateChart( Filter.getFilterOBJ().getMaaling1());
            System.out.println("platform A");

        } else {
            Plot.getPlotOBJ().populateChart(Filter.getFilterOBJ().getMaaling2());
            System.out.println("platform B");
        }
       // Plot.getPlotOBJ().setupChart(linechart);
    });
    private final Thread sqlThread = new Thread(() -> {
        if (Filter.getFilterOBJ().getAorB()) {
            System.out.println("SQl 1");
            SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling1());
        } else {
            System.out.println("SQl 2");
            SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling2());
        }
    });
    public void makeNewThreadIfClosed(Thread thread) {
        if (!thread.isAlive()) {
            Thread t = new Thread(thread);
            t.start();
        }
    }

    public Thread getMainThread(){return mainThread;}
    public ExecutorService getSqlHandler() {
        return SqlHandler;
    }

    /*   boolean shouldIRun;

    public void setShouldIRun(boolean shouldIRun){
        this.shouldIRun=shouldIRun;
    }
    public boolean getShouldIRun() {
        return shouldIRun;
    }*/



}



