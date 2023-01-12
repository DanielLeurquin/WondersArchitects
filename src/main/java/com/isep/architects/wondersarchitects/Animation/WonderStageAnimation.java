package com.isep.architects.wondersarchitects.Animation;


import com.isep.architects.wondersarchitects.wonders.WonderStage;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WonderStageAnimation extends Transition {

    private ImageView view;
    private Rectangle2D newImage;

    private double width;



    private WonderStage stage;

    boolean val = false;

    private double multiplier;

    public WonderStageAnimation(ImageView view, Rectangle2D newImage, WonderStage stage, double multiplier){
        this.newImage = newImage;
        this.view = view;
        this.width = view.getFitWidth();

        this.stage = stage;
        this.multiplier = multiplier;

        view.setPreserveRatio(false);
        setInterpolator(Interpolator.LINEAR);
        setCycleDuration(new Duration(1000));

    }

    @Override
    protected void interpolate(double v) {
        double newWidth = 0;
        double factor = 0;


        if(v<0.5){
            factor = 2*(0.5-v);
            newWidth = factor*this.width;
        }else {
            if(!val){
                view.setViewport(this.newImage);
                val = true;
            }
            factor = 2*(v-0.5);
            newWidth = factor*this.width;

        }

        this.view.setFitWidth(newWidth);
        this.view.setLayoutX(stage.getAxis()*multiplier-stage.getAxis()*multiplier*factor);

    }


}
