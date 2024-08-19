import javax.swing.*;
public class App {
    public static void main(String[] args) throws Exception {
        int boarderWidth = 640;
        int boarderHeight = 480;

        JFrame frame = new JFrame("Parachute Sliders");
        frame.setSize(boarderWidth, boarderHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ParachuteSlider paraSlide = new ParachuteSlider();
        frame.add(paraSlide);
        frame.pack();
        paraSlide.requestFocus();
        frame.setVisible(true);



    }
}
