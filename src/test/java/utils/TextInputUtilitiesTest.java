package utils;

import org.junit.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class TextInputUtilitiesTest {

    //valid number means positive and not null in this case
    @Test
    public void textFieldContainsValidNumber(){
        JTextField jTextField = new JTextField();
        jTextField.setText("1");
        assertTrue(TextInputUtilities.isTextFieldValidNumber(jTextField));
    }

    @Test
    public void textFieldContainsNumber(){
        JTextField jTextField = new JTextField();
        jTextField.setText("1");
        assertTrue(TextInputUtilities.isTextInputNumber(jTextField));
    }

    @Test
    public void stringIsConvertedToDouble(){
        JTextField jTextField = new JTextField();
        jTextField.setText("1.0");
        assertEquals(1.0, TextInputUtilities.turnTextFieldInputIntoDouble(jTextField));
    }
}