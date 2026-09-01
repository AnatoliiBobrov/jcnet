package jcnet;

public class Variable {
    private float _value;
	private float _gradiend;
	private int _generation;
	private GradTree _gradTree;

	public Variable(float value) {
		_value = value;
	}

	public float getValue() {
		return _value;
	}


	protected void backwardFromTree() {
		//Сюда напиши код обратного распространения ошибки
	}

    public void backward() {
        _gradTree.backward();
    }

	public int generation() {
		return _generation;	
	}

	protected void zeroGradFromTree() {
		_gradiend = 0;
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