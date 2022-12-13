/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 *Untuk menampilkan deskripsi trainer dan item card
 * @author Luna
 */
public class TrainerItemController implements Initializable {
    @FXML
    private Text descriptionText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        descriptionText.setText(PokeController.getDescription);
    }    
   
}
