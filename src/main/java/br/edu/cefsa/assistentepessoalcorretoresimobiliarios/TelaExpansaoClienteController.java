/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

/**
 *
 * @author Vinicius
 */
public class TelaExpansaoClienteController extends PadraoController{
    
    @FXML
    public void adicionarAnotacao() throws IOException{
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("telaAdicionarAnotacao.fxml"));
        DialogPane telaAdicionarAnotacao = fxmlLoader.load();
        telaAdicionarAnotacao.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
        telaAdicionarAnotacao.getStyleClass().add("dialog");
        
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(telaAdicionarAnotacao);
        dialog.setTitle("Adicionar Anotação");
        
        Optional<ButtonType> clickedButton = dialog.showAndWait();
        
        if(clickedButton.get() == ButtonType.OK){
            System.out.println("OKKKKKKKKKKKKKK");
            // Cógigo aqui!!!
        }
        
    }
}
