/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.readonlydev.common.version.apiutil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.readonlydev.Exoplanets;

public class Request {

	public static String domain = "https://addons-ecs.forgesvc.net/api/v2/addon/358968/files";

	public static JsonObject get () throws Exception {
		URL               getRequestURL = new URL(domain);
		HttpURLConnection con           = (HttpURLConnection) getRequestURL.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in       = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String         inputLine;
		String         response = "";
		while ((inputLine = in.readLine()) != null) {
			response += inputLine;
			in.close();
			JsonParser parser = new JsonParser();
			JsonArray  array  = parser.parse(response).getAsJsonArray();
			JsonObject json   = array.get(0).getAsJsonObject();
			return json;
		}
		return null;
	}
}
