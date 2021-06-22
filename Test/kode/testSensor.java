package kode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testSensor {

   @Test
    public void checkMaaling(){
       Sensor.getSensorOBJ().open();
       System.out.println(Sensor.getSensorOBJ().maal());
       Sensor.getSensorOBJ().close();

   }
}
