package com.ram.crypto.platform;

import java.util.Map;

import com.ram.crypto.coin.Coin;

public interface Platform {
	String getPlatformName();

	Map<String, Coin> getCoins();
}
