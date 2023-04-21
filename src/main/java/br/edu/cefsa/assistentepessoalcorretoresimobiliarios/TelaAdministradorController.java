/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.UsuarioDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Usuario;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class TelaAdministradorController extends PadraoController {

    public List<Usuario> userLIst;

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Usuario> tabelaUsuarios;

    @FXML
    private TableColumn<Usuario, String> columnNome;

    @FXML
    private TableColumn<Usuario, String> columnEmail;

    @FXML
    private TableColumn<Usuario, String> columnAcoes;

    ObservableList OBList = FXCollections.observableArrayList();

    private DialogPane dialog;

    private Usuario usuario = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            atualizaTabela();
            columnNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
            columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));

            //add cell of button edit 
            Callback<TableColumn<Usuario, String>, TableCell<Usuario, String>> cellFoctory = (TableColumn<Usuario, String> param) -> {
                // make cell containing buttons
                final TableCell<Usuario, String> cell = new TableCell<Usuario, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);

                        } else {

                            FontAwesomeIconView promoveIcon = new FontAwesomeIconView(FontAwesomeIcon.ARROW_UP);
                            FontAwesomeIconView rebaixaIcon = new FontAwesomeIconView(FontAwesomeIcon.ARROW_DOWN);
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.REMOVE);
                            
                            promoveIcon.setStyleClass("icones");
                            promoveIcon.setStyle(
                                    "-glyph-size:1.3em;"
                            );
                            rebaixaIcon.setStyleClass("icones");
                            rebaixaIcon.setStyle(
                                    "-glyph-size:1.3em;"
                            );
                            deleteIcon.setStyleClass("icones");
                            deleteIcon.setStyle(
                                    "-glyph-size:1.3em;"
                            );
                            
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                                try {
                                    
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Deletar Usuário");
                                    alert.setContentText("Confirma a exclusão do usuário?");
                                    dialog = alert.getDialogPane();
                                    dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
                                    dialog.getStyleClass().add("dialog");
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == ButtonType.OK) {

                                        UsuarioDAO dao = new UsuarioDAO();
                                        usuario = tabelaUsuarios.getSelectionModel().getSelectedItem();
                                        dao.remover(usuario);
                                        atualizaTabela();

                                        System.out.println("OKKKKKKKK");
                                    }

                                } catch (PersistenciaException ex) {
                                    Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            promoveIcon.setOnMouseClicked((MouseEvent event) -> {

                                try {
                                    
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Promover Usuário");
                                    alert.setContentText("Confirma a promoção do usuário?");
                                    dialog = alert.getDialogPane();
                                    dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
                                    dialog.getStyleClass().add("dialog");
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == ButtonType.OK) {

                                        UsuarioDAO dao = new UsuarioDAO();
                                        usuario = tabelaUsuarios.getSelectionModel().getSelectedItem();
                                        usuario.setTipo(true);
                                        dao.alterarTipo(usuario);
                                        atualizaTabela();

                                        System.out.println("OKKKKKKKK");
                                    }

                                } catch (PersistenciaException ex) {
                                    Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                             rebaixaIcon.setOnMouseClicked((MouseEvent event) -> {

                                try {
                                    
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Rebaixar Usuário");
                                    alert.setContentText("Confirma o rebaixamento do usuário?");
                                    dialog = alert.getDialogPane();
                                    dialog.getStylesheets().add(getClass().getResource("estiloPrincipal.css").toString());
                                    dialog.getStyleClass().add("dialog");
                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == ButtonType.OK) {

                                        UsuarioDAO dao = new UsuarioDAO();
                                        usuario = tabelaUsuarios.getSelectionModel().getSelectedItem();
                                        usuario.setTipo(false);
                                        dao.alterarTipo(usuario);
                                        atualizaTabela();

                                        System.out.println("OKKKKKKKK");
                                    }

                                } catch (PersistenciaException ex) {
                                    Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (SQLException ex) {
                                    Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });

                            HBox managebtn = new HBox(promoveIcon, rebaixaIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(promoveIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(rebaixaIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            setGraphic(managebtn);

                            setText(null);

                        }
                    }

                };

                return cell;
            };

            columnAcoes.setCellFactory(cellFoctory);
            tabelaUsuarios.setItems(OBList);
        } catch (Exception ex) {
            Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void atualizaTabela() throws PersistenciaException, SQLException {

        try {
            OBList.clear();
            UsuarioDAO dao = new UsuarioDAO();
            List<Usuario> userList = new ArrayList<Usuario>();
            userList = dao.listar();
            for (Usuario user : userList) {
                OBList.add(user);
            }
            tabelaUsuarios.setItems(OBList);
        } catch (PersistenciaException ex) {
            Logger.getLogger(TelaAdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
