package game.player;

import base.GameObject;
import renderer.DrawRect;

import java.awt.*;

public class BagBulletLevel extends GameObject {
    public BagBulletLevel() {
        this.renderer = new DrawRect(Color.LIGHT_GRAY, 100, 25);
        this.position.set(350, 525);
    }
}
