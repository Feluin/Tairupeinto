package graphical;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Label;

public class Numberlabel extends Label {

    public Numberlabel(Integer integer) {
        this.setText(String.valueOf(integer));
        this.setMinSize(20, 20);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.CENTER);
        this.setPrefSize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }
}
