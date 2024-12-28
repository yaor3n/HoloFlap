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
    private JLabel label;

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

        label = new JLabel("Choose your Idol:");
        label.setFont(new Font("SansSerif", Font.BOLD, 35));
        label.setBounds(450, 30, 400, 50);
        label.setForeground(new Color(0xdae3ff));
        bgPanel.add(label);

        back = new JButton("Back");
        back.setFont(new Font("SansSerif", Font.BOLD, 30));
        back.setBounds(500, 600, 200, 60);
        back.setBackground(new Color(0xdae3ff));
        back.setForeground(new Color(0x031930));
        back.setBorder(BorderFactory.createLineBorder(new Color(0x031930), 3));
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

    public static void  main(String[] args) {
        new CharacterSelect();
    }

}
