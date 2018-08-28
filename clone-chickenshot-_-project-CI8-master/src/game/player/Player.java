package game.player;

import base.GameObject;
import base.Vector2D;
import game.enemy.BulletEnemy;
import game.enemy.enemymatrix.EnemyMatrix;
import game.enemy.enemytravel.EnemyTravel;
import game.gift.BulletGift;
import physic.BoxCollider;
import physic.HitPoints;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import scene.GameOverScene;
import scene.SceneManager;
import utils.Utils;

import javax.sound.sampled.Clip;

public class Player extends GameObject implements PhysicBody, HitPoints {
    public Vector2D velocity;
    public BoxCollider boxCollider;
    public int angle;
    private Clip clip;
    public int force;
    public int hitPoints;
    public RunHitObject runHitObject;

    public Player() {
        this.clip = Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/hurt.wav");
        this.hitPoints = 300;
        this.force = 1;
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(60, 50);
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/spaceship.png", 60, 50);
        this.attributes.add(new PlayerShoot());
        this.attributes.add(new PlayerMove());
        this.runHitObject = new RunHitObject(BulletGift.class);
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position.x - 30, this.position.y - 25);
        this.runHitObject.run(this);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        getHitPoint(gameObject);
        if (this.hitPoints <= 0)
            SceneManager.instance.changeScene(new GameOverScene());
    }

    @Override
    public void getHitPoint(GameObject gameObject) {
        boolean hited=false;
        if (gameObject instanceof EnemyMatrix) {
            this.hitPoints -= 3;
            if (this.force > 1) this.force--;
            hited=true;
        }
        if (gameObject instanceof EnemyTravel) {
            this.hitPoints -= 3;
            if (this.force > 1) this.force--;
            hited=true;
        }
        if (gameObject instanceof BulletEnemy) {
            this.hitPoints--;
            if (this.force > 1) this.force--;
           hited=true;
        }
        if(hited){
            this.clip.loop(1);
            this.clip.start();
        }
    }
}
