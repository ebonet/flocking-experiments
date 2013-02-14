package agent;

import graph.FlockingGraph;
import graph.Tour;

/**
 * Evaluates a solution for a TSP problem
 * 
 * @author Balthazar. Created Feb 3, 2013.
 */
public class TSPGoalEvaluator implements GoalEvaluator {

	@Override
	public boolean isGoal(FlockingGraph graph, Tour pathTaken) {
		if (completedTour(graph, pathTaken))
			return true;
		return false;
	}

	public boolean completedTour(FlockingGraph graph, Tour pathTaken) {

		if (pathTaken.size() != graph.getNumberOfNodes() + 1)
			return false;
		if (!pathTaken.firstLocation().equals(pathTaken.lastLocation())) {
			return false;
		}

		return true;
	}
}