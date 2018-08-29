package game.enemy.enemybehind;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.BulletPlayer;
import game.player.Player;
import physic.BoxCollider;
import physic.HitPoints;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import utils.Utils;

import javax.sound.sampled.Clip;
import java.awt.*;

public class EnemyBehind extends GameObject implements PhysicBody, HitPoints {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    private int hitPoints;
    private static final int hp = 2;
    private Clip clipDestroyed;

    public double angle = 0.0;
    // Up = 1, Down = 0
    public int upDown = 1;
    // Left = 1, Right = 0
    public int leftRight = 1;

    public EnemyBehind() {
        this.hitPoints = hp;
        this.clipDestroyed = Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/enemy_destroyed.wav");
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(50, 50);
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/bird.png", 50, 50);
        this.attributes.add(new EnemyBehindMove());
        this.runHitObject = new RunHitObject(Player.class);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(velocity);
        this.boxCollider.position.set(this.position.x - 25, this.position.y - 25);
        this.runHitObject.run(this);
        if (this.position.x > 1024 || this.position.x < 0) {
            this.angle = 0.0;
            this.hitPoints=hp;
            this.isAlive = false;
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
        this.getHitPoint(gameObject);
        if (this.hitPoints <= 0) {
            this.angle = 0.0;
            this.hitPoints = hp;
            this.clipDestroyed.loop(1);
            this.clipDestroyed.start();
            GameObjectManager.instance.score += 20;
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
