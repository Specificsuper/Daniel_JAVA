import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class Server extends Application{
	public void start(Stage stage) throws Exception {
		//为展示内容创建一个文本域
		TextArea ta=new TextArea();
		//创建舞台
		Scene scene=new Scene(new ScrollPane(ta),450,200);
		stage.setTitle("聊天服务器端");
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
					String radius=inputFromClient.readUTF();
					//计算半径
					String text="";
					if(radius.equals("项秉禁")) {
						text="傻逼";
					}
					else if(radius.equals("周丹")) {
						text="牛逼";
					}
					else {
						text="你输入的名字有误";
					}
					//发送面积至客户端
					outputToClient.writeUTF(text);
					
					Platform.runLater(()->{
						ta.appendText("从客户端收到的名字为： "+radius+'\n');
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