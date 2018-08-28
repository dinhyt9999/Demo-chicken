package game.enemy.boss;

import base.Attribute;
import base.FrameCounter;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.BulletEnemy;
import utils.Utils;

import javax.sound.sampled.Clip;

public class BossRoundShoot implements Attribute<Boss> {

    private FrameCounter frameCounter = new FrameCounter(300);
    private Clip clip=Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/Eshooting.wav");


    @Override
    public void run(Boss gameObject) {
        if (this.frameCounter.checkCounter()) {
            for (double angle = 0.0; angle < 360.0; angle += 360 / 15) {
                BulletEnemy bulletEnemy = GameObjectManager.instance.recycle(BulletEnemy.class);
                bulletEnemy.position.set(gameObject.position);
                bulletEnemy.velocity.set(new Vector2D(2, 0).rotate(angle));
            }
            this.clip.loop(1);
            this.clip.start();
            this.frameCounter.resetCount();
        }
    }
}
