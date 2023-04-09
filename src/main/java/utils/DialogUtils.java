/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

/**
 *
 * @author gabri
 */
public class DialogUtils {
    public DialogPane atribuiDialog(Alert alert, String stylesheet, String styleClass){
        DialogPane dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource(stylesheet).toString());
        dialog.getStyleClass().add(styleClass);
        return dialog;
    }
}
