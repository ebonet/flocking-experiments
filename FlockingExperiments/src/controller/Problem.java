package controller;

import graph.FlockingGraph;
import graph.Tour;

import java.util.ArrayList;
import java.util.Random;

import agent.Boid;

/**
 * Defines a Flocking-TSP problem.
 * 
 * @author Balthazar. Created Dec 10, 2012.
 */
public class Problem {
	/**
	 * Distances between nodes
	 */
	FlockingGraph distanceGraph;

	/**
	 * ArrayList of worker boids
	 */
	ArrayList<Boid> boids;

	/**
	 * Number of Boids spawned per time unit
	 */
	int multiplierForBoidSpawn = 3;

	/**
	 * Number of cities
	 */
	int numberOfCities = 10;

	/**
	 * Max number of iterations
	 */
	int maxIterations = 1000;

	/**
	 * Weight of visibility (distances)
	 */
	float weightOfDistance = 2;

	/**
	 * Weight of occupancy (crowdedness)
	 */
	float weightOfOccupancy = 1;

	/**
	 * End of algorithm density threshold
	 */
	float occupancyDensityThreshold = 0.70f;

	/**
	 * Constructor that instantiates a new problem based on a distance graph and max number of iterations
	 * 
	 * @param distanceGraph
	 * @param tMax
	 */
	public Problem(FlockingGraph distanceGraph, int tMax) {
		this.distanceGraph = distanceGraph;
		this.numberOfCities = distanceGraph.getNumberOfNodes();
		this.maxIterations = tMax;

		this.boids = new ArrayList<Boid>();
	}

	/**
	 * Attempts to solve the problem given the constant values below
	 * 
	 * @param m
	 * @param B
	 * @param p
	 * @param e
	 * @return The information about the best found Tour (path)
	 */
	public String solve(int m, float e, float B, float a) {
		// Number of boids spawned per time unit
		this.multiplierForBoidSpawn = m;

		// Set constants
		this.occupancyDensityThreshold = e;
		this.weightOfDistance = B;
		this.weightOfOccupancy = a;

		// Random and seed
		Random r = new Random();

		Tour shortestTour = null;

		// Main Loop
		for (int t = 1; t <= this.maxIterations; t++) { // In each iteration

		}

		if (shortestTour != null)
			return shortestTour.toString();
		else
			return "No Tour found";
	}

}
