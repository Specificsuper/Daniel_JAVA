package com.MazeGameUI;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainGame extends Application{

	public static void main(String[] args) {
		Application.launch(args);
	}
	public void start(Stage stage) throws Exception {
		GamePane gamePane=new GamePane();
		Pane pane=new Pane();
		pane.getChildren().add(gamePane);
		stage.setScene(new Scene(pane,Config.SWIDTH,Config.SHEIGHT));
		stage.setTitle("MAZE_DAMO");
		stage.show();
		gamePane.requestFocus();
	}

}
