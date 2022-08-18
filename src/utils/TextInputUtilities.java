package utils;

import javax.swing.*;

public class TextInputUtilities {

    public static boolean isTextFieldValidNumber(JTextField jTextField){
        return jTextField.getText().equals("") || !isTextInputNumber(jTextField);
    }

    public static boolean isTextInputNumber(JTextField jTextField){
        try{
            int test = Integer.parseInt(jTextField.getText());
            if(test < 0) return false;
        }catch(Exception e){
            return false;
        }
        return true;
    }

    public static double calculatedWeightedGpaOfClass(int index, double grade){
        return (LetterGradeToGpa.getGpaPerClass(index) * grade);
    }

    public static double turnTextFieldInputIntoDouble(JTextField jTextField){
        return Double.parseDouble(jTextField.getText());
    }
}
