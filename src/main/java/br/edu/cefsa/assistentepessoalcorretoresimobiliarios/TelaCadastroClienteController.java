/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.ClienteDAO;
import br.edu.cefsa.enumeradores.EnumEstados;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Cliente;
import br.edu.cefsa.model.ImovelProcuradoCliente;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import utils.ValidaDadosUtils;

/**
 *
 * @author Vinicius
 */
public class TelaCadastroClienteController extends PadraoController {

    @FXML
    private Label lbTituloCliente;

    @FXML
    private Label lbTituloImovel;

    @FXML
    private FontAwesomeIconView iconeADM;

    @FXML
    private DatePicker mskDataNascimento;

    @FXML
    private ToggleGroup qualUso;

    @FXML
    private AnchorPane telaCadastroCliente;

    @FXML
    private ToggleGroup tipoMoradia;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtBairros;

    @FXML
    private TextField txtCEP;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtCondicoes;

    @FXML
    private TextField txtConjuge;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;

    @FXML
    private ComboBox<EnumEstados> txtEstado;

    @FXML
    private TextField txtFaixaPreco;

    @FXML
    private TextField txtMetragem;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNumeroDormitorios;

    @FXML
    private TextField txtNumeroVagas;

    @FXML
    private TextField txtPrazoEntrega;

    @FXML
    private TextField txtProfissao;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Label lbErroBairro;

    @FXML
    private Label lbErroBairros;

    @FXML
    private Label lbErroCEP;

    @FXML
    private Label lbErroCPF;

    @FXML
    private Label lbErroCidade;

    @FXML
    private Label lbErroCondicoes;

    @FXML
    private Label lbErroConjuge;

    @FXML
    private Label lbErroDataNascimento;

    @FXML
    private Label lbErroEmail;

    @FXML
    private Label lbErroEndereço;

    @FXML
    private Label lbErroEstado;

    @FXML
    private Label lbErroFaixaPreco;

    @FXML
    private Label lbErroMetragem;

    @FXML
    private Label lbErroNome;

    @FXML
    private Label lbErroNumeroDormitorios;

    @FXML
    private Label lbErroNumeroVagas;

    @FXML
    private Label lbErroPara;

    @FXML
    private Label lbErroPrazoEntrega;

    @FXML
    private Label lbErroProfissao;

    @FXML
    private Label lbErroTelefone;

    @FXML
    private Label lbErroTipo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iconeADM.setVisible(false);
        if (usuarioLogado.getUsuario().getTipo() == true) {
            iconeADM.setVisible(true);
        }

        List<EnumEstados> listaEnum = new ArrayList<EnumEstados>(Arrays.asList(EnumEstados.values()));
        txtEstado.getItems().setAll(listaEnum);
        //txtEstado.getSelectionModel().select(0);

