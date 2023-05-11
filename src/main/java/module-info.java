module br.edu.cefsa.assistentepessoalcorretoresimobiliarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires java.desktop;
    opens br.edu.cefsa.assistentepessoalcorretoresimobiliarios to javafx.fxml;
    opens br.edu.cefsa.model to javafx.fxml;
    exports br.edu.cefsa.assistentepessoalcorretoresimobiliarios;
    exports br.edu.cefsa.model;
}
