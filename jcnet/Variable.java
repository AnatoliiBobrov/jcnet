package jcnet;

import jcnet.backwardfunctions.BackwardFunction;
public class Variable {
    protected float _value;
	protected float _error;
	private int _generation;
	private GradTree _gradTree;
	protected BackwardFunction _backwardFunction;

	public Variable(float value) {
		_value = value;
	}

	public float getValue() {
		return _value;
	}


	protected void backwardFromTree() {
		_backwardFunction.backward(_error);
	}

    public void backward() {
        _gradTree.backward();
    }

	public int generation() {
		return _generation;	
	}

	protected void zeroGradFromTree() {
		_error = 0;
		_value = 0;
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
		return Float.toString(_value);
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