/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.ClienteDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Vinicius
 */
public class TelaCadastroCliente extends PadraoController{
    
    @FXML
    private DatePicker mskDataNascimento;

    @FXML
    private AnchorPane telaCadastroCliente;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCEP;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtConjuge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtEstado;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtProfissao;

    @FXML
    private TextField txtTelefone;

    @FXML
    private void salvarCliente() throws IOException, SQLException {

        ClienteDAO DAO = new ClienteDAO();
        Cliente cliente = new Cliente(txtNome.getText(), txtCPF.getText(), mskDataNascimento.getValue(), txtConjuge.getText(), 
                txtProfissao.getText(), txtTelefone.getText(), txtEmail.getText(), txtCEP.getText(), 
                txtEndereco.getText(), txtEstado.getText(), "Não tem cidade", txtBairro.getText(), "Não tem Anotação");
        try {
            DAO.inserir(cliente);
            redirecionarTelaPrincipal();
        }catch (PersistenciaException ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
