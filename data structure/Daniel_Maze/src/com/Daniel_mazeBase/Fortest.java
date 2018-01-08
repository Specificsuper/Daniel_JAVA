package com.Daniel_mazeBase;

import java.util.Random;
import java.util.Scanner;

import com.MazeGameUI.Config;
import com.MazeGameUI.Point;
import com.MazeGameUI.Stack;

public class Fortest{
	public static int maze_Row;
	public static int maze_Col;
	public static int maze[][];
	public static int Maze[][];
	public static int cmaze[][];
	public static Random random=new Random();
	static int targetRow;
	static int targetCol;
	static Stack stack=new Stack();
	public static Stack copystack=new Stack();
	Scanner in=new Scanner(System.in);
	public Fortest() {
		maze_Row=Config.ROW;
		maze_Col=Config.ROW;
		initMaze();
		dfsMaze(1,1);
		initTrueMaze();
		Config.maze=this.Maze;
		Config.WIDTH=Config.SWIDTH/Config.maze.length;
	}
	private static void initTrueMaze() {
		int tRow=2*maze_Row+1;
		int tCol=2*maze_Col+1;
		int nRow,nCol;
		Maze=new int [tRow][tCol];
		for(int i=0;i<tRow;i++) {
			for(int j=0;j<tCol;j++) {
				Maze[i][j]=0;
			}
		}
		
		for(int i=1;i<=maze_Row;i++) {
			for(int j=1;j<=maze_Col;j++) {
				Maze[2*i-1][2*j-1]=1;
			}
		}
		for(int i=0;i<copystack.size();i++) {
			Point p=copystack.get(i);
			nRow=2*p.getRow()-1+Config.next[p.getDirection()][0];
			nCol=2*p.getCol()-1+Config.next[p.getDirection()][1];
			Maze[nRow][nCol]=1;
		}
		while(true) {//产生随机入口
			int i=Math.abs(new Random().nextInt()%maze_Row);
			if(Maze[i][1]==1&&i!=0) {
				Maze[i][0]=2;
				break;
			}
		}
		while(true) {//产生随机出口
			int i=Math.abs(new Random().nextInt()%maze_Row);
			if(Maze[i][2*maze_Col-1]==1&&i!=0) {
				Maze[i][2*maze_Col]=3;
				break;
			}
		}
	}
	static void initMaze(){
		maze=new int[maze_Row+2][maze_Col+2];
		for(int i=0;i<=maze_Col+1;i++) {
			maze[0][i]=0;
			maze[maze_Row+1][i]=0;
		}
		for(int i=0;i<=maze_Row+1;i++) {
			maze[i][0]=0;
			maze[i][maze_Col+1]=0;
		}
		for(int i=1;i<=maze_Row;i++) {
			for(int j=1;j<=maze_Col;j++) {
				maze[i][j]=1;
			}
		}
		cmaze=maze;
	}

	static Stack thisAccessible(int Row,int Col) {
		Stack a =new Stack();
		int nRow,nCol;
		for(int i=0;i<=3;i++) {
			 nRow=Row+Config.next[i][0];
			 nCol=Col+Config.next[i][1];
			 if(nRow>=0&&nRow<=maze_Row+1&&nCol>=0&&nCol<=maze_Col+1&&cmaze[nRow][nCol]!=0) {
					a.add(new Point(nRow,nCol,i));													
			}
		}
		return a;
	}
	
	public static void dfsMaze(int Row,int Col){
		int p=0;
		while(!(p!=0&&Row==1&&Col==1)) {
			p++;
			Stack a;
			a=thisAccessible(Row,Col);
			cmaze[Row][Col]=0;
			if(!a.isEmpty()) {
				int x;
				while(true) {
					x=(int)Math.abs(random.nextInt()%a.size());
					if(x>=0&&x<a.size()) {
						break;
					}
				}
				a.get(x);//获得任意一个邻接区域
				stack.push(new Point(Row,Col,a.get(x).getDirection()));
				copystack.push(new Point(Row,Col,a.get(x).getDirection()));
				Row=a. get(x).getRow();
				Col=a.get(x).getCol();
			}
			else {
				Point t=stack.get(stack.size()-1);
				stack.remove(stack.size()-1);
				Row=t.getRow();
				Col=t.getCol();
			}
		}
	}
}
