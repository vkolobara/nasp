package hr.vinko.nasp.lab2.operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.vinko.nasp.lab2.solution.PermutationVector;

public class PermutationMutation implements IMutation {

	@Override
	public PermutationVector mutate(PermutationVector child) {

		List<Integer> mutation = new ArrayList<>();
		for (int i = 0; i < child.solution.length; i++) {
			mutation.add(child.solution[i]);
		}

		int index1 = rand.nextInt(child.solution.length);
		int index2 = rand.nextInt(child.solution.length);

		if (index1 == index2) {
			index2 = child.solution.length;
		} else if (index1 > index2) {
			int swap = index1;
			index1 = index2;
			index2 = swap;
		}

		List<Integer> shuffledSubList = mutation.subList(index1, index2);
		Collections.shuffle(shuffledSubList);

		PermutationVector mut = child.copy();

		for (int i = index1; i < index2; i++) {
			mut.solution[i] = shuffledSubList.get(i - index1);
		}

		return mut;
	}

}
