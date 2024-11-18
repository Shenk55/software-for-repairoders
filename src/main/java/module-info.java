module com.example.repairorder {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.repairorder to javafx.fxml;
    exports com.example.repairorder;
}