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
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;

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
    private TextField txtCondicoes;

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
    private Button btnAdicionarImagem;

    @FXML
    private HBox imagensContainer;

    Queue<File> filaImagens = new LinkedList<File>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iconeADM.setVisible(false);
        if (usuarioLogado.getUsuario().getTipo() == true) {
            iconeADM.setVisible(true);
        }

        if (imovelSelecionado.getImovel() != null) {

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
        } else {

            lbTituloImovel.setText("Cadastrar Imóvel");
        }
    }

    @FXML
    private void salvarImovel() throws IOException, SQLException, PersistenciaException, ParseException {

        //Corrigir double - tirar ponto e virgula;
        byte[] byteArray = null;
        int status = 0;
        if (status == 0) {

            for (int i = 0; i < filaImagens.size(); i++) {

                BufferedImage imagem = ImageIO.read(filaImagens.remove());
                ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
                ImageIO.write((BufferedImage) imagem, "jpg", bytesImg);//seta a imagem para bytesImg
                bytesImg.flush();//limpa a variável
                byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[] 
                bytesImg.close();//fecha a conversão
            }

            ImovelDAO DAO = new ImovelDAO();

            /*
            Imovel imovel = new Imovel("kkk", "TipImovel", true, true, 2,
                    2, 2.2, "kkk", "kkk",
                    "kk", "kk", "kk", "kk", mskPrazoEntrega.getValue(),
                    "kk");
            */
            
            Imovel imovel = new Imovel(txtNomeResidencia.getText(), "TipImovel", "TipFinalidade", Integer.parseInt(txtNumeroDormitorios.getText()),
                    Integer.parseInt(txtNumeroVagas.getText()), Double.parseDouble(txtMetragem.getText()), txtEndereco.getText(), txtBairro.getText(),
                    txtCidade.getText(), txtCEP.getText(), txtComplemento.getText(), txtCaracteristicas.getText(), mskPrazoEntrega.getValue(),
                    txtFaixaPreco.getText(), byteArray, byteArray , byteArray);

            try {
                if (imovelSelecionado.getImovel() != null) {
                    /*
                    Cliente clienteAlterar;
                    clienteAlterar = new Cliente(clienteSelecionado.getCliente().getClienteId(), txtNome.getText(), txtCPF.getText(), mskDataNascimento.getValue(), txtConjuge.getText(),
                            txtProfissao.getText(), txtTelefone.getText(), txtEmail.getText(), txtCEP.getText(), txtEndereco.getText(),
                            txtEstado.getValue().toString(), txtCidade.getText(), txtBairro.getText(), imovelProcurado);
                    DAO.alterar(clienteAlterar);
                     */
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

                filaImagens.add(selectedFile);
            } else {
                filaImagens.remove();
                filaImagens.add(selectedFile);
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
            File f = filaImagens.remove();
            Image imagemPura = new Image(f.toURI().toString());
            ImageView imagem = new ImageView(imagemPura);
            imagem.setFitHeight(120);
            imagem.setFitWidth(220);
            imagem.setPreserveRatio(true);
            imagensContainer.getChildren().add(imagem);
            filaImagens.add(f);
        }

    }
}
