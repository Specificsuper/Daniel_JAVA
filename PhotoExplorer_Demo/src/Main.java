import java.io.*;
import javafx.application.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class Main extends Application{
	BorderPane pane=new BorderPane();
	private Label label;
	private String[] stringView=new String[20];
	private int now=0,then=0;
	private ImageView imageView;
	private Button btOpen,btLast,btNext,btBig,btSmall;
	private FileChooser fileChooser=new FileChooser();
	private FileChooser.ExtensionFilter ext=new FileChooser.ExtensionFilter("图像文件(*.JPG,*.JPEG,*.BMP,*.PNG,*.GIF)","*.JPG","*.GIF","*.JREG","*.BMP","*.PNG");
	public void start(Stage stage) throws Exception {
		btOpen=new Button();
		btOpen.setGraphic(new ImageView("image/OpenAction.gif"));
		btLast=new Button();
		btLast.setGraphic(new ImageView("image/LastAction.gif"));
		btNext=new Button();
		btNext.setGraphic(new ImageView("image/NextAction.gif"));
		btBig=new Button();
		btBig.setGraphic(new ImageView("image/BigAction.gif"));
		btSmall=new Button();
		btSmall.setGraphic(new ImageView("image/SmallAction.gif"));
		
		ToolBar toolBar=new ToolBar();
		toolBar.getItems().addAll(btOpen,btLast,btNext,btBig,btSmall);
		pane.setTop(toolBar);
		
		label=new Label();
		pane.setCenter(label);
		
		MyEvent h=new MyEvent();
		btOpen.setOnAction(h);
		btLast.setOnAction(h);
		btNext.setOnAction(h);
		btBig.setOnAction(h);
		btSmall.setOnAction(h);
		
		Scene scene=new Scene(pane,600,450);
		stage.setTitle("ShowPhoto");
		stage.setScene(scene);
		stage.show();
	}
	class MyEvent implements EventHandler{
		public void handle(Event e) {
			Object obj=e.getSource();
			if(obj==btOpen)
				open();
			else if(obj==btLast)
				previous();
			else if(obj==btNext)
				next();
			else if(obj==btBig)
				big();
			else if(obj==btSmall)
				small();
		}
	}
	public void open() {
		Stage stage=new Stage();
		fileChooser.getExtensionFilters().add(ext);
		fileChooser.setTitle("Open image");
		File file=fileChooser.showOpenDialog(stage);
		String localUrl=file.toURI().toString();//获得图片的路径
		now++;
		then++;
		System.out.println(file);
		stringView[now]=localUrl;
		
		Image localImage =new Image(localUrl,false);
		imageView=new ImageView(localImage);
		imageView.setFitHeight(200);//设置图片的高和宽
		imageView.setFitWidth(200);
		label.setGraphic(imageView);
	}
	public void previous() {
		if(now>1) {
			now--;
			Image localImage =new Image(stringView[now],false);
			imageView=new ImageView(localImage);
			imageView.setFitHeight(200);//设置图片的高和宽
			imageView.setFitWidth(200);
			label.setGraphic(imageView);
		}	
	}
	public void next() {
		if(now>0&&now<then) {
			now++;
			Image localImage =new Image(stringView[now],false);
			imageView=new ImageView(localImage);
			imageView.setFitHeight(200);//设置图片的高和宽
			imageView.setFitWidth(200);
			label.setGraphic(imageView);
		}
	}
	public void big() {
		if(imageView!=null) {
			imageView.setFitHeight(imageView.getFitHeight()+10);
			imageView.setFitWidth(imageView.getFitWidth()+10);
		}
	}
	public void small() {
		if(imageView!=null) {
			imageView.setFitHeight(imageView.getFitHeight()-10);
			imageView.setFitWidth(imageView.getFitWidth()-10);
		}
		}
}
