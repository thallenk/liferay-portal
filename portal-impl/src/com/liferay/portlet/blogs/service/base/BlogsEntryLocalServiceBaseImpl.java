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

package com.liferay.portlet.blogs.service.base;

import com.liferay.asset.kernel.service.persistence.AssetEntryFinder;
import com.liferay.asset.kernel.service.persistence.AssetEntryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetLinkPersistence;
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.blogs.kernel.service.BlogsEntryLocalService;
import com.liferay.blogs.kernel.service.persistence.BlogsEntryFinder;
import com.liferay.blogs.kernel.service.persistence.BlogsEntryPersistence;
import com.liferay.blogs.kernel.service.persistence.BlogsStatsUserFinder;
import com.liferay.blogs.kernel.service.persistence.BlogsStatsUserPersistence;
import com.liferay.counter.kernel.service.persistence.CounterPersistence;
import com.liferay.expando.kernel.service.persistence.ExpandoRowPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandler;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Conjunction;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.CompanyPersistence;
import com.liferay.portal.kernel.service.persistence.GroupFinder;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.ImagePersistence;
import com.liferay.portal.kernel.service.persistence.OrganizationFinder;
import com.liferay.portal.kernel.service.persistence.OrganizationPersistence;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesFinder;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.kernel.service.persistence.SubscriptionPersistence;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.persistence.WorkflowInstanceLinkPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsFinder;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsPersistence;
import com.liferay.trash.kernel.service.persistence.TrashEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the blogs entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.blogs.service.impl.BlogsEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.blogs.service.impl.BlogsEntryLocalServiceImpl
 * @generated
 */
