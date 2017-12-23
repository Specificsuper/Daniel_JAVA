package com.Maze.base;

public class Point {
	private int row,col,direction;
	Point(int row,int col,int direction){
		this.row = row;
		this.col = col;
		this.direction = direction;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public int getDirection() {
		return direction;
	}
}
