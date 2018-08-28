import base.GameObjectManager;
import scene.BossScene;
import scene.SceneManager;
import scene.StartScene;
import scene.VictoryScene;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameCanvas extends JPanel {
    private BufferedImage backBuffered;
    private Graphics graphics;

    public GameCanvas() {
        this.setSize(1024, 600);
        setupBackBuffered();

        SceneManager.instance.changeScene(new StartScene());
        this.setVisible(true);
    }

    private void setupBackBuffered() {
        this.backBuffered = new BufferedImage(1024, 600, BufferedImage.TYPE_INT_ARGB);
        this.graphics = this.backBuffered.getGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(this.backBuffered, 0, 0, null);
    }

    public void renderAll() {
        GameObjectManager.instance.renderAll(graphics);
        this.repaint();
    }

    public void runAll() {
        GameObjectManager.instance.runAll();

        SceneManager.instance.performChangeSceneIfNeeded();
    }
}
