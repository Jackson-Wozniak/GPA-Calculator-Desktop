import components.SetFrame;

import javax.swing.*;

public class Main extends SetFrame {
    public static void main(String[] args) {
        try {

            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }
        new SetFrame();
    }
}
