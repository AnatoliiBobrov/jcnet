package jcnet;
import jcnet.backwardfunctions.ScalarBackward;
public class Functions {

	protected static Variable _scalar(Variable[] input1, Variable[] input2)  throws Exception {
		float sum = 0F;
		for (int i = 0; i < input1.length; i++) {
			sum += input1[i].value * input2[i].value;
		}
		var res = new Variable(sum);
		res._backwardFunction = new ScalarBackward(input1, input2);
		return res;
	}
	

}
