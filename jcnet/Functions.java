package jcnet;

public class Functions {

	protected static Variable _scalar(Variable[] input1, Variable[] input2)  throws Exception {
		float sum = 0F;
		for (int i = 0; i < input1.length; i++) {
			sum += input1[i]._value * input2[i]._value;
		}
		var res = new Variable(sum);
		
		return res;
	}
	

}
