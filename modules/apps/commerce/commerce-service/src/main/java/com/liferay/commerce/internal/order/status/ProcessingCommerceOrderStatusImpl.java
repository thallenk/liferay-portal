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

package com.liferay.commerce.internal.order.status;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.status.CommerceOrderStatus;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Alec Sloan
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.order.status.key=" + ProcessingCommerceOrderStatusImpl.KEY,
		"commerce.order.status.priority:Integer=" + ProcessingCommerceOrderStatusImpl.PRIORITY
	},
	service = CommerceOrderStatus.class
)
public class ProcessingCommerceOrderStatusImpl implements CommerceOrderStatus {

	public static final int KEY =
		CommerceOrderConstants.ORDER_STATUS_PROCESSING;

	public static final int PRIORITY = 50;

	@Override
	public CommerceOrder doTransition(CommerceOrder commerceOrder, long userId)
		throws PortalException {

		commerceOrder.setOrderStatus(KEY);

		return _commerceOrderService.updateCommerceOrder(commerceOrder);
	}

	public int getKey() {
		return KEY;
	}

	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			locale, CommerceOrderConstants.getOrderStatusLabel(KEY));
	}

	public int getPriority() {
		return PRIORITY;
	}

	@Override
	public boolean isComplete(CommerceOrder commerceOrder) {
		if (!commerceOrder.isOpen() && commerceOrder.isApproved() &&
			(commerceOrder.getOrderStatus() !=
				CommerceOrderConstants.ORDER_STATUS_PENDING)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isTransitionCriteriaMet(CommerceOrder commerceOrder)
		throws PortalException {

		if ((commerceOrder.getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_PENDING) ||
			(commerceOrder.getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_ON_HOLD)) {

			return true;
		}

		return false;
	}

	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	private volatile CommerceOrderService _commerceOrderService;

}