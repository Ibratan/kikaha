package kikaha.core;

import java.util.*;
import lombok.*;
import lombok.experimental.Accessors;

/**
 *
 */
@Getter
@Accessors(fluent = true)
@NoArgsConstructor
public class TinyList<T> implements List<T> {

	private static final Object[] EMPTY_ELEMENT_DATA = {};

	transient private Object[] data = EMPTY_ELEMENT_DATA;
	transient private int size = 0;

	public TinyList( T initialValue ){
		data = new Object[]{ initialValue };
		size = 1;
	}

	@Override
	public boolean add(T t) {
		data = Arrays.copyOf( data, size+1 );
		data[size++] = t;
		return true;
	}

	@Override
	public T get(int index) {
		return (T)data[index];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new TinyListIterator<>();
	}

	class TinyListIterator<T> implements Iterator<T> {

		private int cursor = 0;

		@Override
		public boolean hasNext() { return cursor < size; }

		@Override
		public T next() {
			return (T)data[cursor++];
		}
	}

	@Override
	public boolean contains(Object o) {
		throw new UnsupportedOperationException("method 'contains' is unavailable");
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException("method 'toArray' is unavailable");
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		throw new UnsupportedOperationException("method 'toArray' is unavailable");
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException("method 'remove' is unavailable");
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("method 'containsAll' is unavailable");
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		throw new UnsupportedOperationException("method 'addAll' is unavailable");
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		throw new UnsupportedOperationException("method 'addAll' is unavailable");
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("method 'removeAll' is unavailable");
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("method 'retainAll' is unavailable");
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("method 'clear' is unavailable");
	}

	@Override
	public T set(int index, T element) {
		throw new UnsupportedOperationException("method 'set' is unavailable");
	}

	@Override
	public void add(int index, T element) {
		throw new UnsupportedOperationException("method 'add' is unavailable");
	}

	@Override
	public T remove(int index) {
		throw new UnsupportedOperationException("method 'remove' is unavailable");
	}

	@Override
	public int indexOf(Object o) {
		throw new UnsupportedOperationException("method 'indexOf' is unavailable");
	}

	@Override
	public int lastIndexOf(Object o) {
		throw new UnsupportedOperationException("method 'lastIndexOf' is unavailable");
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException("method 'listIterator' is unavailable");
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new UnsupportedOperationException("method 'listIterator' is unavailable");
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("method 'subList' is unavailable");
	}
}
