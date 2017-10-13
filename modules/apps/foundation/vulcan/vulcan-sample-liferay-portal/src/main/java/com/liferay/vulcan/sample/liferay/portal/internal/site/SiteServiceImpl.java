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

package com.liferay.vulcan.sample.liferay.portal.internal.site;

import com.liferay.portal.kernel.exception.NoSuchGroupException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.vulcan.pagination.PageItems;
import com.liferay.vulcan.pagination.Pagination;
import com.liferay.vulcan.sample.liferay.portal.site.Site;
import com.liferay.vulcan.sample.liferay.portal.site.SiteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.ServerErrorException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Victor Oliveira
 */
@Component(immediate = true)
public class SiteServiceImpl implements SiteService {

	@Override
	public PageItems<Site> getPageItems(Pagination pagination, long companyId) {
		List<Group> groups = _groupLocalService.getGroups(companyId, 0, true);
		int count = _groupLocalService.getGroupsCount(companyId, 0, true);

		List<Group> pageGroups = ListUtil.subList(
			groups, pagination.getStartPosition(), pagination.getEndPosition());

		Stream<Group> stream = pageGroups.stream();

		List<Site> sites = stream.map(
			SiteImpl::new
		).collect(
			Collectors.toList()
		);

		return new PageItems<>(sites, count);
	}

	@Override
	public Optional<Site> getSite(long siteId) {
		try {
			Group group = _groupLocalService.getGroup(siteId);

			return Optional.ofNullable(new SiteImpl(group));
		}
		catch (NoSuchGroupException nsge) {
			return Optional.empty();
		}
		catch (PortalException pe) {
			throw new ServerErrorException(500, pe);
		}
	}

	@Reference
	private GroupLocalService _groupLocalService;

}