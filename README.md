# Pokémon Dex

Final Project Mata Kuliah Pemrograman Berbasis Objek (B)

Dibuat oleh: Clarissa Luna Maheswari - 5025211003


# Deskripsi

Pokémon Dex adalah sebuah sarana yang dirancang untuk membuat katalog dan memberikan informasi mengenai berbagai spesies Pokémon yang ditampilkan dalam seri video game, anime, dan manga Pokémon. Di dalam video game, setiap player menemui jenis Pokémon yang baru, datanya akan ditambahkan ke dalam sebuah gawai untuk melihat Pokémon yang sudah ditangkap. Pada Pokémon Dex, pengakses dapat melihat berbagai jenis item berupa Monster Pokémon, Item Pokémon, dan Trainer Pokémon. Item-item tersebut dapat dikategorikan dan dicari berdasarkan Nama, Jenis, HP, ATK, dan DEFENSE untuk Monster Pokémon serta Nama dan Rarity untuk Item Pokémon.

# Demo Aplikasi Youtube

https://youtu.be/Fn35R4S5xx4

# Terinspirasi dan dikembangkan dari:

https://www.youtube.com/watch?v=XlAzQ170kzM

# ASPEK OOP

1) Casting

Casting sudah diterapkan mulai dari upcasting contohnya dari ArrayList ke List, Monster ke Pokemon, Trainer ke Pokemon, dan sebagainya. Terdapat juga proses downcasting yang mana dilakukan secara aman sehingga tidak terjadi runtime error 

Contoh:

Pada bagian ini Pokemon class di-downcast ke Monster:
```
if(pk instanceof Monster){
  Monster monster = (Monster) pk;

```

Serta downcasting secara besar (seluruh elemnet di list) menggunakan method toDifferentClass yang ada di class Algorithm:

```
public static <T extends Pokemon> List<T> toDifferentClass(List<Pokemon> pks, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        pks.forEach(p -> list.add(clazz.cast(p)));
        return list;
    }
    
}
```

2) Constructor

Aspek ini sudah banyak digunaknan dalam program, terutama di dalam package Model.

Contoh:

Pada Class Monster

```public Monster(String label, String name, String bgColor, String imgSrc,
                   String type, List<String> skills, 
                   Stat stat) {
        super(label, name, bgColor, imgSrc);
        this.type = type;
        this.skills = skills;
        this.stat = stat;
    }
```

3) Overloading

Overloading method sudah diterapkan yakni pada class Algorithm, terdapat 2 method yang menerapkannya (find dan collect).

Contoh Method Find:

Terdapat 6 Overloading untuk menghindari single point of failure pada class algorithm.

```
    public static <T> T find(Iterator<T> iterator, T value) {
        Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }
    public static <T> T find(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }
    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }
    public static <T> T find(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }
    public static <T> T find(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) return current;
        }
        return null;
    }
    
```

4) Overriding

Karena banyak diterapkannya implementation dari interface, maka overriding pasti digunakan

Contoh: 

Class TrainerItemController mengimplementasikan interface initalizable dari JavaFX, meng-override method initalize.

```
public class TrainerItemController implements Initializable {
    @FXML
    private Text descriptionText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        descriptionText.setText(PokeController.getDescription);
    }    
   
}
```

5) Encapsulation

Sudah banyak digunakan dalam program, terutama di dalma package Model, class-class di dalamnya sudah menerapkan access modifier private serta penambahan getter dan setter.

Contohnya pada class Monster:

private:
```
private String type;
private List<String> skills;
private Stat stat;
```

Getter Setter:
```
    public String getType() {
        return type;
    }

    public List<String> getSkills() {
        return skills;
    }

    public Stat getStat() {
        return stat;
    }
```
6) Inheritance

Class-class dalam Model sudah menerapkan ini, Pokemon sebagai superclass, serta 3 class lainnya sebagai subclass.

Contoh:
Pada Class Monster:
```
public class Monster extends Pokemon{
```

