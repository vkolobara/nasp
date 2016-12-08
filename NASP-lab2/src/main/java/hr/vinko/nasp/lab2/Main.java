package hr.vinko.nasp.lab2;

import java.io.IOException;
import java.util.Arrays;

import hr.vinko.nasp.lab2.algorithm.GeneticAlgorithm;
import hr.vinko.nasp.lab2.fitness.FitnessFunction;
import hr.vinko.nasp.lab2.operator.GreedyCrossover;
import hr.vinko.nasp.lab2.operator.SwapMutation;
import hr.vinko.nasp.lab2.operator.ICrossover;
import hr.vinko.nasp.lab2.operator.IMutation;
import hr.vinko.nasp.lab2.operator.ISelection;
import hr.vinko.nasp.lab2.operator.PMXCrossover;
import hr.vinko.nasp.lab2.operator.PermutationMutation;
import hr.vinko.nasp.lab2.operator.ShuffleMutation;
import hr.vinko.nasp.lab2.operator.TournamentSelection;
import hr.vinko.nasp.lab2.solution.PermutationVector;
import hr.vinko.nasp.lab2.tsp.ProblemDefinition;

public class Main {

	public static void main(String[] args) throws IOException {
		double pC = 0.8;
		double pM = 0.1;
		int k = 3;
		int popSize = 50;
		int maxIter = 100_000;
		
		ProblemDefinition problem = new ProblemDefinition("tsp/pr2392.tsp");
		
		FitnessFunction fitness = new FitnessFunction(x -> {
			double sum = 0;
			for (int i=0; i<problem.size-1; i++) {
				sum += problem.distances[x.solution[i]][x.solution[i+1]];
			}
			
			sum += problem.distances[x.solution[problem.size-1]][x.solution[0]];
			
			return sum;
		});
		ICrossover cross1 = new GreedyCrossover(problem);
		ICrossover cross2 = new PMXCrossover();
		
		IMutation mut1 = new SwapMutation();
		IMutation mut2 = new PermutationMutation();
		IMutation mut3 = new ShuffleMutation();
		
		ISelection selection = new TournamentSelection(k);
		
		
		GeneticAlgorithm ga = new GeneticAlgorithm(Arrays.asList(cross1, cross2), selection, Arrays.asList(mut1, mut2, mut3), popSize, maxIter, pC, pM);
		
		PermutationVector sol = ga.solve(problem.size, fitness);
		System.out.println("SOLUTION");
		System.out.println(sol);
		System.out.println(sol.fitness);
		
	}

}
