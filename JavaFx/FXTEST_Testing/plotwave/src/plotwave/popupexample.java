/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plotwave;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class popupexample extends Application 
{

public static void main(String[] args) 
{
launch(args);
}

@Override
public void start(Stage primaryStage) 
{
final Group root = new Group();
final ContextMenu cm = new ContextMenu();
MenuItem menu1 = new MenuItem("First Menu");
Menu menu = new Menu("Second Menu ");
MenuItem submenu = new MenuItem("First Sub Menu");
MenuItem submenu1 = new MenuItem("Second Sub Menu");
MenuItem menu3 = new MenuItem("Third Menu");
cm.getItems().add(menu1);
cm.getItems().add(menu);
menu.getItems().add(submenu);
menu.getItems().add(submenu1);
cm.getItems().add(menu3);
Scene scene = new Scene(root, 600, 350,Color.WHITE);
scene.addEventHandler(MouseEvent.MOUSE_CLICKED,
new EventHandler<MouseEvent>() {
@Override public void handle(MouseEvent e) {
if (e.getButton() == MouseButton.SECONDARY)  
cm.show(root, e.getX(),e.getY());
}
});
primaryStage.setScene(scene);
primaryStage.setTitle("POP MENU IN JAVAFX");
primaryStage.setWidth(200);
primaryStage.setHeight(200);
primaryStage.show();
}
}