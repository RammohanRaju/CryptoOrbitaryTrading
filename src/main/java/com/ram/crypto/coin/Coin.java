package com.ram.crypto.coin;

import java.math.BigDecimal;
import com.ram.crypto.util.Constants;

public class Coin {
	private String code, name;
	private BigDecimal price;

	public Coin(final String aCode, final String aName, final BigDecimal aPrice) {
		this.code = aCode;
		this.name = aName;
		this.price = aPrice;
	}

	/**
	 * @return the code
	 */
	public final String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public final void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the price
	 */
	public final BigDecimal getPrice() {
		return price.setScale(2, Constants.ROUNDING_MODE);
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public final void setPrice(BigDecimal price) {
		this.price = price;
	}
}
