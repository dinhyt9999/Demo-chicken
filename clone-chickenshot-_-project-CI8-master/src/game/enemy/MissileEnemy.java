package game.enemy;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.player.Player;
import physic.BoxCollider;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import renderer.OvalRenderer;

import java.awt.*;

public class MissileEnemy extends GameObject implements PhysicBody {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;

    public MissileEnemy() {
        this.velocity = new Vector2D();
        this.boxCollider = new BoxCollider(50, 50);
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/rocket.png", 50, 50);
        this.runHitObject = new RunHitObject(Player.class);
    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 25f, this.position.y - 25f);

        Player player = GameObjectManager.instance.findPlayer();
        this.update(player.position);

        this.runHitObject.run(this);
    }

    private void update(Vector2D position) {
        this.velocity.set(
                position.subtract(this.position).normalize()
        ).multiply(1.5f);
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
