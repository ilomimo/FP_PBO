/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import model.Item;
import model.Monster;
import model.Pokemon;
import model.Trainer;

/**
 *
 * @author Luna
 */


public class PokeController implements Initializable {
    @FXML
    private VBox chosenPokeCard; 

    @FXML
    private VBox holder; 
    
    @FXML
    private Label pokeNameLable;

    @FXML
    private ImageView imgType;

    @FXML
    private ImageView pokeImg;
    
    @FXML
    private Label skillordesc;
    
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
    private Text descriptionText;
    
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

//non FXML annotation
    private List<Pokemon> pokemons = new ArrayList<>();
    private MyListener myListener;
    Pokemon noPk = new Pokemon(
        "NONE", "NOT FOUND","000000", "/img/oops.png"
    );
    
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
            skillordesc.setText("SKILLS");
            loadMonster(monster);
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
            skillordesc.setText("DESCRIPTION");
            loadTrainerItem(item.getDescription());
        }
        else if (pk instanceof Trainer) { //buat Trainer
            Trainer trainer = (Trainer) pk;
            imgType.setImage(new Image(getClass().getResourceAsStream("/img/hat.png")));
            skillordesc.setText("DESCRIPTION");
            loadTrainerItem(trainer.getDescription());
        }
        else { //kalau empty
            imgType.setImage(new Image(getClass().getResourceAsStream("/img/notfound.png")));
            skillordesc.setText("????");
            loadEmptyCard();
        }
       
    }
    
    public static String getDescription;
    private void loadTrainerItem(String desc){
        getDescription = desc;
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/trainer_and_item.fxml"));
            holder.getChildren().setAll(pane);
            holder.setAlignment(Pos.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(PokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Monster selectedMonster;
    private void loadMonster(Monster m){
        selectedMonster = m;
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/monster.fxml"));
            holder.getChildren().setAll(pane);
            holder.setAlignment(Pos.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(PokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadEmptyCard(){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/empty.fxml"));
            holder.getChildren().setAll(pane);
            holder.setAlignment(Pos.CENTER);
        } catch (IOException ex) {
            Logger.getLogger(PokeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void printList(List<? extends Pokemon> ls) {
        int column = 0;
        int row = 1;
        try {
            grid.getChildren().clear();
            for (int i = 0; i < ls.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/grid.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                GridController itemController = fxmlLoader.getController();
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
              "32231b",
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
              "AA5556",
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
       
       pokemons.add(
            new Monster(
              "Monster",
              "Haunter",
              "95629E",
              "/img/monsters/haunter.png",
              "/img/types/ghost.png",
              new ArrayList<String>(){{
                add("Astonish");
                add("Grudge");
                add("Skill Swap");
               }},
              new Monster.Stat(45, 50, 45)
            )
       );
       
       pokemons.add(
            new Monster(
              "Monster",
              "Zapdos",
              "4A4A35",
              "/img/monsters/zapdos.png",
              "/img/types/electric.png",
              new ArrayList<String>(){{
                add("Rising Voltage");
                add("Dual Wingbeat");
                add("Thunder Wave");
               }},
              new Monster.Stat(90, 125, 90)
            )
       );
       
       pokemons.add(
            new Monster(
              "Monster",
              "Rayquaza",
              "634266",
              "/img/monsters/rayquaza.png",
              "/img/types/dragon.png",
              new ArrayList<String>(){{
                add("Dragon Dance");
                add("Hyper Beam");
                add("Dragon Claw");
               }},
              new Monster.Stat(105, 150, 90)
            )
       );
       
       pokemons.add(
            new Monster(
              "Monster",
              "Bibil",
              "634266",
              "/img/monsters/bibil.png",
              "/img/types/rock.png",
              new ArrayList<String>(){{
                add("Magnet Pull");
                add("Sturdy");
                add("Sand Force");
               }},
              new Monster.Stat(30, 45, 135)
            )
       );
       
       
       pokemons.add(
            new Monster(
              "Monster",
              "Umbreon",
              "4A4A42",
              "/img/monsters/umbreon.png",
              "/img/types/dark.png",
              new ArrayList<String>(){{
                add("Dark Pulse");
                add("Dream Eater");
                add("Payback");
               }},
              new Monster.Stat(95, 65, 110)
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
              "6D7B90", 
              "/img/items/pokeball.png"      
            )
       );
        
       pokemons.add(
            new Item(
              "A good, high-performance Poké Ball that provides a higher success rate for catching Pokémon than a standard Poké Ball.",      
              "rare",
              "Item",
              "Greatball",
              "6D7B90", 
              "/img/items/greatball.png"      
            )
       );
       
       pokemons.add(
            new Item(
              "An ultra-high-performance Poké Ball that provides a higher success rate for catching Pokémon than a Great Ball.",      
              "rare",
              "Item",
              "Ultraball",
              "6D7B90", 
              "/img/items/ultraball.png"      
            )
       );
       
       pokemons.add(
            new Item(
              "Back in generation four, if you wanted one to hatch a Manaphy from, you'd have to beat the entirety of Pokemon Ranger.",      
              "ultra-rare",
              "Item",
              "Manaphy Egg",
              "6D7B90", 
              "/img/items/manaphyegg.png"      
            )
       );
       
       pokemons.add(
            new Item(
              "This berry grew natively on the fabled Mirage Island, which was accessible incredibly rarely in Ruby, Sapphire, and Emerald.",      
              "ultra-rare",
              "Item",
              "Liechi Berry",
              "6D7B90", 
              "/img/items/liechiberry.png"      
            )
       );
       
       pokemons.add(
            new Item(
              "Introduced in generation three, the Starf Berry has served as a sort of trophy for various feats in each of the mainline games.",      
              "ultra-rare",
              "Item",
              "Starf Berry",
              "6D7B90", 
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
              "291D36", 
              "/img/trainers/ash.png"      
            )
       );
        
        pokemons.add(
            new Trainer(
              "She is a Gym Leader of Cerulean City's Gym, known officially as the Cerulean Gym.",      
              "Trainer",
              "Misty",
              "291D36", 
              "/img/trainers/misty.png"      
            )
       );
        
        pokemons.add(
            new Trainer(
              "A member of the Sinnoh Elite Four, aims to become a perfect Trainer who is as strong and beautiful as his beloved Bug-type Pokémon.",      
              "Trainer",
              "Aaron",
              "291D36", 
              "/img/trainers/aaron.png"      
            )
       );
        
       pokemons.add(
            new Trainer(
              "This subway boss is the younger of twin brothers who run the battle subway in Unova. He likes working with different combinations of Pokémon in Double Battles.",      
              "Trainer",
              "Emmet",
              "291D36", 
              "/img/trainers/emmet.png"      
            )
       );
       
       pokemons.add(
            new Trainer(
              "Bianca is a Trainer who once traveled across the Unova region. She loves Pokémon and currently works as an assistant to Professor Juniper to learn more about them.",      
              "Trainer",
              "Bianca",
              "291D36", 
              "/img/trainers/bianca.png"      
            )
       );
       
       pokemons.add(
            new Trainer(
              "As the leader of Team Aqua, Archie aims to create an ideal world for Pokémon. He has a wild, outgoing personality and has earned the trust of his subordinates.",      
              "Trainer",
              "Archie",
              "291D36", 
              "/img/trainers/archie.png"      
            )
       );
       
       pokemons.add(
            new Trainer(
              "Gladion doesn't say much, but he cares deeply about his friends, family, and Pokémon. He used to be much more rebellious and fixated on becoming stronger.",      
              "Trainer",
              "Gladion",
              "291D36", 
              "/img/trainers/gladion.png"      
            )
       );
       pokemons.add(
            new Trainer(
              "Alder is the highly experienced former Champion of Unova. He wanders around, telling younger generations the good news of a wonderful future with Pokémon.",      
              "Trainer",
              "Alder",
              "291D36", 
              "/img/trainers/alder.png"      
            )
       );
       
       pokemons.add(
            new Trainer(
              "He is the strong leader of a group of toughs in Alola called Team Skull. The group's rowdy members obey him, and his kindness has earned their devoted respect.",      
              "Trainer",
              "Guzma",
              "291D36", 
              "/img/trainers/guzma.png"      
            )
       );
       
       pokemons.add(
            new Trainer(
              "This member of Team Rocket extends his reach to the stars above, working day and night with Jessie and Meowth to make the dreams of their boss, Giovanni, a reality.",      
              "Trainer",
              "James",
              "291D36", 
              "/img/trainers/james.png"      
            )
       );
       
       pokemons.add(
            new Trainer(
              "Lana is an avid fisher and one of the captains of Akala Island. She loves her family and is constantly helping the family business or babysitting her twin sisters.",      
              "Trainer",
              "Lana",
              "291D36", 
              "/img/trainers/lana.png"      
            )
       );
       
       pokemons.add(
            new Trainer(
              "This highly curious and easygoing Trainer is friendly with everyone and quite easy to get along with. She doesn't worry about the little things and tends to act on instinct.",      
              "Trainer",
              "Selene",
              "291D36", 
              "/img/trainers/selene.png"      
            )
       );
       
    }
}
