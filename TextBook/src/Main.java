import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application{
	private BorderPane pane=new BorderPane();
	private MenuItem miSave,miOpen,miClear,miExit;

	private ColorPicker fore=new ColorPicker();
	private	ColorPicker back=new ColorPicker();
	
	private	TextArea textarea=new TextArea();
	private	Label label=new Label();
	
	private String color;
	private	Button btOpen=new Button();
	private	Button btSave=new Button();
	
	private FileChooser fileChooser=new FileChooser();
	private FileChooser.ExtensionFilter ext=new FileChooser.ExtensionFilter("TXT(*,txt)", "*.txt");
	
	public Main() {
		MenuBar menuBar=new MenuBar();
		Menu file=new Menu("File");
		
		miSave=new MenuItem("Save");
		miOpen=new MenuItem("Open");
		miClear=new MenuItem("Clear");
		miExit=new MenuItem("Exit");
		menuBar.getMenus().add(file);
		file.getItems().addAll(miOpen,miSave,miClear,miExit);
		
		miOpen.setGraphic(new ImageView("image/open.gif"));
		miSave.setGraphic(new ImageView("image/save.gif"));
		
		btOpen.setGraphic(new ImageView("image/open.gif"));
		btSave.setGraphic(new ImageView("image/save.gif"));
		
		ToolBar toolBar=new ToolBar(btOpen,btSave,fore,back);
		
		VBox vbox=new VBox(10);
		vbox.getChildren().addAll(menuBar,toolBar);
		
		pane.setTop(vbox);
		pane.setCenter(textarea);
		pane.setBottom(label);
		
	}
	public void open() {
		Stage stage=new Stage();
		fileChooser.getExtensionFilters().add(ext);
		fileChooser.setTitle("Open File");
		File file=fileChooser.showOpenDialog(stage);
		try {
			BufferedInputStream input=new BufferedInputStream(new FileInputStream(file));
			byte[] b=new byte[input.available()];
			
			input.read(b,0,b.length);
			textarea.setText(new String(b,0,b.length));
			input.close();
			
			label.setText("Open file:"+file.getName());
		} catch (Exception e) {
			label.setText("Open file error!");
		} 
	}
	public void save() {
		Stage stage=new Stage();
		fileChooser.getExtensionFilters().add(ext);
		fileChooser.setTitle("Save Title");
		File file=fileChooser.showSaveDialog(stage);
		
		try {
			BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream(file));
			byte[] b=textarea.getText().getBytes();
			
			output.write(b,0,b.length);
			output.close();
			label.setText("Save file:"+file.getName());
		} catch (Exception e) {
			label.setText("Save file error!");
		} 
		
	}
	public void start(Stage stage) throws Exception {
		Scene scene=new Scene(pane,600,400);
		stage.setScene(scene);
		stage.setTitle("TextBook_Demo");
		stage.show();
		
		fore.setOnAction(e->{
			color=(fore.getValue()+"").substring(2);
			textarea.setStyle("-fx-text-fill:#"+color);
		});
		back.setOnAction(e->{
			Region region=(Region)textarea.lookup(".content");
			
			color=(back.getValue()+"").substring(2);
			region.setStyle("-fx-background-color:#"+color);
		});
		
		MyEvent h=new MyEvent();
		miOpen.setOnAction(h);
		miSave.setOnAction(h);
		miClear.setOnAction(h);
		miExit.setOnAction(h);
		
		btOpen.setOnAction(h);
		btSave.setOnAction(h);
	}
	class MyEvent implements EventHandler{
		public void handle(Event e) {
			Object obj=e.getSource();
			if(obj==miOpen||obj==btOpen) {
				open();
			}
			else if(obj==miSave||obj==btSave) {
				save();
			}
			else if(obj==miClear) {
				textarea.setText(null);
			}
			else if(obj==miExit) {
				System.exit(0);
			}		
		}
	}
}
