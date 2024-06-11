package com.test.images.extras;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {
    private static Alert alert;
    public static void summon(Alert.AlertType alertType, String message, Optional<String> title) {
        alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(message);
        title.ifPresent(alert::setTitle);
        alert.showAndWait();
    }
    public static boolean summon(String message,Optional<String> title) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        title.ifPresent(alert::setTitle);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }
}
