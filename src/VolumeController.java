import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class VolumeController {

    private static VolumeController instance;
    private Clip clip;
    private FloatControl volumeControl;

    // Singleton pattern to ensure only one instance
    private VolumeController() {
        try {
            File audioFile = new File("bgm/MainMusic.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VolumeController getInstance() {
        if (instance == null) {
            instance = new VolumeController();
        }
        return instance;
    }

    public void setVolume(int volume) {
        if (volumeControl != null) {
            if (volume == 0) {
                volumeControl.setValue(volumeControl.getMinimum());
            } else {
                float volumeValue = (float) volume / 100 * volumeControl.getMaximum();
                volumeControl.setValue(volumeValue);
            }
        }
    }

}
