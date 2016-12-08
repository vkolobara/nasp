package hr.vinko.nasp.lab2.operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hr.vinko.nasp.lab2.solution.PermutationVector;
import hr.vinko.nasp.lab2.tsp.ProblemDefinition;

public class GreedyCrossover implements ICrossover {

	private ProblemDefinition problem;

	public GreedyCrossover(ProblemDefinition problem) {
		super();
		this.problem = problem;
	}

	@Override
	public PermutationVector mate(PermutationVector p1, PermutationVector p2) {
		
		PermutationVector child = new PermutationVector(p1.getSize());
		Arrays.fill(child.solution, -1);

		List<Integer> parent1 = Arrays.stream(p1.solution)
	      .boxed()
	      .collect(Collectors.toList());
		List<Integer> parent2 = Arrays.stream(p2.solution)
			      .boxed()
			      .collect(Collectors.toList());
		
		List<Integer> available = new ArrayList<>();
		available.addAll(parent1);
		
		List<Integer> used = new ArrayList<>();
		used.add(p1.solution[0]);
		
		available.remove((Integer) p1.solution[0]);
		
		child.solution[0] = p1.solution[0];
		
		for (int i=1; i<child.getSize(); i++) {
			int index1 = (parent1.indexOf(child.solution[i-1]) + 1) % child.getSize();
			int index2 = (parent2.indexOf(child.solution[i-1]) + 1) % child.getSize();
			
			int best = parent2.get(index2);
			int other = parent1.get(index1);
			
			if (problem.distances[child.solution[i-1]][parent1.get(index1)] < 
			    problem.distances[child.solution[i-1]][parent2.get(index2)]) {
				best = parent1.get(index1);
				
			}
			
			if (used.contains(best)) {
				if (used.contains(other)) {
					child.solution[i] = available.get(rand.nextInt(available.size()));
				} else {
					child.solution[i] = other;
				}
			} else {
				child.solution[i] = best;
			}
			
			used.add((Integer) child.solution[i]);
			available.remove((Integer) child.solution[i]);
			
		}
		
		return child;
	}

}
