import jssc.*;

public class Sensor {

    private static Sensor sensorOBJ = new Sensor();
    private Sensor(){}
    public static Sensor getSensorOBJ(){return sensorOBJ;}


    private SerialPort sensor = new SerialPort("COM4");
    boolean isportclosed = true;

    public void open(){
        try {
            sensor.openPort();
            sensor.setParams(57600,8,1,0);
            sensor.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            sensor.purgePort(SerialPort.PURGE_TXCLEAR | SerialPort.PURGE_RXCLEAR);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public String maal() {
        try {
            if (sensor.getInputBufferBytesCount() > 0) {
                return sensor.readString();
            } else {
                return null;
            }
        } catch (SerialPortException ex) {
            System.out.println("fejl: " + ex);
        }
       return null;
    }

    public void close() {
        try {
            sensor.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public void setIsportclosed(boolean isportclosed){
        this.isportclosed=isportclosed;
    }
    public boolean getIsportclosed(){
        return isportclosed;
    }

}
