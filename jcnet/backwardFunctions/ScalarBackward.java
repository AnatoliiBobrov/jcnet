package jcnet.backwardfunctions;

import java.util.Objects;
import jcnet.Variable;
public class ScalarBackward extends BackwardFunction {
	private Variable[] _input1;
	private Variable[] _input2;

	public ScalarBackward (Variable[] input1, Variable[] input2) 
	throws IllegalArgumentException, NullPointerException{
		
		_input1 = Objects.requireNonNull(input1, "input1 must not be null");
		_input2 = Objects.requireNonNull(input2, "input2 must not be null");
		if (input1.length != input2.length) {
			throw new IllegalArgumentException("Length of input1 and input2 " +
			"must be equal");
		}
	}

	@Override
	public void backward(float error) {
		for (int i = 0; i < _input1.length; i++) {
			_input1[i].error += _input2[i].value * error;
			_input2[i].error += _input1[i].value * error;
		}
	}

}
