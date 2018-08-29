package game.enemy.meteor;

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

public class Meteor extends GameObject implements PhysicBody, HitPoints {
    public Vector2D velocity;

    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    private int hitPoints;
    private static final int hp = 8;
    private Clip clipDestroyed= Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/enemy_destroyed.wav");
    public Meteor() {
        this.hitPoints = hp;
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/asteroid.png", 70, 70);
        this.boxCollider = new BoxCollider(70, 70);
        this.runHitObject = new RunHitObject(Player.class);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set((float) (this.position.x - 35), (float) (this.position.y - 35));
        this.runHitObject.run(this);

        if (this.position.y > 600){
            this.hitPoints=hp;
            this.isAlive = false;
        }
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
            GameObjectManager.instance.score += 100;
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
