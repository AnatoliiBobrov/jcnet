package jcnet;

import jcnet.backwardfunctions.BackwardFunction;
public class Variable {
    public double value;
	public double error;
	private int _generation;
	private GradTree _gradTree;
	protected BackwardFunction _backwardFunction;

	public Variable(double value) {
		this.value = value;
	}

	public double getValue() {
		return this.value;
	}


	protected void backwardFromTree() {
		_backwardFunction.backward(error);
	}

    public void backward() {
        _gradTree.backward();
    }

	public int generation() {
		return _generation;	
	}

	protected void zeroGradFromTree() {
		error = 0;
		value = 0;
		_generation = -1;
		_gradTree = null;
	}


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

	public String toString() {
		return Double.toString(value);
	}
}
/*
 * Variable.java
	value
	gradient /hidden
	backward()
	generation /hidden
	tree /hidden tree in cloud
 */