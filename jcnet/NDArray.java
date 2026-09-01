package jcnet;

public class NDArray {
    private final Variable[] _values;
	private final int[] _shape;

	public NDArray(int... size) {
		int length = 1;
		for (int l : size){
			length *= l;
		}
		_shape = size;

		_values = new Variable[length];
		for (int i = 0; i < length; i++) {
			_values[i] = new Variable(0F);
		}
	}
	public Variable get(int... coords) {
		int fCoords = 0;
		
	}
}

