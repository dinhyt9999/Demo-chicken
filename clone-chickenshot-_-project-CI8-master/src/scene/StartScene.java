package scene;

import base.GameObjectManager;
import game.background.Background;
import utils.StartGamePressSpaceText;
import utils.StartGameText;

import javax.swing.*;


public class StartScene implements Scene {

    @Override
    public void init() {
        GameObjectManager.instance.recycle(Background.class);
        GameObjectManager.instance.recycle(StartGameText.class);
        GameObjectManager.instance.recycle(StartGamePressSpaceText.class);

    }

    @Override
    public void deinit() {
        GameObjectManager.instance.clear();
    }
}
