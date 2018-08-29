package game.enemy.enemytravel;

import action.*;
import base.GameObject;
import base.GameObjectManager;
import base.Vector2D;

import java.util.Random;

public class CreateEnemyTravel extends GameObject {
    private int i;
    private Random random = new Random();
    private Vector2D tempPosition = new Vector2D().set(random.nextInt(2) * 1024, random.nextInt(100) + 50);
    private Vector2D tempVelocity = new Vector2D();

    public CreateEnemyTravel() {
        this.configAction();
    }

    public void configAction() {
        Action createAction = new ActionAdapter() {
            @Override
            public boolean run(GameObject owner) {
                if (tempPosition.x == 1024) {
                    tempVelocity.set(-4f, 2f);
                } else {
                    tempVelocity.set(4f, 2f);
                }
                for (i = 0; i <= 9; i++) {
                    EnemyTravel enemyTravel = GameObjectManager.instance.recycle(EnemyTravel.class);
                    enemyTravel.position.set(tempPosition.x - tempVelocity.x * i * 10, tempPosition.y - tempVelocity.y * i * 10);
                    enemyTravel.velocity.set(tempVelocity);
                }
                return true;
            }
        };

        this.addAction(
                new RepeatActionForever(
                        new SequenceAction(
                                new WaitAction(random.nextInt(1000) + 1000),
                                createAction
                        )
                )
        );
    }
}