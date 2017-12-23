package com.Maze.base;
public class Maze {
	public static int startRow=1;
	public static int startCol=0;
	public static int endRow=1;
	public static int endCol=9;
	public static Stack stack=new Stack();
	
	public static final int [][] next={
			{0,1},//��
			{1,0},//��
			{0,-1},//��
			{-1,0}//��
	};
	
	public static int maze[][]={
			{0,0,0,0,0,0,0,0,0,0},//��0��
			{2,1,1,0,1,1,1,0,1,3},//��1��
			{0,0,1,0,1,1,1,0,1,0},//��2��
			{0,1,1,1,1,0,0,1,1,0},//��3��
			{0,1,0,0,0,1,1,1,1,0},//��4��
			{0,1,0,1,0,1,1,0,1,0},//��5��
			{0,1,0,1,1,1,0,1,1,0},//��6��
			{0,1,0,0,0,1,0,0,1,0},//��7��
			{0,1,1,1,1,1,1,1,1,0},//��8��
			{0,0,0,0,0,0,0,0,0,0}//��9��
	};
	
	public static void main(String[] args) {	
		solveMaze(1,0,0);
	}
	
	public  static void print() {
		if(!stack.getStack().isEmpty()) {
			for(int i=0;i<stack.getStack().size();i++) {
				if(i%3==0) {
					System.out.println();
				}
				System.out.print("("+stack.getStack().get(i).getCol()+","+stack.getStack().get(i).getRow()+","+stack.getStack().get(i).getDirection()+")");
			}
		}
	}
	
	
	public static void solveMaze(int startRow,int startCol,int direction ) {
		int nextRow,nextCol;
		int temp=0;
		if(direction==4) {
			return;
		}
		 if(startRow == endRow && startCol == endCol) {            
			 return;  
	     }  
		for(int i=0;i<4;i++) {
			nextRow=startRow+next[i][1];
			nextCol=startCol+next[i][0];
			if(nextRow>=0&&nextRow<10&&nextCol>=0&&nextCol<10&&maze[nextRow][nextCol]>=1) {
					maze[startRow][startCol]=1;
					temp=1;
					stack.push(new Point(startRow,startCol,i));
					solveMaze(nextRow,nextCol,i+1);
				}
				else {
					continue;
				}
		}
		if(temp==0) {
			Point point=stack.getStack().get(stack.getStack().size()-1);
			stack.pop();
			solveMaze(point.getRow(),point.getCol(),0);
		}		
		}
	}
