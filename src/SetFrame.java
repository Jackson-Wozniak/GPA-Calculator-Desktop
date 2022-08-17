import javax.swing.*;

public class SetFrame extends JFrame{

    public SetFrame(){
        JTabbedPane pane = new JTabbedPane();
        pane.addTab("GPA-Calc", new GpaCalculatorPanel());
        pane.addTab("Grade-Calc", new GradeCalculator());

        this.add(pane);
        this.setSize(450,660);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
