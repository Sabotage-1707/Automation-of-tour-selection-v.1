package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shift {
    private TranslateTransition tt;


    public Shift(Node node, int millis){
        tt = new TranslateTransition(Duration.millis(millis),node);


    }
    public void leftShift(Double length){
        tt.setFromX(length);
        tt.setToX(0f);
    }
    public void rightShift(Double length){
        tt.setFromX(0f);
        tt.setToX(length);
    }
    public void playAnim2(){
        tt.setRate(-1);
        tt.playFromStart();
    }

    public void playAnim(){
        tt.setRate(1);
        tt.playFromStart();
    }
}