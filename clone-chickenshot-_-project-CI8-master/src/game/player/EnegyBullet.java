package game.player;

import base.GameObject;
import input.KeyboardEvent;
import renderer.RectRenderer;

import java.awt.*;

public class EnegyBullet extends GameObject {

    private float enegy = 0;

    public EnegyBullet() {
        this.renderer = new RectRenderer(Color.RED, 0, 25);
        this.position.set(851, 526);
    }

    public void run() {
        super.run();
        if (KeyboardEvent.instance.isSpace) {
            if (this.enegy < 149) {
                this.enegy += 0.3333333333;
                this.renderer = new RectRenderer(Color.RED, (int) enegy, 24);
            }
        } else {
            if (this.enegy > 0) {
                this.enegy -= 1.5;
                this.renderer = new RectRenderer(Color.RED, (int) enegy, 24);
            }
        }
    }
}
