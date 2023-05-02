/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.CalendarioAtendimento;
import br.edu.cefsa.model.Cliente;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Vinicius
 */
public class TelaMostraAtendimentos implements Initializable{
    
    @FXML
    private Label lbTituloAtendimento;

    @FXML
    private GridPane paneMostraAtendimentos;

    private List<CalendarioAtendimento> atendimentos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TESTEEEEEE
        try {
            
            atendimentos = new ArrayList<>(adicionaAtendimentos());
            int column = 0;
            int row = 1;

            for (CalendarioAtendimento atendimento : atendimentos) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("atendimentoBox.fxml"));
                AnchorPane atendimentoBox = fxmlLoader.load();
                AtendimentoBoxController atendimentoBoxController = fxmlLoader.getController();
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
    
    
    private List<CalendarioAtendimento> adicionaAtendimentos(){
        
        List<CalendarioAtendimento> novosAtendimentos = new ArrayList<CalendarioAtendimento>();
        
        LocalDateTime zone = LocalDateTime.parse("2019-03-28T10:15:30");  
        Cliente cliente = new Cliente("Gabriel");
        CalendarioAtendimento atendimento = new CalendarioAtendimento(zone,"Testando textos muito longos, só que não!!!", cliente);
        
        novosAtendimentos.add(atendimento);
        
        LocalDateTime zone2 = LocalDateTime.parse("2019-03-28T10:15:30");  
        Cliente cliente2 = new Cliente("Vinicius");
        CalendarioAtendimento atendimento2 = new CalendarioAtendimento(zone2,"Testando textos muito longos, só que não!!!", cliente2);
        
        novosAtendimentos.add(atendimento2);
        
        
        return novosAtendimentos;
    }
    
}
