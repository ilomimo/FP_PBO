/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
/**
 *
 * @author Luna
 */
public class MonsterController implements Initializable {
    @FXML
    private Label skill1, skill2, skill3;
    
    @FXML
    private Label hp, atk, def;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        skill1.setText(PokeController.selectedMonster.getSkills().get(0));
        skill2.setText(PokeController.selectedMonster.getSkills().get(1));
        skill3.setText(PokeController.selectedMonster.getSkills().get(2));
        hp.setText("HP : " + PokeController.selectedMonster.getStat().getHP());
        atk.setText("ATK : " + PokeController.selectedMonster.getStat().getAttack());
        def.setText("DEF : " + PokeController.selectedMonster.getStat().getDeffense());
    }    
    
}
