import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CharacterSelect extends JFrame implements ActionListener{

    private JButton back;

    CharacterSelect() {

        setSize(1200, 800);
        setTitle("HoloFLap - Character Select");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("icon/hololive.png");
        setIconImage(icon.getImage());

        Image bgImage = new ImageIcon("background/HoloSummerFes5-2.png").getImage();

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        back = new JButton("Back");
        back.setFont(new Font("SansSerif", Font.BOLD, 20));
        back.setBounds(468, 640, 100, 30);
        back.addActionListener(this);
        bgPanel.add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.dispose();
            new MainMenu();
        }
    }
}
