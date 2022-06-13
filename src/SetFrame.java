import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetFrame implements ActionListener{

    JButton[] removeClass = new JButton[5];
    JTextField[] grades = new JTextField[5];
    boolean[] isClassRemoved = new boolean[5];

    public void setFrame(){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);

        String[] grade = {"A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "F"};

        Font bigFont = new Font("Serif", Font.BOLD, 28);
        Color LightGreen = new Color(0,204,0);
        Color DarkGreen = new Color(0,150,0);
        Color Yellow = new Color(255,204,51);
        Color Orange = new Color(205,102,0);
        Color Red = new Color(255,0,0);


        JLabel creditsLabel = new JLabel("Credits:");
        creditsLabel.setBounds(10,5,100,20);
        frame.add(creditsLabel);

        for(int i = 0; i < grades.length; i++){
            grades[i] = new JTextField();
            grades[i].setBounds(10, 30 + (70 * i), 230, 60);
        }

        JComboBox<?>[] listOfGrades = new JComboBox[5];
        for(int i = 0; i < listOfGrades.length; i++){
            listOfGrades[i] = new JComboBox<>(grade);
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
            double gpaSum = 0;
            int classNumber = 0;
            double[] credits = new double[5];
            int[] gpa = new int[5];
            for(int i = 0; i < 5; i++) {
                if (!isClassRemoved[i] && grades[i].getText().trim().length() > 0 && Integer.parseInt(grades[i].getText()) > 0) {
                    credits[i] = Double.parseDouble(grades[i].getText());
                    gpa[i] = listOfGrades[i].getSelectedIndex();
                    CalculateGPA math = new CalculateGPA(credits[i], gpa[i]);
                    gpaSum += math.getGpaPerClass(gpa[i]);
                    classNumber++;
                }/*else if(isClassRemoved[i]){

                }*/else{
                    grades[i].setText("Make sure your input is valid!");
                }
            }


            double finalGPA = gpaSum / classNumber;
            if(finalGPA > 0){
                gpaOutput.setText(String.valueOf(Math.round(finalGPA * 10.00) /10.00));
            }else{
                gpaOutput.setText("Invalid GPA");
            }

            //set the background to a color based on the grade
            if(finalGPA >= 3.5){
                gpaOutput.setBackground(LightGreen);
            }else if(finalGPA >= 3.0){
                gpaOutput.setBackground(DarkGreen);
            }
            else if(finalGPA >= 2.5){
                gpaOutput.setBackground(Yellow);
            }
            else if(finalGPA > 2.0){
                gpaOutput.setBackground(Orange);
            }
            else if(finalGPA <= 2.0){
                gpaOutput.setBackground(Red);
            }
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
            panel.add(grades[i]);
            panel.add(listOfGrades[i]);
            panel.add(removeClass[i]);
        }

        panel.add(gpaButton);
        panel.add(gpaOutput);
        panel.add(clearText);
        frame.add(panel);
        frame.setSize(450,650);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
