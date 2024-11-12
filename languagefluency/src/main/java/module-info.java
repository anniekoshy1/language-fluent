module com.languagefluent {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires junit;
    requires jlayer;
    requires software.amazon.awssdk.core;
    requires software.amazon.awssdk.polly;
    requires software.amazon.awssdk.regions;

    opens com.languagefluent to javafx.fxml;
    exports com.languagefluent;

    opens com.model to javafx.fxml;
    exports com.model;
}
