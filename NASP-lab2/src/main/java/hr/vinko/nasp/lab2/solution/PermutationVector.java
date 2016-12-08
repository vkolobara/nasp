package hr.vinko.nasp.lab2.solution;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PermutationVector implements Comparable<PermutationVector>{

	private int size;
	public int[] solution;
	public double fitness;

	public PermutationVector(int size) {
		super();
		this.size = size;
		this.solution = new int[size];
	}

	public void randomize() {
		List<Integer> list = IntStream.range(0, size).boxed().collect(Collectors.toList());
		Collections.shuffle(list);
		for (int i = 0; i < size; i++) {
			solution[i] = list.get(i);
		}
	}
	
	public PermutationVector copy() {
		PermutationVector copy = new PermutationVector(size);
		copy.solution = Arrays.copyOf(this.solution, size);
		copy.fitness = this.fitness;
		return copy;
		
	}

	public int getSize() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(solution);
		return result;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(solution);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermutationVector other = (PermutationVector) obj;
		if (!Arrays.equals(solution, other.solution))
			return false;
		return true;
	}

	@Override
	public int compareTo(PermutationVector o) {
		return Double.compare(fitness, o.fitness);
	}
	
	

}
