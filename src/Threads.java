import javafx.application.Platform;
import javafx.scene.chart.LineChart;

import javax.sound.sampled.Line;

public class Threads {
    private static Threads threadsOBJ = new Threads();
    private Threads(){}
    private static Threads getThreadsOBJ(){return threadsOBJ;}
    public boolean shouldIRun;
    private Object lock = new Object();

    public void maalThread() {
        while (true) {
            if(Filter.getFilterOBJ().getAorB()){
                Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().getMaaling1());
                Filter.getFilterOBJ().setAorB(false);}
            else{
                Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().getMaaling2());
                Filter.getFilterOBJ().setAorB(true);
            }
            lock.notifyAll();
            System.out.println("have notified");
        }
    }
    public void plotThread(){
        while(true){
            try {
                lock.wait();
                System.out.println("has unlocked me");
                Plot.getPlotOBJ().clearData();
                if(Filter.getFilterOBJ().getAorB()){
                    Plot.getPlotOBJ().plotdata(Filter.getFilterOBJ().getMaaling1());}
                else {
                    Plot.getPlotOBJ().plotdata(Filter.getFilterOBJ().getMaaling2());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void sendDataThread(){
        while(true) {
            try {
                lock.wait();
                if(Filter.getFilterOBJ().getAorB()){
                    SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling1());
                }
                else{
                    SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling2());
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
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
