package com.Daniel_LLK;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class LLKCanvas extends Canvas{
	private final int W=50;
	private final int GameSize=10;

	private int x1=-1;
	private int y1=-1;
	private int x2,y2;

	private int COL=10;
	private int ROW=10;
	private int[]map=new int[10*10];
	private int BLANK=-1;

	private GraphicsContext g=this.getGraphicsContext2D();
	private boolean check=false;

	public LLKCanvas() {
		this.setWidth(660);
		this.setHeight(660);

		this.initMap();
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent e) {
				if(e.getButton()==MouseButton.PRIMARY) {
					x2=(int)((e.getX()-W)/W);
					y2=(int)((e.getY()-W)/W);
					if(x1!=x2||y1!=y2) {
						if(check==false) {
							x1=x2;
							y1=y2;	
						}
						else {
							if(isSame(x1,y1,x2,y2)&&isCut(x1,y1,x2,y2)) {
								int grade=Integer.parseInt(LLKMain.tf.getText())+2;
								LLKMain.tf.setText(String.valueOf(grade));
								if(x1!=x2||y1!=y2) {
									map[y1*COL+x1]=BLANK;
									map[y2*COL+x2]=BLANK;
									myRepaint();
								}
	
							}
						}
						check=!check;
					}
					else {
						check=!check;
					}
				}
			}

		});
	}
	public boolean isCut(int x1, int y1, int x2, int y2) {
		if(x1==x2) {
			if(XLink(x1,y1,y2)) {
				return true;
			}
		}
		else if(y1==y2) {
			if(YLink(x1,x2,y1)) {
				return true;
			}
		}
		
		if(oneCornerLink(x1,y1,x2,y2)) {
			return true;
		}
		else if(twoCornerLink(x1,y1,x2,y2)) {
			return true;
		}
		return false;
	}
	public boolean YLink(int x1, int x2, int y) {
		int t;
		if(x1>x2) {
			t=x1;
			x1=x2;
			x2=t;
		}
		for(int i=x1+1;i<x2;i++) {
			if(map[y*COL+i]!=BLANK) {
				return false;
			}
		}
		return true;
	}
	public boolean XLink(int x, int y1, int y2) {
		int t;
		if(y1>y2) {
			t=y1;
			y1=y2;
			y2=t;
		}
		
		for(int i=y1+1;i<y2;i++) {
			if(map[i*COL+x]!=BLANK) {
				return false;
			}
		}
		return true;
	}
	public boolean oneCornerLink(int x1, int y1, int x2, int y2) {
		int tx1=x1;
		int ty1=y2;
		int tx2=x2;
		int ty2=y1;
		if(map[COL*ty1+tx1]==BLANK) {
			if(YLink(tx1,x2,y2)&&XLink(x1,ty1,y1)){
				return true;
			}
		}
		if(map[COL*ty2+tx2]==BLANK) {
			if((YLink(x1,tx2,y1))&&(XLink(x2,ty2,y2))) {
				return true;
			}
		}
		return false;
	}
	public boolean twoCornerLink(int x1, int y1, int x2, int y2) {
		int x,y,t;
		if(x1>x2) {
			t=x1;
			x1=x2;
			x2=t;
			
			t=y1;
			y1=y2;
			y2=t;
		}
		
		for(y=y1-1;y>-1;y--) {
			if(map[y*COL+x1]!=BLANK) {
				break;
			}
			if(oneCornerLink(x1,y,x2,y2)) {
				return true;
			}
		}
		
		for(y=y1+1;y<ROW;y++) {
			if(map[y*COL+x1]!=BLANK) {
				break;
			}
			if(oneCornerLink(x1,y,x2,y2)) {
				return true;
			}
		}
		
		for(x=x1-1;x>-1;x--) {
			if(map[y1*COL+x]!=BLANK) {
				break;
			}
			if(oneCornerLink(x,y1,x2,y2)) {
				return true;
			}
		}	
		for(x=x1+1;x<COL;x++) {
			if(map[y1*COL+x]!=BLANK) {
				break;
			}
			if(oneCornerLink(x,y1,x2,y2)) {
				return true;
			}
		}
		
		return false;
	}
	public boolean isSame(int x1, int y1, int x2, int y2) {
		if(map[y1*COL+x1]==map[y2*COL+x2]||map[y1*COL+x1]%3==map[y2*COL+x2]%3) {
			return true;
		}
		return false;
	}
	
	public void initMap() {
		for(int i=0;i<(COL*ROW);i++) {
			map[i]=BLANK;
		}

		ArrayList temp=new ArrayList();
		for(int i=0;i<(COL*ROW)/4;i++) {
			for(int j=0;j<4;j++) {
				temp.add(i);
			}
		}

		Random random=new Random();
		for(int i=0;i<ROW*COL;i++){
			int nIndex=random.nextInt(temp.size());
			map[i]=(Integer)temp.get(nIndex);

			temp.remove(nIndex);
		}

		for(int i=0;i<10*10;i++) {
			g.drawImage(createImage(map[i]), W*(i%GameSize)+W,W*(i/GameSize)+W,W,W);
		} 
	}

	public Image createImage(int n) {
		Image image=new Image("Image/"+n%3+".jpg");
		return image;
	}
	public void myRepaint() {
		for(int i=0;i<10*10;i++) {
			if(map[i]==BLANK) {
				g.clearRect(W*(i%GameSize)+W, W*(i/GameSize)+W, W, W);
			}
			else {
				g.drawImage(createImage(map[i]), W*(i%GameSize)+W, W*(i/GameSize)+W,W,W);
			}
		}
	}
}
