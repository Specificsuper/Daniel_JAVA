package com.MazeGameUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class initfirstmessage {
	int x=0;  
	public void  display(){
		    Stage window = new Stage();
		    window.setTitle("title");
		    //modality要使用Modality.APPLICATION_MODEL
		    window.initModality(Modality.APPLICATION_MODAL);
		    window.setMinWidth(500);
		    window.setMinHeight(400);
		    TextField tf=new TextField("5");
		    tf.setScaleX(10);
		    tf.setScaleY(5);
		    tf.setScaleZ(3);
		    tf.setFont(Font.font("Times Roman",20));
		    Button button = new Button("确定");
		    button.setOnAction(e ->{
		    	 Config.WIDTH=Config.SWIDTH/Integer.parseInt(tf.getText().trim().toString());
		    	window.close();
		    });

		    Label label = new Label("请输入您想创建的迷宫大小！");

		    VBox layout = new VBox(10);
		    layout.getChildren().addAll(label ,tf, button);
		    layout.setAlignment(Pos.CENTER);

		    Scene scene = new Scene(layout);
		    window.setScene(scene);
		    //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
		    window.showAndWait();   
	  }
}
