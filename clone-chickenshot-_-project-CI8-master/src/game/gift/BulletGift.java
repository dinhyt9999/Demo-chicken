package game.gift;

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
import renderer.OvalRenderer;
import utils.Utils;

import javax.sound.sampled.Clip;
import java.awt.*;

public class BulletGift extends GameObject implements PhysicBody, HitPoints {
    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    private int hitPoints;
    private Player player;
    //private Clip clip;


    public BulletGift() {
        //  this.clip=Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/levelup.wav");
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/power.png", 25, 25);
        this.boxCollider = new BoxCollider(25, 25);
        this.runHitObject = new RunHitObject(Player.class);
        this.hitPoints = 1;
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set((float) (this.position.x - 12.5), (float) (this.position.y - 12.5));
        this.runHitObject.run(this);
        if (this.position.y > 600) this.isAlive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        getHitPoint(gameObject);
        if (this.hitPoints <= 0) {
            this.isAlive = false;
        }

    }

    @Override
    public void getHitPoint(GameObject gameObject) {
        if (gameObject instanceof Player) {
            player = GameObjectManager.instance.findPlayer();
            if (player.force < 6) {
                player.force++;
//                this.clip.loop(1);
//                this.clip.start();
            }
            this.hitPoints = 0;
        }
    }


}
