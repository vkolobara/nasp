package hr.vinko.nasp.lab2.operator;

import java.util.Random;

import hr.vinko.nasp.lab2.fitness.FitnessFunction;
import hr.vinko.nasp.lab2.solution.PermutationVector;
import hr.vinko.nasp.lab2.solution.Population;

public interface ISelection {
	static Random rand = new Random();
	public PermutationVector[] select(Population pop, FitnessFunction f);
	
}
