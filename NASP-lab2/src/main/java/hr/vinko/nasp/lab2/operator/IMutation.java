package hr.vinko.nasp.lab2.operator;

import java.util.Random;

import hr.vinko.nasp.lab2.solution.PermutationVector;

public interface IMutation {
	static Random rand = new Random();
	
	public PermutationVector mutate(PermutationVector child);

}
