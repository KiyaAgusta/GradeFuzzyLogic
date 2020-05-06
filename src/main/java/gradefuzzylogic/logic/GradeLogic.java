package gradefuzzylogic.logic;

import gradefuzzylogic.model.*;
import java.text.DecimalFormat;

public class GradeLogic {
    private static double[][] gradeIndex = new double[3][3];
    private static double[] points = {60, 70, 80, 90, 100};
    private static String[] gradePoints = {"Poor", "Fair",
                                           "Good", "Very Good",
                                           "Excellent"};
    private static int[][] grade = {{4, 3, 1},
                                    {2, 2, 0},
                                    {1, 0, 0}};

    public GradeLogic(double gpa, double gre) {
        GPA.setGpa(gpa);
        GRE.setGre(gre);
        calculateGradeIndex();
    }
    
    public static String[] maxMethod() {
        int iMax = 0, jMax = 0;
        double max = 0;
        String[] result = new String[2];
        
        for (int i = 0; i < gradeIndex.length; i++) {
            for (int j = 0; j < gradeIndex[i].length; j++) {
                if (gradeIndex[i][j] >= max) {
                    max = gradeIndex[i][j];
                    iMax = i;
                    jMax = j;
                }
            }
        }
        
        result[0] = getDecimalFormat(max);
        result[1] = gradePoints[grade[iMax][jMax]];
        return result;
    }
    
    public static String[] sugenoMethod() {
        double crispDecision, cardinal = 0, ordinal = 0;
        String[] result = new String[2];
        
        for (int i = 0; i < gradeIndex.length; i++) {
            for (int j = 0; j < gradeIndex[i].length; j++) {
                if (gradeIndex[i][j] != 0) {
                    cardinal += gradeIndex[i][j] * points[grade[i][j]];
                    ordinal += gradeIndex[i][j];
                }
            }
        }
        
        crispDecision = cardinal / ordinal;
        result[0] = getDecimalFormat(crispDecision);
        result[1] = getFuzzy(crispDecision);
        
        return result;
    }
    
    public static String getFuzzy(double crisp) {
        String fuzzy = "";
        
        for (int i = 0; i < points.length; i++) {
            if (crisp <= points[0]) {
                fuzzy += gradePoints[0];
                break;
            } else if (crisp == points[i]) {
                fuzzy += gradePoints[i];
                break;
            } else if (i < points.length - 1 && points[i] < crisp && crisp < points[i + 1]) {
                fuzzy += String.valueOf(getBelowFuzzy(crisp, i, i + 1));
                fuzzy += "% " + gradePoints[i] + " & ";
                fuzzy += String.valueOf(getAboveFuzzy(crisp, i, i + 1));
                fuzzy += "% " + gradePoints[i + 1];
                break;
            }
        }
        
        return fuzzy;
    }
    
    public static String getBelowFuzzy(double crisp, int idx1, int idx2) {
        return getDecimalFormat((points[idx2] - crisp) / (points[idx2] - points[idx1]) * 100);
    }
    
    public static String getAboveFuzzy(double crisp, int idx1, int idx2) {
        return getDecimalFormat((crisp - points[idx1]) / (points[idx2] - points[idx1]) * 100);
    }
    
    public static void calculateGradeIndex(){
        gradeIndex[0][0] = Math.min(GPA.isHigh(), GRE.isHigh());
        gradeIndex[0][1] = Math.min(GPA.isHigh(), GRE.isMedium());
        gradeIndex[0][2] = Math.min(GPA.isHigh(), GRE.isLow());
        gradeIndex[1][0] = Math.min(GPA.isMedium(), GRE.isHigh());
        gradeIndex[1][1] = Math.min(GPA.isMedium(), GRE.isMedium());
        gradeIndex[1][2] = Math.min(GPA.isMedium(), GRE.isLow());
        gradeIndex[2][0] = Math.min(GPA.isLow(), GRE.isHigh());
        gradeIndex[2][1] = Math.min(GPA.isLow(), GRE.isMedium());
        gradeIndex[2][2] = Math.min(GPA.isLow(), GRE.isLow());
    }
    
    public static String getDecimalFormat(double number) {
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(number);
    }
}
