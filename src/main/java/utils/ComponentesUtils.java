/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author gabri
 */
public class ComponentesUtils {
    public static Alert criaAlerta(AlertType type, String title, String content){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        if(!content.isBlank() || !content.isEmpty())
            alert.setContentText(content);
        return alert;
    }
    
    public static void setTexto(TextField field, String texto){
        field.setText(texto);
    }
    
    public static void setTexto(Label field, String texto){
        field.setText(texto);
    }
}
