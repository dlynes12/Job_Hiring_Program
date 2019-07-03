import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;



public class View implements EventHandler<ActionEvent> {



    public View(ApplicationModel model , Stage stage) {

        initUI(stage);
    }


    private void initUI(Stage stage) {



        }



    private MenuBar createMenuBar() {

        MenuBar menuBar = new MenuBar();
        Menu menu;
        MenuItem menuItem;

        // A menu for File

        menu = new Menu("File");

        menuItem = new MenuItem("New");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Open");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Save");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());

        menuItem = new MenuItem("Exit");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuBar.getMenus().add(menu);

        // Another menu for Edit

        menu = new Menu("Edit");

        menuItem = new MenuItem("Cut");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Copy");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menuItem = new MenuItem("Paste");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);

        menu.getItems().add(new SeparatorMenuItem());


        menuBar.getMenus().add(menu);

        // Another menu for Fill Style

        menu = new Menu("");

        menuItem = new MenuItem("");
        menuItem.setOnAction(this);
        menu.getItems().add(menuItem);



        menuBar.getMenus().add(menu);

        return menuBar;
    }

    @Override
    public void handle(ActionEvent event) {
        System.out.println(((MenuItem)event.getSource()).getText());
        String selection = ((MenuItem)event.getSource()).getText();
        if (selection == "") {
        }else if (selection == "") {
        }
    }
}