package hr.vinko.nasp.lab2.operator;

import java.util.Arrays;

import hr.vinko.nasp.lab2.solution.PermutationVector;

public class PMXCrossover implements ICrossover{
	
	
	@Override
	public PermutationVector mate(PermutationVector p1, PermutationVector p2) {
		
		int size = p1.getSize();
		PermutationVector child = p1.copy();
		
		int point1 = rand.nextInt(size);
		int point2 = rand.nextInt(size-1);

		if (point1 == point2) {
			point2 = size-1;
		} else if (point2 < point1) {
			int swap = point2;
			point2 = point1;
			point1 = swap;
		}
		
		int[] replacement = new int[size];
		
		Arrays.fill(replacement, -1);
		
		for (int i=point1; i<=point2; i++) {
			child.solution[i] = p1.solution[i];
			replacement[p1.solution[i]] = p2.solution[i];
		}
		
		for (int i=0; i<size; i++) {
			
			if ((i < point1) || (i > point2)) {
				int n = p2.solution[i];
				int m = replacement[n];
				while (m != -1) {
					n = m;
					m = replacement[m];
				}
				child.solution[i] = n;
			}
		}
		
		return child;
	}

}
