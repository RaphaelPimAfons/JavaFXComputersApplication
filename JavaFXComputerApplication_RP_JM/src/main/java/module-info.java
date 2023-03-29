module com.example.javafxcomputerapplication_rp_jm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxcomputerapplication_rp_jm to javafx.fxml;
    exports com.example.javafxcomputerapplication_rp_jm;
    exports com.example.javafxcomputermgnt;
    opens com.example.javafxcomputermgnt to javafx.fxml;
}