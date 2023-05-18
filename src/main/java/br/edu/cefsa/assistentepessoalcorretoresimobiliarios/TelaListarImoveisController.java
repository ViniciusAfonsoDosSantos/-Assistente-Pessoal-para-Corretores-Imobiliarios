/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.ImovelDAO;
import br.edu.cefsa.model.Imovel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class TelaListarImoveisController extends PadraoController {

    @FXML
    private FontAwesomeIconView iconeADM;

    @FXML
    private GridPane paneMostraImoveis;

    ObservableList OBList = FXCollections.observableArrayList();

    private List<Imovel> imoveis;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iconeADM.setVisible(false);
        if (usuarioLogado.getUsuario().getTipo() == true) {
            iconeADM.setVisible(true);
        }

        try {
            
            ImovelDAO imovelDAO = new ImovelDAO();
            imoveis = new ArrayList<>(imovelDAO.listar());
            int column = 0;
            int row = 1;

            for (Imovel imovel : imoveis) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("imovelBox.fxml"));
                AnchorPane imovelBox = fxmlLoader.load();
                ImovelBoxController imovelBoxController = fxmlLoader.getController();
                imovelBoxController.setData(imovel);

                if (column == 3) {
                    column = 0;
                    ++row;
                }

                paneMostraImoveis.add(imovelBox, column++, row);
                GridPane.setMargin(imovelBox, new Insets(10));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
