package hr.vinko.nasp.lab2.fitness;

import hr.vinko.nasp.lab2.solution.PermutationVector;

public class FitnessFunction {
	
	private IUnaryFunction f;

	public FitnessFunction(IUnaryFunction f) {
		super();
		this.f = f;
	}
	
	public double getValue(PermutationVector solution) {
		return f.getValueAt(solution);
	}
	
	
}
