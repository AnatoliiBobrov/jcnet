package jcnet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Basic calculating element in this module.
 */
public class NDArray {
    protected Variable[] _values;
	private int[] _shape;
	private int[] _capacity;
	private int _length;
	private EverlessIterator _columnIterator;
	private EverlessIterator _rowIterator;

	private void _NDArray(int... size) throws IllegalArgumentException {
		// Нужно установить вехний порог размеров массива
		
	 	_length = 1;
		if (size.length < 1) {
			throw new IllegalArgumentException("size must not be empty");
		}
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

	/**
	 * Returns shape of NDArray
	 * @return Shape of NDArray
	 */
	public int[] getShape() {
		return _shape.clone();
	}

	/**
	 * Returns shape of NDArray
	 * 
	 * @throws IllegalArgumentException <p>If array "size" is null or 
	 * contains values less than 1</p>
	 * @throws OutOfMemoryError <p>If count of element in is too large. </p>
	 * @return <p>Shape of NDArray</p>
	 */
	public NDArray(int... size) 
	throws IllegalArgumentException, OutOfMemoryError {
		_NDArray(size);
		_values = new Variable[_length];
		for (int i = 0; i < _length; i++) {
			_values[i] = new Variable(0F);
		}
	}

	/**
	 * Returns new instance of 1D-NDArray with values in array
	 * @param values values of 1D-NDarray
	 * @throws IllegalArgumentException <p>If values is empty</p> 
	 * @return <p>1D-NDArray</p>
	 */
	public static NDArray NDArrayFrom1D(double [] values) 
	throws IllegalArgumentException {
		// надо унифицировать до произвольной размерности
		if (values.length == 0) {
			throw new IllegalArgumentException("values must not be empty");
		}
		var res = new NDArray(values.length);
		for (int x0 = 0; x0 < values.length; x0++) {
			res._values[x0].value = values[x0];
		}
		return res;
	}

	/**
	 * Returns new instance of 2D-NDArray with values in array
	 * @param values values of 2D-NDarray
	 * @throws IllegalArgumentException <p>If values is empty</p> 
	 * @return <p>2D-NDArray</p>
	 */
	public static NDArray NDArrayFrom2D(double[][] values) 
	throws IllegalArgumentException {
		int[] valuesShape = new int[2];
		// надо унифицировать до произвольной размерности
		if (values.length == 0) {
			throw new IllegalArgumentException("shape must be greater " +
				"than 0, input size: [0, 0]");
		} else {
			valuesShape[0] = values.length;
			valuesShape[1] = values[0].length;
			if (valuesShape[1] == 0) {
				throw new IllegalArgumentException("Input array shape must " +
				"be greater than 0, input size: [" +
				Integer.toString(valuesShape[0]) + ", 0]");
			}
			
			for (int i = 1; i < valuesShape[0]; i ++) {
				if (values[i].length != valuesShape[0]) {
					throw new IllegalArgumentException("Input array must not" +
					" be jagged");
				}
			}
		}
		// проверь, что массив был прямоугольным, а не зубчатым
		var res = new NDArray(valuesShape);
		int pointer = 0;
		for (double[] x0 : values) {
			for (double x1 : x0) {
				res._values[pointer].value = x1;
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

		_values = values.clone();
	}

	
	private int _getFlatAtCoords(int... coords) 
	throws IllegalArgumentException {
		int fCoords = 0;
		if (coords.length != _shape.length) {
			throw new IllegalArgumentException("coords must have equal " +
			"count of coordinates as in Shape of this NDArray");
		}
		for (int i = 0; i < coords.length; i++) {
			if (coords[i] >= _shape[i])	{
				throw new IllegalArgumentException("coords out of range: " +
				Arrays.toString(coords) + " shape:" + Arrays.toString(_shape));
			}
			fCoords += _capacity[i] * coords[i];
		}
		return fCoords;
	}

	/**
	 * Returns item from position "coords"
	 * @param coords position of target value
	 * @throws IllegalArgumentException <p>if "coords" out of bounds</p>
	 * @return <p>1D-NDArray with shape [1]</p>
	 */
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
		// здесь передача значение, а нужно ссылку?
		_values[fCoords] = valArray._values[0];
		
	}

	public List<Variable> toFlatList(){
		return new ArrayList<>(Arrays.asList(_values));
	}

	@Override
	public String toString() {
		// Здесь напиши хороший код для строкового представления массива
		return "";
	}

	/**
	 * Returns mathematical multiply (matmul) of this NDArray with other. If shapes
	 * are different, batch multiply is applied
	 * @param other right multiplier
	 * @throws IllegalArgumentException <p>if both NDArrays have incompatible
	 * chapes</p>
	 * @return <p>result of matmul</p>
	 */
	public NDArray matmul(NDArray other) throws IllegalArgumentException{
		// кстати, эта штука сильно мусорит, можно ли что-то с этим сделать?
		int a1, b1, a2, b2;
		int[] outputShape = new int[Math.max(2, 
			Math.max(_shape.length, other._shape.length))];
		// shape compatibility checking
		b1 = _shape[_shape.length - 1];
		b2 = other._shape[other._shape.length - 1];
		if (_shape.length == 1) {
			a1 = 1;
			if (other._shape.length == 1) {
				a2 = 1;
			} else {
				a2 = other._shape[other._shape.length - 2];
			}
		}
		else {
			a1 = _shape[_shape.length - 2];
			if (other._shape.length == 1) {
				a2 = 1;
				outputShape = _shape.clone();
			} else {
				a2 = other._shape[other._shape.length - 2];
			}
		}
		if (a2 != b1) {
			throw new IllegalArgumentException(
				"Incompatible shape of NDArray");
		}
		outputShape[outputShape.length - 1] = a1;
		outputShape[outputShape.length - 2] = b2;
		int[] largest, shortest;
		if (_shape.length > other._shape.length) {
			largest = _shape;
			shortest = other._shape;
		} else {
			largest = other._shape;
			shortest = _shape;
		}
		
		var count = outputShape[outputShape.length - 1] * 
			outputShape[outputShape.length - 2];
		for (int i = largest.length - 3; i > -1; i--) {
			if (i < shortest.length) {
				if (shortest[i] == 1 || shortest[i] == largest[i]) {
					outputShape[i] = largest[i];
				} else {
					throw new IllegalArgumentException(
						"Incompatible shape of NDArray");
				}
			} else {
				outputShape[i] = largest[i];
			}
		}
		
		if (_rowIterator == null) {
			_rowIterator = new EverlessIterator(_values, a1, b1, false);
		}
		if (other._columnIterator == null) {
			other._columnIterator = new EverlessIterator(other._values, a2, b2,
				true);
		}

		var content = new Variable[count];
		try {
			for (int i = 0; i < count; i++) {
			content[i] = Functions._scalar(_rowIterator.next(), 
				other._columnIterator.next());
		}
		} catch (Exception e) {
		}
		
		var res = new NDArray(content, outputShape);
		return res;
	}
}

