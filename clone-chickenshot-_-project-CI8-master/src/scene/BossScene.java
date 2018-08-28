package scene;

import base.GameObjectManager;
import game.background.Background;
import game.enemy.boss.Boss;
import game.gift.CreateBulletGift;
import game.player.*;
import game.score.Score;

public class BossScene implements Scene {

    @Override
    public void init() {
        GameObjectManager.instance.add(new Background());

        setupPlayer();

        setupBoss();

        GameObjectManager.instance.add(new BagEnegyBullet());
        GameObjectManager.instance.add(new EnegyBullet());
        GameObjectManager.instance.add(new BulletEnergyText());

        GameObjectManager.instance.add(new Score());

        GameObjectManager.instance.add(new HitPointPlayer());
        GameObjectManager.instance.add(new HPText());

        GameObjectManager.instance.add(new CreateBulletGift());
    }

    private void setupPlayer() {
        Player player = GameObjectManager.instance.recycle(Player.class);
        player.position.set(200, 300);
    }

    private void setupBoss() {
        Boss boss = GameObjectManager.instance.recycle(Boss.class);
        boss.position.set(500, 100);
        boss.velocity.set(1, 0);
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
    }
}
