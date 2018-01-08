package com.MazeGameUI;
public class Config {
	public static final int SWIDTH=660;
	public static final int SHEIGHT=660;
	public static int WIDTH=44;
	public static int ROW=15;
	
	public static final int RIGHT=0;
	public static final int DOWN=1;
	public static final int LEFT=2;
	public static final int UP=3;
	
	public static final int WALL=0;
	public static final int ROAD=1;
	public static final int ENTRANCE=2;
	public static final int EXIT=3;
	public static final int [][] next={
			{0,1},//ср
			{1,0},//об
			{0,-1},//вС
			{-1,0}//ио
	};
	public static int maze[][];
}
