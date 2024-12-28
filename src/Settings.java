import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Settings extends JFrame{

    Settings() {
        setSize(1200, 800);
        setTitle("HoloFLap");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("icon/hololive.png");
        setIconImage(icon.getImage());

        setVisible(true);
    }

}
