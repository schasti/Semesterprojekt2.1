import javafx.application.Platform;
import javafx.scene.chart.LineChart;

import javax.sound.sampled.Line;

public class Threads {
    private static Threads threadsOBJ = new Threads();
    private Threads(){}
    public static Threads getThreadsOBJ(){return threadsOBJ;}
    public boolean shouldIRun;


    public void maalThread() {
        while (true) {
            synchronized (this) {
                Threads.getThreadsOBJ().setShouldIRun(true);
                if (Sensor.getSensorOBJ().getIsportclosed()) {
                    SQL.getSqlOBJ().makeConnectionSQL();
                    Sensor.getSensorOBJ().setIsportclosed(false);
                    Sensor.getSensorOBJ().open();
                }
                if (Filter.getFilterOBJ().getAorB()) {
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().getMaaling1());
                    Filter.getFilterOBJ().setAorB(false);
                } else {
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().getMaaling2());
                    Filter.getFilterOBJ().setAorB(true);
                }
                notifyAll();
                System.out.println("have notified");
                if (Threads.getThreadsOBJ().getShouldIRun()==false) {
                    break;
                }
            }
        }
    }
    public void plotThread(){
        while(true){
            synchronized (this) {
                try {
                    System.out.println("tråd plot er begyndt....");
                    wait();
                    System.out.println("tråd plot kører videre....");
                    Plot.getPlotOBJ().clearData();
                    if (Filter.getFilterOBJ().getAorB()) {
                        Plot.getPlotOBJ().plotdata(Filter.getFilterOBJ().getMaaling1());
                    } else {
                        Plot.getPlotOBJ().plotdata(Filter.getFilterOBJ().getMaaling2());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void sendDataThread(){
        while(true) {
            synchronized (this) {
                try {
                    System.out.println("tråd senddata er begyndt.....");
                    wait();
                    System.out.println("tråd senddata kører videre...");
                    if (Filter.getFilterOBJ().getAorB()) {
                        SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling1());
                    } else {
                        SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling2());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!Threads.getThreadsOBJ().getShouldIRun()) {
                    break;
                }
            }
        }

    }
    public void setShouldIRun(boolean shouldIRun){
        this.shouldIRun=shouldIRun;
    }
    public boolean getShouldIRun(){
        return shouldIRun;
    }
}
