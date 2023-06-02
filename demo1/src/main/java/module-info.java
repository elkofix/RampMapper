module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires junit;


    opens com.example.demo1 to javafx.fxml;
    opens com.example.demo1.model to com.google.gson;
    exports com.example.demo1;
    exports com.example.demo1.test to junit;
    exports com.example.demo1.controller;
    opens com.example.demo1.controller to javafx.fxml;
}