package game.enemy.boss;

import action.*;
import base.FrameCounter;
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
import scene.SceneManager;
import scene.VictoryScene;

public class Boss extends GameObject implements PhysicBody, HitPoints {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    private int hitPoints;
    private static final int hp = 10;

    public BossRoundShoot bossRoundShoot;

    public Boss() {
        this.hitPoints = hp;
        this.velocity = new Vector2D();
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/alien.png", 200, 200);
        this.boxCollider = new BoxCollider(200, 200);
        this.runHitObject = new RunHitObject(Player.class);

        this.attributes.add(new BossTripleShoot());
        this.attributes.add(new BossMissileShoot());

        this.bossRoundShoot = new BossRoundShoot();

        this.configAction();
    }

    public void configAction() {
        Action moveAction = new ActionAdapter() {
            FrameCounter frameCounter = new FrameCounter(50);

            @Override
            public boolean run(GameObject owner) {
                Boss boss = (Boss) owner;
                if (owner.position.addUp(boss.velocity).x > 924) ((Boss) owner).velocity.set(-1, 0);
                if (owner.position.addUp(boss.velocity).x < 100) ((Boss) owner).velocity.set(1, 0);
                owner.position.addUp(boss.velocity);
                return frameCounter.checkCounter();
            }

            @Override
            public void reset() {
                this.frameCounter.resetCount();
            }
        };

        Action roundShootAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                Boss boss = (Boss) owner;
                boss.bossRoundShoot.run(boss);
                return true;
            }
        };

        this.addAction(
                new RepeatActionForever(
                        new SequenceAction(
                                moveAction,
                                roundShootAction,
                                new WaitAction(40)
                        )
                )
        );
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position.x - 100, this.position.y - 100);
        this.runHitObject.run(this);
    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.getHitPoint(gameObject);
        if (this.hitPoints <= 0) {
            SceneManager.instance.changeScene(new VictoryScene());
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
