package game.enemy.roundshootenemy;

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

public class RoundShootEnemy extends GameObject implements PhysicBody, HitPoints {
    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    private int hitPoints;
    private static final int hp = 5;
    private Clip clipDestroyed= Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/enemy_destroyed.wav");
    public RoundShootEnemy() {

        this.hitPoints = hp;
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/kraken (1).png", 100, 100);
        this.attributes.add(new RoundShootEnemyShoot());
        this.boxCollider = new BoxCollider(100, 100);
        this.runHitObject = new RunHitObject(Player.class);
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position.x - 50, this.position.y - 50);
        this.runHitObject.run(this);
        if (this.position.y > 600 || this.position.y < 0) this.isAlive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.getHitPoint(gameObject);
        if (this.hitPoints <= 0) {

            this.clipDestroyed.loop(1);
            this.clipDestroyed.start();
            GameObjectManager.instance.score += 50;
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
        System.out.println(this.hitPoints);
    }
}
