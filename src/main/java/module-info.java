module com.isep.architects.wondersarchitects {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.isep.architects.wondersarchitects to javafx.fxml;
    opens com.isep.architects.wondersarchitects.controllers to javafx.fxml;
    exports com.isep.architects.wondersarchitects;
    exports com.isep.architects.wondersarchitects.controllers to javafx.fxml;
}