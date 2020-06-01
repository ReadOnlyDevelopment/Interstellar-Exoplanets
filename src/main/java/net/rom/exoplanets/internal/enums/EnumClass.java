/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal.enums;

public enum EnumClass {

	D("D"), H("H"), J("J"), K("K"), L("L"), M("M"), N("N"), R("R"), T("T"), Y("Y");

	private EnumClass planetClass;
	private String planetClassStr;

	private EnumClass(String strClass) {
		this.planetClassStr = strClass;
	}

	private EnumClass(EnumClass pClass) {
		this.planetClass = pClass;
	}

	public void setPlanetStrClass(String strClass) {
		this.planetClassStr = strClass;
	}

	public void setPlanetClass(EnumClass pClass) {
		this.planetClass = pClass;
	}

	public String getPlanetStrClass() {
		return this.planetClassStr;
	}

	public EnumClass getPlanetClass() {
		return this.planetClass;
	}

}
