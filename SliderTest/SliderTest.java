import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SliderTest extends Application{
	private Button bt=new Button("Change Button Text Color");
	class ColorDialog{
		private Stage stage=new Stage();
		private double Red=0;
		private double Green=0;
		private double Blue=0;
		ColorDialog(){
			BorderPane mainPn=new BorderPane();
			Rectangle rectangle=new Rectangle(0,0,500,200);
			
			GridPane pn1=new GridPane();

			Text tv0=new Text(5,2,"Red");
			Text tv1=new Text("Green");
			Text tv2=new Text("Blue");
			
			pn1.setLayoutX(3);
			pn1.setLayoutY(4);
			Slider sl0=new Slider(0,100,0);
			Slider sl1=new Slider(0,100,0);
			Slider sl2=new Slider(0,100,0);
	
			Button bt0=new Button("OK");
			Button bt1=new Button("CANCEL");
			pn1.add(tv0, 0, 0);
			pn1.add(tv1, 0, 1);
			pn1.add(tv2, 0, 2);
			pn1.add(sl0, 2, 0);
			pn1.add(sl1, 2, 1);
			pn1.add(sl2, 2, 2);
			pn1.add(bt0, 1, 3);
			pn1.add(bt1, 2, 3);
			pn1.setAlignment(Pos.CENTER);
			mainPn.setTop(rectangle);
			mainPn.setBottom(pn1);
			
			sl0.valueProperty().addListener(ov->{
				rectangle.setFill(new Color(sl0.getValue()/100.0,sl1.getValue()/100.0,sl2.getValue()/100.0,1));
			});
			sl1.valueProperty().addListener(ov->{
				rectangle.setFill(new Color(sl0.getValue()/100.0,sl1.getValue()/100.0,sl2.getValue()/100.0,1));
			});
			sl2.valueProperty().addListener(ov->{
				rectangle.setFill(new Color(sl0.getValue()/100.0,sl1.getValue()/100.0,sl2.getValue()/100.0,1));
			});
				bt0.setOnAction(e->{
					try {
						Red=sl0.getValue()/100.0*1;
						Green=sl1.getValue()/100.0*1;
						Blue=sl2.getValue()/100.0*1;
						Color color=new Color(Red,Green,Blue, 1);
						bt.setTextFill(color);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
				bt1.setOnAction(e->{
					sl0.setValue(0);
					sl1.setValue(0);
					sl2.setValue(0);
					bt.setTextFill(Color.BLACK);
				});
			
			stage.setScene(new Scene(mainPn,500,300));
			stage.show();
		}
	}
	public void start(Stage stage) throws Exception {
		BorderPane pn=new BorderPane();
		pn.setCenter(bt);
		Scene scene=new Scene(pn,500,300); 
		stage.setScene(scene);
		stage.show();
		bt.setOnAction(e->{
			ColorDialog Cd=new ColorDialog(); 
		});
	}}