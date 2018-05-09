package com.ram.crypto.orbitary;

import java.math.BigDecimal;

public class Difference {
	private String coinCode;
	private BigDecimal wazirxPrice, koinexPrice, diff, diffPercentage;

	/**
	 * @return the coinCode
	 */
	public final String getCoinCode() {
		return coinCode;
	}

	/**
	 * @param coinCode
	 *            the coinCode to set
	 */
	public final void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	/**
	 * @return the wazirxPrice
	 */
	public final BigDecimal getWazirxPrice() {
		return wazirxPrice;
	}

	/**
	 * @param wazirxPrice
	 *            the wazirxPrice to set
	 */
	public final void setWazirxPrice(BigDecimal wazirxPrice) {
		this.wazirxPrice = wazirxPrice;
	}

	/**
	 * @return the koinexPrice
	 */
	public final BigDecimal getKoinexPrice() {
		return koinexPrice;
	}

	/**
	 * @param koinexPrice
	 *            the koinexPrice to set
	 */
	public final void setKoinexPrice(BigDecimal koinexPrice) {
		this.koinexPrice = koinexPrice;
	}

	/**
	 * @return the diff
	 */
	public final BigDecimal getDiff() {
		return diff;
	}

	/**
	 * @param diff
	 *            the diff to set
	 */
	public final void setDiff(BigDecimal diff) {
		this.diff = diff;
	}

	/**
	 * @return the diffPercentage
	 */
	public final BigDecimal getDiffPercentage() {
		return diffPercentage;
	}

	/**
	 * @param diffPercentage
	 *            the diffPercentage to set
	 */
	public final void setDiffPercentage(BigDecimal diffPercentage) {
		this.diffPercentage = diffPercentage;
	}

	public static Difference getNewInstance() {
		return new Difference();
	}

	@Override
	public String toString() {
		return String.format("%10s", this.getCoinCode()) + " | " + String.format("%10s", this.getWazirxPrice()) + " | "
				+ String.format("%10s", this.getKoinexPrice()) + " | " + String.format("%10s", this.getDiff()) + " | "
				+ String.format("%10s", this.getDiffPercentage());
	}
}
