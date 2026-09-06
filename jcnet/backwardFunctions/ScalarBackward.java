package jcnet.backwardfunctions;

import jcnet.Variable;
public class ScalarBackward extends BackwardFunction {
	private Variable[] _input1;
	private Variable[] _input2;

	public ScalarBackward (Variable[] input1, Variable[] input2) {
		_input1 = input1;
		_input2 = input2;
		
	}
	
	public void backward(float error) {
		// сюда напиши код
	}

}
