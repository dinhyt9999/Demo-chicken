package game.player;

import base.Attribute;
import base.FrameCounter;
import base.GameObjectManager;
import input.KeyboardEvent;
import utils.Utils;

import javax.sound.sampled.Clip;

public class PlayerShoot implements Attribute<Player> {
    private FrameCounter frameCounter;
    private float enegy = 0;
    private Clip clip;

    public PlayerShoot() {
        this.frameCounter = new FrameCounter(10);
        this.clip = Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/Pshooting.wav");
    }

    @Override
    public void run(Player gameObject) {
        //
        if (KeyboardEvent.instance.isSpace && this.frameCounter.checkCounter() && this.enegy < 40) {
            BulletPlayer bulletPlayer = GameObjectManager.instance.recycle(BulletPlayer.class);
            bulletPlayer.setImage();
            bulletPlayer.position.set(gameObject.position.x - 3, gameObject.position.y - 19);
            bulletPlayer.velocity.set(0, -4.5f);
            this.enegy++;
            this.frameCounter.resetCount();
            this.clip.loop(1);
            this.clip.start();
        }
        if (!KeyboardEvent.instance.isSpace && enegy > 0) {
            enegy -= 0.4;
        }
    }
}
