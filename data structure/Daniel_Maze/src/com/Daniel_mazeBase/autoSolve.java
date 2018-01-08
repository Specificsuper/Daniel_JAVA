package com.Daniel_mazeBase;

import com.MazeGameUI.Config;
import com.MazeGameUI.Point;
import com.MazeGameUI.Stack;

public class autoSolve {
	public static int startRow=1;
	public static int startCol=0;
	public static int endRow=1;
	public static int endCol=9;
	public static Stack stack=new Stack();
	public static int Weight=10;
	public static int Height=10;	
	public static  int maze[][];
	autoSolve(int maze[][]){
		this.maze=maze;
		dfsMaze0(maze,1,0);
	}
	public static int p=0; 
	public static Stack getStack() {
		return stack;
	}
	public static void dfsMaze0(int[][] maze,int Row,int Col) {
		int nextR;
		int nextC;
		if(Row==1&&Col==9) {
			print();
			return;
		}
		for(int i=0;i<4;i++) {
			nextR=Row+Config.next[i][0];
			nextC=Col+Config.next[i][1];
			maze[Row][Col]=0;
			if((nextR>0&&nextR<Height)&&(nextC>0&&nextC<Weight)&&(maze[nextR][nextC]!=0)) {
				stack.push(new Point(Row,Col,i));
				dfsMaze0(maze,nextR,nextC);
				if(!stack.isEmpty()) {
					stack.remove(stack.size()-1);
				}
			}		
			maze[Row][Col]=1;
		}
	}
	public static void print() {
		if(!stack.isEmpty()) {
		for(int i=0;i<stack.size();i++) {
			System.out.print("("+stack.get(i).getRow()+","+stack.get(i).getCol()+","+stack.get(i).getDirection()+")");
			if(i%5==0) {
				System.out.println();
			}
				i=i+1;
			}
		}
	}
}

