/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Luna
 */

import Functionality.Algorithm;
import Functionality.Predicate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.Main;
import main.MyListener;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import model.Item;
import model.Monster;
import model.Pokemon;
import model.Trainer;

public class PokeController implements Initializable {
    @FXML
    private VBox chosenPokeCard; 

    @FXML
    private Label pokeNameLable;

    @FXML
    private ImageView imgType;

    @FXML
    private ImageView pokeImg;

    @FXML
    private Label skill1, skill2, skill3;
    
    @FXML
    private Label hp, atk, def;
    
    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    
    @FXML
    private TextField searchBar;
    
    @FXML
    private ToggleGroup monstersort;
    
    @FXML
    private ToggleGroup itemsort;
    
    @FXML
    private void actionSearch(ActionEvent event) {
        Predicate<Pokemon> pred = a -> a.getName().toLowerCase().contains(searchBar.getText().toLowerCase());
        Pokemon pk = Algorithm.find(pokemons, pred);
        if (pk != null) setChosenPokemon(pk);
        else setChosenPokemon(noPk);
    }
    
    @FXML
    private void resetView(ActionEvent event){
        printList(pokemons);
    }
    
    @FXML
    private void filterMonster(ActionEvent event) {
        RadioButton selected = (RadioButton) monstersort.getSelectedToggle();
        List<Pokemon> unfiltered = pokemons;
        
//collect all monster
        List<Pokemon> monstersPk = Algorithm.<Pokemon>collect(unfiltered, a ->  a.getLabel().equals("Monster"));
        List<Monster> monster = Algorithm.toDifferentClass(monstersPk, Monster.class);
        
        if(selected == null) return;
        else {
            if(selected.getText().equals("Name")){
                monster.sort(Comparator.comparing(Monster::getName));
                printList(monster);
            }
            else if(selected.getText().equals("Type")) {
                monster.sort(Comparator.comparing(Monster::getType));
                printList(monster);
            }
            //descending
            else if(selected.getText().equals("HP")){
                monster.sort(Comparator.comparing(Monster::getHP).reversed());
                printList(monster);
            }
            //descending
            else if(selected.getText().equals("ATK")){
                monster.sort(Comparator.comparing(Monster::getATK).reversed());
                printList(monster);
            }
            //descending
            else if(selected.getText().equals("DEF")){
                monster.sort(Comparator.comparing(Monster::getDEF).reversed());
                printList(monster);
            }
        }

    }
    
    @FXML 
    private void filterItem(ActionEvent event) {
        RadioButton selected = (RadioButton) itemsort.getSelectedToggle();
        List<Pokemon> unfiltered = pokemons;
        
    //collect all item
        List<Pokemon> itemPk = Algorithm.<Pokemon>collect(unfiltered, a ->  a.getLabel().equals("Item"));
        List<Item> item = Algorithm.toDifferentClass(itemPk, Item.class);
        
        if(selected == null) return;
        else {
            if(selected.getText().equals("Name")){
                item.sort(Comparator.comparing(Item::getName));
                printList(item);
            }
            else if(selected.getText().equals("Rarity")) {
                item.sort(Comparator.comparing(Item::getRarity).reversed());
                printList(item);
            }
        }
    }
    
    @FXML 
    private void filterTrainer(ActionEvent e) {
        List<Pokemon> unfiltered = pokemons;
        
    //collect all trainers
        List<Pokemon> trainerPk = Algorithm.<Pokemon>collect(unfiltered, a ->  a.getLabel().equals("Trainer"));
        List<Trainer> trainer = Algorithm.toDifferentClass(trainerPk, Trainer.class);   
        printList(trainer);
    }

    private List<Pokemon> pokemons = new ArrayList<>();
    private MyListener myListener;
    Pokemon noPk = new Pokemon(
        "NONE", "NOT FOUND","000000", "/img/oops.png"
    );
    
    private void fillData() {  
        //add monster
        addMonster();
        //aditem
        addItem();
        //addtrainer
        addTrainer();
    }
    

