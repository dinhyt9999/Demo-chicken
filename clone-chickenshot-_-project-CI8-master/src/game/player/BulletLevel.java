package game.player;

import base.GameObject;
import base.GameObjectManager;
import renderer.RectRenderer;
import renderer.Renderer;

import java.awt.*;

public class BulletLevel extends GameObject {
    private int preForce;
    public BulletLevel() {
        preForce=-1;
        this.renderer = new RectRenderer(Color.LIGHT_GRAY, 20, 24);
    }
    @Override
    public void run() {
        super.run();
        Player player=GameObjectManager.instance.findPlayer();
//        this.position.set(player.position.x - 10, player.position.y + 25);
        if(player.force!=preForce){
            this.position.set(350, 527);
            this.renderer = new RectRenderer(Color.LIGHT_GRAY, player.force*20, 25);
            System.out.println(player.force);
            preForce=player.force;
        }
    }
}
