package utils;

public class LetterGradeToGpa {

    //convert index of combo box to grade(i.e index 0 at "A" = 4.0 gpa etc)
    public static double getGpaPerClass(int index){
        double gpa;
        switch (index) {
            case 0 -> {
                gpa = 4.0;
                return gpa;
            }
            case 1 -> {
                gpa = 3.7;
                return gpa;
            }
            case 2 -> {
                gpa = 3.3;
                return gpa;
            }
            case 3 -> {
                gpa = 3.0;
                return gpa;
            }
            case 4 -> {
                gpa = 2.7;
                return gpa;
            }
            case 5 -> {
                gpa = 2.4;
                return gpa;
            }
            case 6 -> {
                gpa = 2.0;
                return gpa;
            }
            case 7 -> {
                gpa = 1.7;
                return gpa;
            }
            case 8 -> {
                gpa = 1.4;
                return gpa;
            }
            case 9 -> {
                gpa = 1.0;
                return gpa;
            }
            case 10 -> {
                gpa = 0.0;
                return gpa;
            }
        }
        return 0.0;
    }

}
