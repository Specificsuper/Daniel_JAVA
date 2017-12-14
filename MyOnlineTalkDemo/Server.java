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
		//Ϊչʾ���ݴ���һ���ı���
		TextArea ta=new TextArea();
		//������̨
		Scene scene=new Scene(new ScrollPane(ta),450,200);
		stage.setTitle("�����������");
		stage.setScene(scene);
		stage.show();
		
		new Thread(()->{
			try {
				//�����������׽���
				ServerSocket serverSocket=new ServerSocket(8000);
				Platform.runLater(()->
				ta.appendText("������ "+ new Date() +'\n'));
				
				//������������
				Socket socket=serverSocket.accept();
				
				//�����������������
				DataInputStream inputFromClient=new DataInputStream(socket.getInputStream());
				DataOutputStream outputToClient=new DataOutputStream(socket.getOutputStream());
				
				while(true) {
					//��������
					String radius=inputFromClient.readUTF();
					//����뾶
					String text="";
					if(radius.equals("�����")) {
						text="ɵ��";
					}
					else if(radius.equals("�ܵ�")) {
						text="ţ��";
					}
					else {
						text="���������������";
					}
					//����������ͻ���
					outputToClient.writeUTF(text);
					
					Platform.runLater(()->{
						ta.appendText("�ӿͻ����յ�������Ϊ�� "+radius+'\n');
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