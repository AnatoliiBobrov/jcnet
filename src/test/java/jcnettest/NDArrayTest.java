package jcnettest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import jcnet.NDArray;
public class NDArrayTest {
	
	@Test
	public void getShapeTest() {
		var rowValues_2 = new double[][]{
			new double[]{2.0, 4.0}, 
			new double[]{3.0, 5.0}};
		var guineaPig_2 = NDArray.NDArrayFrom2D(rowValues_2);
		var actual_2 = guineaPig_2.getShape();
		var expected_2 = new int[]{2,2};
		assertEquals(expected_2, actual_2);
	}
	
	@Test
	public void NDArray_IntArray_Size_Empty_Array_Throws_IAE_test() {
		var emptyArr = new int[0];
		IllegalArgumentException e = assertThrows(
			IllegalArgumentException.class, 
			() -> new NDArray(emptyArr));
		assertTrue(e.getMessage().contains("size must not be empty"));
	}

	@Test
	public void NDArray_IntArray_Size_Array_Has_Zero_test() {
		var hasZeroArr = new int[]{0, 0};
		IllegalArgumentException e = assertThrows(
			IllegalArgumentException.class, 
			() -> new NDArray(hasZeroArr));
		assertTrue(e.getMessage().contains("shape must be greater than 0," +
				" input size: [0, 0]"));
	}

	@Test
	public void NDArray_IntArray_Size_Array_Has_Zero_Throws_IAE_test() {
		var hasZeroArr = new int[]{0, 0};
		IllegalArgumentException e = assertThrows(
			IllegalArgumentException.class, 
			() -> new NDArray(hasZeroArr));
		assertTrue(e.getMessage().contains("shape must be greater than 0," +
				" input size: [0, 0]"));
	}
	
	@Test
	public void NDArrayFrom1D_Regular_Value_test() {
		var rowValues = new double[]{2.0, 4.0};
		var guineaPig = NDArray.NDArrayFrom1D(rowValues);
		var actual = guineaPig.getShape();
		var expected = new int[]{2};
		assertEquals(expected, actual);
	}

	@Test
	public void NDArrayFrom1D_Empty_Value_Throws_IAE_test() {
		var emptyArray = new double[0];
		IllegalArgumentException e = assertThrows(
			IllegalArgumentException.class, 
			() -> NDArray.NDArrayFrom1D(emptyArray));
		assertTrue(e.getMessage().contains("values must not be empty"));
	}

	@Test
	public void NDArrayFrom2D_Empty_First_Dimension_Throws_IAE_test() {
		var emptyFirstD = new double[0][];
		IllegalArgumentException e = assertThrows(
			IllegalArgumentException.class, 
			() -> NDArray.NDArrayFrom2D(emptyFirstD));
		assertTrue(e.getMessage().contains("shape must be greater than 0, " +
			"input size: [0, 0]"));
	}

	@Test
	public void NDArrayFrom2D_Empty_Second_Dimension_Throws_IAE_test() {
		var emptySecondD = new double[][]{new double[0], new double[]{1, 2}};
		IllegalArgumentException e = assertThrows(
			IllegalArgumentException.class, 
			() -> NDArray.NDArrayFrom2D(emptySecondD));
		assertTrue(e.getMessage().contains("Input array shape must be " +
			"greater than 0, input size: [1, 0]"));
	}

	@Test
	public void NDArrayFrom2D_Jagged_Values_IAE_test() {
		var emptySecondD = new double[][]{new double[]{1, 2}, new double[0]};
		IllegalArgumentException e = assertThrows(
			IllegalArgumentException.class, 
			() -> NDArray.NDArrayFrom2D(emptySecondD));
		assertTrue(e.getMessage().contains("Input array must not be jagged"));
	}
	
	@Test
	public void NDArrayFrom2D_Regular_Values_test() {
		var value2d = new double[][]{
			new double[]{1, 2}, 
			new double[]{3, 5}};
		var twoDArray = NDArray.NDArrayFrom2D(value2d);
		
	}
	/*
	

	

	public static NDArray NDArrayFrom2D(double[][] values) 
	throws IllegalArgumentException {
		// надо унифицировать до произвольной размерности
		if (values.length == 0) {
			throw new IllegalArgumentException("shape must be greater " +
				"than 0, input size: [0, 0]");
		} else {
			if (values[0].length == 0) {
				throw new IllegalArgumentException("Input array shape must be greater "+
				"than 0, input size: [" + Integer.toString(values.length) + 
				", 0]");
			}
		}
		var res = new NDArray(values.length, values[0].length);
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
		return new ArrayList<>(Arrays.asList(_values));
	}

	@Override
	public String toString() {
		// Здесь напиши хороший код для строкового представления массива
		return "";
	}

	// Скрой от глаз юзверя, это на новый год
	// кстати, эта штука сильно мусорит, можно ли что-то с этим сделать?
	public NDArray matmul(NDArray other) throws IllegalArgumentException{
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
	*/
}

