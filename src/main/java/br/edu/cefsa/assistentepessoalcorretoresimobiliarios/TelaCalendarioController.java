/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.edu.cefsa.assistentepessoalcorretoresimobiliarios;

import br.edu.cefsa.model.CalendarioAtendimento;
import br.edu.cefsa.model.Cliente;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vinicius
 */
public class TelaCalendarioController extends PadraoController {

    /**
     * Initializes the controller class.
     */
    LocalDateTime dateFocus;
    LocalDateTime today;

    @FXML
    private FlowPane calendar;

    @FXML
    private FontAwesomeIconView iconeADM;

    @FXML
    private Label txtAno;

    @FXML
    private Label txtMes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        iconeADM.setVisible(false);
        if (usuarioLogado.getUsuario().getTipo() == true) {
            iconeADM.setVisible(true);
        }

        dateFocus = LocalDateTime.now();
        today = LocalDateTime.now();
        desenharCalendario();

    }

    @FXML
    void retrocesso(MouseEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        desenharCalendario();
    }

    @FXML
    void avanca(MouseEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        desenharCalendario();
    }

    @FXML
    private void desenharCalendario() {

        txtAno.setText(String.valueOf(dateFocus.getYear()));
        txtMes.setText(String.valueOf(dateFocus.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"))));

        double calendarWidth = calendar.getPrefWidth();
        double calendarHeight = calendar.getPrefHeight();
        double strokeWidth = 1.5;
        double spacingH = calendar.getHgap();
        double spacingV = calendar.getVgap();

        //List of activities for a given month
        Map<Integer, List<CalendarioAtendimento>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

        int monthMaxDate = dateFocus.getMonth().maxLength();
        //Check for leap year
        if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
            monthMaxDate = 28;
        }
        int dateOffset = LocalDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0).getDayOfWeek().getValue();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();
                Pane pane = new Pane();

                int calculatedDate = (j + 1) + (7 * i);
                int currentDate = calculatedDate - dateOffset;
                if (calculatedDate > dateOffset && currentDate <= monthMaxDate) {
                    pane.setStyle(
                            "-fx-border-color: linear-gradient(to right, #1A3384, #2B49B3);\n"
                            + "    -fx-border-width: 1.5px;\n"
                            + "    -fx-border-radius: 30px 30px 30px 30px;\n"
                            + "    -fx-background-color:#FFFFFF;\n"
                            + "    -fx-background-radius:30px 30px 30px 30px;"
                    );
                } else {

                    pane.setStyle(
                            "-fx-border-color: transparent ;\n"
                            + "    -fx-border-width: 1.5px;\n"
                            + "    -fx-border-radius: 30px 30px 30px 30px;\n"
                            + "    -fx-background-color: transparent;\n"
                            + "    -fx-background-radius:30px 30px 30px 30px;"
                    );

                }

                double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
                pane.setPrefWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
                pane.setPrefHeight(rectangleHeight);
                pane.setOnMouseClicked(mouseEvent -> {

                    criaEventoCliqueMouse(currentDate);

                });
                stackPane.getChildren().add(pane);

                if (calculatedDate > dateOffset) {

                    if (currentDate <= monthMaxDate) {
                        Text date = new Text(String.valueOf(currentDate));
                        double textTranslationY = -(rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        stackPane.getChildren().add(date);

                        List<CalendarioAtendimento> calendarActivities = calendarActivityMap.get(currentDate);
                        if (calendarActivities != null) {
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane, currentDate);
                        }

                    }
                    if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                        pane.setStyle(
                                "-fx-border-color: #7DCAA2;\n"
                                + "    -fx-border-width: 1.5px;\n"
                                + "    -fx-border-radius: 30px 30px 30px 30px;\n"
                                + "    -fx-background-color:#FFFFFF;\n"
                                + "    -fx-background-radius:30px 30px 30px 30px;"
                        );
                    }
                }
                calendar.getChildren().add(stackPane);
            }
        }
    }

    private void createCalendarActivity(List<CalendarioAtendimento> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane, int currentDate) {
        VBox calendarActivityBox = new VBox();
        Pane pane = new Pane();
        Label text = new Label("+ Ações");
        text.setStyle("-fx-font: 6px");
        pane.getChildren().add(text);
        calendarActivityBox.getChildren().add(pane);

        pane.setStyle("    -fx-background-color:#E5707A;\n"
                + "    -fx-background-radius:10px 10px 10px 10px;");

        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setSpacing(5);
        calendarActivityBox.setStyle("    -fx-background-color: transparent;");
        calendarActivityBox.setOnMouseClicked(mouseEvent -> {

            criaEventoCliqueMouse(currentDate);

        });
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<CalendarioAtendimento>> createCalendarMap(List<CalendarioAtendimento> calendarActivities) {
        Map<Integer, List<CalendarioAtendimento>> calendarActivityMap = new HashMap<>();

        for (CalendarioAtendimento activity : calendarActivities) {
            int activityDate = activity.getData().getDayOfMonth();
            if (!calendarActivityMap.containsKey(activityDate)) {
                calendarActivityMap.put(activityDate, List.of(activity));
            } else {
                List<CalendarioAtendimento> OldListByDate = calendarActivityMap.get(activityDate);

                List<CalendarioAtendimento> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return calendarActivityMap;
    }

    private Map<Integer, List<CalendarioAtendimento>> getCalendarActivitiesMonth(LocalDateTime dateFocus) {
        List<CalendarioAtendimento> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();

        Cliente cliente = new Cliente("Testeee");
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            LocalDateTime time = LocalDateTime.of(year, month, random.nextInt(27) + 1, 16, 0, 0, 0);
            calendarActivities.add(new CalendarioAtendimento(time, "Anotação", cliente));
        }

        return createCalendarMap(calendarActivities);
    }

    private void criaEventoCliqueMouse(int currentDate) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("telaMostraAtendimentos.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Atendimentos");
            stage.setScene(scene);
            stage.show();
            TelaMostraAtendimentos telaMostraAtendimentos = fxmlLoader.getController();
            telaMostraAtendimentos.setAtendimento(currentDate, txtMes.getText(), dateFocus.getYear());

        } catch (Exception ex) {
            Logger.getLogger(TelaLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
