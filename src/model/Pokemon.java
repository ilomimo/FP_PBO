/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author luna
 */

public class Pokemon {
   private String label;
   private String name;
   private String bgColor;
   private String imgSrc;

    public Pokemon(String label, String name, String bgColor, String imgSrc) {
        this.label = label;
        this.name = name;
        this.bgColor = bgColor;
        this.imgSrc = imgSrc;
    }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public String getBgColor() {
        return bgColor;
    }

    public String getImgSrc() {
        return imgSrc;
    }
    
}
