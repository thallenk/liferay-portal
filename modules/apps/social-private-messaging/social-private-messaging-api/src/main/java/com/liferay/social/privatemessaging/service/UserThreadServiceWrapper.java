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

package com.liferay.social.privatemessaging.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UserThreadService}.
 *
 * @author Brian Wing Shun Chan
 * @see UserThreadService
 * @generated
 */
public class UserThreadServiceWrapper
	implements UserThreadService, ServiceWrapper<UserThreadService> {

	public UserThreadServiceWrapper(UserThreadService userThreadService) {
		_userThreadService = userThreadService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserThreadServiceUtil} to access the user thread remote service. Add custom service methods to <code>com.liferay.social.privatemessaging.service.impl.UserThreadServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public com.liferay.message.boards.kernel.model.MBMessage
			getLastThreadMessage(long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userThreadService.getLastThreadMessage(mbThreadId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _userThreadService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.message.boards.kernel.model.MBMessage>
			getThreadMessages(
				long mbThreadId, int start, int end, boolean ascending)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userThreadService.getThreadMessages(
			mbThreadId, start, end, ascending);
	}

	@Override
	public int getThreadMessagesCount(long mbThreadId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _userThreadService.getThreadMessagesCount(mbThreadId);
	}

	@Override
	public java.util.List<com.liferay.social.privatemessaging.model.UserThread>
			getUserUserThreads(boolean deleted)
		throws com.liferay.portal.kernel.security.auth.PrincipalException {

		return _userThreadService.getUserUserThreads(deleted);
	}

	@Override
	public UserThreadService getWrappedService() {
		return _userThreadService;
	}

	@Override
	public void setWrappedService(UserThreadService userThreadService) {
		_userThreadService = userThreadService;
	}

	private UserThreadService _userThreadService;

}