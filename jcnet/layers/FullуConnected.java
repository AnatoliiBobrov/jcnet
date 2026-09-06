package jcnet.layers;

import java.util.List;
import jcnet.NDArray;
import jcnet.Variable;

public class FullуConnected extends jcnet.Module {
	private NDArray _params;

	@Override
	public NDArray calculate(NDArray input)  throws Exception {
		return null;
	}

	@Override
	public List<Variable> parameters() {
		return _params.toFlatList();
	}
}
