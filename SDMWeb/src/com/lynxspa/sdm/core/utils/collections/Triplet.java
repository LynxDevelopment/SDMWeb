package com.lynxspa.sdm.core.utils.collections;

public class Triplet<X, Y, Z> {
	private X	first;
	private Y	second;
	private Z	third;

	private Triplet(X first, Y second, Z third) {
		super();
		this.first = first;
		this.second = second;
		this.third = third;
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

	public Z getThird() {
		return third;
	}

	public void setThird(Z third) {
		this.third = third;
	}

	public static <X, Y, Z> Triplet<X, Y, Z> makeTriplet(X first, Y second, Z third) {
		return new Triplet<X, Y, Z>(first, second, third);
	}

}
