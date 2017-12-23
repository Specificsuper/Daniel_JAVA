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
}
