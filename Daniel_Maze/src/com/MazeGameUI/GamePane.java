package com.MazeGameUI;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GamePane extends Canvas{
	private Image WALL;
	private Image ENTRANCE;
	private Image UP[]=new Image[4];
	private Image DOWN[]=new Image[4];
	private Image LEFT[]=new Image[4];
	private Image RIGHT[]=new Image[4];

	private int direct=0;
	private int row=0;
	private int col=0;
	private int index=0;
	private final int n=Config.maze.length;
	private int exitRow;
	private int exitCol;
	private GraphicsContext gc=this.getGraphicsContext2D();
	public GamePane() {
		this.setWidth(Config.SWIDTH);
		this.setHeight(Config.SHEIGHT);
		
		this.initImage();
		this.initMaze();
		
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent e) {
				switch(e.getCode()) {
				case LEFT:
					direct=Config.LEFT;
					break;
				case RIGHT:
					direct=Config.RIGHT;
					break;
				case UP:
					direct=Config.UP;
					break;
				case DOWN:
					direct=Config.DOWN;
					break;	
				}
				playGame();
			}
		});
	}
	protected void playGame() {
		if(index==3) {
			index=0;
		}
		if(direct==Config.LEFT) {
			if(col-1>0&&Config.maze[row][col-1]!=Config.WALL) {
				gc.drawImage(LEFT[index], (col-1)*Config.WIDTH, row*Config.WIDTH);
				fillBlock();
				index++;
				col--;
			}
		}
		else if(direct==Config.RIGHT) {
			if(col+1<n&&Config.maze[row][col+1]!=Config.WALL) {
				gc.drawImage(RIGHT[index], (col+1)*Config.WIDTH, row*Config.WIDTH);
				fillBlock();
				index++;
				col++;
			}
		}
		else if(direct==Config.UP) {
			if(row-1>0&&Config.maze[row-1][col]!=Config.WALL) {
				gc.drawImage(UP[index], col*Config.WIDTH, (row-1)*Config.WIDTH);
				fillBlock();
				index++;
				row--;
			}
		}
		else if(direct==Config.DOWN) {
			if(row+1<n&&Config.maze[row+1][col]!=Config.WALL) {
				gc.drawImage(DOWN[index], col*Config.WIDTH, (row+1)*Config.WIDTH);
				fillBlock();
				index++;
				row++;
			}
		}
		if(row==exitRow&&col==exitCol) {
			GameOver();
		}
	}
	private void GameOver() {
		System.out.println("ÄãÓ®ÁË£¡");
		System.exit(1);
	}
	private void fillBlock() {
		gc.setFill(Color.WHITE);
		gc.fillRect(col*Config.WIDTH, row*Config.WIDTH,Config.WIDTH,Config.WIDTH);
	}
	private void initImage() {
		WALL=new Image("Image/wall.png"); 
		for(int i=0;i<4;i++) {
			LEFT[i]=new Image("Image/left"+i+".png");
			RIGHT[i]=new Image("Image/right"+i+".png");
			UP[i]=new Image("Image/up"+i+".png");
			DOWN[i]=new Image("Image/down"+i+".png");
		}
	}
	private void initMaze() {
		for(int i=0;i<Config.maze.length;i++) {
			for(int j=0;j<Config.maze[i].length;j++) {
				if(Config.maze[i][j]==Config.ENTRANCE) {
					row=i;
					col=j;
				}
				if(Config.maze[i][j]==Config.EXIT) {
					exitRow=i;
					exitCol=j;
					gc.setFill(Color.RED);
					gc.fillRect(j*Config.WIDTH, i*Config.WIDTH, Config.WIDTH, Config.WIDTH);
					gc.setFill(Color.BLACK);
					gc.fillOval(j*Config.WIDTH+15, i*Config.WIDTH+20, 10, 10);
					gc.fillOval(j*Config.WIDTH+35, i*Config.WIDTH+20, 10, 10);
				}
				if(Config.maze[i][j]==Config.WALL) {
					gc.drawImage(WALL,j*Config.WIDTH , i*Config.WIDTH,Config.WIDTH,Config.WIDTH);
				}
			}
		}
		initEntrance();
	}
	private void initEntrance() {
		if(Config.maze[row][col]==Config.ENTRANCE) {
			if(col-1>0&&Config.maze[row][col-1]==Config.ROAD) {
				direct=Config.LEFT;
				gc.drawImage(LEFT[0], col*Config.WIDTH, row*Config.WIDTH);
			}
			else if(col+1<n&&Config.maze[row][col+1]==Config.ROAD) {
				direct=Config.RIGHT;
				gc.drawImage(RIGHT[0], col*Config.WIDTH, row*Config.WIDTH);
			}
			else if(row+1<n&&Config.maze[row+1][col]==Config.ROAD) {
				direct=Config.DOWN;
				gc.drawImage(DOWN[0], col*Config.WIDTH, row*Config.WIDTH);
			}
			else if(row-1>0&&Config.maze[row-1][col]==Config.ROAD) {
				direct=Config.UP;
				gc.drawImage(UP[0], col*Config.WIDTH, row*Config.WIDTH);
			}
		}
		
	}
}
