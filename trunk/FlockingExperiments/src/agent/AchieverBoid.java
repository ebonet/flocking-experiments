package agent;

import graph.Position;
import graph.Tour;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class AchieverBoid extends Boid {

	private Tour pathToFollow;

	public AchieverBoid(Boid boid) {
		super(boid);
		this.pathToFollow = new Tour(this.pathTaken);
		this.pathToFollow.calculateCost(getGraph());
		this.environment.registerPath(this.pathToFollow);

		this.color = Color.BLUE;
		// TODO: achiever's speed should be set to something inversely proportional to the distance flown
	}

	@Override
	public void tryToMove(double distance) {

		// 1) Check for other achiever boids around
		Set<AchieverBoid> boidsInSight = getBoidsInSight();

		// 2) Compare the traveled distance and 3) Update the path
		for (AchieverBoid boid : boidsInSight) {

			// TODO: maybe this should consider only the current walked distance, aka visible current tiredness
			if (boid.getPathDistance() < this.getPathDistance()) {
				this.environment.unregisterPath(this.pathToFollow);

				this.pathToFollow = new Tour(boid.getPathToFollow());

				this.environment.registerPath(this.pathToFollow);
			}

			if (boid.getPathDistance() <= this.getPathDistance()) {
				this.color = boid.color;
			}

		}

		// 4) Move as before

		super.tryToMove(distance);
	}

	@Override
	public Double getPathDistance() {
		return this.pathToFollow.lastCalculatedCost;
	}

	private Set<AchieverBoid> getBoidsInSight() {
		// TODO: include neighboring edge's initial regions in sight range?
		// TODO: maybe they should only see forward, but not backward
		Set<AchieverBoid> boidsInSight = new HashSet<>();

		for (AchieverBoid b : this.environment.getAllAchievers()) {

			if (b.isInSight(this)) {
				boidsInSight.add(b);
			}
		}

		return boidsInSight;
	}

	@Override
	public boolean isInSight(Boid b) {
		return b.pos.isSameEdge(this.pos)
				&& (Math.abs(b.getPos().getDistance() - this.pos.getDistance()) < b.visionRange);
	}

	public Tour getPathToFollow() {
		return this.pathToFollow;
	}

	@Override
	public void decide() {
		if (this.pathTaken.lastLocation() != this.pos.edge.getTo())
			this.pathTaken.offer(this.getPos().edge.getTo());

		if (this.goalEvaluator.isGoal(getGraph(), this.pathTaken)) {
			respawn();
			return;
		}

		int currentNode = this.getPos().edge.getTo();
		int nodeIndex = this.pathToFollow.indexOf(currentNode);
		int nextNode = this.pathToFollow.get(nodeIndex + 1);

		moveToNextEdge(loadEdge(currentNode, nextNode));
	}

	public void respawn() {
		// System.out.println(this.pathToFollow.toString());
		this.pathTaken.clear();
		this.pathTaken.offer(this.pathToFollow.get(0));
		this.setPosition(new Position(loadEdge(this.pathToFollow.get(0), this.pathToFollow.get(1)), 0d));
	}

}
