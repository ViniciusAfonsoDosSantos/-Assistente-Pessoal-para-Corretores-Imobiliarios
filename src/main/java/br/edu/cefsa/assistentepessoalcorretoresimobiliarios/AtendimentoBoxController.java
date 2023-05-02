/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.CalendarioAtendimento;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class AtendimentoBoxController {

    @FXML
    private Label atendimentoTitulo;

    @FXML
    private FontAwesomeIconView iconeEditar;

    @FXML
    private FontAwesomeIconView iconeRemover;

    @FXML
    private TextArea txtAnotacao;

    public void setData(CalendarioAtendimento atendimento) throws IOException {

        txtAnotacao.setText(atendimento.getAnotacao());
        atendimentoTitulo.setText("Atendimento no dia: " + atendimento.getData().toLocalDate().toString());
        txtAnotacao.setEditable(false);
    }
    
    @FXML
    private void permiteAtualizar() throws IOException {
        
        txtAnotacao.setEditable(true);
        
    }

}
