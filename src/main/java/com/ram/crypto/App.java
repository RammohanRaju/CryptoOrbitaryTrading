package com.ram.crypto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import com.ram.crypto.coin.Coin;
import com.ram.crypto.comparator.DifferencePercentageComparator;
import com.ram.crypto.orbitary.Difference;
import com.ram.crypto.order.Order;
import com.ram.crypto.platform.Koinex;
import com.ram.crypto.platform.Wazirx;
import com.ram.crypto.util.Constants;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		Wazirx wazirx = new Wazirx();
		Koinex koinex = new Koinex();
		Map<String, Coin> wazirxCoins = wazirx.getCoins();
		Map<String, Coin> koinexCoins = koinex.getCoins();
		System.out.println(String.format("%10s", "Coin") + " | " + String.format("%10s", "Wazirx") + " | "
				+ String.format("%10s", "Koinex") + " | " + String.format("%10s", "Diff W") + " | "
				+ String.format("%10s", "% Diff W"));
		System.out.println("--------------------------------------------------------------");
		List<Difference> differences = new ArrayList<>();
		for (String coinCode : wazirxCoins.keySet()) {
			Coin wazirxCoin = wazirxCoins.get(coinCode);
			Coin koinexCoin = koinexCoins.get(coinCode);
			if (koinexCoin == null) {
				continue;
			}

			BigDecimal diff = wazirxCoin.getPrice().subtract(koinexCoin.getPrice());
			BigDecimal diffPercentage = diff.multiply(new BigDecimal(100)).divide(koinexCoin.getPrice(),
					Constants.ROUNDING_MODE);
			Difference difference = Difference.getNewInstance();
			difference.setCoinCode(coinCode);
			difference.setDiff(diff);
			difference.setDiffPercentage(diffPercentage);
			difference.setWazirxPrice(wazirxCoin.getPrice());
			difference.setKoinexPrice(koinexCoin.getPrice());
			differences.add(difference);
		}
		Collections.sort(differences, new DifferencePercentageComparator());
		differences.forEach(d -> System.out.println(d));

		System.out.println("***************************************************************");
		System.out.println("***************************************************************");
		Difference minDifference = differences.stream().min(Comparator.comparing(Difference::getDiffPercentage)).get();
		Difference maxDifference = differences.stream().max(Comparator.comparing(Difference::getDiffPercentage)).get();

		System.out.println(minDifference);

		System.out.println(maxDifference);
		System.out.println("***************************************************************");

		Order buyOrder = new Order(koinex, maxDifference.getCoinCode(), new BigDecimal("10000"));
		buyOrder.buy();

		System.out.println("---------------");

		Order sellOrder = new Order(wazirx, maxDifference.getCoinCode(), buyOrder.getTotalBuyCoins());
		sellOrder.sell();

		System.out.println("---------------");

		BigDecimal withdrawFeeInCoin = koinex.getCoinWithdrawFee().get(maxDifference.getCoinCode());
		System.out.println("Withdraw fee in coins: " + withdrawFeeInCoin);
		System.out.println("Withdraw fee in amount: " + withdrawFeeInCoin.multiply(maxDifference.getKoinexPrice()));

		// Trade trade = new Trade(wazirx, koinex, differences, new BigDecimal("5000"));
		// TODO

	}
}
