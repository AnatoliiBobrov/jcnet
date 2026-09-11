package jcnet;

import jcnet.backwardfunctions.BackwardFunction;

/**
 * It's basic element in NDArray and contains value in GradTree
 */
public class Variable {
	
	/**
	 * Value of Variable
	 */
    public double value;

	/**
	 * Error of current Variable value
	 */
	public double error;

	private int _generation;
	private GradTree _gradTree;
	protected BackwardFunction _backwardFunction;

	/**
	 * Creates new instance of Variable
	 * <p>
	 * It's basic element in NDArray and contains value in GradTree
	 * </p>
	 *
	 * @param value Value of Variable
	 * @return Instance of Variable
	 */
	public Variable(double value) {
		this.value = value;
	}

	/**
	 * Returns value of Variable
	 * @return Value of Variable
	 */
	public double getValue() {
		return this.value;
	}


	protected void backwardFromTree() {
		_backwardFunction.backward(error);
	}

	/**
	 * Backpropagate loss in GradTree
	 */
    public void backward() {
        _gradTree.backward();
    }

	/**
	 * Returns number of Variable in GradTree
	 * @return Number of Variable
	 */
	public int generation() {
		return _generation;	
	}

	protected void zeroGradFromTree() {
		error = 0;
		value = 0;
		_generation = -1;
		_gradTree = null;
	}

	/**
	 * Reset all gradients in Variable and in GradTree
	 */
	public void zeroGrad() {
		_gradTree.zeroGrad();
	}
	
	protected void addToTree(GradTree tree) {
		if (tree != null) {
			_gradTree = tree;
			tree.add(this);
		}
		else {
			// что-то здесь надо прописать, так не пойдет
		}
	}

	/**
	 * Returns string representation of Variable 
	 * @return String representation like "Variable(value)"
	 */
	@Override
	public String toString() {
		return "Variable(" + Double.toString(value) + ")";
	}
}