package utils;

import base.GameObject;
import renderer.TextRenderer;

import java.awt.*;

public class VictoryText extends GameObject {

    public VictoryText() {
        this.position.set(10, 250);
        this.renderer = new TextRenderer(
                "CHICKEN DINNER!",
                Color.WHITE,
                "Agency FB",
                100
        );
    }
}
