package game.enemy.enemytravel;

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

public class EnemyTravel extends GameObject implements PhysicBody, HitPoints {
    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    private int hitPoints;
    private Clip clipDestroyed= Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/enemy_destroyed.wav");
    private static final int hp = 1;
    public EnemyTravel() {
        this.hitPoints = hp;
        this.velocity = new Vector2D(2.5f, 2.5f);
        this.boxCollider = new BoxCollider(60, 60);
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/chickenegg.png", 60, 60);
        this.runHitObject = new RunHitObject(Player.class);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(velocity);
        this.boxCollider.position.set(this.position.x - 30, this.position.y - 30);
        this.runHitObject.run(this);

        if (this.velocity.x == -4f) {
            if (this.position.x < 0) this.isAlive = false;
        } else {
            if (this.position.x > 1024) this.isAlive = false;
        }

        if (this.position.y > 600) this.isAlive = false;
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
        this.getHitPoint(gameObject);
        if (this.hitPoints <= 0) {
            GameObjectManager.instance.score += 30;
            this.isAlive = false;
            this.clipDestroyed.loop(1);
            this.clipDestroyed.start();
            this.hitPoints=hp;
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
