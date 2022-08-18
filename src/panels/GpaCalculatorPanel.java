package panels;

import utils.CustomColors;
import utils.TextInputUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class GpaCalculatorPanel extends JPanel implements ActionListener{

    private final JButton[] removeClass = new JButton[5];
    private final JTextField[] classCredits = new JTextField[5];
    private final boolean[] isClassRemoved = new boolean[5];
    final String[] letterGrade = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"};
    final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    final Font bigFont = new Font("Serif", Font.BOLD, 28);


    public GpaCalculatorPanel(){
        this.setLayout(null);

        JLabel creditsLabel = new JLabel("Credits:");
        creditsLabel.setBounds(20,10,100,20);
        this.add(creditsLabel);

        for(int i = 0; i < classCredits.length; i++){
            classCredits[i] = new JTextField();
            classCredits[i].setBounds(10, 30 + (70 * i), 230, 60);
        }

        JComboBox<?>[] listOfGrades = new JComboBox[5];
        for(int i = 0; i < listOfGrades.length; i++){
            listOfGrades[i] = new JComboBox<>(letterGrade);
            listOfGrades[i].setBounds(250, 30 + (70 * i), 100, 60);
            listOfGrades[i].setFocusable(false);
        }

        JButton gpaOutput = new JButton();
        gpaOutput.setBounds(70,440,200,100);
        gpaOutput.setFont(bigFont);
        gpaOutput.setBackground(Color.WHITE);
        gpaOutput.setBorderPainted(false);
        gpaOutput.setFocusable(false);

        for(int i = 0; i < 5; i++){
            removeClass[i] = new JButton("X");
            removeClass[i].setBounds(360, 30 + (70 * i), 60, 60);
            removeClass[i].setFocusable(false);
            removeClass[i].addActionListener(this);

        }

        JButton gpaButton = new JButton("Calculate GPA");
        gpaButton.setBounds(70,390,200,40);
        gpaButton.setFocusable(false);
        gpaButton.addActionListener(e -> {
            int totalCredits = 0;
            double weightedGpa = 0;
            for(int i = 0; i < 5; i++) {
                if (isClassRemoved[i]){
                    continue;
                }
                if(TextInputUtilities.isTextFieldValidNumber(classCredits[i])){
                    JOptionPane.showMessageDialog(
                            new JFrame(), "All fields must be valid numbers");
                    return;
                }

                double classCredit = TextInputUtilities.turnTextFieldInputIntoDouble(classCredits[i]);
                totalCredits += classCredit;
                weightedGpa += TextInputUtilities.calculatedWeightedGpaOfClass(
                        listOfGrades[i].getSelectedIndex(),
                        classCredit);
            }

            double finalGPA = weightedGpa / totalCredits;
            if(finalGPA < 0){
                gpaOutput.setText("Invalid GPA");
                return;
            }
            gpaOutput.setText(decimalFormat.format(finalGPA));
            setBackgroundOfGpaOutput(gpaOutput, finalGPA);
        });



        JButton clearText = new JButton("Clear Text");
        clearText.setBounds(120,545,100,40);
        clearText.setFocusable(false);
        clearText.addActionListener(e -> {
            for (JTextField jTextField : classCredits) {
                jTextField.setText("");
            }
        });

        for(int i = 0; i < 5; i++){
            this.add(classCredits[i]);
            this.add(listOfGrades[i]);
            this.add(removeClass[i]);
        }
        this.add(gpaButton);
        this.add(gpaOutput);
        this.add(clearText);
    }

    public void setBackgroundOfGpaOutput(JButton gpaOutput, double finalGPA){
        if(finalGPA >= 3.5){
            gpaOutput.setBackground(CustomColors.lightGreen);
        }else if(finalGPA >= 3.0){
            gpaOutput.setBackground(CustomColors.darkGreen);
        }
        else if(finalGPA >= 2.5){
            gpaOutput.setBackground(CustomColors.yellow);
        }
        else if(finalGPA > 2.0){
            gpaOutput.setBackground(CustomColors.orange);
        }
        else if(finalGPA <= 2.0){
            gpaOutput.setBackground(CustomColors.red);
        }
    }

    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < 5; i++){
            if(e.getSource()==removeClass[i]){
                if(!isClassRemoved[i]){
                    classCredits[i].setText("");
                    classCredits[i].setEditable(false);
                    isClassRemoved[i] = true;
                    classCredits[i].setFocusable(false);
                    removeClass[i].setText("+");
                }else{
                    classCredits[i].setEditable(true);
                    classCredits[i].setFocusable(true);
                    removeClass[i].setText("X");
                    isClassRemoved[i] = false;
                }
            }
        }
    }
}

