package com.MazeGameUI;
import com.Daniel_mazeBase.Fortest;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainGame extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}
	public void start(Stage stage) throws Exception {
		Fortest ft=new Fortest();
		GamePane gamePane=new GamePane();
		Pane pane=new Pane();
		BorderPane mainPane =new BorderPane();
		mainPane.getChildren().addAll(gamePane);
		stage.setScene(new Scene(mainPane,800,700));
		stage.setTitle("MAZE_DAMO");
		stage.show();
		gamePane.requestFocus();
	}

}
