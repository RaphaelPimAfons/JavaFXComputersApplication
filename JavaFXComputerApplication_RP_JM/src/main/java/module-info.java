module com.example.javafxcomputerapplication_rp_jm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxcomputerapplication_rp_jm to javafx.fxml;
    exports com.example.javafxcomputerapplication_rp_jm;
}