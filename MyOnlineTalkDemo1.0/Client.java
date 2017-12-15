import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client extends Application {
	DataOutputStream toServer = null;
	DataInputStream fromServer=null;

	public void start(Stage stage){
		BorderPane pane=new BorderPane();
		pane.setPadding(new Insets(5,10,10,5));
		pane.setStyle("-fx-border-color: green");
		Button button=new Button("发送");
		pane.setRight(button);
		
		TextField tf=new TextField();
		tf.setAlignment(Pos.TOP_LEFT);
		pane.setCenter(tf);
		
		BorderPane mainPane=new BorderPane();
		TextArea ta=new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setBottom(pane);;
		
		Scene scene=new Scene(mainPane,450,200);
		stage.setTitle("qq简易聊天，客户端 Powered by Daniel");
		stage.setScene(scene);
		stage.show();
		
		try {
			Socket socket=new Socket("localhost",8000);
			System.out.println("local port: "+socket.getLocalPort());
			fromServer=new DataInputStream(socket.getInputStream());
			toServer=new DataOutputStream(socket.getOutputStream());
			ta.appendText("连接到服务器成功！ "+ "时间："+new Date() +'\n');
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		new Thread(()->{
			button.setOnAction(e->{
				try {
					String clientMessage=tf.getText().trim().toString();
					if(!clientMessage.equals("")) {
						toServer.writeUTF(clientMessage);
						toServer.flush();
						Platform.runLater(()->{
						ta.appendText("客户端 对 服务端 说："+clientMessage+'\n');
						tf.setText("");
						});
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});	
		}).start();
		new Thread(()->{
			while(true) {
				try {
					String serverMessage="";
					serverMessage=fromServer.readUTF();
					if(serverMessage!=""){
						ta.appendText("服务器 对 客户端 说："+serverMessage+'\n');
					}
				} catch (IOException ex) {
					System.out.println(ex.toString());
				}
			}
		}).start();
		}
	}