package game.enemy.boss;

import base.Attribute;
import base.FrameCounter;
import base.GameObjectManager;
import game.enemy.MissileEnemy;
import utils.Utils;

import javax.sound.sampled.Clip;

public class BossMissileShoot implements Attribute<Boss> {

    private Clip clip;
    private FrameCounter frameCounter = new FrameCounter(300);

    public BossMissileShoot() {
        this.clip = Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/Eshooting.wav");
    }

    @Override
    public void run(Boss gameObject) {
        if (this.frameCounter.checkCounter()) {
            MissileEnemy missileEnemy = GameObjectManager.instance.recycle(MissileEnemy.class);
            missileEnemy.position.set(gameObject.position);
            this.clip.loop(1);
            this.clip.start();
            this.frameCounter.resetCount();
        }
    }
}
