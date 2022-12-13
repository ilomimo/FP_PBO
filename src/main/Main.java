/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Luna
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/views/dex.fxml"));
        primaryStage.setTitle("Pokemon DEX");
        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(800);
        primaryStage.setWidth(1300);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
