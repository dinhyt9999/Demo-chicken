package utils;

import base.GameObject;
import input.KeyboardEvent;
import renderer.TextRenderer;
import scene.SceneManager;
import scene.StartScene;

import javax.sound.sampled.Clip;
import java.awt.*;

public class EndGamePressSpaceText extends GameObject {
    private Clip clip=Utils.loadAudio("clone-chickenshot-_-project-CI8-master/sound/press.wav");
    public EndGamePressSpaceText() {
        this.position.set(60, 480);
        this.renderer = new TextRenderer(
                "Press space to return to the start menu",
                Color.WHITE,
                "Agency FB",
                50
        );
    }

    @Override
    public void run() {
        if (KeyboardEvent.instance.isSpace) {
            this.clip.loop(1);
            this.clip.start();
            SceneManager.instance.changeScene(new StartScene());
        }
    }
}
