/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author luna
 */
import java.util.List;

public class Trainer extends Pokemon {
    private String description;

    public Trainer(String description, String label, String name, String bgColor, String imgSrc) {
        super(label, name, bgColor, imgSrc);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
}
