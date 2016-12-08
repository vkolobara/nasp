package hr.vinko.nasp.lab2.operator;

import hr.vinko.nasp.lab2.fitness.FitnessFunction;
import hr.vinko.nasp.lab2.solution.PermutationVector;
import hr.vinko.nasp.lab2.solution.Population;

public class TournamentSelection implements ISelection{
	
	private int k;

	public TournamentSelection(int k) {
		super();
		this.k = k;
	}

	@Override
	public PermutationVector[] select(Population pop, FitnessFunction f) {
		
		Population tournament = new Population(k);
		
		while (!tournament.isFull()) {
			PermutationVector r = pop.get(rand.nextInt(pop.getPopSize()));
//			if (tournament.getPopulation().contains(r)) continue;
			tournament.add(r);
		}
		
		PermutationVector best1 = tournament.getBest().copy();
		tournament.remove(best1);
		PermutationVector best2 = tournament.getBest().copy();
		tournament.remove(best2);
		PermutationVector worst = tournament.getWorst().copy();
		
		return new PermutationVector[]{best1, best2, worst};
	}
	
	

}
