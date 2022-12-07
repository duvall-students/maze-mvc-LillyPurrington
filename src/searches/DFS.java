package searches;

import java.awt.Point;
import java.util.Stack;

import application.Maze;

public class DFS extends SearchAlgorithms{
	
	public DFS(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);
		// The data structure for DFS is a stack.
		Stack<Point> stack =new Stack<>();
		stack.push(startPoint);
		data = stack;
	}
	
	// When a new node is chosen, push it on the stack
	@Override
	protected void recordLink(Point next){
		Stack<Point> stack = (Stack<Point>)data;
		// FIXME: add try/catch for ClassCastException
		stack.push(next);
	}
	
	/*
	 * Get the next fringe point to consider.
	 * 
	 * This implementation resets the "current" instance variable 
	 * to be the next one on the fringe data structure.
	 */
	@Override
	protected void resetCurrent(){
		Stack<Point> stack = (Stack<Point>)data;
		current = stack.peek();
	}


	@Override
	protected void nextNull() {
		maze.markVisited(current);
		Stack<Point> stack = (Stack<Point>)data;
		stack.pop();		
	}
}
