package game.enemy.enemymatrix;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.BulletPlayer;
import game.player.Player;
import game.score.Score;
import physic.BoxCollider;
import physic.HitPoints;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import renderer.OvalRenderer;
import utils.Utils;

import javax.sound.sampled.Clip;
import java.awt.*;

public class EnemyMatrix extends GameObject implements PhysicBody, HitPoints {
    public Vector2D velocity;
    public Vector2D temp;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    private int hitPoints;
    private static final int hp = 1;
    private Clip clipDestroyed= Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/enemy_destroyed.wav");
    public EnemyMatrix() {
        this.hitPoints = hp;
        this.velocity = new Vector2D(2.5f, 0);
        this.boxCollider = new BoxCollider(50, 50);
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/bird 2.png", 50, 50);
        this.temp = new Vector2D(this.position.x, this.position.y);
        this.attributes.add(new EnemyMatrixShoot());
        this.attributes.add(new EnemyMatrixMove());
        this.runHitObject = new RunHitObject(Player.class);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(velocity);
        this.boxCollider.position.set(this.position.x - 25, this.position.y - 25);
        this.attributes.add(new EnemyMatrixMove());
        this.runHitObject.run(this);

        if (this.position.y > 600) {
            this.isAlive = false;
            this.hitPoints=hp;
        }
    }

    @Override
    public void render(Graphics graphics) {
        super.render(graphics);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        getHitPoint(gameObject);
        if (this.hitPoints <= 0) {
            GameObjectManager.instance.score += 10;
            this.clipDestroyed.loop(1);
            this.clipDestroyed.start();
            this.hitPoints = hp;
            this.isAlive = false;
        }
    }

    @Override
    public void getHitPoint(GameObject gameObject) {
        if (gameObject instanceof Player)
            this.hitPoints = 0;
        if (gameObject instanceof BulletPlayer) {
            Player player = GameObjectManager.instance.findPlayer();
            this.hitPoints -= player.force;
        }
    }
}

