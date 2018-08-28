package game.enemy;

import base.GameObject;
import base.Vector2D;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;

public class BulletEnemy2 extends GameObject implements PhysicBody {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;

    public BulletEnemy2() {
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(20, 20);
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/easter-egg.png", 20, 20);
        this.runHitObject = new RunHitObject(Player.class);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 10, this.position.y - 10);
        this.runHitObject.run(this);

        if (this.position.x > 1024 || this.position.x < 0) this.isAlive = false;
        if (this.position.y > 600 || this.position.y < 0) this.isAlive = false;
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
    }
}

