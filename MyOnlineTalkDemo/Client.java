import java.io.*;
import java.net.Socket;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application{
	DataOutputStream toServer=null;
	DataInputStream fromServer=null;
	public void start(Stage stage) throws Exception {
		BorderPane pane=new BorderPane();
		pane.setPadding(new Insets(5,5,5,5));
		pane.setStyle("-fx-border-color: blue");
		pane.setLeft(new Label("请输入您的姓名："));
		
		TextField tf=new TextField();
		tf.setAlignment(Pos.BOTTOM_RIGHT);
		pane.setCenter(tf);
		
		BorderPane mainPane=new BorderPane();
		TextArea ta=new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setTop(pane);
		
		Scene scene=new Scene(mainPane,450,200);
		stage.setTitle("聊天客户端");
		stage.setScene(scene);
		stage.show();
		tf.setOnAction(e->{
			try {
				String radius=tf.getText().trim().toString();
				toServer.writeUTF(radius);;
				toServer.flush();
				String area=fromServer.readUTF();
				ta.appendText("您的姓名为： "+radius+'\n');
				ta.appendText("您被识别为："+area+'\n');
			} catch (IOException ex) {
				System.out.println(ex);
			}
		});
		try {
			Socket socket=new Socket("localhost",8000);
			System.out.println("local port: "+socket.getLocalPort());
			fromServer=new DataInputStream(socket.getInputStream());
			toServer=new DataOutputStream(socket.getOutputStream());
		} catch (IOException ex) {
			ta.appendText(ex.toString()+'\n');
		}
		
	}

}
