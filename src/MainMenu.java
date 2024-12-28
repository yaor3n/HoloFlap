import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MainMenu extends JFrame implements ActionListener {

    private JButton play, settings, exit;
    private Image bgImage;
    private JLabel title;
    private static Clip musicClip;
    private static boolean isSongPlaying = false;

    MainMenu() {

        setSize(1200, 800);
        setTitle("HoloFLap");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("icon/hololive.png");
        setIconImage(icon.getImage());

        bgImage = randomBG();

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        bgPanel.setLayout(null);
        setContentPane(bgPanel);

        title = new JLabel("HoloFlap");
        title.setFont(new Font("Arial",Font.ITALIC,200));
        title.setBounds(200,10,1000,500);
        title.setForeground(new Color(0x031930));
        add(title);


        Font buttonFont = new Font("SansSerif", Font.BOLD, 30);

        play = new JButton("Play");
        play.setFont(buttonFont);
        play.setBounds(468, 420, 250, 70);
        play.setBackground(new Color(0xdae3ff));
        play.setForeground(new Color(0x031930));
        play.setBorder(BorderFactory.createLineBorder(new Color(0x031930), 3));
        play.addActionListener(this);
        bgPanel.add(play);

        settings = new JButton("Settings");
        settings.setFont(buttonFont);
        settings.setBounds(468, 530, 250, 70);
        settings.setBackground(new Color(0xdae3ff));
        settings.setForeground(new Color(0x031930));
        settings.setBorder(BorderFactory.createLineBorder(new Color(0x031930), 3));
        settings.addActionListener(this);
        bgPanel.add(settings);

        exit = new JButton("Exit");
        exit.setFont(buttonFont);
        exit.setBounds(468, 640, 250, 70);
        exit.setBackground(new Color(0xdae3ff));
        exit.setForeground(new Color(0x031930));
        exit.setBorder(BorderFactory.createLineBorder(new Color(0x031930), 3));
        exit.addActionListener(this);
        bgPanel.add(exit);

        setVisible(true);

        if (!isSongPlaying) {
            playMusic("bgm/MainMusic.wav");
            isSongPlaying = true;
        }

}

    private static void playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.start();
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Stop music
    private static void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            musicClip.close();
        }
    }

    private Image randomBG() {
        String[] backgrounds = {
                "background/mm1.jpeg",
                "background/mm2.jpeg",
                "background/mm3.jpeg",
                "background/mm4.jpeg"
        };

        Random random = new Random();
        String randomImage = backgrounds[random.nextInt(backgrounds.length)];
        return new ImageIcon(randomImage).getImage();
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == exit) {
            stopMusic();
            this.dispose();
        } else if (e.getSource() == play) {
            this.dispose();
            new CharacterSelect();
        } else if (e.getSource() == settings) {
            stopMusic();
            this.dispose();
            new Settings();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu());
    }
}
