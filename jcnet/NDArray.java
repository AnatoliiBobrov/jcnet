package jcnet;
import java.util.Arrays;

public class NDArray {
    private Variable[] _values;
	private int[] _shape;
	private int[] _capacity;
	private int _length;

	private void _NDArray(int... size) throws IllegalArgumentException{
		// Нужно установить вехний порог размеров массива
		
	 	_length = 1;
		_capacity = new int[size.length];// Нужно проверить, не вводится ли 
										 // пустой массив
		for (int i = size.length - 1; i > -1; i--){
			_capacity[i] = _length;
			if (size[i] < 1) {
				throw new IllegalArgumentException("shape must be greater " +
				"than 0, input size: " + Arrays.toString(size));
			}
			_length *= size[i];
		}
		_shape = size;
	}


	public NDArray(int... size) throws IllegalArgumentException{
		_NDArray(size);
		_values = new Variable[_length];
		for (int i = 0; i < _length; i++) {
			_values[i] = new Variable(0F);
		}
	}

	private NDArray(Variable[] values, int... size) throws IllegalArgumentException{
		_NDArray(size);
		if (_length != values.length) {
			throw new IllegalArgumentException("shape must be greater " +
			"than 0, input size: " + Arrays.toString(size));
		}

		_values = values;
	}

	private int _getFlatAtCoords(int... coords) throws IllegalArgumentException{
		int fCoords = 0;
		if (coords.length != _shape.length) {
			throw new IllegalArgumentException("coords must have equal " +
			"count of coordinates as in Shape of this NDArray");
		}
		for (int i = 0; i < coords.length; i++) {
			int newCoord = 0;
			if (coords[i] >= _shape[i])	{
				throw new IllegalArgumentException("coords out of range: " +
				Arrays.toString(coords) + " shape:" + Arrays.toString(_shape));
			}
			fCoords += _capacity[i] * coords[i];
		}
		return fCoords;
	}

	public NDArray get(int... coords) throws IllegalArgumentException{
		var fCoords = _getFlatAtCoords(coords);
		return new NDArray(new Variable[]{_values[fCoords]}, 1);
	}

	public NDArray set(int... coords) throws IllegalArgumentException{
		// здесь еще не готово
		var fCoords = _getFlatAtCoords(coords);
		return new NDArray(new Variable[]{_values[fCoords]}, 1);
	}
}

