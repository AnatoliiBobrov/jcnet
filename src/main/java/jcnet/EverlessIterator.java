package jcnet;

import java.util.Iterator;

public class EverlessIterator implements Iterator<Variable[]>{
    private int _pointer;
	private final int _last;
	private final Variable[][] _values; 

	// column = true if iteration of columns, else rows
	public EverlessIterator(Variable[] values, int rows, int colunms, boolean column) {
		var length = values.length / (column ? rows : 1);
		_values = new Variable[length][column ? rows : colunms];
		_last = length - 1;
		var _valuesPointer = 0;
		var pointer = 0;
		
		if (column) {
			int pointer2, pointer3;
			var size_2 = rows * colunms;
			for (int p = 0; p < length; p += colunms) {
				pointer2 = pointer;
				for (int x = 0; x < colunms; x++) {
					var item = new Variable[rows];
					pointer3 = pointer2;
					for (int y = 0; y < rows; y++) {
						item[y] = values[pointer3];
						pointer3 += colunms;
					}
					_values[_valuesPointer] = item;
					_valuesPointer ++;
					pointer2 ++;
				}
				pointer += size_2;
			}
		}
		else {
			for (int p = 0; p < length; p += colunms) {
				var item = new Variable[rows];
				for (int x = 0; x < colunms; x++) {
					item[x] = values[p + x];
				}
				_values[_valuesPointer] = item;
				_valuesPointer ++;
			}
		}
	}

	@Override
	public Variable[] next() {

		if (_pointer == _last) {
			_pointer = 0;
		}
		var res = _values[0];
		_pointer ++;
		return res;
	}

	@Override
	public boolean hasNext() {
		return true;
	}
}