package com.Daniel_LLK;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LLKMain extends Application{
	public static TextField tf=new TextField();
	public void start(Stage stage) throws Exception {
		HBox box=new HBox();
		BorderPane pane=new BorderPane();
		Button again=new Button("重新游戏");
		Button exit=new Button("退出");
		LLKCanvas llkCanvas=new LLKCanvas();
		
		tf.setEditable(false);
		tf.setText("0");
		
		box.getChildren().addAll(tf,again,exit);
		box.setAlignment(Pos.TOP_CENTER);

		pane.setTop(box);
		pane.setCenter(llkCanvas);
		
		Scene scene=new Scene(pane,660,660);
		stage.setTitle("连连看");
		stage.setScene(scene);
		stage.show();
		
		again.setOnAction(e->{
			llkCanvas.initMap();
		});
		
		exit.setOnAction(e->{
			System.exit(0);
		});
	}
		public static void main(String[] args) {
			Application.launch(args);
		}

}
