package game.player;

import base.GameObject;
import renderer.TextRenderer;

import java.awt.*;

public class HPText extends GameObject {

    public HPText() {
        this.position.set(0, 545);
        this.renderer = new TextRenderer(
                "Health",
                Color.YELLOW,
                "Agency FB",
                25
        );
    }
}
