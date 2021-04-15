module telebookfx {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;

    exports telebook.main to javafx.graphics;
    opens telebook.controller to javafx.fxml;
    opens telebook.model to javafx.base;
}