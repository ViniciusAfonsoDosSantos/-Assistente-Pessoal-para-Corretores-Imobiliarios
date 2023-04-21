/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.Cliente;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author Vinicius
 */
public class TelaPrincipalController extends PadraoController{
    
    @FXML
    private Pane sidebarPane;
    
    @FXML
    private FontAwesomeIconView iconeMenu;
    
    @FXML
    private FontAwesomeIconView iconeADM;
    
    @FXML
    private GridPane paneMostraClientes;
    
    private List<Cliente> clientes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        iconeADM.setVisible(false);
        if(usuarioLogado.getUsuario().getTipo() == true){
            iconeADM.setVisible(true);
        }
        
        clientes = new ArrayList<>(adicionaClientes());
        int column = 0;
        int row = 1;
   
        try{
             for(Cliente cliente : clientes){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("clienteBox.fxml"));
                AnchorPane clienteBox = fxmlLoader.load();
                ClienteBoxController clienteBoxController = fxmlLoader.getController();
                clienteBoxController.setData(cliente);
                
                if(column == 2){
                    column = 0;
                    ++row;
                }
                
                paneMostraClientes.add(clienteBox, column++, row);
                GridPane.setMargin(clienteBox, new Insets(10)); 
            }      
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private List<Cliente> adicionaClientes(){
        List<Cliente> novosClientes = new ArrayList<Cliente>();
        
        Cliente cliente = new Cliente();
        cliente.setNome("Vinicius");
        cliente.setEnderecoResidencial("Rua 1");
        cliente.setEmail("email");
        cliente.setTelefone("12345678");
        novosClientes.add(cliente);
        
        cliente = new Cliente();
        cliente.setNome("Gabriel");
        cliente.setEnderecoResidencial("Rua 2");
        cliente.setEmail("email2");
        cliente.setTelefone("12345678");
        novosClientes.add(cliente);
        
        cliente = new Cliente();
        cliente.setNome("José");
        cliente.setEnderecoResidencial("Rua 3");
        cliente.setEmail("email3");
        cliente.setTelefone("12345678");
        novosClientes.add(cliente);
        
        cliente = new Cliente();
        cliente.setNome("Rodrigo");
        cliente.setEnderecoResidencial("Rua 4");
        cliente.setEmail("email4");
        cliente.setTelefone("12345678");
        novosClientes.add(cliente);
        
        return novosClientes;
    }
}
