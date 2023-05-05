/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.CalendarioAtendimento;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class AtendimentoBoxController {

    @FXML
    private AnchorPane atendimentoBox;

    @FXML
    private Label atendimentoTitulo;

    @FXML
    private FontAwesomeIconView iconeEditar;

    @FXML
    private FontAwesomeIconView iconeRemover;

    @FXML
    private TextArea txtAnotacao;

    @FXML
    private ComboBox<String> cbCliente;

    @FXML
    private ComboBox<?> cbImovel;

    public void setData(CalendarioAtendimento atendimento) throws IOException {

        txtAnotacao.setText(atendimento.getAnotacao());
        atendimentoTitulo.setText("Atendimento ##");
        cbCliente.setValue(atendimento.getCliente().getNome());
        txtAnotacao.setDisable(true);
        cbCliente.setDisable(true);
        cbImovel.setDisable(true);
        iconeEditar.setOnMouseClicked(mouseEvent -> {
            try {
                permiteAtualizar();
            } catch (Exception ex) {
                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    public void setNovoAtendimento() throws IOException {

        atendimentoTitulo.setText("Atendimento ##");
        txtAnotacao.setDisable(false);
        cbCliente.setDisable(false);
        cbImovel.setDisable(false);
        atendimentoBox.getStyleClass().add("pane_boxcliente_selecionado");
        iconeEditar.setGlyphName("CHECK");
        iconeEditar.setStyleClass("icones_verde");
        iconeEditar.setOnMouseClicked(mouseEvent -> {

            try {
                permiteSalvar();
            } catch (Exception ex) {
                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    @FXML
    private void permiteAtualizar() throws IOException {

        mudaIconeFuncao();
        txtAnotacao.setDisable(false);
        cbCliente.setDisable(false);
        cbImovel.setDisable(false);
        atendimentoBox.getStyleClass().add("pane_boxcliente_selecionado");

    }

    @FXML
    private void permiteSalvar() throws IOException {

        mudaIconeFuncao();
        txtAnotacao.setDisable(true);
        cbCliente.setDisable(true);
        cbImovel.setDisable(true);
        atendimentoBox.getStyleClass().clear();
        atendimentoBox.getStyleClass().add("pane_boxcliente");
    }

    @FXML
    private void mudaIconeFuncao() throws IOException {

        if ("EDIT".equals(iconeEditar.getGlyphName())) {
            iconeEditar.setGlyphName("CHECK");
            iconeEditar.getStyleClass().clear();
            iconeEditar.setStyleClass("icones_verde");
            iconeEditar.setOnMouseClicked(mouseEvent -> {

                try {
                    permiteSalvar();
                } catch (Exception ex) {
                    Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        } else {
            iconeEditar.setGlyphName("EDIT");
            iconeEditar.getStyleClass().clear();
            iconeEditar.setStyleClass("icones");
            iconeEditar.setOnMouseClicked(mouseEvent -> {

                try {
                    permiteAtualizar();
                } catch (Exception ex) {
                    Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
        }
    }
}
