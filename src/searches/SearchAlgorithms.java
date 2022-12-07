package searches;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import application.Maze;

public abstract class SearchAlgorithms {
	
	protected Maze maze;					// The maze being solved
	protected Point goal;					// The goal Point - will let us know when search is successful
	protected Collection<Point> data;		// Data structure used to keep "fringe" points
	protected boolean searchOver = false;	// Is search done?
	protected boolean searchResult = false;	// Was it successful?
	protected Point current;				// Current point being explored
	
	public SearchAlgorithms(Maze mazeBlocks, Point startPoint, Point goalPoint) {
		maze = mazeBlocks;
		goal = goalPoint;
		current = startPoint;
		maze.markPath(current);
	}
	
	public boolean step() {
		if (searchOver) {
			return end();
		}
		// Find possible next steps
		Collection<Point> neighbors = getNeighbors();
		// Choose one to be a part of the path
		Point next = chooseNeighbor(neighbors);
		// mark the next step
		if(next!=null){
			maze.markPath(next);
			recordLink(next);
		}else {
			nextNull();
		}
		resetCurrent();
		checkSearchOver();
		return searchResult;
	}

	protected abstract void recordLink(Point next);

	protected Point chooseNeighbor(Collection<Point> neighbors) {
		for(Point p: neighbors){
			if(maze.get(p)==Maze.EMPTY){
				return p;
			}
		}
		return null;
	}

	/*
	 * Search is over and unsuccessful if there are no more fringe points to consider.
	 * Search is over and successful if the current point is the same as the goal.
	 */
	private void checkSearchOver(){
		if(data!= null && data.isEmpty()) {
			searchOver = true;
			searchResult = false;
		}
		if(isGoal(current)){
			searchOver = true;
			searchResult = true;
		}
	}
	protected abstract void resetCurrent();

	protected boolean isGoal(Point square){
		return square!= null && square.equals(goal);
	}

	protected abstract void nextNull();

	private Collection<Point> getNeighbors() {
		List<Point> maybeNeighbors = new ArrayList<>();
		maybeNeighbors.add(new Point(current.x-1,current.y));
		maybeNeighbors.add(new Point(current.x+1,current.y));
		maybeNeighbors.add(new Point(current.x,current.y+1));
		maybeNeighbors.add(new Point(current.x,current.y-1));
		List<Point> neighbors = new ArrayList<>();
		for(Point p: maybeNeighbors){
			if(maze.inBounds(p)){
				neighbors.add(p);
			}
		}
		return neighbors;	}

	protected boolean end() {
		return searchResult;
	}

}
