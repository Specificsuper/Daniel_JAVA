import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class Server extends Application{
	public void start(Stage stage) throws Exception {
		//设置文本输入框和发送按钮
		BorderPane pane=new BorderPane();
		pane.setPadding(new Insets(5,10,10,5));
		pane.setStyle("-fx-border-color: blue");
		Button button=new Button("发送");
		pane.setRight(button);
		//设置显示文本域
		TextField tf=new TextField();
		tf.setAlignment(Pos.TOP_LEFT);
		pane.setCenter(tf);
		//设置主pane布局
		BorderPane mainPane=new BorderPane();
		TextArea ta=new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setBottom(pane);;
		//初始化场景舞台
		Scene scene=new Scene(mainPane,450,200);
		stage.setTitle("qq简易聊天，服务器端 Powered by Daniel");
		stage.setScene(scene);
		stage.show();
		
		new Thread(()->{
			try {
				//创建服务器套接字
				ServerSocket serverSocket=new ServerSocket(8000);
				Platform.runLater(()->
				ta.appendText("服务开启 "+ new Date() +'\n'));
				
				//监听连接请求
				Socket socket=serverSocket.accept();
				
				//创建数据输入输出流
				DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
				while(true) {
					//接受数据
					String clientMessage=inputFromClient.readUTF();
					Platform.runLater(()->{
						ta.appendText("客户端 对 服务器 说： "+clientMessage+'\n');
					});
					//发送消息至客户端
					button.setOnAction(e->{
						try {
							String serverMessage=tf.getText().toString();
							if(serverMessage.equals("")) {
								tf.setText("");
							}
							else {
								outputToClient.writeUTF(serverMessage);
								Platform.runLater(()->{
									ta.appendText("服务器 对 客户端 说： "+serverMessage+'\n');
								});	
								tf.setText("");
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
				});
				}
			}
			 catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}).start();
	}
}