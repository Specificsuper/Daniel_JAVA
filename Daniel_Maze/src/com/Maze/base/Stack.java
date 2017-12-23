package com.Maze.base;

import java.util.ArrayList;

public class Stack {
	private ArrayList<Point> stack=new ArrayList<>();

	public ArrayList<Point> getStack() {
		return stack;
	}
	
	public void push(Point point){
		stack.add(point);
	}
	public void pop() {
		if(!stack.isEmpty()) {
			
		}
		else {
			stack.remove(stack.get(stack.size()-1));
		}
	}
}
