/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.ImovelDAO;
import br.edu.cefsa.exception.PersistenciaException;
import br.edu.cefsa.model.Imovel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;
import javafx.scene.control.RadioButton;
import javax.imageio.ImageIO;
import utils.ValidaDadosUtils;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class TelaAdicionarImovelController extends PadraoController {

    @FXML
    private FontAwesomeIconView iconeADM;

    @FXML
    private DatePicker mskPrazoEntrega;

    @FXML
    private ToggleGroup qualUso;

    @FXML
    private AnchorPane telaAdicionarImovel;

    @FXML
    private ToggleGroup tipoMoradia;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtCEP;

    @FXML
    private TextField txtCaracteristicas;

    @FXML
    private TextField txtCidade;

    @FXML
    private TextField txtComplemento;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtFaixaPreco;

    @FXML
    private TextField txtMetragem;

    @FXML
    private TextField txtNomeResidencia;

    @FXML
    private TextField txtNumeroDormitorios;

    @FXML
    private TextField txtNumeroVagas;

    @FXML
    private Label lbTituloImovel;

    @FXML
    private RadioButton rbApto;

    @FXML
    private RadioButton rbCasa;

    @FXML
    private RadioButton rbComl;

    @FXML
    private RadioButton rbFlat;

    @FXML
    private RadioButton rbLocacao;

    @FXML
    private RadioButton rbVenda;

    @FXML
    private Button btnAdicionarImagem;

    @FXML
    private HBox imagensContainer;
    
     @FXML
    private Label lbErroAdicionarImagem;

    @FXML
    private Label lbErroBairro;

    @FXML
    private Label lbErroCEP;

    @FXML
    private Label lbErroCidade;

    @FXML
    private Label lbErroComplemento;

    @FXML
    private Label lbErroEnderecoResidencia;

    @FXML
    private Label lbErroFaixaPreco;

    @FXML
    private Label lbErroFinalidade;

    @FXML
    private Label lbErroMetragem;

    @FXML
    private Label lbErroNomeCaracteristicas;

    @FXML
    private Label lbErroNomeResidencia;

    @FXML
    private Label lbErroNumeroDormitorios;

    @FXML
    private Label lbErroPrazoEntrega;

    @FXML
    private Label lbErroTipo;

    @FXML
    private Label lbErroVagas;

    Queue<byte[]> filaImagens = new LinkedList<byte[]>();

    byte[] byteArray = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iconeADM.setVisible(false);
        if (usuarioLogado.getUsuario().getTipo() == true) {
            iconeADM.setVisible(true);
        }

        if (imovelSelecionado.getImovel() != null) {

            try {
                lbTituloImovel.setText("Atualizar Imovel");
                txtNomeResidencia.setText(imovelSelecionado.getImovel().getNome());
                txtCEP.setText(imovelSelecionado.getImovel().getCEP());
                txtEndereco.setText(imovelSelecionado.getImovel().getRua());
                txtCidade.setText(imovelSelecionado.getImovel().getCidade());
                txtBairro.setText(imovelSelecionado.getImovel().getBairro());
                txtComplemento.setText(imovelSelecionado.getImovel().getComplemento());
                txtCaracteristicas.setText(imovelSelecionado.getImovel().getCaracteristicas());
                mskPrazoEntrega.setValue(imovelSelecionado.getImovel().getPrazoEntrega());
                txtNumeroDormitorios.setText(String.valueOf(imovelSelecionado.getImovel().getNumDorms()));
                txtNumeroVagas.setText(String.valueOf(imovelSelecionado.getImovel().getNumVagas()));
                txtMetragem.setText(String.valueOf(imovelSelecionado.getImovel().getMetragem()));
                txtFaixaPreco.setText(imovelSelecionado.getImovel().getFaixaPreco());
                
                if ("Apto".equals(imovelSelecionado.getImovel().getTipoImovel())) {
                    rbApto.setSelected(true);
                } else if ("Casa".equals(imovelSelecionado.getImovel().getTipoImovel())) {
                    rbCasa.setSelected(true);
                } else if ("Coml".equals(imovelSelecionado.getImovel().getTipoImovel())) {
                    rbComl.setSelected(true);
                } else if ("Flat".equals(imovelSelecionado.getImovel().getTipoImovel())) {
                    rbFlat.setSelected(true);
                }

                if ("Venda".equals(imovelSelecionado.getImovel().getFinalidade())) {
                    rbVenda.setSelected(true);
                } else if ("Locacao".equals(imovelSelecionado.getImovel().getFinalidade())) {
                    rbLocacao.setSelected(true);
                }
                filaImagens.add(imovelSelecionado.getImovel().getImagem1());
                filaImagens.add(imovelSelecionado.getImovel().getImagem2());
                filaImagens.add(imovelSelecionado.getImovel().getImagem3());

                adicionarImagemTela();

            } catch (IOException ex) {
                Logger.getLogger(TelaAdicionarImovelController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TelaAdicionarImovelController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PersistenciaException ex) {
                Logger.getLogger(TelaAdicionarImovelController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(TelaAdicionarImovelController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            lbTituloImovel.setText("Cadastrar Imóvel");
        }
    }

    @FXML
    private void salvarImovel() throws IOException, SQLException, PersistenciaException, ParseException {

        //Corrigir double - tirar ponto e virgula;
        int status = ValidaCampos();
        if (status == 0) {

            ImovelDAO DAO = new ImovelDAO();

            RadioButton tipoImovelSelecionado = (RadioButton) tipoMoradia.getSelectedToggle();
            String tipoImovel = tipoImovelSelecionado.getText();
            RadioButton qualUsoSelecionado = (RadioButton) qualUso.getSelectedToggle();
            String tipoFinalidade = qualUsoSelecionado.getText();
            Imovel imovel = new Imovel(txtNomeResidencia.getText(), tipoImovel, tipoFinalidade, Integer.parseInt(txtNumeroDormitorios.getText()),
                    Integer.parseInt(txtNumeroVagas.getText()), Double.parseDouble(txtMetragem.getText()), txtEndereco.getText(), txtBairro.getText(),
                    txtCidade.getText(), txtCEP.getText(), txtComplemento.getText(), txtCaracteristicas.getText(), mskPrazoEntrega.getValue(),
                    txtFaixaPreco.getText(), filaImagens.remove(), filaImagens.remove(), filaImagens.remove());

            try {
                if (imovelSelecionado.getImovel() != null) {
                    Imovel imovelAlterar = new Imovel(txtNomeResidencia.getText(), tipoImovel, tipoFinalidade, Integer.parseInt(txtNumeroDormitorios.getText()),
                    Integer.parseInt(txtNumeroVagas.getText()), Double.parseDouble(txtMetragem.getText()), txtEndereco.getText(), txtBairro.getText(),
                    txtCidade.getText(), txtCEP.getText(), txtComplemento.getText(), txtCaracteristicas.getText(), mskPrazoEntrega.getValue(),
                    txtFaixaPreco.getText(), filaImagens.remove(), filaImagens.remove(), filaImagens.remove());
                    DAO.alterar(imovelAlterar);

                } else {
                    DAO.inserir(imovel);
                }
                redirecionarListarImovel();
            } catch (PersistenciaException ex) {
                Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void adicionarImovelImagem() throws IOException, SQLException, PersistenciaException, ParseException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + "/Downloads"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG Image", "*.png"),
                new FileChooser.ExtensionFilter("All image files", "*.jpg", "*.png"));

        Stage stage = (Stage) btnAdicionarImagem.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {

            if (filaImagens.size() < 3) {
                BufferedImage imagem = ImageIO.read(selectedFile);
                ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
                ImageIO.write((BufferedImage) imagem, "jpg", bytesImg);//seta a imagem para bytesImg
                bytesImg.flush();//limpa a variável
                byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[] 
                bytesImg.close();//fecha a conversão
                filaImagens.add(byteArray);
            } else {
                BufferedImage imagem = ImageIO.read(selectedFile);
                ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
                ImageIO.write((BufferedImage) imagem, "jpg", bytesImg);//seta a imagem para bytesImg
                bytesImg.flush();//limpa a variável
                byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[] 
                bytesImg.close();//fecha a conversão
                filaImagens.remove();
                filaImagens.add(byteArray);

            }

            adicionarImagemTela();

        } else {
            System.out.println("No file has been selected");
        }

    }

    @FXML
    private void adicionarImagemTela() throws IOException, SQLException, PersistenciaException, ParseException {

        imagensContainer.getChildren().clear();
        for (int i = 0; i < filaImagens.size(); i++) {
            byte[] byteArray2 = filaImagens.remove();
            InputStream inputStream = new ByteArrayInputStream(byteArray2);
            Image imagemPura = new Image(inputStream);
            ImageView imagem = new ImageView(imagemPura);
            imagem.setFitHeight(160);
            imagem.setFitWidth(220);
            imagem.setPreserveRatio(true);
            imagensContainer.getChildren().add(imagem);
            filaImagens.add(byteArray2);
        }

    }
    
    
        @FXML
    private int ValidaCampos() {
        int statusFinal = 0;
        int status = validaCamposEmBranco();
        if (status == 1) {
            return status;
        }
        if (!ValidaDadosUtils.VerificaCEP(txtCEP.getText())) {
            lbErroCEP.setText("CEP no Formato incorreto");
            statusFinal++;
        }
        if(filaImagens.size() < 3){
            lbErroAdicionarImagem.setText("Favor adicionar pelo menos 3 imagens");
            statusFinal++;        
        }        
        if (Integer.parseInt(txtNumeroDormitorios.getText()) < 0) {
            lbErroNumeroDormitorios.setText("Numero de Dormitorios menor que 0!");
            statusFinal++;
        }
        if (Integer.parseInt(txtNumeroVagas.getText()) < 0) {
            lbErroVagas.setText("Numero de Vagas menor que 0!");
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
        RadioButton tipoImovelSelecionado = (RadioButton) tipoMoradia.getSelectedToggle();
        
        RadioButton qualUsoSelecionado = (RadioButton) qualUso.getSelectedToggle();
        
        
        if (!ValidaDadosUtils.ValidaTexto(txtNomeResidencia.getText())) {
            lbErroNomeResidencia.setText("Nome da Residencia não Preenchido");
            status++;
        }
        if (tipoImovelSelecionado == null) {
            lbErroTipo.setText("Tipo não Preenchido");
            status++;
        }
        if (mskPrazoEntrega.getValue() == null) {
            lbErroPrazoEntrega.setText("Prazo de Entrega não Preenchido");
            status++;
        }
        if (qualUsoSelecionado == null) {
            lbErroFinalidade.setText("Finalidade não Preenchida");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtNumeroDormitorios.getText())) {
            lbErroNumeroDormitorios.setText("Numero de Dormitorios não Preenchidos");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCidade.getText())) {
            lbErroCidade.setText("Cidade não Preenchida");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCEP.getText())) {
            lbErroCEP.setText("CEP não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtEndereco.getText())) {
            lbErroEnderecoResidencia.setText("Endereço não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtNumeroVagas.getText())) {
            lbErroVagas.setText("Vagas não Preenchidas");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtMetragem.getText())) {
            lbErroMetragem.setText("Metragem não Preenchida");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtBairro.getText())) {
            lbErroBairro.setText("Bairro não Preenchido");
            status++;
        }          
        if (!ValidaDadosUtils.ValidaTexto(txtComplemento.getText())) {
            lbErroComplemento.setText("Complemento não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtCaracteristicas.getText())) {
            lbErroNomeCaracteristicas.setText("Caracteristicas não Preenchido");
            status++;
        }
        if (!ValidaDadosUtils.ValidaTexto(txtFaixaPreco.getText())) {
            lbErroFaixaPreco.setText("Faixa de Preço não Preenchida");
            status++;
        }
        return status;
    }
    
}
