module edu.miracosta.cs112.finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.miracosta.cs112.finalproject to javafx.fxml;
    exports edu.miracosta.cs112.finalproject;
}