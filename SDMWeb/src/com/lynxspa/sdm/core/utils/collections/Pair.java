package com.lynxspa.sdm.core.utils.collections;

public class Pair<X, Y> {
	private X	first;
	private Y	second;

	private Pair(X first, Y second) {
		this.first = first;
		this.second = second;
	}

	public Pair() {
	}

	public X getFirst() {
		return first;
	}

	public void setFirst(X first) {
		this.first = first;
	}

	public Y getSecond() {
		return second;
	}

	public void setSecond(Y second) {
		this.second = second;
	}
	
	public static <X, Y> Pair<X, Y> makePair(X first, Y second) {
		return new Pair<X, Y>(first, second);
	}

}
