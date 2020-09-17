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
package net.romvoid95.common.utility.system;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;

@UtilityClass
public class FileUtil {

	private static final File CONFIG_DIRECTORY = Minecraft.getMinecraft().mcDataDir;

	public static void find (String fileName, String entry, String with) {
		File file = new File(CONFIG_DIRECTORY + "/config/" + fileName);
		System.out.println(file.toPath());
		String oldContent = "";

		BufferedReader reader = null;

		FileWriter writer = null;
		try {
			reader = new BufferedReader(new FileReader(file));

			//Reading all the lines of input text file into oldContent

			String line = reader.readLine();

			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();

				line = reader.readLine();
			}

			String newContent = oldContent.replaceAll(entry, with);

			writer = new FileWriter(file);

			writer.write(newContent);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {

				reader.close();

				writer.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
