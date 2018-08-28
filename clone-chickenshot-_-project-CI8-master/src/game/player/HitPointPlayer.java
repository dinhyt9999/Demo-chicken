package game.player;


import base.GameObject;
import base.GameObjectManager;
import renderer.RectRenderer;

import java.awt.*;

public class HitPointPlayer extends GameObject {
    private int preHP;
    public HitPointPlayer() {
        preHP=-1;
        this.renderer = new RectRenderer(Color.GREEN, 20, 25);
    }


    @Override
    public void run() {
        super.run();
        Player player = GameObjectManager.instance.findPlayer();
//        this.position.set(player.position.x - 10, player.position.y + 25);
        if(player.hitPoints!=preHP){
            this.position.set(50, 525);
            this.renderer = new RectRenderer(Color.GREEN, player.hitPoints, 25);
            preHP=player.hitPoints;
        }

    }
}
