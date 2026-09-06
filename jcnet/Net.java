package jcnet;

import java.util.ArrayList;
import java.util.List;

public class Net extends Module{
    private List<Module> _layers;
	
	/**
	 * Create Net from list of layers (by reference)
	 */
	public Net(List<Module> layers) {
		// Такое не безопасно, так как пользователь может в процессе изменить 
		// лист слоев
		_layers = layers;
	}

	@Override
	public List<Variable> parameters() {
		List<Variable> _params = new ArrayList<Variable>();
		for (Module layer : _layers) {
			_params.addAll(layer.parameters());
		}
		return _params;
	}

	@Override
	public NDArray calculate(NDArray input) throws Exception {
		var _layersIter = _layers.iterator();
		if (!_layersIter.hasNext()) {
			throw new Exception("List of layers is empty");
		}
		var x0 = input;
		while (_layersIter.hasNext()) {
			x0 = _layersIter.next().calculate(x0);
		}
		return x0;
	}
}
/*
 * Net.java (module)
	parameters()
	create(list of modules)
 */