package game.enemy.enemymatrix;

import base.Attribute;
import base.FrameCounter;
import base.GameObjectManager;
import game.enemy.BulletEnemy;
import utils.Utils;

import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyMatrixShoot implements Attribute<EnemyMatrix> {
    private Random random = new Random();
    private FrameCounter frameCounter = new FrameCounter(560 + random.nextInt(150));
    private Clip clip;

    public EnemyMatrixShoot() {
        this.clip = Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/Eshooting.wav");
    }

    @Override
    public void run(EnemyMatrix enemyMatrix) {

        if (this.frameCounter.checkCounter() ) {
            int shoot = random.nextInt(100);
            System.out.println(shoot);
            if(shoot==1){
                System.out.println("shoot");
                BulletEnemy bulletEnemy = GameObjectManager.instance.recycle(BulletEnemy.class);
                bulletEnemy.position.set(enemyMatrix.position);
                bulletEnemy.velocity.set(0, random.nextInt(2) + 1);
                this.frameCounter.resetCount();
                this.clip.loop(1);
                this.clip.start();
            }
        }
    }
}

