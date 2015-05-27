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

package com.liferay.portlet.configuration.icon.igoogle;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.configuration.BasePortletConfigurationIcon;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletPreferences;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class IGooglePortletConfigurationIcon
	extends BasePortletConfigurationIcon {

	public IGooglePortletConfigurationIcon(HttpServletRequest request) {
		super(request);

		_portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);
	}

	@Override
	public String getCssClass() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		return portletDisplay.getNamespace() + "expose-as-widget";
	}

	@Override
	public String getIconCssClass() {
		return "icon-plus-sign";
	}

	@Override
	public String getMessage() {
		return "add-to-igoogle";
	}

	@Override
	public String getURL() {
		try {
			return "http://fusion.google.com/add?source=atgs&moduleurl=" +
				PortalUtil.getGoogleGadgetURL(_portlet, themeDisplay);
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public boolean isLabel() {
		return true;
	}

	@Override
	public boolean isShow() {
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		PortletPreferences portletSetup =
			PortletPreferencesFactoryUtil.getStrictLayoutPortletSetup(
				themeDisplay.getLayout(), portletDisplay.getId());

		boolean lfrIgoogleShowAddAppLink = GetterUtil.getBoolean(
			portletSetup.getValue(
				"lfrIgoogleShowAddAppLink", StringPool.BLANK));

		if (lfrIgoogleShowAddAppLink) {
			return true;
		}

		return false;
	}

	private final Portlet _portlet;

}