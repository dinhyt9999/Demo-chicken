package game.player;

import base.GameObject;
import renderer.TextRenderer;

import java.awt.*;

public class BulletEnergyText extends GameObject {

    public BulletEnergyText() {
        this.position.set(790, 545);
        this.renderer = new TextRenderer(
                "Energy",
                Color.YELLOW,
                "Agency FB",
                25
        );
    }
}
