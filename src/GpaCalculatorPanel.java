import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class GpaCalculatorPanel extends JPanel implements ActionListener{

    JButton[] removeClass = new JButton[5];
    JTextField[] grades = new JTextField[5];
    boolean[] isClassRemoved = new boolean[5];
    final String[] letterGrade = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"};

    public GpaCalculatorPanel(){
        this.setLayout(null);

        Font bigFont = new Font("Serif", Font.BOLD, 28);

        JLabel creditsLabel = new JLabel("Credits:");
        creditsLabel.setBounds(20,10,100,20);
        this.add(creditsLabel);

        for(int i = 0; i < grades.length; i++){
            grades[i] = new JTextField();
            grades[i].setBounds(10, 30 + (70 * i), 230, 60);
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
                if (!isClassRemoved[i] && !grades[i].getText().equals("") && Integer.parseInt(grades[i].getText()) > 0 ) {
                    weightedGpa += (LetterGradeToGpa.getGpaPerClass(listOfGrades[i].getSelectedIndex())
                            * Double.parseDouble(grades[i].getText()));
                    totalCredits += Double.parseDouble(grades[i].getText());
                    continue;
                }
                JOptionPane.showMessageDialog(new JFrame(), "all fields must be valid numbers");
                return;
            }
            double finalGPA = weightedGpa / totalCredits;
            if(finalGPA < 0){
                gpaOutput.setText("Invalid GPA");
                return;
            }
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            gpaOutput.setText(decimalFormat.format(finalGPA));
            setBackgroundOfGpaOutput(gpaOutput, finalGPA);
        });



        JButton clearText = new JButton("Clear Text");
        clearText.setBounds(120,545,100,40);
        clearText.setFocusable(false);
        clearText.addActionListener(e -> {
            for (JTextField jTextField : grades) {
                jTextField.setText("");
            }
        });

        for(int i = 0; i < 5; i++){
            this.add(grades[i]);
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
                    grades[i].setText("");
                    grades[i].setEditable(false);
                    isClassRemoved[i] = true;
                    grades[i].setFocusable(false);
                    removeClass[i].setText("+");
                }else{
                    grades[i].setEditable(true);
                    grades[i].setFocusable(true);
                    removeClass[i].setText("X");
                    isClassRemoved[i] = false;
                }
            }
        }
    }
}

