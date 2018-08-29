package utils;

import base.GameObject;
import input.KeyboardEvent;
import renderer.TextRenderer;
import scene.EnemyScene;
import scene.SceneManager;

import javax.sound.sampled.Clip;
import java.awt.*;

public class StartGamePressSpaceText extends GameObject {
    private Clip clip=Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/press.wav");

    public StartGamePressSpaceText() {
        this.position.set(100, 350);
        this.renderer = new TextRenderer(
                "Press space to start the game!",
                Color.WHITE,
                "Agency FB",
                60
        );
    }

    @Override
    public void run() {
        if (KeyboardEvent.instance.isSpace){
            this.clip.loop(1);
            this.clip.start();
            SceneManager.instance.changeScene(new EnemyScene());
        }
    }
}
