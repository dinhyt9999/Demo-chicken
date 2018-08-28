package game.enemy.boss;

import base.*;
import game.enemy.BulletEnemy2;
import utils.Utils;

import javax.sound.sampled.Clip;

public class BossRoundShoot extends GameObject {

    private Clip clip = Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/Eshooting.wav");

    public void run(Boss gameObject) {
        for (double angle = 0.0; angle < 360.0; angle += 360 / 15) {
            BulletEnemy2 bulletEnemy = GameObjectManager.instance.recycle(BulletEnemy2.class);
            bulletEnemy.position.set(gameObject.position);
            bulletEnemy.velocity.set(new Vector2D(2, 0).rotate(angle));
        }

        this.clip.loop(1);
        this.clip.start();
    }
}
