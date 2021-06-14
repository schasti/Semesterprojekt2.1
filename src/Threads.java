import javafx.application.Platform;
import javafx.scene.chart.LineChart;

import javax.sound.sampled.Line;

public class Threads {
    private static Threads threadsOBJ = new Threads();
    private Threads(){}
    private static Threads getThreadsOBJ(){return threadsOBJ;}

    private LineChart lineChart;

    private Thread mainthread = new Thread(new Runnable() {
        @Override
        public void run() {
            Platform.runLater(()-> Plot.getPlotOBJ().clearData(lineChart));
        }
    });
}
