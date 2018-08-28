package game.player;

import base.GameObject;
import renderer.DrawRect;

import java.awt.*;

public class BagHpPlayer extends GameObject {
    public BagHpPlayer() {
        this.renderer = new DrawRect(Color.RED, 150, 25);
        this.position.set(50, 525);
    }
}
