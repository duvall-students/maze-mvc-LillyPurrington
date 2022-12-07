package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

import application.Maze;

public class Greedy extends BFS{	

	// Keeps up with the child-parent trail so we can recreate the chosen path
	HashMap<Point,Point> childParent;


	public Greedy(Maze mazeBlocks, Point startPoint, Point goalPoint){
		super(mazeBlocks, startPoint, goalPoint);

		childParent = new HashMap<>();
		// For a greedy searcher, we will use a priority queue
		// based on the number of steps away from the goal.		
		data = new PriorityQueue<Point>(15, (p1, p2) -> distanceToGoal(p1)-distanceToGoal(p2));
		data.add(startPoint);
	}

	protected int distanceToGoal(Point p){
		return goal.x-p.x + goal.y-p.y;
	}

	/*
	 * Of all the neighbors that are not a wall choose the one
	 * with the smallest distance to goal.
	 */
	protected Point chooseNeighbor(Collection<Point> neighbors){
		List<Point> corridors = new ArrayList<>();
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				corridors.add(p);
			}
		}
		return closestToGoal(corridors);
	}

	/*
	 * Of all the neighbors, choose one with the smallest distance to goal.
	 */
	protected Point closestToGoal(Collection<Point> neighbors){
		int smallestDistance = Integer.MAX_VALUE;
		Point next = null;
		for(Point p: neighbors){
			int dist = distanceToGoal(p);
			if(dist < smallestDistance){
				next = p;
				smallestDistance = dist;
			}

		}
		return next;
	}

	/*
	 * When a next step is found, add it to the queue and remember the child-parent relationship
	 */
	protected void recordLink(Point next){	
		data.add(next);
		childParent.put(next,current);
	}

	/*
	 * The current node is the one chosen by the priority queue
	 */
	protected void resetCurrent(){
		PriorityQueue<Point> queue = (PriorityQueue<Point>) data;
		current = queue.peek();
	}

}




