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
		    //modalityҪʹ��Modality.APPLICATION_MODEL
		    window.initModality(Modality.APPLICATION_MODAL);
		    window.setMinWidth(500);
		    window.setMinHeight(400);
		    TextField tf=new TextField("5");
		    tf.setScaleX(10);
		    tf.setScaleY(5);
		    tf.setScaleZ(3);
		    tf.setFont(Font.font("Times Roman",20));
		    Button button = new Button("ȷ��");
		    button.setOnAction(e ->{
		    	 Config.WIDTH=Config.SWIDTH/Integer.parseInt(tf.getText().trim().toString());
		    	window.close();
		    });

		    Label label = new Label("���������봴�����Թ���С��");

		    VBox layout = new VBox(10);
		    layout.getChildren().addAll(label ,tf, button);
		    layout.setAlignment(Pos.CENTER);

		    Scene scene = new Scene(layout);
		    window.setScene(scene);
		    //ʹ��showAndWait()�ȴ���������ڣ������������main�е��Ǹ����ڲ�����Ӧ
		    window.showAndWait();   
	  }
}
