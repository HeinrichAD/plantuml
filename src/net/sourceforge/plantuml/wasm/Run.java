/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 *
 */
package net.sourceforge.plantuml.wasm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import net.sourceforge.plantuml.core.DiagramDescription;

public class Run {

	public static void main(String[] argsArray) throws IOException {
		final String formatString = argsArray[0];
		final String pathOut = argsArray[1];
		// final String uml = argsArray[2].replaceAll("\\\\n", "\n");
		final String uml = argsArray[2] + "\n";

		final SourceStringReader sr = new SourceStringReader(uml);
		final FileOutputStream fos = new FileOutputStream(new File(pathOut));
		final FileFormatOption format = new FileFormatOption(
				"svg".equalsIgnoreCase(formatString) ? FileFormat.SVG : FileFormat.PNG);
		DiagramDescription description = sr.outputImage(fos, format);
		fos.close();
		if (description.getDescription() != null && description.getDescription().contains("error")) {
			System.exit(1);
		}
		System.exit(0);

	}
}