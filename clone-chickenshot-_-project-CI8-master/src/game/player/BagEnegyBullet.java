package game.player;

import base.GameObject;
import renderer.DrawRect;

import java.awt.*;

public class BagEnegyBullet extends GameObject {
    public BagEnegyBullet() {

        this.renderer = new DrawRect(Color.RED, 150, 25);
        this.position.set(850, 525);
    }
}
