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
import javafx.scene.control.RadioButton;
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
    
     @FXML
    private RadioButton rbApto;

    @FXML
    private RadioButton rbCasa;

    @FXML
    private RadioButton rbComl;

    @FXML
    private RadioButton rbFlat;

    @FXML
    private RadioButton rbInvestimento;

    @FXML
    private RadioButton rbUso;

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
            ImovelProcuradoCliente imovelProcurado = new ImovelProcuradoCliente();
            imovelProcurado = clienteSelecionado.getCliente().getImovelProcuradoCliente();
            
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
            
            txtNumeroDormitorios.setText(String.valueOf(imovelProcurado.getNumDorms()));
            txtNumeroVagas.setText(String.valueOf(imovelProcurado.getNumVagas()));
            txtMetragem.setText(String.valueOf(imovelProcurado.getMetragem()));
            txtFaixaPreco.setText(imovelProcurado.getFaixaPreco());
            txtBairros.setText(imovelProcurado.getBairros());
            txtCondicoes.setText(imovelProcurado.getCondicoes());
            
            if("Apto".equals(clienteSelecionado.getCliente().getImovelProcuradoCliente().getTipoImovel())){
                
                rbApto.setSelected(true);
            }
            else if("Casa".equals(clienteSelecionado.getCliente().getImovelProcuradoCliente().getTipoImovel())){
                rbCasa.setSelected(true);
            }
            else if("Coml".equals(clienteSelecionado.getCliente().getImovelProcuradoCliente().getTipoImovel())){
                rbComl.setSelected(true);
            }
            else if("Flat".equals(clienteSelecionado.getCliente().getImovelProcuradoCliente().getTipoImovel())){
                rbFlat.setSelected(true);
            }

            if("Uso".equals(clienteSelecionado.getCliente().getImovelProcuradoCliente().getTipoAquisicao())){
                rbUso.setSelected(true);
            }
            else if("Investimento".equals(clienteSelecionado.getCliente().getImovelProcuradoCliente().getTipoAquisicao())){
                rbInvestimento.setSelected(true);
            }
            txtCPF.setDisable(true);

        } else {
            lbTituloCliente.setText("Cadastrar Cliente");
            lbTituloImovel.setText("Imóvel Procurado");
        }
    }

    @FXML
    private void salvarCliente() throws IOException, SQLException, PersistenciaException, ParseException {

        //Corrigir double - tirar ponto e virgula;
        int status = ValidaCampos();
        if (status == 0) {
            
            ClienteDAO DAO = new ClienteDAO();
            RadioButton tipoImovelSelecionado = (RadioButton) tipoMoradia.getSelectedToggle();
            String tipoImovel = tipoImovelSelecionado.getText();
            RadioButton qualUsoSelecionado = (RadioButton) qualUso.getSelectedToggle();
            String tipoFinalidade = qualUsoSelecionado.getText();
            ImovelProcuradoCliente imovelProcurado = new ImovelProcuradoCliente(tipoImovel, tipoFinalidade, Integer.parseInt(txtNumeroDormitorios.getText()), Integer.parseInt(txtNumeroVagas.getText()), 
                    Double.parseDouble(txtMetragem.getText()), txtBairros.getText(), txtCondicoes.getText(), txtFaixaPreco.getText());
    
            Cliente cliente = new Cliente(txtNome.getText(), txtCPF.getText(), mskDataNascimento.getValue(), txtConjuge.getText(),
                    txtProfissao.getText(), txtTelefone.getText(), txtEmail.getText(), txtCEP.getText() , txtEndereco.getText(),
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
        

        int idade = 0;
        int statusFinal = 0;
        int status = validaCamposEmBranco();
        if(mskDataNascimento.getValue() != null){
            LocalDate dataAtual = LocalDate.now();
            Period periodo = Period.between( mskDataNascimento.getValue(), dataAtual);
            idade = periodo.getYears();
        }
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
        if ( idade < 18) {
            lbErroDataNascimento.setText("Clientes menor de idade");
            statusFinal++;
        }
        if (Integer.parseInt(txtNumeroDormitorios.getText()) < 0) {
            lbErroNumeroDormitorios.setText("Numero de Dormitorios menor que 0!");
            statusFinal++;
        }
        if (Integer.parseInt(txtNumeroVagas.getText()) < 0) {
            lbErroNumeroVagas.setText("Numero de Vagas menor que 0!");
            statusFinal++;
        }
        if (Integer.parseInt(txtMetragem.getText()) < 0) {
            lbErroMetragem.setText("Metragem menor que 0!");
            statusFinal++;
        }        
        return statusFinal;
    }

    private int validaCamposEmBranco() {
        int status = 0;

        if (!ValidaDadosUtils.ValidaTexto(txtNome.getText())) {
            lbErroNome.setText("Nome não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCPF.getText())) {
            lbErroCPF.setText("CPF não Preenchido");
            status++;
        }
        if (mskDataNascimento.getValue() == null) {
            lbErroDataNascimento.setText("Data de Nascimento não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtProfissao.getText())) {
            lbErroProfissao.setText("Profissão não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtConjuge.getText())) {
            lbErroConjuge.setText("Conjuge não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtTelefone.getText())) {
            lbErroTelefone.setText("Telefone não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtEmail.getText())) {
            lbErroEmail.setText("Email não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtEndereco.getText())) {
            lbErroEndereço.setText("Endereço não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCEP.getText())) {
            lbErroCEP.setText("CEP não Preenchido");
            status++;
        }
        if (txtEstado.getValue() == null) {
            lbErroEstado.setText("Estado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCidade.getText())) {
            lbErroCidade.setText("Cidade não Preenchida");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtBairro.getText())) {
            lbErroBairro.setText("Bairro não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtNumeroDormitorios.getText())) {
            lbErroNumeroDormitorios.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtNumeroVagas.getText())) {
            lbErroNumeroVagas.setText("Dado não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtMetragem.getText())) {
            lbErroMetragem.setText("Metragem não Preenchida");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtFaixaPreco.getText())) {
            lbErroFaixaPreco.setText("Faixa de preço não Preenchida");
            status++;
        }        
        if (!ValidaDadosUtils.ValidaTexto(txtBairros.getText())) {
            lbErroBairros.setText("Bairros não Preenchidos");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCondicoes.getText())) {
            lbErroCondicoes.setText("Condições não Preenchidas");
            status++;
        }
        return status;
    }
}
