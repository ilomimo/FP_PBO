/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Pokemon;
/**
 *
 * @author Luna
 */
public class GridController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLable;

    @FXML
    private ImageView img;

    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(pk);
    }

    private Pokemon pk;
    private MyListener myListener;

    public void setData(Pokemon pk, MyListener myListener) {
        this.pk = pk;
        this.myListener = myListener;
        nameLabel.setText(pk.getName());
        priceLable.setText(pk.getLabel());
        Image image = new Image(getClass().getResourceAsStream(pk.getImgSrc()));
        img.setImage(image);
    }
}

