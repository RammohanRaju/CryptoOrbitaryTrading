package com.ram.crypto.ticker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Service {
	public static Gson gson = new GsonBuilder().create();

	private Service() {

	}

	public static JsonObject fetchCoins(String aUrl) {
		StringBuilder output = new StringBuilder();
		try {
			URL url = new URL(aUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
			}
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			String temp;
			while ((temp = br.readLine()) != null) {
				output = output.append(temp);
			}
			conn.disconnect();

		} catch (Exception e) {
			System.out.println("Exception in NetClientGet:- " + e);
		}
		JsonObject convertedObject = gson.fromJson(output.toString(), JsonObject.class);
		return convertedObject;

	}
}
