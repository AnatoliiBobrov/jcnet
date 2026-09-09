package jcnet;

import java.util.List;

public abstract class Module {

	public abstract NDArray calculate(NDArray input)  throws Exception;

	public abstract List<Variable> parameters();

}
