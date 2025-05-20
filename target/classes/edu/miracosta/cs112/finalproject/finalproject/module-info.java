module edu.miracosta.cs112.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens finalproject edu.miracosta.cs112.finalproject.finalproject to javafx.fxml;
    exports edu.miracosta.cs112.finalproject.finalproject;
    exports edu.miracosta.cs112.ListAdventures.controllers;
    opens edu.miracosta.cs112.ListAdventures.controllers to javafx.fxml;
    exports edu.miracosta.cs112.ListAdventures.models;
    opens edu.miracosta.cs112.ListAdventures.models to javafx.fxml;
}