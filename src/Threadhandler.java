// import java.util.concurrent.CountDownLatch;

public class Threadhandler extends Thread  {

    private static Threadhandler threadhandlerOBJ = new Threadhandler();
    private Threadhandler(){}
    public static Threadhandler getThreadhandlerOBJ(){return threadhandlerOBJ; }

    boolean shouldIRun;

    public void setShouldIRun(boolean shouldIRun){
        this.shouldIRun=shouldIRun;
    }
    public boolean getShouldIRun() {
        return shouldIRun;
    }

}



