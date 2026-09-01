package jcnet;

import java.util.ArrayList;
import java.util.List;

public class Net extends Module{
    private List<Module> _layers;

	public Net(List<Module> layers) {
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

	
}
/*
 * Net.java (module)
	parameters()
	create(list of modules)
 */