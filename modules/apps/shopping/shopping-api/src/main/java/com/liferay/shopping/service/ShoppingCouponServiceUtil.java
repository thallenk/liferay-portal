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

package com.liferay.shopping.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for ShoppingCoupon. This utility wraps
 * <code>com.liferay.shopping.service.impl.ShoppingCouponServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCouponService
 * @generated
 */
public class ShoppingCouponServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingCouponServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ShoppingCouponServiceUtil} to access the shopping coupon remote service. Add custom service methods to <code>com.liferay.shopping.service.impl.ShoppingCouponServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.shopping.model.ShoppingCoupon addCoupon(
			String code, boolean autoCode, String name, String description,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			boolean neverExpire, boolean active, String limitCategories,
			String limitSkus, double minOrder, double discount,
			String discountType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCoupon(
			code, autoCode, name, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, neverExpire,
			active, limitCategories, limitSkus, minOrder, discount,
			discountType, serviceContext);
	}

	public static void deleteCoupon(long groupId, long couponId)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().deleteCoupon(groupId, couponId);
	}

	public static com.liferay.shopping.model.ShoppingCoupon getCoupon(
			long groupId, long couponId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCoupon(groupId, couponId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.shopping.model.ShoppingCoupon>
			search(
				long groupId, long companyId, String code, boolean active,
				String discountType, boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().search(
			groupId, companyId, code, active, discountType, andOperator, start,
			end);
	}

	public static com.liferay.shopping.model.ShoppingCoupon updateCoupon(
			long couponId, String name, String description, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			boolean neverExpire, boolean active, String limitCategories,
			String limitSkus, double minOrder, double discount,
			String discountType,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCoupon(
			couponId, name, description, startDateMonth, startDateDay,
			startDateYear, startDateHour, startDateMinute, endDateMonth,
			endDateDay, endDateYear, endDateHour, endDateMinute, neverExpire,
			active, limitCategories, limitSkus, minOrder, discount,
			discountType, serviceContext);
	}

	public static ShoppingCouponService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ShoppingCouponService, ShoppingCouponService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ShoppingCouponService.class);

		ServiceTracker<ShoppingCouponService, ShoppingCouponService>
			serviceTracker =
				new ServiceTracker
					<ShoppingCouponService, ShoppingCouponService>(
						bundle.getBundleContext(), ShoppingCouponService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}