/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.vulcan.test.internal.json;

import static com.liferay.vulcan.test.internal.json.JsonElementType.BOOLEAN;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.util.Objects;

import org.hamcrest.Matcher;

/**
 * @author Alejandro Hernández
 */
public class IsJsonBoolean
	extends AbstractJsonElementMatcher<JsonPrimitive, Boolean> {

	public IsJsonBoolean(Matcher<Boolean> booleanMatcher) {
		super(BOOLEAN, booleanMatcher, JsonElement::getAsBoolean);

		Objects.requireNonNull(booleanMatcher);
	}

}