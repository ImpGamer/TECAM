package com.test.images.extras;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Validation {
    public static boolean containsLabel(VBox vBox) {
        for(Node node: vBox.getChildren()) {
            if(node instanceof Label) {
                return true;
            }
        }
        return false;
    }
}