7) Polymorphism

Contoh:

Find pada Algorithm Class

```
    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) return current;
        }
        return null;
    }
    
```

8) ArrayList

Contoh:

ArrayList pada Collect yang terletak pada Algorithm Class
```
    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }
    public static <T> List<T> collect(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) list.add(current);
        }
        return list;
    }
```
9) Exception Handling

Karena digunakannya process IO, maka dilakukan exception handling terhadap IOException.
Contoh:

```
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
```
10) GUI Interface

Tampilan awal:
![image](https://user-images.githubusercontent.com/83297152/207369874-d7e60fc1-894b-47d7-8672-965ee71f5169.png)

Menggunakan fitur sorting:
![image](https://user-images.githubusercontent.com/83297152/207370469-8d74325f-36dd-4c5e-86be-3e084c6a1baf.png)

Menggunakan fitur searching:
![image](https://user-images.githubusercontent.com/83297152/207371106-d7a7bf93-5c3b-4c30-ac8b-a54e5bc0caa0.png)

11) Abstract Class

Aspek ini banyak digunakan pada Predicate dan MyListener.

Contoh:

Pada class Predicate
```
public interface Predicate <T> {
    public abstract boolean predicate(T args);
}
```

Digunakan di method di class Algorithm:
```
    public static <T> T find(Iterator<T> iterator, T value) {
        Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }
```
12) Generics

Generic class terdapat pada class Predicate, sedangkan generic method terdapat pada seluruh method di class Algorithm : find, collect, dan toDifferentClass yang menggunakan wildcard.

Contoh:

```
   public static <T> List<T> collect(Iterator<T> iterator, T value) {
        Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }
    public static <T> List<T> collect(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }
    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }
    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> list = new ArrayList<>();
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current)) list.add(current);
        }
        return list;
    }
```
Pada contoh method di atas, T bersifat generic, artinya kita bisa melakukan method collect untuk semua jenis object, pada program semisal kita melakukan method collect pada list dari Monster. Maka method tersebut akan mengembalikan list dari objek Monster. Hal serupa juga terdapat pada method find yang mana akan mengembalikan single objek dari tipe objek yang diinginkan.

```
//cast to desired Class that extends Pokemon    
    public static <T extends Pokemon> List<T> toDifferentClass(List<Pokemon> pks, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        pks.forEach(p -> list.add(clazz.cast(p)));
        return list;
    }
    
}
```

Pada method toDifferentClass tipe generic yang digunakan dispesifikasikan agar object yang diterima hanya object meng extends Pokemon yakni Monster, Trainer, dan Item. Adapun parameter yang diterima adalah list berisi pokemon, dan object Class. Inti dari method ini adalah kita mengubah setiap elemen di list Pokemon yang semula bertipe Pokemon menjadi tipe subclassnya, misal ke Monster. 

```
//collect all monster
        List<Pokemon> monstersPk = Algorithm.<Pokemon>collect(unfiltered, a ->  a.getLabel().equals("Monster"));
        List<Monster> monster = Algorithm.toDifferentClass(monstersPk, Monster.class);

```
pada potongan program di atas, kita melakukan algorithm collect untuk list bertipe pokemon dengan predicate label “Monster”. Artinya kita hanya akan melakukan collect terhadap objek yang memiliki label “Monster”. Setelah itu, objek-objek Pokemon yang berlabel Monster akan di-casting ke tipe Monster dengan memberikan argumen kedua pada method toDifferentClass berupa Monster.class. 


13) Collection

Pada program ini digunakan collection berupa List atau ArrayList  
```
private List<Pokemon> pokemons = new ArrayList<>();
```
14) Input Output
Input Ouput sudah jelas digunakan karena terdapat input file di directory (berupa fxml, resources image) dan  output terhadapnya.

Bagian yang memanggil method getResource:
```

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
```
Contoh lain (terdapat di IOException juga):
```
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
```

https://intip.in/PenjelasanAspekOOPLuna


