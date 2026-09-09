package test.jcnet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import jcnet.Variable;
public class VariableTest {

	private final Variable variable = new Variable(1.0);

	@Test
	public void isValueInConstructorGood() {
		double val_1 = 4.0;
		assertEquals(val_1, (new Variable(val_1)).getValue, 
			"Value in variable must be " + double.toString(val_1));

		assertEquals((double(-1)), (new Variable(-1)).getValue, 
			"Value in variable must be " + double.toString(double(-1)));

		assertEquals((double(-1f)), (new Variable(-1f)).getValue, 
			"Value in variable must be " + double.toString(double(-1f)));
	}
	/*
	@Test
	public double getValue() {
		return this.value;
	}

	@Test
	protected void backwardFromTree() {
		_backwardFunction.backward(error);
	}
	@Test
    public void backward() {
        _gradTree.backward();
    }
	@Test
	public int generation() {
		return _generation;	
	}
	@Test
	protected void zeroGradFromTree() {
		error = 0;
		value = 0;
		_generation = -1;
		_gradTree = null;
	}

	@Test
	public void zeroGrad() {
		_gradTree.zeroGrad();
	}
	
	@Test
	protected void addToTree(GradTree tree) {
		if (tree != null) {
			_gradTree = tree;
			tree.add(this);
		}
		else {
			// что-то здесь надо прописать, так не пойдет
		}
	}

	@Test
	public String toString() {
		return Double.toString(value);
	}
	*/
}
