package jcnet;

import java.util.ArrayList;
import java.util.List;

public class GradTree {
    private List<Variable> _variables;

	protected GradTree() {
		_variables = new ArrayList<>();
	}
	
	protected void add(Variable variable) {
		_variables.add(variable);
	}

	protected void zeroGrad() {
		for (Variable variable : _variables) {
			variable.zeroGradFromTree();
			}
		}
	}

	protected void backward() {
		for (Variable variable : _variables.reversed()) {
			variable.backward();
			}
		}
	}
}
/*
 * Variable.java
	value
	gradient /hidden
	backward()
	generation /hidden
	tree /hidden tree in cloud
 */