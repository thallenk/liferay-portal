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

package com.liferay.mobile.device.rules.service.base;

import com.liferay.mobile.device.rules.model.MDRAction;
import com.liferay.mobile.device.rules.service.MDRActionService;
import com.liferay.mobile.device.rules.service.persistence.MDRActionPersistence;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupInstancePersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.SystemEventPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the mdr action remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.mobile.device.rules.service.impl.MDRActionServiceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see com.liferay.mobile.device.rules.service.impl.MDRActionServiceImpl
 * @generated
 */
public abstract class MDRActionServiceBaseImpl
	extends BaseServiceImpl
	implements MDRActionService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MDRActionService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.mobile.device.rules.service.MDRActionServiceUtil</code>.
	 */

	/**
	 * Returns the mdr action local service.
	 *
	 * @return the mdr action local service
	 */
	public com.liferay.mobile.device.rules.service.MDRActionLocalService
		getMDRActionLocalService() {

		return mdrActionLocalService;
	}

	/**
	 * Sets the mdr action local service.
	 *
	 * @param mdrActionLocalService the mdr action local service
	 */
	public void setMDRActionLocalService(
		com.liferay.mobile.device.rules.service.MDRActionLocalService
			mdrActionLocalService) {

		this.mdrActionLocalService = mdrActionLocalService;
	}

	/**
	 * Returns the mdr action remote service.
	 *
	 * @return the mdr action remote service
	 */
	public MDRActionService getMDRActionService() {
		return mdrActionService;
	}

	/**
	 * Sets the mdr action remote service.
	 *
	 * @param mdrActionService the mdr action remote service
	 */
	public void setMDRActionService(MDRActionService mdrActionService) {
		this.mdrActionService = mdrActionService;
	}

	/**
	 * Returns the mdr action persistence.
	 *
	 * @return the mdr action persistence
	 */
	public MDRActionPersistence getMDRActionPersistence() {
		return mdrActionPersistence;
	}

	/**
	 * Sets the mdr action persistence.
	 *
	 * @param mdrActionPersistence the mdr action persistence
	 */
	public void setMDRActionPersistence(
		MDRActionPersistence mdrActionPersistence) {

		this.mdrActionPersistence = mdrActionPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the system event local service.
	 *
	 * @return the system event local service
	 */
	public com.liferay.portal.kernel.service.SystemEventLocalService
		getSystemEventLocalService() {

		return systemEventLocalService;
	}

	/**
	 * Sets the system event local service.
	 *
	 * @param systemEventLocalService the system event local service
	 */
	public void setSystemEventLocalService(
		com.liferay.portal.kernel.service.SystemEventLocalService
			systemEventLocalService) {

		this.systemEventLocalService = systemEventLocalService;
	}

	/**
	 * Returns the system event persistence.
	 *
	 * @return the system event persistence
	 */
	public SystemEventPersistence getSystemEventPersistence() {
		return systemEventPersistence;
	}

	/**
	 * Sets the system event persistence.
	 *
	 * @param systemEventPersistence the system event persistence
	 */
	public void setSystemEventPersistence(
		SystemEventPersistence systemEventPersistence) {

		this.systemEventPersistence = systemEventPersistence;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the mdr rule group instance local service.
	 *
	 * @return the mdr rule group instance local service
	 */
	public
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService
			getMDRRuleGroupInstanceLocalService() {

		return mdrRuleGroupInstanceLocalService;
	}

	/**
	 * Sets the mdr rule group instance local service.
	 *
	 * @param mdrRuleGroupInstanceLocalService the mdr rule group instance local service
	 */
	public void setMDRRuleGroupInstanceLocalService(
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService
			mdrRuleGroupInstanceLocalService) {

		this.mdrRuleGroupInstanceLocalService =
			mdrRuleGroupInstanceLocalService;
	}

	/**
	 * Returns the mdr rule group instance remote service.
	 *
	 * @return the mdr rule group instance remote service
	 */
	public com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceService
		getMDRRuleGroupInstanceService() {

		return mdrRuleGroupInstanceService;
	}

	/**
	 * Sets the mdr rule group instance remote service.
	 *
	 * @param mdrRuleGroupInstanceService the mdr rule group instance remote service
	 */
	public void setMDRRuleGroupInstanceService(
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceService
			mdrRuleGroupInstanceService) {

		this.mdrRuleGroupInstanceService = mdrRuleGroupInstanceService;
	}

	/**
	 * Returns the mdr rule group instance persistence.
	 *
	 * @return the mdr rule group instance persistence
	 */
	public MDRRuleGroupInstancePersistence
		getMDRRuleGroupInstancePersistence() {

		return mdrRuleGroupInstancePersistence;
	}

	/**
	 * Sets the mdr rule group instance persistence.
	 *
	 * @param mdrRuleGroupInstancePersistence the mdr rule group instance persistence
	 */
	public void setMDRRuleGroupInstancePersistence(
		MDRRuleGroupInstancePersistence mdrRuleGroupInstancePersistence) {

		this.mdrRuleGroupInstancePersistence = mdrRuleGroupInstancePersistence;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return MDRActionService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MDRAction.class;
	}

	protected String getModelClassName() {
		return MDRAction.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = mdrActionPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRActionLocalService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRActionLocalService
		mdrActionLocalService;

	@BeanReference(type = MDRActionService.class)
	protected MDRActionService mdrActionService;

	@BeanReference(type = MDRActionPersistence.class)
	protected MDRActionPersistence mdrActionPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.SystemEventLocalService.class
	)
	protected com.liferay.portal.kernel.service.SystemEventLocalService
		systemEventLocalService;

	@ServiceReference(type = SystemEventPersistence.class)
	protected SystemEventPersistence systemEventPersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService.class
	)
	protected
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService
			mdrRuleGroupInstanceLocalService;

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceService.class
	)
	protected
		com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceService
			mdrRuleGroupInstanceService;

	@BeanReference(type = MDRRuleGroupInstancePersistence.class)
	protected MDRRuleGroupInstancePersistence mdrRuleGroupInstancePersistence;

}