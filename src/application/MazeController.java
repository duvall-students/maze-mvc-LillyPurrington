package application;

import java.awt.Point;

import searches.BFS;
import searches.DFS;
import searches.Greedy;
import searches.Magic;
import searches.RandomWalk;
import searches.SearchAlgorithms;

public class MazeController {

	
	
	
	/* 
	 * Logic of the program
	 */
	private SearchAlgorithms algorithm;
	private String search = "";		// This string tells which algorithm is currently chosen.  Anything other than 
	// the implemented search class names will result in no search happening.

	// Where to start and stop the search
	private Point start;
	private Point goal;

	// The maze to search
	private Maze maze;
	
	public MazeController(int numRows, int numColumns) {
		start = new Point(1,1);
		goal = new Point(numRows-2, numColumns-2);
		maze = new Maze(numRows, numColumns);
	}
	
	public void newMaze() {
		maze.createMaze(maze.getNumRows(),maze.getNumCols());
		search = "";
	}
	
	public void doOneStep() {
	//	if(search.equals("DFS")) dfs.step();
	//	else if (search.equals("BFS")) bfs.step();
	//	else if (search.equals("Greedy")) greedy.step();
	//	else if (search.equals("RandomWalk")) rand.step();
	//	else if (search.equals("Magic")) magic.step();
		if (algorithm != null) {
			algorithm.step();
		}
	}
	
	
	public void startSearch(String searchType) {
		maze.reColorMaze();
		search = searchType;
		
		if(search.equals("DFS")) algorithm = new DFS(maze, start, goal);
		else if (search.equals("BFS")) algorithm = new BFS(maze, start, goal);
		else if (search.equals("Greedy")) algorithm = new Greedy(maze, start, goal);
		else if (search.equals("RandomWalk")) algorithm = new RandomWalk(maze, start, goal);
		else if (search.equals("Magic")) algorithm = new Magic(maze, start, goal);
	}

	public int getCellState(Point position) {
		return maze.get(position);
	}
	
	

}
