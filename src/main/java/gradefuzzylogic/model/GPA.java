package gradefuzzylogic.model;

public class GPA {
    private static double gpa;  
    private static double[] points = {0, 2.2, 3.0, 3.8};
    
    public static double isLow(){
        if (points[0] <= gpa && gpa <= points[1])
            return 1;
        else if (points[1] < gpa && gpa < points[2])
            return (points[2] - gpa) / (points[2] - points[1]);
        else
            return 0;
    }
    
    public static double isMedium(){
        if (gpa == points[2])
            return 1;
        else if (points[1] < gpa && gpa < points[2])
            return (gpa - points[1]) / (points[2] - points[1]);
        else if (points[2] < gpa && gpa < points[3])
            return (points[3] - gpa) / (points[3] - points[2]);
        else
            return 0;
    }
    
    public static double isHigh(){
        if (points[2] < gpa && gpa < points[3])
            return (gpa - points[2]) / (points[3] - points[2]);
        else if (points[3] <= gpa)
            return 1;
       else
            return 0;
    }

    public static double getGpa() {
        return gpa;
    }

    public static void setGpa(double gpa) {
        GPA.gpa = gpa;
    }
}
