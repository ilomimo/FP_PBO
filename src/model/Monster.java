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

public class Monster extends Pokemon {
    private String type; 
    private List<String> skills;
    private Stat stat;

    public Monster(String label, String name, String bgColor, String imgSrc,
                   String type, List<String> skills, 
                   Stat stat) {
        super(label, name, bgColor, imgSrc);
        this.type = type;
        this.skills = skills;
        this.stat = stat;
    }

    public String getType() {
        return type;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Stat getStat() {
        return stat;
    }
    
    public int getHP() {
        return getStat().getHP();
    }
    
    public int getATK() {
        return getStat().getAttack();
    }
    
    public int getDEF() {
        return getStat().getDeffense();
    }

    
    public static class Stat {
        private int HP;
        private int attack;
        private int deffense;

        public Stat(int HP, int attack, int deffense) {
            this.HP = HP;
            this.attack = attack;
            this.deffense = deffense;
        }

        public int getHP() {
            return HP;
        }

        public int getAttack() {
            return attack;
        }

        public int getDeffense() {
            return deffense;
        }
        
        
        
    }
   
}
