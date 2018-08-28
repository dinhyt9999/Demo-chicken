package game.player;

import base.GameObject;
import base.GameObjectManager;
import renderer.ImageRenderer;
import renderer.RectRenderer;

import java.awt.*;

public class LevelBullet extends GameObject {
    private int preForce;

    public LevelBullet() {
        preForce=-1;
        this.renderer = new ImageRenderer("clone-chickenshot-_-project-CI8-master/image/BulletLevel/cola.png", 25, 25);
    }
    private String linkImage(Player player){
        switch (player.force) {
            case 1: {
                return ("clone-chickenshot-_-project-CI8-master/image/BulletLevel/cola.png");
            }
            case 2: {
                return ("clone-chickenshot-_-project-CI8-master/image/BulletLevel/hot-dog.png");
            }
            case 3: {
               return ("clone-chickenshot-_-project-CI8-master/image/BulletLevel/pizza.png");
            }
            case 4: {
                return ("clone-chickenshot-_-project-CI8-master/image/BulletLevel/burger (1).png");
            }
            case 5: {
               return ("clone-chickenshot-_-project-CI8-master/image/BulletLevel/pizza (1).png");
            }
            default:{
                return ("clone-chickenshot-_-project-CI8-master/image/BulletLevel/cola.png");
            }
        }
    }
    @Override
    public void run() {
        super.run();
        Player player= GameObjectManager.instance.findPlayer();
//        this.position.set(player.position.x - 10, player.position.y + 25);
        if(preForce!=player.force){
            this.position.set(300, 530);
            this.renderer=new ImageRenderer(linkImage(player),25,30);
            preForce=player.force;
        }

    }
}
