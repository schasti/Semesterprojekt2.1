public class Filter {

    private static Filter FilterOBJ = new Filter();
    private Filter() { }
    public static Filter getFilterOBJ() { return FilterOBJ;}



    int tal1 = 0, tal2 = 0;
    int[] maaling1 = new int[3125];
    int[] maaling2 = new int[3125];  //5 sekunders målinger
    String[] raaData;
    String buffer = "";

    public void filtrering() {
        while (tal1 < 3125) {
            String maal = Sensor.getSensorOBJ().maal();
            if (maal != null) {
                buffer = buffer + maal;
                int tjekskilletegn = buffer.indexOf("A");
                if (tjekskilletegn > -1) {
                    raaData = buffer.split("A");
                    if (raaData != null && raaData.length > 0) {
                        if (buffer.charAt(buffer.length() - 1) != 65) {
                            buffer = raaData[raaData.length - 1];
                            raaData[raaData.length - 1] = null;
                        } else {
                            buffer = "";
                        }
                        while (tal2 < raaData.length - 1 && raaData.length > 1) {
                            if (raaData[tal2] != null) {
                                if ((tal1 + tal2) >= 3125) {
                                    break;
                                }
                               maaling1[tal1 + tal2] = Integer.parseInt(raaData[tal2]);
                            }
                            tal2++;
                        }
                        tal1 = tal1 + tal2;
                        tal2 = 0;
                    }
                }
            }
        } tal1=0;
    }
    public int[] getMaaling1(){return maaling1;}
}
