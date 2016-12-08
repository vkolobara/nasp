package hr.vinko.nasp.lab2.operator;

import java.util.Random;

import hr.vinko.nasp.lab2.solution.PermutationVector;

public interface ICrossover {
	
	static Random rand = new Random();
		
	public PermutationVector mate(PermutationVector p1, PermutationVector p2);
	
	
}
