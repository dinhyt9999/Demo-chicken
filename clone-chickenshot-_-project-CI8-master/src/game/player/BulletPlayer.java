package game.player;

import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;
import game.enemy.MissileEnemy;
import game.enemy.boss.Boss;
import game.enemy.enemybehind.EnemyBehind;
import game.enemy.meteor.Meteor;
import game.enemy.enemymatrix.EnemyMatrix;
import game.enemy.enemytravel.EnemyTravel;
import game.enemy.growupenemy.EnemyGrowUp;
import game.enemy.roundshootenemy.RoundShootEnemy;
import physic.BoxCollider;
import physic.HitPoints;
import physic.PhysicBody;
import physic.RunHitObject;
import renderer.ImageRenderer;
import renderer.OvalRenderer;

import java.awt.*;

public class BulletPlayer extends GameObject implements PhysicBody, HitPoints {
    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    public int force;

    public BulletPlayer() {
        //this.force = 1;
        this.velocity = new Vector2D();
        this.setImage();
        //  this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/bolt.png",30,50);
        this.boxCollider = new BoxCollider(40, 40);
        this.runHitObject = new RunHitObject(
                EnemyMatrix.class,
                EnemyTravel.class,
                RoundShootEnemy.class,
                EnemyGrowUp.class,
                Meteor.class,
                EnemyBehind.class,
                Boss.class,
                MissileEnemy.class
        );
    }

    public void setImage() {
        switch (GameObjectManager.instance.findPlayer().force) {
            case 1: {
                this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/BulletLevel/cola.png", 40, 40);
                break;
            }
            case 2: {
                this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/BulletLevel/hot-dog.png", 40, 40);
                break;
            }
            case 3: {
                this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/BulletLevel/pizza.png", 40, 40);
                break;
            }
            case 4: {
                this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/BulletLevel/burger (1).png", 40, 40);
                break;
            }
            case 5: {
                this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/BulletLevel/pizza (1).png", 50, 50);
                break;
            }
        }

    }

    @Override
    public void run() {
        super.run();
        this.position.addUp(this.velocity);
        this.boxCollider.position.set(this.position.x - 15, this.position.y - 25);
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

    @Override
    public void getHitPoint(GameObject gameObject) {

    }
}