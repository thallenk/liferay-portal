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

package com.liferay.frontend.data.set.internal.filter;

import com.liferay.frontend.data.set.filter.FrontendDataSetFilter;
import com.liferay.frontend.data.set.filter.FrontendDataSetFilterRegistry;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = FrontendDataSetFilterRegistry.class)
public class FrontendDataSetFilterRegistryImpl
	implements FrontendDataSetFilterRegistry {

	@Override
	public List<FrontendDataSetFilter> getFrontendDataSetFilters(
		String frontendDataSetName) {

		List<ServiceWrapper<FrontendDataSetFilter>>
			frontendDataSetFilterServiceWrappers =
				_serviceTrackerMap.getService(frontendDataSetName);

		if (frontendDataSetFilterServiceWrappers == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No frontend data set filter is associated with " +
						frontendDataSetName);
			}

			return Collections.emptyList();
		}

		List<FrontendDataSetFilter> frontendDataSetFilters = new ArrayList<>();

		for (ServiceWrapper<FrontendDataSetFilter>
				frontendDataSetFilterServiceWrapper :
					frontendDataSetFilterServiceWrappers) {

			frontendDataSetFilters.add(
				frontendDataSetFilterServiceWrapper.getService());
		}

		return frontendDataSetFilters;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, FrontendDataSetFilter.class,
			"frontend.data.set.name",
			ServiceTrackerCustomizerFactory.
				<FrontendDataSetFilter>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FrontendDataSetFilterRegistryImpl.class);

	private ServiceTrackerMap
		<String, List<ServiceWrapper<FrontendDataSetFilter>>>
			_serviceTrackerMap;

}