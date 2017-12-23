package com.MazeGameUI;
public class Config {
	public static final int SWIDTH=660;
	public static final int SHEIGHT=660;
	public static final int WIDTH=66;
	
	public static final int RIGHT=0;
	public static final int DOWN=1;
	public static final int LEFT=2;
	public static final int UP=3;
	
	public static final int WALL=0;
	public static final int ROAD=1;
	public static final int ENTRANCE=2;
	public static final int EXIT=3;
	
	public static int maze[][]={
			{0,0,0,0,0,0,0,0,0,0},//第0行
			{2,1,1,0,1,1,1,0,1,3},//第1行
			{0,0,1,0,1,1,1,0,1,0},//第2行
			{0,1,1,1,1,0,0,1,1,0},//第3行
			{0,1,0,0,0,1,1,1,1,0},//第4行
			{0,1,0,1,0,1,1,0,1,0},//第5行
			{0,1,0,1,1,1,0,1,1,0},//第6行
			{0,1,0,0,0,1,0,0,1,0},//第7行
			{0,1,1,1,1,1,1,1,1,0},//第8行
			{0,0,0,0,0,0,0,0,0,0}//第9行
	};
}
