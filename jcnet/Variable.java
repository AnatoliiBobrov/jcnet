package jcnet;

import java.util.ArrayList;
import java.util.List;

public class Variable {
    private float _value;
	private float _gradiend;
	private int _generation;
	private GradTree _gradTree;

	public float value() {
		return _value;
	}

    public void backward() {
        return ;
    }

	public int generation() {
		return _generation;	
	}

	protected void zeroGradFromTree() {
		_gradiend = 0;
		_value = 0;
		_generation = -1;
		_gradTree = None;
	}


	public void zeroGrad() {
		_gradTree.zeroGrad();
	}
	
	protected void addToTree(GradTree tree) {
		if (tree != None) {
			_gradTree = tree;
			tree.add(this);
		}
		else {

		}
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