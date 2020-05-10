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

package net.rom.api;

import java.lang.reflect.Field;

//TODO Finish
final class InterstellarAPIImpl {
    private static final String CORE_API_CLASS = "net.rom.exoplanets.internal.APIInternal";
    private static final String CORE_API_FIELD = "INSTANCE";
    private static InterstellarAPI API_INSTANCE;

    static InterstellarAPI instance() {
        if (API_INSTANCE == null) {
            try {
                final Class<?> apiClass = Class.forName(CORE_API_CLASS);
                final Field apiField = apiClass.getField(CORE_API_FIELD);
                API_INSTANCE = (InterstellarAPI) apiField.get(apiClass);
            } catch (Exception e) {
            	//TODO
			}
        }
        return API_INSTANCE;
    }
}
