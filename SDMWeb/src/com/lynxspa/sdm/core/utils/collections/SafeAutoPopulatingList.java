package com.lynxspa.sdm.core.utils.collections;

import java.util.List;

import org.springframework.util.AutoPopulatingList;

public class SafeAutoPopulatingList<T> extends AutoPopulatingList<T> {
	private static final long	serialVersionUID	= -7616455080096795821L;

	public SafeAutoPopulatingList(List<T> backingList, Class<? extends T> elementClass) {
		super(backingList, elementClass);
	}

	@Override
	public T set(int index, T element) {
		while (this.size() <= index) {
			this.add(null);
		}
		return super.set(index, element);
	}
}
