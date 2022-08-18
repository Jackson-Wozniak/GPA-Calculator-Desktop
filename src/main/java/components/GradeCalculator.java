package components;

import javax.swing.*;

public class GradeCalculator extends JPanel {
    static JTextField[] gradePercents = new JTextField[6];
    static JTextField[] gradeWeights = new JTextField[6];
    int[] grades = new int[6];
    int[] weights = new int[6];

    public GradeCalculator(){
        this.setLayout(null);

        JLabel percentLabel = new JLabel("Category Grade:");
        percentLabel.setBounds(10,10,150,30);
        this.add(percentLabel);

        JLabel weightLabel = new JLabel("Category Weight:");
        weightLabel.setBounds(170,10,150,30);
        this.add(weightLabel);

        for(int i = 0; i < 6; i++){
            gradePercents[i] = new JTextField();
            gradePercents[i].setBounds(10,40 + (i * 60),150,40);
            this.add(gradePercents[i]);
            gradeWeights[i] = new JTextField();
            gradeWeights[i].setBounds(170,40 + (i * 60),150,40);
            this.add(gradeWeights[i]);
        }

        JButton clearText = new JButton("Clear Text");
        clearText.setBounds(100, 440, 120, 30);
        this.add(clearText);
        clearText.setFocusable(false);
        clearText.addActionListener(e -> {
            for(JTextField txt : gradeWeights) txt.setText("");
            for(JTextField txt : gradePercents) txt.setText("");
        });

        JTextField outputWindow = new JTextField();
        outputWindow.setBounds(100, 480, 120, 30);
        this.add(outputWindow);
        outputWindow.setFocusable(false);
        outputWindow.setEditable(false);

        JLabel outputWindowLabel = new JLabel("Final Grade:");
        outputWindowLabel.setBounds(30, 480, 70, 30);
        this.add(outputWindowLabel);

        JButton calculateGrade = new JButton("Calculate Class Grade");
        calculateGrade.setBounds(60, 400, 200,30);
        this.add(calculateGrade);
        calculateGrade.setFocusable(false);
        calculateGrade.addActionListener(e -> {
            for(int i = 0; i < gradeWeights.length; i++){
                try{
                    Integer.parseInt(gradeWeights[i].getText());
                    weights[i] = Integer.parseInt(gradeWeights[i].getText());
                }catch (Exception ex){
                    weights[i] = 0;
                }
            }
            for(int i = 0; i < gradePercents.length; i++){
                try{
                    Integer.parseInt(gradePercents[i].getText());
                    grades[i] = Integer.parseInt(gradePercents[i].getText());
                }catch (Exception ex){
                    grades[i] = 0;
                }
            }
            double finalGrade = getFinalGrade();
            if(finalGrade > 100){
                outputWindow.setText("Grade above 100%");
            }else if(finalGrade < 0){
                outputWindow.setText("Grade below 0%");
            }else{
                outputWindow.setText(finalGrade + "%");
            }

        });
    }

    public double getFinalGrade(){
        double gradeSum = 0;
        for(int i = 0; i < weights.length; i++){
            gradeSum += weights[i] * grades[i];
        }
        return gradeSum * .01;
    }
}
