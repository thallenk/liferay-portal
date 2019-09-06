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

package com.liferay.sync.service.base;

import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.SyncDeviceLocalService;
import com.liferay.sync.service.persistence.SyncDLFileVersionDiffPersistence;
import com.liferay.sync.service.persistence.SyncDLObjectFinder;
import com.liferay.sync.service.persistence.SyncDLObjectPersistence;
import com.liferay.sync.service.persistence.SyncDevicePersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the sync device local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.sync.service.impl.SyncDeviceLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sync.service.impl.SyncDeviceLocalServiceImpl
 * @generated
 */
public abstract class SyncDeviceLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements SyncDeviceLocalService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>SyncDeviceLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.sync.service.SyncDeviceLocalServiceUtil</code>.
	 */

	/**
	 * Adds the sync device to the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncDevice the sync device
	 * @return the sync device that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SyncDevice addSyncDevice(SyncDevice syncDevice) {
		syncDevice.setNew(true);

		return syncDevicePersistence.update(syncDevice);
	}

	/**
	 * Creates a new sync device with the primary key. Does not add the sync device to the database.
	 *
	 * @param syncDeviceId the primary key for the new sync device
	 * @return the new sync device
	 */
	@Override
	@Transactional(enabled = false)
	public SyncDevice createSyncDevice(long syncDeviceId) {
		return syncDevicePersistence.create(syncDeviceId);
	}

	/**
	 * Deletes the sync device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncDeviceId the primary key of the sync device
	 * @return the sync device that was removed
	 * @throws PortalException if a sync device with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SyncDevice deleteSyncDevice(long syncDeviceId)
		throws PortalException {

		return syncDevicePersistence.remove(syncDeviceId);
	}

	/**
	 * Deletes the sync device from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncDevice the sync device
	 * @return the sync device that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public SyncDevice deleteSyncDevice(SyncDevice syncDevice) {
		return syncDevicePersistence.remove(syncDevice);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			SyncDevice.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return syncDevicePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDeviceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return syncDevicePersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDeviceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return syncDevicePersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return syncDevicePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return syncDevicePersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public SyncDevice fetchSyncDevice(long syncDeviceId) {
		return syncDevicePersistence.fetchByPrimaryKey(syncDeviceId);
	}

	/**
	 * Returns the sync device with the matching UUID and company.
	 *
	 * @param uuid the sync device's UUID
	 * @param companyId the primary key of the company
	 * @return the matching sync device, or <code>null</code> if a matching sync device could not be found
	 */
	@Override
	public SyncDevice fetchSyncDeviceByUuidAndCompanyId(
		String uuid, long companyId) {

		return syncDevicePersistence.fetchByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns the sync device with the primary key.
	 *
	 * @param syncDeviceId the primary key of the sync device
	 * @return the sync device
	 * @throws PortalException if a sync device with the primary key could not be found
	 */
	@Override
	public SyncDevice getSyncDevice(long syncDeviceId) throws PortalException {
		return syncDevicePersistence.findByPrimaryKey(syncDeviceId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(syncDeviceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SyncDevice.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("syncDeviceId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			syncDeviceLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(SyncDevice.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"syncDeviceId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(syncDeviceLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(SyncDevice.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("syncDeviceId");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<SyncDevice>() {

				@Override
				public void performAction(SyncDevice syncDevice)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, syncDevice);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(SyncDevice.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return syncDeviceLocalService.deleteSyncDevice(
			(SyncDevice)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return syncDevicePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns the sync device with the matching UUID and company.
	 *
	 * @param uuid the sync device's UUID
	 * @param companyId the primary key of the company
	 * @return the matching sync device
	 * @throws PortalException if a matching sync device could not be found
	 */
	@Override
	public SyncDevice getSyncDeviceByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException {

		return syncDevicePersistence.findByUuid_C_First(uuid, companyId, null);
	}

	/**
	 * Returns a range of all the sync devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.sync.model.impl.SyncDeviceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @return the range of sync devices
	 */
	@Override
	public List<SyncDevice> getSyncDevices(int start, int end) {
		return syncDevicePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of sync devices.
	 *
	 * @return the number of sync devices
	 */
	@Override
	public int getSyncDevicesCount() {
		return syncDevicePersistence.countAll();
	}

	/**
	 * Updates the sync device in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param syncDevice the sync device
	 * @return the sync device that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public SyncDevice updateSyncDevice(SyncDevice syncDevice) {
		return syncDevicePersistence.update(syncDevice);
	}

	/**
	 * Returns the sync device local service.
	 *
	 * @return the sync device local service
	 */
	public SyncDeviceLocalService getSyncDeviceLocalService() {
		return syncDeviceLocalService;
	}

	/**
	 * Sets the sync device local service.
	 *
	 * @param syncDeviceLocalService the sync device local service
	 */
	public void setSyncDeviceLocalService(
		SyncDeviceLocalService syncDeviceLocalService) {

		this.syncDeviceLocalService = syncDeviceLocalService;
	}

	/**
	 * Returns the sync device persistence.
	 *
	 * @return the sync device persistence
	 */
	public SyncDevicePersistence getSyncDevicePersistence() {
		return syncDevicePersistence;
	}

	/**
	 * Sets the sync device persistence.
	 *
	 * @param syncDevicePersistence the sync device persistence
	 */
	public void setSyncDevicePersistence(
		SyncDevicePersistence syncDevicePersistence) {

		this.syncDevicePersistence = syncDevicePersistence;
	}

	/**
	 * Returns the sync dl file version diff local service.
	 *
	 * @return the sync dl file version diff local service
	 */
	public com.liferay.sync.service.SyncDLFileVersionDiffLocalService
		getSyncDLFileVersionDiffLocalService() {

		return syncDLFileVersionDiffLocalService;
	}

	/**
	 * Sets the sync dl file version diff local service.
	 *
	 * @param syncDLFileVersionDiffLocalService the sync dl file version diff local service
	 */
	public void setSyncDLFileVersionDiffLocalService(
		com.liferay.sync.service.SyncDLFileVersionDiffLocalService
			syncDLFileVersionDiffLocalService) {

		this.syncDLFileVersionDiffLocalService =
			syncDLFileVersionDiffLocalService;
	}

	/**
	 * Returns the sync dl file version diff persistence.
	 *
	 * @return the sync dl file version diff persistence
	 */
	public SyncDLFileVersionDiffPersistence
		getSyncDLFileVersionDiffPersistence() {

		return syncDLFileVersionDiffPersistence;
	}

	/**
	 * Sets the sync dl file version diff persistence.
	 *
	 * @param syncDLFileVersionDiffPersistence the sync dl file version diff persistence
	 */
	public void setSyncDLFileVersionDiffPersistence(
		SyncDLFileVersionDiffPersistence syncDLFileVersionDiffPersistence) {

		this.syncDLFileVersionDiffPersistence =
			syncDLFileVersionDiffPersistence;
	}

	/**
	 * Returns the sync dl object local service.
	 *
	 * @return the sync dl object local service
	 */
	public com.liferay.sync.service.SyncDLObjectLocalService
		getSyncDLObjectLocalService() {

		return syncDLObjectLocalService;
	}

	/**
	 * Sets the sync dl object local service.
	 *
	 * @param syncDLObjectLocalService the sync dl object local service
	 */
	public void setSyncDLObjectLocalService(
		com.liferay.sync.service.SyncDLObjectLocalService
			syncDLObjectLocalService) {

		this.syncDLObjectLocalService = syncDLObjectLocalService;
	}

	/**
	 * Returns the sync dl object persistence.
	 *
	 * @return the sync dl object persistence
	 */
	public SyncDLObjectPersistence getSyncDLObjectPersistence() {
		return syncDLObjectPersistence;
	}

	/**
	 * Sets the sync dl object persistence.
	 *
	 * @param syncDLObjectPersistence the sync dl object persistence
	 */
	public void setSyncDLObjectPersistence(
		SyncDLObjectPersistence syncDLObjectPersistence) {

		this.syncDLObjectPersistence = syncDLObjectPersistence;
	}

	/**
	 * Returns the sync dl object finder.
	 *
	 * @return the sync dl object finder
	 */
	public SyncDLObjectFinder getSyncDLObjectFinder() {
		return syncDLObjectFinder;
	}

	/**
	 * Sets the sync dl object finder.
	 *
	 * @param syncDLObjectFinder the sync dl object finder
	 */
	public void setSyncDLObjectFinder(SyncDLObjectFinder syncDLObjectFinder) {
		this.syncDLObjectFinder = syncDLObjectFinder;
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
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
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

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.sync.model.SyncDevice", syncDeviceLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.sync.model.SyncDevice");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return SyncDeviceLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return SyncDevice.class;
	}

	protected String getModelClassName() {
		return SyncDevice.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = syncDevicePersistence.getDataSource();

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

	@BeanReference(type = SyncDeviceLocalService.class)
	protected SyncDeviceLocalService syncDeviceLocalService;

	@BeanReference(type = SyncDevicePersistence.class)
	protected SyncDevicePersistence syncDevicePersistence;

	@BeanReference(
		type = com.liferay.sync.service.SyncDLFileVersionDiffLocalService.class
	)
	protected com.liferay.sync.service.SyncDLFileVersionDiffLocalService
		syncDLFileVersionDiffLocalService;

	@BeanReference(type = SyncDLFileVersionDiffPersistence.class)
	protected SyncDLFileVersionDiffPersistence syncDLFileVersionDiffPersistence;

	@BeanReference(
		type = com.liferay.sync.service.SyncDLObjectLocalService.class
	)
	protected com.liferay.sync.service.SyncDLObjectLocalService
		syncDLObjectLocalService;

	@BeanReference(type = SyncDLObjectPersistence.class)
	protected SyncDLObjectPersistence syncDLObjectPersistence;

	@BeanReference(type = SyncDLObjectFinder.class)
	protected SyncDLObjectFinder syncDLObjectFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}