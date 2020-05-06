package gradefuzzylogic.model;

public class GRE {
    private static double gre;
    private static double[] points = {0, 800, 1200, 1800};
    
    public static  double isLow(){
        if (points[0] <= gre && gre <= points[1])
            return 1;
        else if (points[1] < gre && gre < points[2])
            return (points[2] - gre) / (points[2] - points[1]);
        else
            return 0;
    }
    
    public static double isMedium(){
        if (gre == points[2])
            return 1;
        else if (points[1] < gre && gre < points[2])
            return (gre - points[1]) / (points[2] - points[1]);
        else if (points[2] < gre && gre < points[3])
            return (points[3] - gre) / (points[3] - points[2]);
        else
            return 0;
    }
    
    public static double isHigh(){
        if (points[2] < gre && gre < points[3])
            return (gre - points[2]) / (points[3] - points[2]);
        else if (points[3] <= gre)
            return 1;
       else
            return 0;
    }

    public static double getGre() {
        return gre;
    }

    public static void setGre(double gre) {
        GRE.gre = gre;
    }
}