public abstract class BlogsEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements BlogsEntryLocalService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>BlogsEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.blogs.kernel.service.BlogsEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the blogs entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param blogsEntry the blogs entry
	 * @return the blogs entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BlogsEntry addBlogsEntry(BlogsEntry blogsEntry) {
		blogsEntry.setNew(true);

		return blogsEntryPersistence.update(blogsEntry);
	}

	/**
	 * Creates a new blogs entry with the primary key. Does not add the blogs entry to the database.
	 *
	 * @param entryId the primary key for the new blogs entry
	 * @return the new blogs entry
	 */
	@Override
	@Transactional(enabled = false)
	public BlogsEntry createBlogsEntry(long entryId) {
		return blogsEntryPersistence.create(entryId);
	}

	/**
	 * Deletes the blogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the blogs entry
	 * @return the blogs entry that was removed
	 * @throws PortalException if a blogs entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BlogsEntry deleteBlogsEntry(long entryId) throws PortalException {
		return blogsEntryPersistence.remove(entryId);
	}

	/**
	 * Deletes the blogs entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blogsEntry the blogs entry
	 * @return the blogs entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public BlogsEntry deleteBlogsEntry(BlogsEntry blogsEntry) {
		return blogsEntryPersistence.remove(blogsEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			BlogsEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return blogsEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portlet.blogs.model.impl.BlogsEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return blogsEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portlet.blogs.model.impl.BlogsEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return blogsEntryPersistence.findWithDynamicQuery(
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
		return blogsEntryPersistence.countWithDynamicQuery(dynamicQuery);
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

		return blogsEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public BlogsEntry fetchBlogsEntry(long entryId) {
		return blogsEntryPersistence.fetchByPrimaryKey(entryId);
	}

	/**
	 * Returns the blogs entry matching the UUID and group.
	 *
	 * @param uuid the blogs entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching blogs entry, or <code>null</code> if a matching blogs entry could not be found
	 */
	@Override
	public BlogsEntry fetchBlogsEntryByUuidAndGroupId(
		String uuid, long groupId) {

		return blogsEntryPersistence.fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the blogs entry with the primary key.
	 *
	 * @param entryId the primary key of the blogs entry
	 * @return the blogs entry
	 * @throws PortalException if a blogs entry with the primary key could not be found
	 */
	@Override
	public BlogsEntry getBlogsEntry(long entryId) throws PortalException {
		return blogsEntryPersistence.findByPrimaryKey(entryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(blogsEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(BlogsEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("entryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			blogsEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(BlogsEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("entryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(blogsEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(BlogsEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("entryId");
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
					Criterion modifiedDateCriterion =
						portletDataContext.getDateRangeCriteria("modifiedDate");

					if (modifiedDateCriterion != null) {
						Conjunction conjunction =
							RestrictionsFactoryUtil.conjunction();

						conjunction.add(modifiedDateCriterion);

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(
							RestrictionsFactoryUtil.gtProperty(
								"modifiedDate", "lastPublishDate"));

						Property lastPublishDateProperty =
							PropertyFactoryUtil.forName("lastPublishDate");

						disjunction.add(lastPublishDateProperty.isNull());

						conjunction.add(disjunction);

						modifiedDateCriterion = conjunction;
					}

					Criterion statusDateCriterion =
						portletDataContext.getDateRangeCriteria("statusDate");

					if ((modifiedDateCriterion != null) &&
						(statusDateCriterion != null)) {

						Disjunction disjunction =
							RestrictionsFactoryUtil.disjunction();

						disjunction.add(modifiedDateCriterion);
						disjunction.add(statusDateCriterion);

						dynamicQuery.add(disjunction);
					}

					Property workflowStatusProperty =
						PropertyFactoryUtil.forName("status");

					if (portletDataContext.isInitialPublication()) {
						dynamicQuery.add(
							workflowStatusProperty.ne(
								WorkflowConstants.STATUS_IN_TRASH));
					}
					else {
						StagedModelDataHandler<?> stagedModelDataHandler =
							StagedModelDataHandlerRegistryUtil.
								getStagedModelDataHandler(
									BlogsEntry.class.getName());

						dynamicQuery.add(
							workflowStatusProperty.in(
								stagedModelDataHandler.
									getExportableStatuses()));
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<BlogsEntry>() {

				@Override
				public void performAction(BlogsEntry blogsEntry)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, blogsEntry);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(BlogsEntry.class.getName())));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return blogsEntryLocalService.deleteBlogsEntry(
			(BlogsEntry)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return blogsEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns all the blogs entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the blogs entries
	 * @param companyId the primary key of the company
	 * @return the matching blogs entries, or an empty list if no matches were found
	 */
	@Override
	public List<BlogsEntry> getBlogsEntriesByUuidAndCompanyId(
		String uuid, long companyId) {

		return blogsEntryPersistence.findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of blogs entries matching the UUID and company.
	 *
	 * @param uuid the UUID of the blogs entries
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of blogs entries
	 * @param end the upper bound of the range of blogs entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching blogs entries, or an empty list if no matches were found
	 */
	@Override
	public List<BlogsEntry> getBlogsEntriesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<BlogsEntry> orderByComparator) {

		return blogsEntryPersistence.findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the blogs entry matching the UUID and group.
	 *
	 * @param uuid the blogs entry's UUID
	 * @param groupId the primary key of the group
	 * @return the matching blogs entry
	 * @throws PortalException if a matching blogs entry could not be found
	 */
	@Override
	public BlogsEntry getBlogsEntryByUuidAndGroupId(String uuid, long groupId)
		throws PortalException {

		return blogsEntryPersistence.findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns a range of all the blogs entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portlet.blogs.model.impl.BlogsEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of blogs entries
	 * @param end the upper bound of the range of blogs entries (not inclusive)
	 * @return the range of blogs entries
	 */
	@Override
	public List<BlogsEntry> getBlogsEntries(int start, int end) {
		return blogsEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of blogs entries.
	 *
	 * @return the number of blogs entries
	 */
	@Override
	public int getBlogsEntriesCount() {
		return blogsEntryPersistence.countAll();
	}

	/**
	 * Updates the blogs entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param blogsEntry the blogs entry
	 * @return the blogs entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public BlogsEntry updateBlogsEntry(BlogsEntry blogsEntry) {
		return blogsEntryPersistence.update(blogsEntry);
	}

	/**
	 * Returns the blogs entry local service.
	 *
	 * @return the blogs entry local service
	 */
	public BlogsEntryLocalService getBlogsEntryLocalService() {
		return blogsEntryLocalService;
	}

	/**
	 * Sets the blogs entry local service.
	 *
	 * @param blogsEntryLocalService the blogs entry local service
	 */
	public void setBlogsEntryLocalService(
		BlogsEntryLocalService blogsEntryLocalService) {

		this.blogsEntryLocalService = blogsEntryLocalService;
	}

	/**
	 * Returns the blogs entry persistence.
	 *
	 * @return the blogs entry persistence
	 */
	public BlogsEntryPersistence getBlogsEntryPersistence() {
		return blogsEntryPersistence;
	}

	/**
	 * Sets the blogs entry persistence.
	 *
	 * @param blogsEntryPersistence the blogs entry persistence
	 */
	public void setBlogsEntryPersistence(
		BlogsEntryPersistence blogsEntryPersistence) {

		this.blogsEntryPersistence = blogsEntryPersistence;
	}

	/**
	 * Returns the blogs entry finder.
	 *
	 * @return the blogs entry finder
	 */
	public BlogsEntryFinder getBlogsEntryFinder() {
		return blogsEntryFinder;
	}

	/**
	 * Sets the blogs entry finder.
	 *
	 * @param blogsEntryFinder the blogs entry finder
	 */
	public void setBlogsEntryFinder(BlogsEntryFinder blogsEntryFinder) {
		this.blogsEntryFinder = blogsEntryFinder;
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
	 * Returns the counter persistence.
	 *
	 * @return the counter persistence
	 */
	public CounterPersistence getCounterPersistence() {
		return counterPersistence;
	}

	/**
	 * Sets the counter persistence.
	 *
	 * @param counterPersistence the counter persistence
	 */
	public void setCounterPersistence(CounterPersistence counterPersistence) {
		this.counterPersistence = counterPersistence;
	}

	/**
	 * Returns the company local service.
	 *
	 * @return the company local service
	 */
	public com.liferay.portal.kernel.service.CompanyLocalService
		getCompanyLocalService() {

		return companyLocalService;
	}

	/**
	 * Sets the company local service.
	 *
	 * @param companyLocalService the company local service
	 */
	public void setCompanyLocalService(
		com.liferay.portal.kernel.service.CompanyLocalService
			companyLocalService) {

		this.companyLocalService = companyLocalService;
	}

	/**
	 * Returns the company persistence.
	 *
	 * @return the company persistence
	 */
	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	/**
	 * Sets the company persistence.
	 *
	 * @param companyPersistence the company persistence
	 */
	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.kernel.service.GroupLocalService
		getGroupLocalService() {

		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.kernel.service.GroupLocalService groupLocalService) {

		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public com.liferay.portal.kernel.service.ImageLocalService
		getImageLocalService() {

		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(
		com.liferay.portal.kernel.service.ImageLocalService imageLocalService) {

		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
	}

	/**
	 * Returns the organization local service.
	 *
	 * @return the organization local service
	 */
	public com.liferay.portal.kernel.service.OrganizationLocalService
		getOrganizationLocalService() {

		return organizationLocalService;
	}

	/**
	 * Sets the organization local service.
	 *
	 * @param organizationLocalService the organization local service
	 */
	public void setOrganizationLocalService(
		com.liferay.portal.kernel.service.OrganizationLocalService
			organizationLocalService) {

		this.organizationLocalService = organizationLocalService;
	}

	/**
	 * Returns the organization persistence.
	 *
	 * @return the organization persistence
	 */
	public OrganizationPersistence getOrganizationPersistence() {
		return organizationPersistence;
	}

	/**
	 * Sets the organization persistence.
	 *
	 * @param organizationPersistence the organization persistence
	 */
	public void setOrganizationPersistence(
		OrganizationPersistence organizationPersistence) {

		this.organizationPersistence = organizationPersistence;
	}

	/**
	 * Returns the organization finder.
	 *
	 * @return the organization finder
	 */
	public OrganizationFinder getOrganizationFinder() {
		return organizationFinder;
	}

	/**
	 * Sets the organization finder.
	 *
	 * @param organizationFinder the organization finder
	 */
	public void setOrganizationFinder(OrganizationFinder organizationFinder) {
		this.organizationFinder = organizationFinder;
	}

	/**
	 * Returns the portlet preferences local service.
	 *
	 * @return the portlet preferences local service
	 */
	public com.liferay.portal.kernel.service.PortletPreferencesLocalService
		getPortletPreferencesLocalService() {

		return portletPreferencesLocalService;
	}

	/**
	 * Sets the portlet preferences local service.
	 *
	 * @param portletPreferencesLocalService the portlet preferences local service
	 */
	public void setPortletPreferencesLocalService(
		com.liferay.portal.kernel.service.PortletPreferencesLocalService
			portletPreferencesLocalService) {

		this.portletPreferencesLocalService = portletPreferencesLocalService;
	}

	/**
	 * Returns the portlet preferences persistence.
	 *
	 * @return the portlet preferences persistence
	 */
	public PortletPreferencesPersistence getPortletPreferencesPersistence() {
		return portletPreferencesPersistence;
	}

	/**
	 * Sets the portlet preferences persistence.
	 *
	 * @param portletPreferencesPersistence the portlet preferences persistence
	 */
	public void setPortletPreferencesPersistence(
		PortletPreferencesPersistence portletPreferencesPersistence) {

		this.portletPreferencesPersistence = portletPreferencesPersistence;
	}

	/**
	 * Returns the portlet preferences finder.
	 *
	 * @return the portlet preferences finder
	 */
	public PortletPreferencesFinder getPortletPreferencesFinder() {
		return portletPreferencesFinder;
	}

	/**
	 * Sets the portlet preferences finder.
	 *
	 * @param portletPreferencesFinder the portlet preferences finder
	 */
	public void setPortletPreferencesFinder(
		PortletPreferencesFinder portletPreferencesFinder) {

		this.portletPreferencesFinder = portletPreferencesFinder;
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
	 * Returns the subscription local service.
	 *
	 * @return the subscription local service
	 */
	public com.liferay.portal.kernel.service.SubscriptionLocalService
		getSubscriptionLocalService() {

		return subscriptionLocalService;
	}

	/**
	 * Sets the subscription local service.
	 *
	 * @param subscriptionLocalService the subscription local service
	 */
	public void setSubscriptionLocalService(
		com.liferay.portal.kernel.service.SubscriptionLocalService
			subscriptionLocalService) {

		this.subscriptionLocalService = subscriptionLocalService;
	}

	/**
	 * Returns the subscription persistence.
	 *
	 * @return the subscription persistence
	 */
	public SubscriptionPersistence getSubscriptionPersistence() {
		return subscriptionPersistence;
	}

	/**
	 * Sets the subscription persistence.
	 *
	 * @param subscriptionPersistence the subscription persistence
	 */
	public void setSubscriptionPersistence(
		SubscriptionPersistence subscriptionPersistence) {

		this.subscriptionPersistence = subscriptionPersistence;
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

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the workflow instance link local service.
	 *
	 * @return the workflow instance link local service
	 */
	public com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		getWorkflowInstanceLinkLocalService() {

		return workflowInstanceLinkLocalService;
	}

	/**
	 * Sets the workflow instance link local service.
	 *
	 * @param workflowInstanceLinkLocalService the workflow instance link local service
	 */
	public void setWorkflowInstanceLinkLocalService(
		com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
			workflowInstanceLinkLocalService) {

		this.workflowInstanceLinkLocalService =
			workflowInstanceLinkLocalService;
	}

	/**
	 * Returns the workflow instance link persistence.
	 *
	 * @return the workflow instance link persistence
	 */
	public WorkflowInstanceLinkPersistence
		getWorkflowInstanceLinkPersistence() {

		return workflowInstanceLinkPersistence;
	}

	/**
	 * Sets the workflow instance link persistence.
	 *
	 * @param workflowInstanceLinkPersistence the workflow instance link persistence
	 */
	public void setWorkflowInstanceLinkPersistence(
		WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence) {

		this.workflowInstanceLinkPersistence = workflowInstanceLinkPersistence;
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.asset.kernel.service.AssetEntryLocalService
		getAssetEntryLocalService() {

		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.asset.kernel.service.AssetEntryLocalService
			assetEntryLocalService) {

		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {

		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset entry finder.
	 *
	 * @return the asset entry finder
	 */
	public AssetEntryFinder getAssetEntryFinder() {
		return assetEntryFinder;
	}

	/**
	 * Sets the asset entry finder.
	 *
	 * @param assetEntryFinder the asset entry finder
	 */
	public void setAssetEntryFinder(AssetEntryFinder assetEntryFinder) {
		this.assetEntryFinder = assetEntryFinder;
	}

	/**
	 * Returns the asset link local service.
	 *
	 * @return the asset link local service
	 */
	public com.liferay.asset.kernel.service.AssetLinkLocalService
		getAssetLinkLocalService() {

		return assetLinkLocalService;
	}

	/**
	 * Sets the asset link local service.
	 *
	 * @param assetLinkLocalService the asset link local service
	 */
	public void setAssetLinkLocalService(
		com.liferay.asset.kernel.service.AssetLinkLocalService
			assetLinkLocalService) {

		this.assetLinkLocalService = assetLinkLocalService;
	}

	/**
	 * Returns the asset link persistence.
	 *
	 * @return the asset link persistence
	 */
	public AssetLinkPersistence getAssetLinkPersistence() {
		return assetLinkPersistence;
	}

	/**
	 * Sets the asset link persistence.
	 *
	 * @param assetLinkPersistence the asset link persistence
	 */
	public void setAssetLinkPersistence(
		AssetLinkPersistence assetLinkPersistence) {

		this.assetLinkPersistence = assetLinkPersistence;
	}

	/**
	 * Returns the expando row local service.
	 *
	 * @return the expando row local service
	 */
	public com.liferay.expando.kernel.service.ExpandoRowLocalService
		getExpandoRowLocalService() {

		return expandoRowLocalService;
	}

	/**
	 * Sets the expando row local service.
	 *
	 * @param expandoRowLocalService the expando row local service
	 */
	public void setExpandoRowLocalService(
		com.liferay.expando.kernel.service.ExpandoRowLocalService
			expandoRowLocalService) {

		this.expandoRowLocalService = expandoRowLocalService;
	}

	/**
	 * Returns the expando row persistence.
	 *
	 * @return the expando row persistence
	 */
	public ExpandoRowPersistence getExpandoRowPersistence() {
		return expandoRowPersistence;
	}

	/**
	 * Sets the expando row persistence.
	 *
	 * @param expandoRowPersistence the expando row persistence
	 */
	public void setExpandoRowPersistence(
		ExpandoRowPersistence expandoRowPersistence) {

		this.expandoRowPersistence = expandoRowPersistence;
	}

	/**
	 * Returns the ratings stats local service.
	 *
	 * @return the ratings stats local service
	 */
	public com.liferay.ratings.kernel.service.RatingsStatsLocalService
		getRatingsStatsLocalService() {

		return ratingsStatsLocalService;
	}

	/**
	 * Sets the ratings stats local service.
	 *
	 * @param ratingsStatsLocalService the ratings stats local service
	 */
	public void setRatingsStatsLocalService(
		com.liferay.ratings.kernel.service.RatingsStatsLocalService
			ratingsStatsLocalService) {

		this.ratingsStatsLocalService = ratingsStatsLocalService;
	}

	/**
	 * Returns the ratings stats persistence.
	 *
	 * @return the ratings stats persistence
	 */
	public RatingsStatsPersistence getRatingsStatsPersistence() {
		return ratingsStatsPersistence;
	}

	/**
	 * Sets the ratings stats persistence.
	 *
	 * @param ratingsStatsPersistence the ratings stats persistence
	 */
	public void setRatingsStatsPersistence(
		RatingsStatsPersistence ratingsStatsPersistence) {

		this.ratingsStatsPersistence = ratingsStatsPersistence;
	}

	/**
	 * Returns the ratings stats finder.
	 *
	 * @return the ratings stats finder
	 */
	public RatingsStatsFinder getRatingsStatsFinder() {
		return ratingsStatsFinder;
	}

	/**
	 * Sets the ratings stats finder.
	 *
	 * @param ratingsStatsFinder the ratings stats finder
	 */
	public void setRatingsStatsFinder(RatingsStatsFinder ratingsStatsFinder) {
		this.ratingsStatsFinder = ratingsStatsFinder;
	}

	/**
	 * Returns the trash entry local service.
	 *
	 * @return the trash entry local service
	 */
	public com.liferay.trash.kernel.service.TrashEntryLocalService
		getTrashEntryLocalService() {

		return trashEntryLocalService;
	}

	/**
	 * Sets the trash entry local service.
	 *
	 * @param trashEntryLocalService the trash entry local service
	 */
	public void setTrashEntryLocalService(
		com.liferay.trash.kernel.service.TrashEntryLocalService
			trashEntryLocalService) {

		this.trashEntryLocalService = trashEntryLocalService;
	}

	/**
	 * Returns the trash entry persistence.
	 *
	 * @return the trash entry persistence
	 */
	public TrashEntryPersistence getTrashEntryPersistence() {
		return trashEntryPersistence;
	}

	/**
	 * Sets the trash entry persistence.
	 *
	 * @param trashEntryPersistence the trash entry persistence
	 */
	public void setTrashEntryPersistence(
		TrashEntryPersistence trashEntryPersistence) {

		this.trashEntryPersistence = trashEntryPersistence;
	}

	/**
	 * Returns the blogs stats user local service.
	 *
	 * @return the blogs stats user local service
	 */
	public com.liferay.blogs.kernel.service.BlogsStatsUserLocalService
		getBlogsStatsUserLocalService() {

		return blogsStatsUserLocalService;
	}

	/**
	 * Sets the blogs stats user local service.
	 *
	 * @param blogsStatsUserLocalService the blogs stats user local service
	 */
	public void setBlogsStatsUserLocalService(
		com.liferay.blogs.kernel.service.BlogsStatsUserLocalService
			blogsStatsUserLocalService) {

		this.blogsStatsUserLocalService = blogsStatsUserLocalService;
	}

	/**
	 * Returns the blogs stats user persistence.
	 *
	 * @return the blogs stats user persistence
	 */
	public BlogsStatsUserPersistence getBlogsStatsUserPersistence() {
		return blogsStatsUserPersistence;
	}

	/**
	 * Sets the blogs stats user persistence.
	 *
	 * @param blogsStatsUserPersistence the blogs stats user persistence
	 */
	public void setBlogsStatsUserPersistence(
		BlogsStatsUserPersistence blogsStatsUserPersistence) {

		this.blogsStatsUserPersistence = blogsStatsUserPersistence;
	}

	/**
	 * Returns the blogs stats user finder.
	 *
	 * @return the blogs stats user finder
	 */
	public BlogsStatsUserFinder getBlogsStatsUserFinder() {
		return blogsStatsUserFinder;
	}

	/**
	 * Sets the blogs stats user finder.
	 *
	 * @param blogsStatsUserFinder the blogs stats user finder
	 */
	public void setBlogsStatsUserFinder(
		BlogsStatsUserFinder blogsStatsUserFinder) {

		this.blogsStatsUserFinder = blogsStatsUserFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.blogs.kernel.model.BlogsEntry",
			blogsEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.blogs.kernel.model.BlogsEntry");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return BlogsEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return BlogsEntry.class;
	}

	protected String getModelClassName() {
		return BlogsEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = blogsEntryPersistence.getDataSource();

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

	@BeanReference(type = BlogsEntryLocalService.class)
	protected BlogsEntryLocalService blogsEntryLocalService;

	@BeanReference(type = BlogsEntryPersistence.class)
	protected BlogsEntryPersistence blogsEntryPersistence;

	@BeanReference(type = BlogsEntryFinder.class)
	protected BlogsEntryFinder blogsEntryFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = CounterPersistence.class)
	protected CounterPersistence counterPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.CompanyLocalService.class
	)
	protected com.liferay.portal.kernel.service.CompanyLocalService
		companyLocalService;

	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.GroupLocalService.class
	)
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;

	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ImageLocalService.class
	)
	protected com.liferay.portal.kernel.service.ImageLocalService
		imageLocalService;

	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.OrganizationLocalService.class
	)
	protected com.liferay.portal.kernel.service.OrganizationLocalService
		organizationLocalService;

	@BeanReference(type = OrganizationPersistence.class)
	protected OrganizationPersistence organizationPersistence;

	@BeanReference(type = OrganizationFinder.class)
	protected OrganizationFinder organizationFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.PortletPreferencesLocalService.class
	)
	protected com.liferay.portal.kernel.service.PortletPreferencesLocalService
		portletPreferencesLocalService;

	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;

	@BeanReference(type = PortletPreferencesFinder.class)
	protected PortletPreferencesFinder portletPreferencesFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.SubscriptionLocalService.class
	)
	protected com.liferay.portal.kernel.service.SubscriptionLocalService
		subscriptionLocalService;

	@BeanReference(type = SubscriptionPersistence.class)
	protected SubscriptionPersistence subscriptionPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService.class
	)
	protected com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService
		workflowInstanceLinkLocalService;

	@BeanReference(type = WorkflowInstanceLinkPersistence.class)
	protected WorkflowInstanceLinkPersistence workflowInstanceLinkPersistence;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetEntryLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;

	@BeanReference(type = AssetEntryFinder.class)
	protected AssetEntryFinder assetEntryFinder;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetLinkLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetLinkLocalService
		assetLinkLocalService;

	@BeanReference(type = AssetLinkPersistence.class)
	protected AssetLinkPersistence assetLinkPersistence;

	@BeanReference(
		type = com.liferay.expando.kernel.service.ExpandoRowLocalService.class
	)
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService
		expandoRowLocalService;

	@BeanReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;

	@BeanReference(
		type = com.liferay.ratings.kernel.service.RatingsStatsLocalService.class
	)
	protected com.liferay.ratings.kernel.service.RatingsStatsLocalService
		ratingsStatsLocalService;

	@BeanReference(type = RatingsStatsPersistence.class)
	protected RatingsStatsPersistence ratingsStatsPersistence;

	@BeanReference(type = RatingsStatsFinder.class)
	protected RatingsStatsFinder ratingsStatsFinder;

	@BeanReference(
		type = com.liferay.trash.kernel.service.TrashEntryLocalService.class
	)
	protected com.liferay.trash.kernel.service.TrashEntryLocalService
		trashEntryLocalService;

	@BeanReference(type = TrashEntryPersistence.class)
	protected TrashEntryPersistence trashEntryPersistence;

	@BeanReference(
		type = com.liferay.blogs.kernel.service.BlogsStatsUserLocalService.class
	)
	protected com.liferay.blogs.kernel.service.BlogsStatsUserLocalService
		blogsStatsUserLocalService;

	@BeanReference(type = BlogsStatsUserPersistence.class)
	protected BlogsStatsUserPersistence blogsStatsUserPersistence;

	@BeanReference(type = BlogsStatsUserFinder.class)
	protected BlogsStatsUserFinder blogsStatsUserFinder;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}