    private void setChosenPokemon(Pokemon pk) {
        pokeNameLable.setText(pk.getName());
        pokeImg.setImage(new Image(getClass().getResourceAsStream(pk.getImgSrc())));
        chosenPokeCard.setStyle("-fx-background-color: #" + pk.getBgColor() + ";\n" +
                "    -fx-background-radius: 30;");
        
        if(pk instanceof Monster){
            Monster monster = (Monster) pk;
            imgType.setImage(new Image(getClass().getResourceAsStream(monster.getType())));
            skill1.setText(monster.getSkills().get(0));
            skill2.setText(monster.getSkills().get(1));
            skill3.setText(monster.getSkills().get(2));
            hp.setText("HP : " + monster.getStat().getHP());
            atk.setText("ATK : " + monster.getStat().getAttack());
            def.setText("DEF : " + monster.getStat().getDeffense());
        }
        else if (pk instanceof Item) { //buat item 
            Item item = (Item) pk;
            if(item.getRarity().equals("common")){
                imgType.setImage(new Image(getClass().getResourceAsStream("/img/rarity/common.png")));
            }
            else if(item.getRarity().equals("rare")){
                imgType.setImage(new Image(getClass().getResourceAsStream("/img/rarity/rare.png")));
            }
            else if(item.getRarity().equals("ultra-rare")){
                imgType.setImage(new Image(getClass().getResourceAsStream("/img/rarity/ultra-rare.png")));
            }
        }
        else if (pk instanceof Trainer) { //buat Trainer
            Trainer trainer = (Trainer) pk;
            imgType.setImage(new Image(getClass().getResourceAsStream("/img/hat.png")));
        }
        else { //kalau empty
            imgType.setImage(new Image(getClass().getResourceAsStream("/img/notfound.png")));
            skill1.setText("-"); 
            skill2.setText("-"); 
            skill3.setText("-"); 
            hp.setText("-"); 
            atk.setText("-");
            def.setText("-");
        }
       
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillData();
        if (pokemons.size() > 0) {
            setChosenPokemon(pokemons.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Pokemon pk) {
                    setChosenPokemon(pk);
                }
            };
            printList(pokemons);
        }
        
    }
    
    void printList(List<? extends Pokemon> ls) {
        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();
            for (int i = 0; i < ls.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(ls.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    void addMonster(){
        pokemons.add(
            new Monster(
              "Monster",
              "Pikachu",
              "A7745B",
              "/img/monsters/pikachu.png",  
              "/img/types/electric.png",
              new ArrayList<String>(){{
                add("Electro ball");
                add("Thunder wave");
                add("Discharge");
               }},
              new Monster.Stat(35, 55, 20)
            )
       );
       
       pokemons.add(
            new Monster(
              "Monster",
              "Bulbasaur",
              "6A7324",
              "/img/monsters/bulbasaur.png",
              "/img/types/grass.png",
              new ArrayList<String>(){{
                add("Vine Whip");
                add("Leech Seed");
                add("Razor Laaf");
               }},
              new Monster.Stat(45, 49, 49)
            )
       ); 
        
       pokemons.add(
            new Monster(
              "Monster",
              "Charmander",
              "F16C31",
              "/img/monsters/charmander.png",
              "/img/types/fire.png",
              new ArrayList<String>(){{
                add("Fire Fang");
                add("Dragon Breath");
                add("Smokescreen");
               }},
              new Monster.Stat(39, 52, 43)
            )
       ); 
       
       pokemons.add(
            new Monster(
              "Monster",
              "Squirtle",
              "291D36",
              "/img/monsters/squirtle.png",
              "/img/types/water.png",
              new ArrayList<String>(){{
                add("Water Gun");
                add("Water Pulse");
                add("Aqua Tail");
               }},
              new Monster.Stat(44, 48, 65)
            )
       );

    }
    
    void addItem(){
        pokemons.add(
            new Item(
              "A device for catching wild Pokémon. It's thrown like a ball at a Pokémon, comfortably encapsulating its target.",      
              "common",
              "Item",
              "Pokeball",
              "A7745B", //diseuain sendiri
              "/img/items/pokeball.png"      
            )
       );
        
       pokemons.add(
            new Item(
              "A good, high-performance Poké Ball that provides a higher success rate for catching Pokémon than a standard Poké Ball.",      
              "rare",
              "Item",
              "Greatball",
              "A7745B", //diseuain sendiri
              "/img/items/greatball.png"      
            )
       );
       
       pokemons.add(
            new Item(
              "An ultra-high-performance Poké Ball that provides a higher success rate for catching Pokémon than a Great Ball.",      
              "rare",
              "Item",
              "Ultraball",
              "A7745B", //diseuain sendiri
              "/img/items/ultraball.png"      
            )
       );
       
       pokemons.add(
            new Item(
              "Back in generation four, if you wanted one to hatch a Manaphy from, you'd have to beat the entirety of Pokemon Ranger.",      
              "ultra-rare",
              "Item",
              "Manaphy Egg",
              "A7745B", //diseuain sendiri
              "/img/items/manaphyegg.png"      
            )
       );
       
       pokemons.add(
            new Item(
              "This berry grew natively on the fabled Mirage Island, which was accessible incredibly rarely in Ruby, Sapphire, and Emerald.",      
              "ultra-rare",
              "Item",
              "Liechi Berry",
              "A7745B", //diseuain sendiri
              "/img/items/liechiberry.png"      
            )
       );
       
       pokemons.add(
            new Item(
              "Introduced in generation three, the Starf Berry has served as a sort of trophy for various feats in each of the mainline games.",      
              "ultra-rare",
              "Item",
              "Starf Berry",
              "A7745B", //diseuain sendiri
              "/img/items/starfberry.png"      
            )
       );
       
       
        
    }
    
    void addTrainer(){
        pokemons.add(
            new Trainer(
              "Our main character and best friend of Pikachu.",      
              "Trainer",
              "Ash Ketchum",
              "291D36", //diseuain sendiri
              "/img/trainers/ash.png"      
            )
       );
        
        pokemons.add(
            new Trainer(
              "She is a Gym Leader of Cerulean City's Gym, known officially as the Cerulean Gym.",      
              "Trainer",
              "Misty",
              "291D36", //diseuain sendiri
              "/img/trainers/misty.png"      
            )
       );
    }
}
