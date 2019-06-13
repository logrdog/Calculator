/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calcgui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;



/**
 *
 * @author logan
 */
public class CalcGui extends Application {
    private CalcStack model = new CalcStack();
    private int width = 400;
    private int height = 500;
    private String top;
    private String bottom;
    private TextField topNumber;
    private TextField bottomNumber;
    private Button addButton;
    private Button subButton;
    private Line line;
    private Pane root;
    private Scene scene;
    private Label label;
    
    @Override
    public void start(Stage primaryStage) {
        label = new Label();
        line = new Line();
        topNumber = new TextField();
        bottomNumber = new TextField();
        addButton = new Button();
        subButton = new Button();
        root = new Pane();
        
        label.setLayoutX(width/2-50);
        label.setLayoutY(height/2-115);
        line.setLayoutX(width/2-50);
        line.setLayoutY(height/2-125);
        line.setEndX((width/2));
        topNumber.setPrefSize(200, 50);
        topNumber.setLayoutX((width/2)-50);
        bottomNumber.setPrefSize(200, 50);
        bottomNumber.setLayoutX((width/2)-50);
        bottomNumber.setLayoutY(60);
        addButton.setText("+");
        addButton.setLayoutX((width/2)-80);
        addButton.setLayoutY((height/2)-180);
        subButton.setText("-");
        subButton.setLayoutX((width/2)-80);
        subButton.setLayoutY((height/2)-150);
        
        
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void handle(Event event) {
                if(event.getSource().equals(addButton)){
                    setTop(topNumber.getText());
                    top = getTop();
                    setBottom(bottomNumber.getText());
                    bottom = getBottom();
                    model.getGreaterSize(top, bottom);
                    model.setStackTop(top); 
                    model.setStackBottom(bottom);
                    model.add();
                    label.setText(model.getString());
                } 
                if(event.getSource().equals(subButton)){
                    setTop(topNumber.getText());
                    top = getTop();
                    setBottom(bottomNumber.getText());
                    bottom = getBottom();
                    model.getGreaterSize(top, bottom);
                    model.setStackTop(top);
                    model.setStackBottom(bottom);
                    model.subtract();
                    label.setText(model.getString());
                }
            }
        };
        
        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        subButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
        root.getChildren().addAll(topNumber, bottomNumber, line, addButton, subButton, label);
        
        scene = new Scene(root, height, width);
        primaryStage.setTitle("Big Integers Calculator!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
    */
    public static void main(String[] args) {
        launch(args);
    }
    
    /** 
     * @param str
     * sets string for top
    */
    public void setTop(String str){
        top = str;
    }
    /** 
     * @param str
     * sets String for bottom
    */
    public void setBottom(String str){
        bottom = str;
    }
    /** 
     * @return top
     * returns String for top
    */
    public String getTop(){
        return top;
    }
    /**
       
     * @return String
     * returns String for bottom 
    */
    public String getBottom(){
        return bottom;
    }
}
