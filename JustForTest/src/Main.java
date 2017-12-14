import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	String text="";
	public void start(Stage stage) throws Exception {
		BorderPane pane=new BorderPane();
		Label label=new Label("Welcome");
		pane.setCenter(label);
		Thread thread=new Thread(new Runnable() {
			public void run() {
				try {
					while(true) {
						if(label.getText().trim().length()==0) {
							text="Welcome";
						}
						else {
							text="";
						}
						Platform.runLater(new Runnable() {
							public void run() {
								label.setText(text);
							}
						});
						Thread.sleep(100);
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		thread.start();
		Scene scene=new Scene(pane,500,400);
		stage.setScene(scene);
		stage.setTitle("FlashText");
		stage.show();
	}
	
}
