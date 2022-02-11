package de.telekom.sea7.Model;

import java.util.ArrayList;
import java.util.Iterator;

import de.telekom.sea7.GenericList;

public class GenericListImpl<T> implements Iterable<T>, GenericList<T> {

	private ArrayList<T> genericList;

	public GenericListImpl() {
		genericList = new ArrayList<T>();
	}

	@Override
	public void add(T t) {
		genericList.add(t);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return genericList.iterator();
	}

	@Override
	public int getIndex(T t) {
		return this.genericList.indexOf(t);

	}

	@Override
	public T getOneObject(int index) {
		return genericList.get(index);
	}
}
