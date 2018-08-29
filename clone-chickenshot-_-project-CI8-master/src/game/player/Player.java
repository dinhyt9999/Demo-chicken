package game.player;

import base.GameObject;
import base.Vector2D;
import game.enemy.BulletEnemy;
import game.enemy.MissileEnemy;
import game.enemy.boss.Boss;
import game.enemy.enemybehind.EnemyBehind;
import game.enemy.enemymatrix.EnemyMatrix;
import game.enemy.enemytravel.EnemyTravel;
import game.enemy.growupenemy.EnemyGrowUp;
import game.enemy.meteor.Meteor;
import game.enemy.roundshootenemy.RoundShootEnemy;
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
    private Clip clipHurt;
    private Clip clipDestroyed;

    public int force;
    public int hitPoints;
    public RunHitObject runHitObject;

    public Player() {
        this.clipHurt = Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/hurt.wav");
        this.clipDestroyed=Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/player_destroyed.wav");
        this.hitPoints = 100;
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
        if (this.hitPoints <= 0){
            this.clipDestroyed.loop(1);
            this.clipDestroyed.start();
            SceneManager.instance.changeScene(new GameOverScene());
        }

    }

    @Override
    public void getHitPoint(GameObject gameObject) {
        boolean hited = false;
        if (gameObject instanceof EnemyMatrix
                || gameObject instanceof EnemyTravel
                || gameObject instanceof Meteor
                || gameObject instanceof EnemyGrowUp
                || gameObject instanceof RoundShootEnemy
                || gameObject instanceof EnemyBehind
                || gameObject instanceof Boss
        ) {
            this.hitPoints -= 3;
            if (this.force > 1) this.force--;
            hited = true;
        }
        if (gameObject instanceof BulletEnemy || gameObject instanceof MissileEnemy) {
            this.hitPoints--;
            if (this.force > 1) this.force--;
            hited = true;
        }
        if (hited) {
            this.clipHurt.loop(1);
            this.clipHurt.start();
        }
    }
}
