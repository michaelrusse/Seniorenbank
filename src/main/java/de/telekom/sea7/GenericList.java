package de.telekom.sea7;

import java.util.Iterator;

public interface GenericList<T> extends Iterable<T>{

	void add(T t);

	Iterator<T> iterator();

	int getIndex(T t);

	T getOneObject(int index);

}