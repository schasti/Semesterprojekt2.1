import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Threadhandler  {

    private static Threadhandler threadhandlerOBJ = new Threadhandler();
    private Threadhandler(){}
    public static Threadhandler getThreadhandlerOBJ(){return threadhandlerOBJ; }

    private LineChart linechart;
    public boolean shouldIRun;
    private final ExecutorService SqlExecutor = Executors.newSingleThreadExecutor();



    private final Thread mainThread = new Thread(new Runnable() {
        @Override
        public void run() {

            Sensor.getSensorOBJ().open();
            SQL.getSqlOBJ().makeConnectionSQL();
            Threadhandler.getThreadhandlerOBJ().setShouldIRun(true);

            while (Threadhandler.getThreadhandlerOBJ().getShouldIRun()) {
                if(Filter.getFilterOBJ().getAorB()) {
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().getMaaling1());
                    System.out.println("Filter A");
                    Filter.getFilterOBJ().setAorB(false);
                    Platform.runLater(plotThread);
                    getSqlExecutor().execute(sqlThread);
                }
                else {
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().getMaaling2());
                    System.out.println("Filter b");
                    Filter.getFilterOBJ().setAorB(true);
                    Platform.runLater(plotThread);
                    getSqlExecutor().execute(sqlThread);
                }
            }
            SQL.getSqlOBJ().removeConnectionSQL();
            Sensor.getSensorOBJ().close();
        }
    });

    private final Thread plotThread = new Thread(() -> {
        if (Filter.getFilterOBJ().getAorB()) {
            Plot.getPlotOBJ().populateChart( linechart,Filter.getFilterOBJ().getMaaling1());
            System.out.println("plot A");
        }
        else {
            Plot.getPlotOBJ().populateChart(linechart,Filter.getFilterOBJ().getMaaling2());
            System.out.println("plot B");
        }
    });

    private final Thread sqlThread = new Thread(() -> {
        if (Filter.getFilterOBJ().getAorB()) {
            System.out.println("SQl 1");
            SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling1());
        }
        else {
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
    public ExecutorService getSqlExecutor() {
        return SqlExecutor;
    }

    public void setShouldIRun(boolean shouldIRun){
        this.shouldIRun=shouldIRun;
    }
    public boolean getShouldIRun(){
        return shouldIRun;
    }

    public void setLinechart(LineChart lineChart){ this.linechart=lineChart; }
    public LineChart getLinechart(){return linechart;}

}



