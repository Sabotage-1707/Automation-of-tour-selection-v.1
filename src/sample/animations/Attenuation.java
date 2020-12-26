package sample.animations;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Attenuation {
    private FadeTransition ft;
    public Attenuation(Node node){
        ft = new FadeTransition (Duration.millis(1900.0), node);
        node.setOpacity(1);
        ft.setFromValue(1);
        ft.setToValue(0);
    }

    public void playAnim(){
        ft.playFromStart();
    }
}
