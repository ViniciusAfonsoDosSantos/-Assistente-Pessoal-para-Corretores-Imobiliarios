/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.AtendimentoDAO;
import br.edu.cefsa.DAO.ClienteDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Atendimento;
import br.edu.cefsa.model.Cliente;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class AtendimentoBoxController {

    private DialogPane dialog;

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
    private ComboBox<Cliente> cbCliente;

    @FXML
    private ComboBox<?> cbImovel;
    
     @FXML
    private Label lbErroAnotacao;

    @FXML
    private Label lbErroCliente;

    @FXML
    private Label lbErroImovel;

    Atendimento atendimento;

    public void setData(Atendimento atendimento) throws IOException {

        try {
            this.atendimento = atendimento;
            ClienteDAO dao = new ClienteDAO();
            cbCliente.setConverter(new StringConverter<Cliente>() {
                @Override
                public String toString(Cliente object) {
                    if (object == null) {
                        return "";
                    } else {
                        return object.getNome();
                    }
                }

                @Override
                public Cliente fromString(String string) {
                    return null;
                }
            });

            List<Cliente> listaClientes = dao.listar();
            ObservableList ObList = FXCollections.observableList(listaClientes);
            cbCliente.setItems(ObList);
            ClienteDAO clientedao = new ClienteDAO();
            Cliente cliente = new Cliente(atendimento.getClienteID());
            cbCliente.getSelectionModel().select(clientedao.listarPorID(cliente));
            txtAnotacao.setText(atendimento.getAnotacoes());
            atendimentoTitulo.setText("Atendimento: " + atendimento.getAtendimentoId());
            txtAnotacao.setDisable(true);
            cbCliente.setDisable(true);
            cbImovel.setDisable(true);
            iconeRemover.setOnMouseClicked(mouseEvent -> {
                try {
                    deletarAtendimento();
                } catch (Exception ex) {
                    Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            iconeEditar.setOnMouseClicked(mouseEvent -> {
                try {
                    permiteAtualizar();
                } catch (Exception ex) {
                    Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (PersistenciaException ex) {
            Logger.getLogger(AtendimentoBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setNovoAtendimento(LocalDate data) throws IOException {

        try {
            atendimentoTitulo.setText("Atendimento ##");
            ClienteDAO dao = new ClienteDAO();

            cbCliente.setConverter(new StringConverter<Cliente>() {
                @Override
                public String toString(Cliente object) {

                    if (object == null) {
                        return "";
                    } else {
                        return object.getNome();
                    }
                }

                @Override
                public Cliente fromString(String string) {
                    return null;
                }
            });
            List<Cliente> listaClientes = dao.listar();
            ObservableList ObList = FXCollections.observableList(listaClientes);
            cbCliente.setItems(ObList);
            txtAnotacao.setDisable(false);
            cbCliente.setDisable(false);
            cbImovel.setDisable(false);
            atendimentoBox.getStyleClass().add("pane_boxcliente_selecionado");
            iconeEditar.setGlyphName("CHECK");
            iconeEditar.setStyleClass("icones_verde");
            iconeEditar.setOnMouseClicked(mouseEvent -> {

                try {
                    inserir(data);
                    permiteAtualizar();
                } catch (Exception ex) {
                    Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });

        } catch (PersistenciaException ex) {
            Logger.getLogger(AtendimentoBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inserir(LocalDate data) throws IOException {

        try {
            Cliente cliente = cbCliente.getValue();
            Atendimento atendimentoNovo = new Atendimento(data, txtAnotacao.getText(), cliente.getClienteId(), 1);
            AtendimentoDAO dao = new AtendimentoDAO();
            dao.inserir(atendimentoNovo);
            this.atendimento = atendimentoNovo;
            iconeRemover.setOnMouseClicked(mouseEvent -> {
                try {
                    deletarAtendimento();
                } catch (Exception ex) {
                    Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (PersistenciaException ex) {
            Logger.getLogger(AtendimentoBoxController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AtendimentoBoxController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void alterar() throws IOException {

        try {
            Cliente cliente = cbCliente.getValue();
            AtendimentoDAO dao = new AtendimentoDAO();
            Atendimento atendimentoAlterar = new Atendimento(atendimento.getAtendimentoId(), atendimento.getDataAtendimento(), txtAnotacao.getText(), cliente.getClienteId(), atendimento.getImovelID());
            dao.alterar(atendimentoAlterar);
            this.atendimento = atendimentoAlterar;
        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletarAtendimento() throws PersistenciaException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deletar Atendimento");
        alert.setContentText("Confirma a exclusão do Atendimento?");
        dialog = alert.getDialogPane();
        dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
        dialog.getStyleClass().add("dialog");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            AtendimentoDAO dao = new AtendimentoDAO();
            System.out.println("cliente => " + atendimento);
            dao.remover(atendimento);
        }
    }
    //

    private void permiteAtualizar() throws IOException {

        mudaIconeFuncao();
        txtAnotacao.setDisable(false);
        cbCliente.setDisable(false);
        cbImovel.setDisable(false);
        atendimentoBox.getStyleClass().add("pane_boxcliente_selecionado");

    }

    private void permiteSalvar() throws IOException {

        mudaIconeFuncao();
        alterar();
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
