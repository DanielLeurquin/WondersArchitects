package com.isep.architects.wondersarchitects.Animation;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpritePulseAnimation extends Transition {

    private ImageView sprite;

    private double growthRatio;

    private double baseWidth;

    private double baseHeight;


    public SpritePulseAnimation(ImageView sprite, double growthRatio){
        this.sprite = sprite;
        this.growthRatio = growthRatio-1;

        baseWidth = sprite.getFitWidth();
        baseHeight = sprite.getFitHeight();

        setInterpolator(Interpolator.LINEAR);
        setCycleDuration(new Duration(500));

    }

    @Override
    protected void interpolate(double v) {
        if(v<0.5){
            sprite.setFitWidth(baseWidth*(1+growthRatio*(2*v)));
            sprite.setFitHeight(baseWidth*(1+growthRatio*(2*v)));
        }else {
            sprite.setFitWidth(baseWidth*(1+growthRatio*(2*(1-v))));
            sprite.setFitHeight(baseWidth*(1+growthRatio*(2*(1-v))));
        }

    }
}
