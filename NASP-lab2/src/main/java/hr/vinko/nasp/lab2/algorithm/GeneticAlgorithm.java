package hr.vinko.nasp.lab2.algorithm;

import java.util.List;
import java.util.Random;

import hr.vinko.nasp.lab2.fitness.FitnessFunction;
import hr.vinko.nasp.lab2.operator.ICrossover;
import hr.vinko.nasp.lab2.operator.IMutation;
import hr.vinko.nasp.lab2.operator.ISelection;
import hr.vinko.nasp.lab2.solution.PermutationVector;
import hr.vinko.nasp.lab2.solution.Population;

public class GeneticAlgorithm {

	private List<ICrossover> crossover;
	private ISelection selection;
	private List<IMutation> mutation;
	private int popSize;
	private int maxIter;
	private double pC;
	private double pM;
	
	private Random rand = new Random();
	
	
	
	public GeneticAlgorithm(List<ICrossover> crossover, ISelection selection, List<IMutation> mutation, int popSize, int maxIter, double pC, double pM) {
		super();
		this.crossover = crossover;
		this.selection = selection;
		this.mutation = mutation;
		this.popSize = popSize;
		this.maxIter = maxIter;
		this.pC = pC;
		this.pM = pM;
	}



	public PermutationVector solve(int solutionSize, FitnessFunction fitness) {
		Population pop = new Population(popSize);
		pop.randomize(fitness, solutionSize);
		pop.getPopulation().forEach(x -> x.fitness = fitness.getValue(x));
		
		double best = pop.getBest().fitness;
		
		int numCross = (int) Math.ceil(pC * popSize);
		int numMut = (int) Math.ceil(pM * popSize);
		
		for (int i=0; i<maxIter; i++) {
			for (int j=0; j<numCross; j++) {
				PermutationVector[] selected = selection.select(pop, fitness);
				PermutationVector child = cross(selected[0], selected[1]);
				child = mutate(child);
				child.fitness = fitness.getValue(child);
				pop.remove(selected[2]);
				pop.add(child);
			}
			
			for (int j=0; j<numMut; j++) {
				PermutationVector forMutate = pop.get(rand.nextInt(popSize));
				forMutate = mutate(forMutate);
				forMutate.fitness = fitness.getValue(forMutate);
				
			}
			
			double value = pop.getBest().fitness;
			
			if (value < best) {
				best = value;
				System.out.println("Generation " + (i+1) + ": " + best);
				System.out.println(pop.getBest());
				System.out.println();
			}
			
		}
		
		return pop.getBest();
	}
	
	
	public PermutationVector cross(PermutationVector p1, PermutationVector p2) {
		return crossover.get(rand.nextInt(crossover.size())).mate(p1, p2);
	}

	public PermutationVector mutate(PermutationVector child) {
		return mutation.get(rand.nextInt(mutation.size())).mutate(child);
	}

}
