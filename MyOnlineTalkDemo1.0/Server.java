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
		//�����ı������ͷ��Ͱ�ť
		BorderPane pane=new BorderPane();
		pane.setPadding(new Insets(5,10,10,5));
		pane.setStyle("-fx-border-color: blue");
		Button button=new Button("����");
		pane.setRight(button);
		//������ʾ�ı���
		TextField tf=new TextField();
		tf.setAlignment(Pos.TOP_LEFT);
		pane.setCenter(tf);
		//������pane����
		BorderPane mainPane=new BorderPane();
		TextArea ta=new TextArea();
		mainPane.setCenter(new ScrollPane(ta));
		mainPane.setBottom(pane);;
		//��ʼ��������̨
		Scene scene=new Scene(mainPane,450,200);
		stage.setTitle("qq�������죬�������� Powered by Daniel");
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
					String clientMessage=inputFromClient.readUTF();
					Platform.runLater(()->{
						ta.appendText("�ͻ��� �� ������ ˵�� "+clientMessage+'\n');
					});
					//������Ϣ���ͻ���
					button.setOnAction(e->{
						try {
							String serverMessage=tf.getText().toString();
							if(serverMessage.equals("")) {
								tf.setText("");
							}
							else {
								outputToClient.writeUTF(serverMessage);
								Platform.runLater(()->{
									ta.appendText("������ �� �ͻ��� ˵�� "+serverMessage+'\n');
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