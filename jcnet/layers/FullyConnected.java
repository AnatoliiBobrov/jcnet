package jcnet.layers;

import java.util.List;
import jcnet.NDArray;
import jcnet.Variable;

public class FullyConnected extends jcnet.Module {
	private NDArray _params;
	private int _inps;
	private int _outs;
	
	public FullyConnected(int inps, int outs) throws IllegalArgumentException{
		if (inps < 1) {
			throw new IllegalArgumentException("inps must be grater than 0");
		}
		if (outs < 1) {
			throw new IllegalArgumentException("outs must be grater than 0");
		}
		_inps = inps;
		_outs = outs;
		_params = new NDArray(_inps, _outs);
		var rand = new java.util.Random();
		double bound = Math.sqrt(6f);
		//rand.
	}

	@Override
	public NDArray calculate(NDArray input){
		// здесь нужно тело функции
		return null;
	}

	@Override
	public List<Variable> parameters() {
		return _params.toFlatList();
	}
}
