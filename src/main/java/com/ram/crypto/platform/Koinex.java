package com.ram.crypto.platform;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.ram.crypto.coin.Coin;
import com.ram.crypto.ticker.Service;

public class Koinex implements Platform {
	private static final BigDecimal BIG_DECIMAL_0_15 = new BigDecimal(0.15);
	private BigDecimal sellFeePercentage, buyFeePercentage;

	private Map<String, Coin> coins = new HashMap<>();
	private Map<String, BigDecimal> coinWithdrawFee = new HashMap<>();

	public Koinex() {
		this.sellFeePercentage = BIG_DECIMAL_0_15;
		this.buyFeePercentage = BIG_DECIMAL_0_15;
		coinWithdrawFee.put("BTC", new BigDecimal("0.0005"));
		coinWithdrawFee.put("XRP", new BigDecimal("0.02"));
		coinWithdrawFee.put("ETH", new BigDecimal("0.003"));
		coinWithdrawFee.put("TRX", new BigDecimal("60"));
		coinWithdrawFee.put("ZIL", new BigDecimal("40"));
		coinWithdrawFee.put("NCASH", new BigDecimal("60"));
		coinWithdrawFee.put("BAT", new BigDecimal("10"));
		coinWithdrawFee.put("GNT", new BigDecimal("10"));
		coinWithdrawFee.put("ZRX", new BigDecimal("5"));
		coinWithdrawFee.put("EOS", new BigDecimal("0.4"));
		coinWithdrawFee.put("LTC", new BigDecimal("0.01"));
		coinWithdrawFee.put("BCH", new BigDecimal("0.001"));

		JsonObject pricesJson = Service.fetchCoins("https://koinex.in/api/ticker");
		JsonObject inrPrices = pricesJson.getAsJsonObject("prices").getAsJsonObject("inr");
		// System.out.println(inrPrices.get("BTC"));

		addCoin("BTC", inrPrices.get("BTC").getAsBigDecimal(), "Bitcoin");
		addCoin("XRP", inrPrices.get("XRP").getAsBigDecimal(), "Ripple");
		addCoin("ETH", inrPrices.get("ETH").getAsBigDecimal(), "Ethereum");
		addCoin("TRX", inrPrices.get("TRX").getAsBigDecimal(), "Tron");
		addCoin("ZIL", inrPrices.get("ZIL").getAsBigDecimal(), "Zilliqa");
		addCoin("NCASH", inrPrices.get("NCASH").getAsBigDecimal(), "Nucleus Vision");
		addCoin("BAT", inrPrices.get("BAT").getAsBigDecimal(), "Basic Attention Token");
		addCoin("GNT", inrPrices.get("GNT").getAsBigDecimal(), "Golem");
		addCoin("ZRX", inrPrices.get("ZRX").getAsBigDecimal(), "0x Protocol");
		addCoin("EOS", inrPrices.get("EOS").getAsBigDecimal(), "EOS");
		addCoin("LTC", inrPrices.get("LTC").getAsBigDecimal(), "Litecoin");
		addCoin("BCH", inrPrices.get("BCH").getAsBigDecimal(), "Bitcoin Cash");

	}

	private void addCoin(final String aCoinCode, final BigDecimal aPrice, final String aCoinName) {
		coins.put(aCoinCode, new Coin(aCoinCode, aCoinName, aPrice));
	}

	/**
	 * @return the sellFeePercentage
	 */
	public final BigDecimal getSellFeePercentage() {
		return sellFeePercentage;
	}

	/**
	 * @return the buyFeePercentage
	 */
	public final BigDecimal getBuyFeePercentage() {
		return buyFeePercentage;
	}

	/**
	 * @return the coins
	 */
	public final Map<String, Coin> getCoins() {
		return coins;
	}

	/**
	 * @return the coinWithdrawFee
	 */
	public final Map<String, BigDecimal> getCoinWithdrawFee() {
		return coinWithdrawFee;
	}

	@Override
	public String getPlatformName() {
		return this.getClass().getSimpleName();
	}

}
