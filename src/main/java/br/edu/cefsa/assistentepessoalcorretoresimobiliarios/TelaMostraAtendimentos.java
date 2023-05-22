/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.DAO.AtendimentoDAO;
import br.edu.cefsa.model.Atendimento;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Vinicius
 */
public class TelaMostraAtendimentos implements Initializable {

    @FXML
    private Label lbTituloAtendimento;

    @FXML
    private GridPane paneMostraAtendimentos;

    private List<Atendimento> atendimentos;
    
    private LocalDate data;
    
    @FXML
    private AtendimentoBoxController atendimentoBoxController;
    
    private TelaCalendarioController parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TESTEEEEE...
    }
    
    public void setParentController(TelaCalendarioController parentController) {
        this.parentController = parentController;
    }

    public void setAtendimento(Integer diaAtendimento, Month mes, Integer ano) throws IOException {

        lbTituloAtendimento.setText("Atendimentos do dia: " + diaAtendimento.toString() + " de " + String.valueOf(mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR"))) + " de " + ano.toString());
        data = LocalDate.of(ano, mes, diaAtendimento);
        criaAtendimentos(0, 1);
    }

    public void adicionaAtendimento() throws IOException {

        paneMostraAtendimentos.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("atendimentoBox.fxml"));
        AnchorPane atendimentoBox = fxmlLoader.load();
        atendimentoBoxController = fxmlLoader.getController();
        atendimentoBoxController.setParentController(this);
        atendimentoBoxController.setNovoAtendimento(data);
        paneMostraAtendimentos.add(atendimentoBox, 0, 1);
        GridPane.setMargin(atendimentoBox, new Insets(10));
        criaAtendimentos(0, 2);
    }
    
    public void desenhaCalendarioOutraTela() throws IOException {
        parentController.desenharCalendario();
    }
    
    public void limpaGrid() throws IOException {
        paneMostraAtendimentos.getChildren().clear();
    }

    public void criaAtendimentos(int column, int row) throws IOException {

        try {

            
            AtendimentoDAO dao = new AtendimentoDAO();
            atendimentos = dao.listarPorData(data);
            
            if(atendimentos.isEmpty()){
                parentController.desenharCalendario();
            }

            for (Atendimento atendimento : atendimentos) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("atendimentoBox.fxml"));
                AnchorPane atendimentoBox = fxmlLoader.load();
                atendimentoBoxController = fxmlLoader.getController();
                atendimentoBoxController.setParentController(this);
                atendimentoBoxController.setData(atendimento);

                if (column == 1) {
                    column = 0;
                    ++row;
                }

                paneMostraAtendimentos.add(atendimentoBox, column++, row);
                GridPane.setMargin(atendimentoBox, new Insets(10));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
}
