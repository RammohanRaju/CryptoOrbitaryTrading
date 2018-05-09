package com.ram.crypto.platform;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.ram.crypto.coin.Coin;
import com.ram.crypto.ticker.Service;

public class Wazirx implements Platform {

	private static final BigDecimal BIG_DECIMAL_0_10 = new BigDecimal(0.10);
	private static final BigDecimal BIG_DECIMAL_0_25 = new BigDecimal(0.25);
	private BigDecimal makerFeePercentage, takerFeePercentage;

	private Map<String, BigDecimal> coinWithdrawFee = new HashMap<>();

	private Map<String, Coin> coins = new HashMap<>();

	public Wazirx() {
		this.makerFeePercentage = BIG_DECIMAL_0_10;
		this.takerFeePercentage = BIG_DECIMAL_0_25;

		coinWithdrawFee.put("BTC", new BigDecimal("0.0005"));
		coinWithdrawFee.put("XRP", new BigDecimal("0.02"));
		coinWithdrawFee.put("ETH", new BigDecimal("0.003"));
		coinWithdrawFee.put("TRX", new BigDecimal("60"));
		coinWithdrawFee.put("ZIL", new BigDecimal("50"));
		coinWithdrawFee.put("NCASH", new BigDecimal("60"));
		// coinWithdrawFee.put("BAT", new BigDecimal("10"));// TODO
		coinWithdrawFee.put("GNT", new BigDecimal("10"));
		// coinWithdrawFee.put("ZRX", new BigDecimal("5"));// TODO
		coinWithdrawFee.put("EOS", new BigDecimal("0.39"));
		coinWithdrawFee.put("LTC", new BigDecimal("0.01"));
		coinWithdrawFee.put("BCH", new BigDecimal("0.001"));

		JsonObject pricesJson = Service.fetchCoins("https://api.wazirx.com/api/v2/tickers");
		// System.out.println(pricesJson.getAsJsonObject("btcinr").get("last").getAsBigDecimal());

		addCoin("BAT", pricesJson.getAsJsonObject("batinr").get("last").getAsBigDecimal(), "Basic Attention Token");
		addCoin("BTC", pricesJson.getAsJsonObject("btcinr").get("last").getAsBigDecimal(), "Bitcoin");
		addCoin("EOS", pricesJson.getAsJsonObject("eosinr").get("last").getAsBigDecimal(), "EOS");
		addCoin("ETH", pricesJson.getAsJsonObject("ethinr").get("last").getAsBigDecimal(), "Ethereum");
		addCoin("GNT", pricesJson.getAsJsonObject("gntinr").get("last").getAsBigDecimal(), "Golem");
		addCoin("TRX", pricesJson.getAsJsonObject("trxinr").get("last").getAsBigDecimal(), "Tron");
		addCoin("ZRX", pricesJson.getAsJsonObject("zrxinr").get("last").getAsBigDecimal(), "0x Protocol");
		addCoin("ZIL", pricesJson.getAsJsonObject("zilinr").get("last").getAsBigDecimal(), "Zilliqa");

		addCoin("NCASH", pricesJson.getAsJsonObject("ncashinr").get("last").getAsBigDecimal(), "Nucleus Vision");
		addCoin("BCH", pricesJson.getAsJsonObject("bchinr").get("last").getAsBigDecimal(), "Bitcoin Cash");
		addCoin("LTC", pricesJson.getAsJsonObject("ltcinr").get("last").getAsBigDecimal(), "Litecoin");
		addCoin("XRP", pricesJson.getAsJsonObject("xrpinr").get("last").getAsBigDecimal(), "Ripple");
	}

	private void addCoin(final String aCoinCode, final BigDecimal aPrice, final String aCoinName) {
		coins.put(aCoinCode, new Coin(aCoinCode, aCoinName, aPrice));
	}

	/**
	 * @return the makerFeePercentage
	 */
	public final BigDecimal getMakerFeePercentage() {
		return makerFeePercentage;
	}

	/**
	 * @return the takerFeePercentage
	 */
	public final BigDecimal getTakerFeePercentage() {
		return takerFeePercentage;
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
