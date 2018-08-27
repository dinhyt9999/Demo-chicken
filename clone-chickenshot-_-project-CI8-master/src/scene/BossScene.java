package scene;

import base.GameObjectManager;
import game.background.Background;
import game.enemy.boss.Boss;
import game.gift.CreateBulletGift;
import game.player.BagEnegyBullet;
import game.player.EnegyBullet;
import game.player.HitPointPlayer;
import game.player.Player;
import game.score.Score;

public class BossScene implements Scene {

    @Override
    public void init() {
        GameObjectManager.instance.add(new Background());

        setupPlayer();

        setupBoss();

        GameObjectManager.instance.add(new BagEnegyBullet());
        GameObjectManager.instance.add(new EnegyBullet());

        GameObjectManager.instance.add(new Score());

        GameObjectManager.instance.add(new HitPointPlayer());
        GameObjectManager.instance.add(new CreateBulletGift());
    }

    private void setupPlayer() {
        Player player = GameObjectManager.instance.recycle(Player.class);
        player.position.set(200, 300);
    }

    private void setupBoss() {
        Boss boss = GameObjectManager.instance.recycle(Boss.class);
        boss.position.set(400, 50);
    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
    }
}
