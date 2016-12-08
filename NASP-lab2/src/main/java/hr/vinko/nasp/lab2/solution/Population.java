package hr.vinko.nasp.lab2.solution;

import java.util.ArrayList;
import java.util.List;

import hr.vinko.nasp.lab2.fitness.FitnessFunction;

public class Population {

	private List<PermutationVector> population;
	private int popSize;
	
	public Population(int popSize) {
		super();
		this.popSize = popSize;
		population = new ArrayList<>(popSize);
	}
	
	public void randomize (FitnessFunction fitness, int solutionSize) {
		while (!isFull()) {
			PermutationVector sol = new PermutationVector(solutionSize);
			sol.randomize();
			sol.fitness = fitness.getValue(sol);
			population.add(sol);
		}
	}
	
	public PermutationVector get(int index) {
		return population.get(index);
	}
	
	public void setAt(int index, PermutationVector sol) {
		population.set(index, sol);
	}
	
	public void add(PermutationVector sol) {
		if (!isFull()) {
			population.add(sol);
		}
	}
	
	public void remove(PermutationVector x) {
		population.remove(x);
	}
	
	public PermutationVector getBest() {
		return population.stream().max((o1, o2) -> -o1.compareTo(o2)).get();
	}
	
	public PermutationVector getWorst() {
		return population.stream().max((o1, o2) -> o1.compareTo(o2)).get();
	}
	
	public boolean isFull() {
		return popSize == population.size();
	}

	public int getPopSize() {
		return popSize;
	}

	public List<PermutationVector> getPopulation() {
		return population;
	}

	
	
}
