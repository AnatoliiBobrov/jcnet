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
}
/*
 * Variable.java
	value
	gradient /hidden
	backward()
	generation /hidden
	tree /hidden tree in cloud
 */