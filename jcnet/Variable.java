package jcnet;
public class Variable{
    private float _value;
	private float _gradiend;
	private int _generation;

	public float value(){
		return _value;
	}

    public void backward(){
        return ;
    }

	public int generation(){
		return _generation;	
	}

	public void zeroGrad(){
		_gradiend = 0;
		_value = 0;
		_generation = -1;
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