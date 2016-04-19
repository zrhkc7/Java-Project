/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zrhkc7championselector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dale
 * http://www.tutorialspoint.com/java/java_serialization.htm
 */
public class UserInterfaceController implements Initializable {

    private Stage stage;

    @FXML
    private ComboBox supportComboBox;
    @FXML 
    private ComboBox marksmanComboBox;
    @FXML
    private ComboBox midlaneComboBox;       
    @FXML
    private ComboBox toplaneComboBox;        
    @FXML
    private ComboBox jungleComboBox;
    Champion champion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        champion = new Champion();
    }
    
    public void ready(Stage stage) {
        this.stage = stage;
       
        supportComboBox.getItems().add("Blitzcrank");
        supportComboBox.getItems().add("Morgana");
        
        marksmanComboBox.getItems().add("Vayne");
        marksmanComboBox.getItems().add("Draven");
        
        midlaneComboBox.getItems().add("Ahri");
        midlaneComboBox.getItems().add("Malzahar");
        
        toplaneComboBox.getItems().add("Garen");
        toplaneComboBox.getItems().add("Pantheon");
        
        jungleComboBox.getItems().add("Master Yi");
        jungleComboBox.getItems().add("Volibear");
        
    }
    
    @FXML
    public void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try
            {
               FileInputStream fileIn = new FileInputStream(file.getPath());
               ObjectInputStream in = new ObjectInputStream(fileIn);
               champion = (Champion) in.readObject();
               in.close();
               fileIn.close();
               fillFormFromChampion(champion);
            }catch(IOException ioex)
            {
               String message = "Exception occurred while opening " + file.getPath();
               displayExceptionAlert(message, ioex);
            }catch(ClassNotFoundException cnfex)
            {
               String message = "Class not found exception occurred while opening " + file.getPath();
               displayExceptionAlert(message, cnfex);
            }
        }
    }
    
    @FXML
    public void handleSave(ActionEvent event) {
        champion = createChampionFromFormData();
        if (champion == null) {
            return;
        }
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try
            {
               FileOutputStream fileOut = new FileOutputStream(file.getPath());
               ObjectOutputStream out = new ObjectOutputStream(fileOut);
               out.writeObject(champion);
               out.close();
               fileOut.close();
            }catch(IOException ioex)
            {
                String message = "Exception occurred while saving to " + file.getPath();
                displayExceptionAlert(message, ioex);
            } 
        }        
    }
    
    @FXML
    public void handleClose(ActionEvent event) {
        stage.close();
    }
    
    @FXML
    public void handleAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Champion Selector");
        alert.setContentText("This application was developed by Zach Hogan for CS3330 at the University of Missouri.");
        
        TextArea textArea = new TextArea("This application is used for selecting League of Legends champions based on their name to make different five man team compositions");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);
        alert.getDialogPane().setExpandableContent(expContent);
        
        alert.showAndWait();
    }
    
    private Champion createChampionFromFormData() {
        
        Champion p = new Champion();

        if (supportComboBox.getValue() != null &&  !supportComboBox.getValue().toString().isEmpty()) {
            p.setSupportChampionName(supportComboBox.getValue().toString());
        } else {
            displayErrorAlert("A champion must be selected.");
            return null;
        }
        
        if (marksmanComboBox.getValue() != null &&  !marksmanComboBox.getValue().toString().isEmpty()) {
            p.setMarksmanChampionName(marksmanComboBox.getValue().toString());
        } else {
            displayErrorAlert("A champion must be selected.");
            return null;
        }
        
        if (midlaneComboBox.getValue() != null &&  !midlaneComboBox.getValue().toString().isEmpty()) {
            p.setMidChampionName(midlaneComboBox.getValue().toString());
        } else {
            displayErrorAlert("A champion must be selected.");
            return null;
        }
        
        if (toplaneComboBox.getValue() != null &&  !toplaneComboBox.getValue().toString().isEmpty()) {
            p.setTopChampionName(toplaneComboBox.getValue().toString());
        } else {
            displayErrorAlert("A champion must be selected.");
            return null;
        }
        
        if (jungleComboBox.getValue() != null &&  !jungleComboBox.getValue().toString().isEmpty()) {
            p.setJungleChampionName(jungleComboBox.getValue().toString());
        } else {
            displayErrorAlert("A champion must be selected.");
            return null;
        }  
        
        return p;
    }
    
    private void fillFormFromChampion(Champion champion) {
        supportComboBox.setValue(champion.getSupportChampionName());
        marksmanComboBox.setValue(champion.getMarksmanChampionName());
        midlaneComboBox.setValue(champion.getMidChampionName());
        toplaneComboBox.setValue(champion.getTopChampionName());
        jungleComboBox.setValue(champion.getJungleChampionName());
    }
    
    private void displayErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void displayExceptionAlert(String message, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(message);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }
    
}