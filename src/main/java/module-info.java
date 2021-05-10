module com.mycompany.biblio {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.mycompany.biblio to javafx.fxml;
    exports com.mycompany.biblio;
}
