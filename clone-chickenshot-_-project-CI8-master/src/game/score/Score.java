package game.score;

import base.GameObject;
import base.GameObjectManager;
import renderer.TextRenderer;
import scene.BossScene;
import scene.SceneManager;

import java.awt.*;

public class Score extends GameObject {

    public String stringScore;

    public Score() {
        this.position.set(15, 40);
        this.renderer = new TextRenderer("0", Color.YELLOW, "Arial", 25);
    }

    @Override
    public void run() {
        super.run();
        stringScore = String.valueOf(GameObjectManager.instance.score);
        this.renderer = new TextRenderer(stringScore, Color.YELLOW, "Arial", 25);

        if (GameObjectManager.instance.score >= 900 && SceneManager.instance.isBossScene != 1) {
            SceneManager.instance.isBossScene = 1;
            SceneManager.instance.changeScene(new BossScene());
        }
    }
}
