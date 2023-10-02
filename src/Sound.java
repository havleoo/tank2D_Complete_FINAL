import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    private static Clip bulletBrickSE, bulletTankSE;
    private static Clip fireSE;
    private static Clip explodeBrickSE, explodeTankSE;
    private static Clip pauseSE, startSE;
    private static Clip gameOverSE;

    private static boolean initialized = false;

    /*
     * Khoi tao Sound
     */
    public static void initialize() {
        System.out.println("Initialize: ");
        try {
            File bBSE = new File("sound/bullet_hit_2.wav");
            bulletBrickSE = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(bBSE);
            bulletBrickSE.open(ais);
            bulletBrickSE.setFramePosition(bulletBrickSE.getFrameLength());

            File bTSE = new File("sound/bullet_hit_1.wav");
            bulletTankSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(bTSE);
            bulletTankSE.open(ais);
            bulletTankSE.setFramePosition(bulletTankSE.getFrameLength());

            File fSE = new File("sound/bullet_shot.wav");
            fireSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(fSE);
            fireSE.open(ais);
            fireSE.setFramePosition(fireSE.getFrameLength());

            File eBSE = new File("sound/explosion_1.wav");
            explodeBrickSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(eBSE);
            explodeBrickSE.open(ais);
            explodeBrickSE.setFramePosition(explodeBrickSE.getFrameLength());

            File eTSE = new File("sound/explosion_2.wav");
            explodeTankSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(eTSE);
            explodeTankSE.open(ais);
            explodeTankSE.setFramePosition(explodeTankSE.getFrameLength());

            File pSE = new File("sound/pause.wav");
            pauseSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(pSE);
            pauseSE.open(ais);
            pauseSE.setFramePosition(pauseSE.getFrameLength());

            File sSE = new File("sound/stage_start.wav");
            startSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(sSE);
            startSE.open(ais);
            startSE.setFramePosition(startSE.getFrameLength());

            File gOSE = new File("sound/game_over.wav");
            gameOverSE = AudioSystem.getClip();
            ais = AudioSystem.getAudioInputStream(gOSE);
            gameOverSE.open(ais);
            gameOverSE.setFramePosition(gameOverSE.getFrameLength());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
        initialized = true;
    }

    /*
     * Play sound for bullet hit the brick
     */
    public static void BulletHitBrick() {
        if (initialized) {
            bulletBrickSE.loop(1);
        } else {
            initialize();
            bulletBrickSE.loop(1);
        }
    }

    /*
     * Play sound for bullet hit the tank
     */
    public static void BulletHitTank() {
        if (initialized) {
            bulletTankSE.loop(1);
        } else {
            initialize();
            bulletTankSE.loop(1);
        }
    }

    /*
     * Play sound for bullet fire
     */
    public static void BulletFire() {
        if (initialized) {
            fireSE.loop(1);
        } else {
            initialize();
            fireSE.loop(1);
        }
    }

    /*
     * Play sound for brick when it's exploded
     */
    public static void ExplodeBrick() {
        if (initialized) {
            explodeBrickSE.loop(1);
        } else {
            initialize();
            explodeBrickSE.loop(1);
        }
    }

    /*
     * Play sound for tank when it's exploded
     */
    public static void ExplodeTank() {
        if (initialized) {
            explodeTankSE.loop(1);
        } else {
            initialize();
            explodeTankSE.loop(1);
        }
    }

    /*
     * Play sound when game is paused
     */
    public static void Paused() {
        if (initialized) {
            pauseSE.loop(1);
        } else {
            initialize();
            pauseSE.loop(1);
        }
    }

    /*
     * Play sound when the stage or game is started
     */
    public static void Started() {
        if (initialized) {
            startSE.loop(1);
        } else {
            initialize();
            startSE.loop(1);
        }
    }

    /*
     * Play sound when game over
     */
    public static void GameOver() {
        if (initialized) {
            gameOverSE.loop(1);
        } else {
            initialize();
            gameOverSE.loop(1);
        }
    }
}
