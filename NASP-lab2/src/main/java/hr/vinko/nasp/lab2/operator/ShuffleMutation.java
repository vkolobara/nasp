package hr.vinko.nasp.lab2.operator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import hr.vinko.nasp.lab2.solution.PermutationVector;

public class ShuffleMutation implements IMutation{

	@Override
	public PermutationVector mutate(PermutationVector child) {
		List<Integer> mutation = Arrays.stream(child.solution).boxed().collect(Collectors.toList());

		Collections.shuffle(mutation);

		PermutationVector mut = child.copy();
		
		for (int i=0; i<mut.getSize(); i++) {
			mut.solution[i] = mutation.get(i);
		}
		
		return mut;
		
	}

}
