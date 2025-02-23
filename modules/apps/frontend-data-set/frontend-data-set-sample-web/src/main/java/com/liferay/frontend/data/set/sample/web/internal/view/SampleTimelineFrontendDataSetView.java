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

package com.liferay.frontend.data.set.sample.web.internal.view;

import com.liferay.frontend.data.set.sample.web.internal.constants.FrontendDataSetSampleFrontendDataSetNames;
import com.liferay.frontend.data.set.view.FrontendDataSetView;
import com.liferay.frontend.data.set.view.timeline.BaseTimelineFrontendDataSetView;

import org.osgi.service.component.annotations.Component;

/**
 * @author Javier de Arcos
 */
@Component(
	enabled = false,
	property = "frontend.data.set.name=" + FrontendDataSetSampleFrontendDataSetNames.FRONTEND_DATA_SET_SAMPLES,
	service = FrontendDataSetView.class
)
public class SampleTimelineFrontendDataSetView
	extends BaseTimelineFrontendDataSetView {

	@Override
	public String getDate() {
		return "date";
	}

	@Override
	public String getDescription() {
		return "description";
	}

	@Override
	public String getTitle() {
		return "title";
	}

}