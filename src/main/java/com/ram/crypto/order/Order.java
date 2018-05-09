package com.ram.crypto.order;

import java.math.BigDecimal;
import com.ram.crypto.coin.Coin;
import com.ram.crypto.platform.Koinex;
import com.ram.crypto.platform.Platform;
import com.ram.crypto.platform.Wazirx;
import com.ram.crypto.util.Constants;

public class Order {

	private Coin coin;
	private BigDecimal sourceAmountOrQuantity;
	private Platform platform;
	private BigDecimal totalBuyCoins;

	public Order(Platform aPlatform, String aCoinCode, BigDecimal aSourceAmountOrQuantity) {
		this.platform = aPlatform;
		this.coin = aPlatform.getCoins().get(aCoinCode);
		this.sourceAmountOrQuantity = aSourceAmountOrQuantity;
	}

	public void buy() {
		BigDecimal buyFeePercentage = null;
		if (platform instanceof Wazirx) {
			buyFeePercentage = ((Wazirx) platform).getTakerFeePercentage();
		} else if (platform instanceof Koinex) {
			buyFeePercentage = ((Koinex) platform).getBuyFeePercentage();
		}
		BigDecimal buyingFee = sourceAmountOrQuantity.multiply(buyFeePercentage).divide(new BigDecimal("100"))
				.setScale(4, Constants.ROUNDING_MODE);
		BigDecimal remainingAmountForOrder = sourceAmountOrQuantity.subtract(buyingFee);

		BigDecimal totalCoins = remainingAmountForOrder.divide(coin.getPrice(), Constants.ROUNDING_MODE);

		System.out.println("Buy order: " + platform.getPlatformName());
		System.out.println("Coin: " + coin.getCode() + " @ " + coin.getPrice());
		System.out.println("Total coins: " + totalCoins);
		System.out.println(
				"Total amount: " + remainingAmountForOrder + " + " + buyingFee + " = " + sourceAmountOrQuantity);
		totalBuyCoins = totalCoins;
	}

	public void sell() {
		BigDecimal sellFeePercentage = null;
		if (platform instanceof Wazirx) {
			sellFeePercentage = ((Wazirx) platform).getMakerFeePercentage();
		} else if (platform instanceof Koinex) {
			sellFeePercentage = ((Koinex) platform).getSellFeePercentage();
		}
		BigDecimal totalSellAmount = sourceAmountOrQuantity.multiply(coin.getPrice()).setScale(4,
				Constants.ROUNDING_MODE);

		BigDecimal sellingFee = totalSellAmount.multiply(sellFeePercentage).divide(new BigDecimal("100")).setScale(4,
				Constants.ROUNDING_MODE);

		BigDecimal effectiveSellAmount = totalSellAmount.subtract(sellingFee);

		System.out.println("Sell order: " + platform.getPlatformName());
		System.out.println("Coin: " + coin.getCode() + " @ " + coin.getPrice());
		System.out.println("Total coins: " + sourceAmountOrQuantity);
		System.out.println("Total amount: " + effectiveSellAmount + " + " + sellingFee + " = " + totalSellAmount);
	}

	/**
	 * @return the totalBuyCoins
	 */
	public final BigDecimal getTotalBuyCoins() {
		return totalBuyCoins;
	}

}
