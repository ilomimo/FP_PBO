/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author luna
 */
public class Item extends Pokemon {
    private String description;
    private String rarity; //ada tiga aja : common, rare, ultrarare

    public Item(String description, String rarity, String label, String name, String bgColor, String imgSrc) {
        super(label, name, bgColor, imgSrc);
        this.description = description;
        this.rarity = rarity;
    }
    
    
    
    public String getDescription() {
        return description;
    }

    public String getRarity() {
        return rarity;
    }
    
}
