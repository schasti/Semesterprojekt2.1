public class Filter {

    private static Filter FilterOBJ = new Filter();
    private Filter() { }
    public static Filter getFilterOBJ() { return FilterOBJ;}



    int tal1 = 0, tal2 = 0;
    int[] maaling1 = new int[3125];
    int[] maaling2 = new int[3125];  //5 sekunders målinger
    String[] raaData;
    String buffer = "";
    boolean AorB;

    public void filtrering(int[] maaling) {
        while (tal1 < 3125) {
            String maal = Sensor.getSensorOBJ().maal();
            if (maal != null) {
                buffer = buffer + maal;
                int tjekskilletegn = buffer.indexOf("A");
                if (tjekskilletegn > -1) {
                    raaData = buffer.split("A");
                    if (raaData != null && raaData.length > 0) {
                            if (buffer.charAt(buffer.length()-1 ) != 65) {
                                buffer = raaData[raaData.length - 1];
                                raaData[raaData.length - 1] = null;
                            } else {
                                buffer = "";
                            }
                        while (tal2 < raaData.length - 1 && raaData.length > 1) {
                            if (raaData[tal2] != null) {
                                if ((tal1 + tal2) >= 3125) {
                                    System.out.println("Done");
                                    break;
                                }
                                if(tal1+tal2==1500){
                                    System.out.println("halvvejs");
                                }
                                try{
                                    maaling[tal1 + tal2] = Integer.parseInt(raaData[tal2]);
                                }
                                catch (NumberFormatException e){maaling[tal1+tal2]=0;}
                            }
                            tal2++;
                        }
                        tal1 = tal1 + tal2;
                        tal2 = 0;
                    }
                }
            }
        } tal1=0;
        //Threadhandler.getThreadhandlerOBJ().latch.countDown();
    }
    public int[] getMaaling1(){return maaling1;}
    public int[] getMaaling2() {return maaling2;}

    public void setAorB(boolean AorB){this.AorB=AorB;}
    public boolean getAorB(){return AorB;}
}
