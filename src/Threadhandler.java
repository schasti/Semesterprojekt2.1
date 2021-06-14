/* import java.util.concurrent.CountDownLatch;

public class Threadhandler extends Thread  {

    CountDownLatch latch = new CountDownLatch(1);
    private static Threadhandler threadhandlerOBJ = new Threadhandler();
    private Threadhandler(){}
    public static Threadhandler getThreadhandlerOBJ(){return threadhandlerOBJ;}

    boolean isrunning = true;


    public void run(){
        Sensor.getSensorOBJ().open();
        while(isrunning) {
            new Thread(new Runnable() {
                public void run() {
                    Filter.getFilterOBJ().filtrering(Filter.getFilterOBJ().maaling1);
                }
            }).start();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                public void run() {
                    SQL.getSqlOBJ().writetoDB(Filter.getFilterOBJ().getMaaling1());
                }
            }).start();
        }


    }
    public void stoprunning(){
        isrunning=false;
    }

}

 */

