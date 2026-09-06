package jcnet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NDArray {
    protected Variable[] _values;
	private int[] _shape;
	private int[] _capacity;
	private int _length;

	private void _NDArray(int... size) throws IllegalArgumentException {
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


	public NDArray(int... size) throws IllegalArgumentException {
		_NDArray(size);
		_values = new Variable[_length];
		for (int i = 0; i < _length; i++) {
			_values[i] = new Variable(0F);
		}
	}

	public static NDArray NDArrayFrom1D(int[] values) 
	throws IllegalArgumentException {
		// надо унифицировать до произвольной размерности
		var res = new NDArray(values.length);
		for (int x0 = 0; x0 < values.length; x0++) {
			res._values[x0]._value = values[x0];
		}
		return res;
	}

	public static NDArray NDArrayFrom2D(int[][] values) 
	throws IllegalArgumentException {
		// надо унифицировать до произвольной размерности
		if (values.length == 0) {
			throw new IllegalArgumentException("shape must be greater " +
				"than 0, input size: [0, 0]");
		}
		else {
			if (values[0].length == 0) {
				throw new IllegalArgumentException("Input array shape must be greater "+
				"than 0, input size: [" + Integer.toString(values.length) + 
				", 0]");
		}
		var res = new NDArray(values.length, values[0].length);
		int pointer = 0;
		for (int[] x0 : values) {
			for (int x1 : x0) {
				res._values[pointer]._value = x1;
				pointer ++;
			}
		}
		return res;
	}

	private NDArray(Variable[] values, int... size) 
	throws IllegalArgumentException{
		_NDArray(size);
		if (_length != values.length) {
			throw new IllegalArgumentException("shape must be greater " +
			"than 0, input size: " + Arrays.toString(size));
		}

		_values = values;
	}

	private int _getFlatAtCoords(int... coords) 
	throws IllegalArgumentException {
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

	public NDArray get(int... coords) throws IllegalArgumentException {
		var fCoords = _getFlatAtCoords(coords);
		return new NDArray(new Variable[]{_values[fCoords]}, 1);
	}

	public NDArray getSlice(int[] leftUp, int[] rightBottom) 
	throws IllegalArgumentException {
		// я сдался, надо будет дописать код
		if (leftUp.length != _shape.length) {
			throw new IllegalArgumentException("leftUp must have " +
			_shape.length + " dimensions");
		}

		if (rightBottom.length != _shape.length) {
			throw new IllegalArgumentException("rightBottom must have " +
			_shape.length + " dimensions");
		}

		return null;
	}

	public void set(NDArray valArray, int... coords) 
	throws IllegalArgumentException{
		var fCoords = _getFlatAtCoords(coords);
		if (valArray._values.length != 1) {
			throw new IllegalArgumentException(
				"Shape of valArray must be [1]");
		}
		_values[fCoords] = valArray._values[0];
	}

	public List<Variable> toFlatList(){
		return new ArrayList<Variable>(Arrays.asList(_values));
	}

	public String toString() {
		// Здесь напиши хороший код для строкового представления массива
		return "";
	}

	// Скрой от глаз юзверя, это на новый год
	// кстати, эта штука сильно мусорит, можно ли что-то с этим сделать?
	public NDArray _matmul(NDArray other) {
		int a1, b1, b2;
		a1 = this._shape[this._shape.length - 2];
		b1 = this._shape[this._shape.length - 1];
		b2 = other._shape[other._shape.length - 1];
		var res = new Variable[a1 * b2];
		return null;
	}
}

