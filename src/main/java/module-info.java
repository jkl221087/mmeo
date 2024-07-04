module com.example.memowebapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.memowebapp to javafx.fxml;
    exports com.example.memowebapp;
    exports com.example.memowebapp.dao;
    opens com.example.memowebapp.dao to javafx.fxml;
}