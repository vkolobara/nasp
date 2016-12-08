package hr.vinko.nasp.lab2.operator;

import hr.vinko.nasp.lab2.solution.PermutationVector;

public class SwapMutation implements IMutation{
	
	@Override
	public PermutationVector mutate(PermutationVector child) {
		
		PermutationVector mutation = child.copy();
		
		int point1 = rand.nextInt(mutation.solution.length);
		int point2 = rand.nextInt(mutation.solution.length);
		
		int swap = mutation.solution[point1];
		mutation.solution[point1] = mutation.solution[point2];
		mutation.solution[point2] = swap;
		
		return mutation;
	}

}
