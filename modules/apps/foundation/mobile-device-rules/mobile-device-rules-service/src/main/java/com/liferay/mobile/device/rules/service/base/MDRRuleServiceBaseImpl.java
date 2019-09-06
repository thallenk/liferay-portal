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

import com.liferay.mobile.device.rules.model.MDRRule;
import com.liferay.mobile.device.rules.service.MDRRuleService;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupFinder;
import com.liferay.mobile.device.rules.service.persistence.MDRRuleGroupPersistence;
import com.liferay.mobile.device.rules.service.persistence.MDRRulePersistence;
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
 * Provides the base implementation for the mdr rule remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.mobile.device.rules.service.impl.MDRRuleServiceImpl}.
 * </p>
 *
 * @author Edward C. Han
 * @see com.liferay.mobile.device.rules.service.impl.MDRRuleServiceImpl
 * @generated
 */
public abstract class MDRRuleServiceBaseImpl
	extends BaseServiceImpl implements MDRRuleService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>MDRRuleService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.mobile.device.rules.service.MDRRuleServiceUtil</code>.
	 */

	/**
	 * Returns the mdr rule local service.
	 *
	 * @return the mdr rule local service
	 */
	public com.liferay.mobile.device.rules.service.MDRRuleLocalService
		getMDRRuleLocalService() {

		return mdrRuleLocalService;
	}

	/**
	 * Sets the mdr rule local service.
	 *
	 * @param mdrRuleLocalService the mdr rule local service
	 */
	public void setMDRRuleLocalService(
		com.liferay.mobile.device.rules.service.MDRRuleLocalService
			mdrRuleLocalService) {

		this.mdrRuleLocalService = mdrRuleLocalService;
	}

	/**
	 * Returns the mdr rule remote service.
	 *
	 * @return the mdr rule remote service
	 */
	public MDRRuleService getMDRRuleService() {
		return mdrRuleService;
	}

	/**
	 * Sets the mdr rule remote service.
	 *
	 * @param mdrRuleService the mdr rule remote service
	 */
	public void setMDRRuleService(MDRRuleService mdrRuleService) {
		this.mdrRuleService = mdrRuleService;
	}

	/**
	 * Returns the mdr rule persistence.
	 *
	 * @return the mdr rule persistence
	 */
	public MDRRulePersistence getMDRRulePersistence() {
		return mdrRulePersistence;
	}

	/**
	 * Sets the mdr rule persistence.
	 *
	 * @param mdrRulePersistence the mdr rule persistence
	 */
	public void setMDRRulePersistence(MDRRulePersistence mdrRulePersistence) {
		this.mdrRulePersistence = mdrRulePersistence;
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
	 * Returns the mdr rule group local service.
	 *
	 * @return the mdr rule group local service
	 */
	public com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService
		getMDRRuleGroupLocalService() {

		return mdrRuleGroupLocalService;
	}

	/**
	 * Sets the mdr rule group local service.
	 *
	 * @param mdrRuleGroupLocalService the mdr rule group local service
	 */
	public void setMDRRuleGroupLocalService(
		com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService
			mdrRuleGroupLocalService) {

		this.mdrRuleGroupLocalService = mdrRuleGroupLocalService;
	}

	/**
	 * Returns the mdr rule group remote service.
	 *
	 * @return the mdr rule group remote service
	 */
	public com.liferay.mobile.device.rules.service.MDRRuleGroupService
		getMDRRuleGroupService() {

		return mdrRuleGroupService;
	}

	/**
	 * Sets the mdr rule group remote service.
	 *
	 * @param mdrRuleGroupService the mdr rule group remote service
	 */
	public void setMDRRuleGroupService(
		com.liferay.mobile.device.rules.service.MDRRuleGroupService
			mdrRuleGroupService) {

		this.mdrRuleGroupService = mdrRuleGroupService;
	}

	/**
	 * Returns the mdr rule group persistence.
	 *
	 * @return the mdr rule group persistence
	 */
	public MDRRuleGroupPersistence getMDRRuleGroupPersistence() {
		return mdrRuleGroupPersistence;
	}

	/**
	 * Sets the mdr rule group persistence.
	 *
	 * @param mdrRuleGroupPersistence the mdr rule group persistence
	 */
	public void setMDRRuleGroupPersistence(
		MDRRuleGroupPersistence mdrRuleGroupPersistence) {

		this.mdrRuleGroupPersistence = mdrRuleGroupPersistence;
	}

	/**
	 * Returns the mdr rule group finder.
	 *
	 * @return the mdr rule group finder
	 */
	public MDRRuleGroupFinder getMDRRuleGroupFinder() {
		return mdrRuleGroupFinder;
	}

	/**
	 * Sets the mdr rule group finder.
	 *
	 * @param mdrRuleGroupFinder the mdr rule group finder
	 */
	public void setMDRRuleGroupFinder(MDRRuleGroupFinder mdrRuleGroupFinder) {
		this.mdrRuleGroupFinder = mdrRuleGroupFinder;
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
		return MDRRuleService.class.getName();
	}

	protected Class<?> getModelClass() {
		return MDRRule.class;
	}

	protected String getModelClassName() {
		return MDRRule.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = mdrRulePersistence.getDataSource();

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
		type = com.liferay.mobile.device.rules.service.MDRRuleLocalService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRRuleLocalService
		mdrRuleLocalService;

	@BeanReference(type = MDRRuleService.class)
	protected MDRRuleService mdrRuleService;

	@BeanReference(type = MDRRulePersistence.class)
	protected MDRRulePersistence mdrRulePersistence;

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
		type = com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService
		mdrRuleGroupLocalService;

	@BeanReference(
		type = com.liferay.mobile.device.rules.service.MDRRuleGroupService.class
	)
	protected com.liferay.mobile.device.rules.service.MDRRuleGroupService
		mdrRuleGroupService;

	@BeanReference(type = MDRRuleGroupPersistence.class)
	protected MDRRuleGroupPersistence mdrRuleGroupPersistence;

	@BeanReference(type = MDRRuleGroupFinder.class)
	protected MDRRuleGroupFinder mdrRuleGroupFinder;

}