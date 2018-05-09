package com.ram.crypto.comparator;

import java.util.Comparator;

import com.ram.crypto.orbitary.Difference;

public class DifferencePercentageComparator implements Comparator<Difference> {

	@Override
	public int compare(Difference diff1, Difference diff2) {
		return diff1.getDiffPercentage().compareTo(diff2.getDiffPercentage());
	}

}