        if (clienteSelecionado.getCliente() != null) {

            lbTituloCliente.setText("Atualizar Cliente");
            lbTituloImovel.setText("Atualizar Imóvel Procurado");
            txtNome.setText(clienteSelecionado.getCliente().getNome());
            txtConjuge.setText(clienteSelecionado.getCliente().getConjuge());
            txtCidade.setText(clienteSelecionado.getCliente().getCidade());
            txtCPF.setText(clienteSelecionado.getCliente().getCPF());
            mskDataNascimento.setValue(clienteSelecionado.getCliente().getDataNascimento());
            txtProfissao.setText(clienteSelecionado.getCliente().getProfissao());
            txtTelefone.setText(clienteSelecionado.getCliente().getTelefone());
            txtEmail.setText(clienteSelecionado.getCliente().getEmail());
            txtCEP.setText(clienteSelecionado.getCliente().getCEP());
            txtEndereco.setText(clienteSelecionado.getCliente().getEnderecoResidencial());
            txtEstado.setValue(EnumEstados.valueOf(clienteSelecionado.getCliente().getEstado()));
            txtBairro.setText(clienteSelecionado.getCliente().getBairro());
            txtCPF.setDisable(true);

        } else {
            lbTituloCliente.setText("Cadastrar Cliente");
            lbTituloImovel.setText("Imóvel Procurado");
        }
    }

    @FXML
    private void salvarCliente() throws IOException, SQLException, PersistenciaException, ParseException {

        int status = ValidaCampos();
        ImovelProcuradoCliente imovelProcurado = new ImovelProcuradoCliente();
        imovelProcurado.numDorms = 0;
        imovelProcurado.numVagas = 0;
        imovelProcurado.metragem = 0;
        if (status == 0) {
            
            ClienteDAO DAO = new ClienteDAO();
            Cliente cliente = new Cliente(txtNome.getText(), txtCPF.getText(), mskDataNascimento.getValue(), txtConjuge.getText(),
                    txtProfissao.getText(), txtTelefone.getText(), txtEmail.getText(), txtEndereco.getText(), txtCEP.getText(),
                    txtEstado.getValue().toString(), txtCidade.getText(), txtBairro.getText(), imovelProcurado);

            try {
                if (clienteSelecionado.getCliente() != null) {
                    Cliente clienteAlterar;
                    clienteAlterar = new Cliente(clienteSelecionado.getCliente().getClienteId(), txtNome.getText(), txtCPF.getText(), mskDataNascimento.getValue(), txtConjuge.getText(),
                            txtProfissao.getText(), txtTelefone.getText(), txtEmail.getText(), txtCEP.getText(), txtEndereco.getText(),
                            txtEstado.getValue().toString(), txtCidade.getText(), txtBairro.getText(), imovelProcurado);
                    DAO.alterar(clienteAlterar);
                } else {
                    DAO.inserir(cliente);
                }
                redirecionarTelaPrincipal();
            } catch (PersistenciaException ex) {
                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /*
    @FXML
    private void receiveData() {
        Stage stage = (Stage) telaCadastroCliente.getScene().getWindow();
        Cliente cliente = (Cliente) stage.getUserData();
        txtNome.setText(cliente.getNome());
        txtConjuge.setText(cliente.getConjuge());
        txtCidade.setText(cliente.getCidade());
    }
    
    @FXML
    public static void mostraUsuario(Cliente cliente) throws IOException {
        txtNome.setText(cliente.getNome());
        txtConjuge.setText(cliente.getConjuge());
        txtCidade.setText(cliente.getCidade());
        
    }
     */
    @FXML
    private int ValidaCampos() {
        LocalDate dataAtual = LocalDate.now();
        //Period periodo = Period.between( mskDataNascimento.getValue(), dataAtual);
        //int idade = periodo.getYears();
        int statusFinal = 0;
        int status = validaCamposEmBranco();
        if (status == 1) {
            return status;
        }
        if (!ValidaDadosUtils.VerificaEmail(txtEmail.getText())) {
            lbErroEmail.setText("Email no Formato Incorreto");
            statusFinal++;
        }
        if (!ValidaDadosUtils.VerificaCPF(txtCPF.getText())) {
            lbErroCPF.setText("CPF No Formato Incorreto");
            statusFinal++;
        }
        if (!ValidaDadosUtils.VerificaPhone(txtTelefone.getText())) {
            lbErroTelefone.setText("Celular no Formato Incorreto");
            statusFinal++;
        }
        if (!ValidaDadosUtils.VerificaCEP(txtCEP.getText())) {
            lbErroCEP.setText("CEP no Formato incorreto");
            statusFinal++;
        }
        /*if ( idade < 18) {
            lbErroDataNascimento.setText("Clientes menor de idade");
            statusFinal++;
        }*/

        return statusFinal;
    }

    private int validaCamposEmBranco() {
        int status = 0;

        if (!ValidaDadosUtils.ValidaTexto(txtNome.getText())) {
            lbErroNome.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCPF.getText())) {
            lbErroCPF.setText("Dado não Preenchido");
            status++;
        }
        if (mskDataNascimento.getValue() == null) {
            lbErroDataNascimento.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtProfissao.getText())) {
            lbErroProfissao.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtConjuge.getText())) {
            lbErroConjuge.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtTelefone.getText())) {
            lbErroTelefone.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtEmail.getText())) {
            lbErroEmail.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtEndereco.getText())) {
            lbErroEndereço.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCEP.getText())) {
            lbErroCEP.setText("Dado não Preenchido");
            status++;
        }
        if (txtEstado.getValue() == null) {
            lbErroEstado.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCidade.getText())) {
            lbErroCidade.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtBairro.getText())) {
            lbErroBairro.setText("Dado não Preenchido");
            status++;
        }
        return status;
    }
}
