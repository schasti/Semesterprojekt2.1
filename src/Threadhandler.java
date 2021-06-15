// import java.util.concurrent.CountDownLatch;

public class Threadhandler extends Thread  {

    private static Threadhandler threadhandlerOBJ = new Threadhandler();
    private Threadhandler(){}
    public static Threadhandler getThreadhandlerOBJ(){return threadhandlerOBJ; }

    Thread maal = new Thread(new Runnable() {
        @Override
        public void run() {
            Threads.getThreadsOBJ().maalThread();
        }
    });
    Thread plot = new Thread(new Runnable() {
        @Override
        public void run() {
            Threads.getThreadsOBJ().plotThread();
        }
    });
    Thread send = new Thread(new Runnable() {
        @Override
        public void run() {
            Threads.getThreadsOBJ().sendDataThread();
        }
    });

    public void startThread(){
        maal.start();
        send.start();
        plot.start();
    }

 /*   boolean shouldIRun;

    public void setShouldIRun(boolean shouldIRun){
        this.shouldIRun=shouldIRun;
    }
    public boolean getShouldIRun() {
        return shouldIRun;
    }*/



}



