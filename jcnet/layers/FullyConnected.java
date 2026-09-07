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
		
		var rand = new java.util.Random();
		double bound = Math.sqrt(6.0 / (_inps + _outs));
		double _bound = -bound;
		double[][] twoD = new double[_inps][_outs];
		for (int x = 0; x < _inps; x++) {
			for (int y = 0; y < _outs; y ++) {
				twoD[x][y] = rand.nextDouble(_bound, bound);
			}
		}
		_params = NDArray.NDArrayFrom2D(twoD);
	}

	@Override
	public NDArray calculate(NDArray input) throws IllegalArgumentException{
		var inpShape = input.getShape();
		int batches = 0;
		if (inpShape.length > 2) {
			throw new IllegalArgumentException("input shape mush be [" + _inps +
			"] or [batch, " + _inps + "]");
		}
		if (inpShape.length == 2) {
			batches = inpShape[0];
			if (inpShape[1] != _inps) {
				throw new IllegalArgumentException("input shape mush be [" + 
				inpShape[0] + ", " + _inps + "]");
			}
		}
		else {
			if (inpShape[0] != _inps) {
				throw new IllegalArgumentException("input shape mush be [" + 
				_inps + "]");
			}
		}

		for (int batch = 0; batch < batches; batch ++) {
			var inpRow = new Variable[_inps];
			
			for (int x = 0; x < _inps; x++) {
				for (int y = 0; y < _outs; y ++) {
					for (int z = 0; z < _outs; z++) {

					}
				}
			}
		}
		return null;
	}

	@Override
	public List<Variable> parameters() {
		return _params.toFlatList();
	}
}
