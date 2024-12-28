import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame implements ActionListener {

    private JSlider volumeSlider;
    private JButton back, mute;
    private static boolean isMuted = false;
    private static int volume = 50;

    Settings() {
        setSize(1200, 800);
        setTitle("HoloFLap - Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
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

        volumeSlider = new JSlider(0, 100, volume);
        volumeSlider.setMajorTickSpacing(25);
        volumeSlider.setMinorTickSpacing(10);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.setFont(new Font("SansSerif", Font.BOLD, 15));
        volumeSlider.setBounds(290, 200, 600, 50);
        volumeSlider.setForeground(new Color(0xdae3ff));
        volumeSlider.setBackground(new Color(0x01050d));

        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (!isMuted) {  // Only update volume if not muted
                    volume = volumeSlider.getValue();
                    VolumeController.getInstance().setVolume(volume);
                }
            }
        });

        JLabel volumeLabel = new JLabel("Music Volume");
        volumeLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        volumeLabel.setBounds(500, 150, 200, 30);
        volumeLabel.setForeground(new Color(0xdae3ff));

        bgPanel.add(volumeLabel);
        bgPanel.add(volumeSlider);

        back = new JButton("Back");
        back.setFont(new Font("SansSerif", Font.BOLD, 30));
        back.setBounds(500, 550, 200, 60);
        back.setBackground(new Color(0xdae3ff));
        back.setForeground(new Color(0x031930));
        back.setBorder(BorderFactory.createLineBorder(new Color(0x031930), 3));
        back.addActionListener(this);
        bgPanel.add(back);

        mute = new JButton("Mute");
        mute.setFont(new Font("SansSerif", Font.BOLD, 30));
        mute.setBounds(500, 300, 200, 60);
        mute.setBackground(new Color(0xdae3ff));
        mute.setForeground(new Color(0x031930));
        mute.setBorder(BorderFactory.createLineBorder(new Color(0x5194fd), 3));
        mute.addActionListener(this);
        bgPanel.add(mute);

        setContentPane(bgPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            this.dispose();
            new MainMenu();
        } else if (e.getSource() == mute) {
            isMuted = !isMuted;  // Toggle mute state
            if (isMuted) {
                volume = 0;  // Set volume to 0 when muted
                VolumeController.getInstance().setVolume(volume);
                mute.setText("Unmute");
            } else {
                volume = volumeSlider.getValue();  // Restore previous volume
                VolumeController.getInstance().setVolume(volume);
                mute.setText("Mute");
            }
        }
    }

    public static void main(String[] args) {
        new Settings();
    }
}
