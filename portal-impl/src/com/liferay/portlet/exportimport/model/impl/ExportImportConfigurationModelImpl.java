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

package com.liferay.portlet.exportimport.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.model.ExportImportConfiguration;
import com.liferay.exportimport.kernel.model.ExportImportConfigurationModel;
import com.liferay.exportimport.kernel.model.ExportImportConfigurationSoap;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.NoSuchModelException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ContainerModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.TrashedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the ExportImportConfiguration service. Represents a row in the &quot;ExportImportConfiguration&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link ExportImportConfigurationModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ExportImportConfigurationImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExportImportConfigurationImpl
 * @see ExportImportConfiguration
 * @see ExportImportConfigurationModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class ExportImportConfigurationModelImpl extends BaseModelImpl<ExportImportConfiguration>
	implements ExportImportConfigurationModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a export import configuration model instance should use the {@link ExportImportConfiguration} interface instead.
	 */
	public static final String TABLE_NAME = "ExportImportConfiguration";
	public static final Object[][] TABLE_COLUMNS = {
			{ "mvccVersion", Types.BIGINT },
			{ "exportImportConfigurationId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "type_", Types.INTEGER },
			{ "settings_", Types.CLOB },
			{ "status", Types.INTEGER },
			{ "statusByUserId", Types.BIGINT },
			{ "statusByUserName", Types.VARCHAR },
			{ "statusDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("exportImportConfigurationId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("settings_", Types.CLOB);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("statusByUserId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("statusByUserName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("statusDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table ExportImportConfiguration (mvccVersion LONG default 0 not null,exportImportConfigurationId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,name VARCHAR(200) null,description STRING null,type_ INTEGER,settings_ TEXT null,status INTEGER,statusByUserId LONG,statusByUserName VARCHAR(75) null,statusDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table ExportImportConfiguration";
	public static final String ORDER_BY_JPQL = " ORDER BY exportImportConfiguration.createDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY ExportImportConfiguration.createDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.exportimport.kernel.model.ExportImportConfiguration"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.exportimport.kernel.model.ExportImportConfiguration"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.exportimport.kernel.model.ExportImportConfiguration"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long STATUS_COLUMN_BITMASK = 4L;
	public static final long TYPE_COLUMN_BITMASK = 8L;
	public static final long CREATEDATE_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static ExportImportConfiguration toModel(
		ExportImportConfigurationSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		ExportImportConfiguration model = new ExportImportConfigurationImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setExportImportConfigurationId(soapModel.getExportImportConfigurationId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setType(soapModel.getType());
		model.setSettings(soapModel.getSettings());
		model.setStatus(soapModel.getStatus());
		model.setStatusByUserId(soapModel.getStatusByUserId());
		model.setStatusByUserName(soapModel.getStatusByUserName());
		model.setStatusDate(soapModel.getStatusDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<ExportImportConfiguration> toModels(
		ExportImportConfigurationSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<ExportImportConfiguration> models = new ArrayList<ExportImportConfiguration>(soapModels.length);

		for (ExportImportConfigurationSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.exportimport.kernel.model.ExportImportConfiguration"));

	public ExportImportConfigurationModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _exportImportConfigurationId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setExportImportConfigurationId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _exportImportConfigurationId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return ExportImportConfiguration.class;
	}

	@Override
	public String getModelClassName() {
		return ExportImportConfiguration.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<ExportImportConfiguration, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<ExportImportConfiguration, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<ExportImportConfiguration, Object> attributeGetterFunction = entry.getValue();

			attributes.put(attributeName,
				attributeGetterFunction.apply((ExportImportConfiguration)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<ExportImportConfiguration, Object>> attributeSetterBiConsumers =
			getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<ExportImportConfiguration, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((ExportImportConfiguration)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<ExportImportConfiguration, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<ExportImportConfiguration, Object>> getAttributeSetterBiConsumers() {
		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<ExportImportConfiguration, Object>> _attributeGetterFunctions;
	private static final Map<String, BiConsumer<ExportImportConfiguration, Object>> _attributeSetterBiConsumers;

	static {
		Map<String, Function<ExportImportConfiguration, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<ExportImportConfiguration, Object>>();
		Map<String, BiConsumer<ExportImportConfiguration, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<ExportImportConfiguration, ?>>();


		attributeGetterFunctions.put("mvccVersion", ExportImportConfiguration::getMvccVersion);
		attributeSetterBiConsumers.put("mvccVersion", (BiConsumer<ExportImportConfiguration, Long>)ExportImportConfiguration::setMvccVersion);
		attributeGetterFunctions.put("exportImportConfigurationId", ExportImportConfiguration::getExportImportConfigurationId);
		attributeSetterBiConsumers.put("exportImportConfigurationId", (BiConsumer<ExportImportConfiguration, Long>)ExportImportConfiguration::setExportImportConfigurationId);
		attributeGetterFunctions.put("groupId", ExportImportConfiguration::getGroupId);
		attributeSetterBiConsumers.put("groupId", (BiConsumer<ExportImportConfiguration, Long>)ExportImportConfiguration::setGroupId);
		attributeGetterFunctions.put("companyId", ExportImportConfiguration::getCompanyId);
		attributeSetterBiConsumers.put("companyId", (BiConsumer<ExportImportConfiguration, Long>)ExportImportConfiguration::setCompanyId);
		attributeGetterFunctions.put("userId", ExportImportConfiguration::getUserId);
		attributeSetterBiConsumers.put("userId", (BiConsumer<ExportImportConfiguration, Long>)ExportImportConfiguration::setUserId);
		attributeGetterFunctions.put("userName", ExportImportConfiguration::getUserName);
		attributeSetterBiConsumers.put("userName", (BiConsumer<ExportImportConfiguration, String>)ExportImportConfiguration::setUserName);
		attributeGetterFunctions.put("createDate", ExportImportConfiguration::getCreateDate);
		attributeSetterBiConsumers.put("createDate", (BiConsumer<ExportImportConfiguration, Date>)ExportImportConfiguration::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", ExportImportConfiguration::getModifiedDate);
		attributeSetterBiConsumers.put("modifiedDate", (BiConsumer<ExportImportConfiguration, Date>)ExportImportConfiguration::setModifiedDate);
		attributeGetterFunctions.put("name", ExportImportConfiguration::getName);
		attributeSetterBiConsumers.put("name", (BiConsumer<ExportImportConfiguration, String>)ExportImportConfiguration::setName);
		attributeGetterFunctions.put("description", ExportImportConfiguration::getDescription);
		attributeSetterBiConsumers.put("description", (BiConsumer<ExportImportConfiguration, String>)ExportImportConfiguration::setDescription);
		attributeGetterFunctions.put("type", ExportImportConfiguration::getType);
		attributeSetterBiConsumers.put("type", (BiConsumer<ExportImportConfiguration, Integer>)ExportImportConfiguration::setType);
		attributeGetterFunctions.put("settings", ExportImportConfiguration::getSettings);
		attributeSetterBiConsumers.put("settings", (BiConsumer<ExportImportConfiguration, String>)ExportImportConfiguration::setSettings);
		attributeGetterFunctions.put("status", ExportImportConfiguration::getStatus);
		attributeSetterBiConsumers.put("status", (BiConsumer<ExportImportConfiguration, Integer>)ExportImportConfiguration::setStatus);
		attributeGetterFunctions.put("statusByUserId", ExportImportConfiguration::getStatusByUserId);
		attributeSetterBiConsumers.put("statusByUserId", (BiConsumer<ExportImportConfiguration, Long>)ExportImportConfiguration::setStatusByUserId);
		attributeGetterFunctions.put("statusByUserName", ExportImportConfiguration::getStatusByUserName);
		attributeSetterBiConsumers.put("statusByUserName", (BiConsumer<ExportImportConfiguration, String>)ExportImportConfiguration::setStatusByUserName);
		attributeGetterFunctions.put("statusDate", ExportImportConfiguration::getStatusDate);
		attributeSetterBiConsumers.put("statusDate", (BiConsumer<ExportImportConfiguration, Date>)ExportImportConfiguration::setStatusDate);


		_attributeGetterFunctions = Collections.unmodifiableMap(attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap((Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public long getMvccVersion() {
		return _mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	@JSON
	@Override
	public long getExportImportConfigurationId() {
		return _exportImportConfigurationId;
	}

	@Override
	public void setExportImportConfigurationId(long exportImportConfigurationId) {
		_exportImportConfigurationId = exportImportConfigurationId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@JSON
	@Override
	public String getSettings() {
		if (_settings == null) {
			return "";
		}
		else {
			return _settings;
		}
	}

	@Override
	public void setSettings(String settings) {
		_settings = settings;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@JSON
	@Override
	public long getStatusByUserId() {
		return _statusByUserId;
	}

	@Override
	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	@Override
	public String getStatusByUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getStatusByUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
	}

	@JSON
	@Override
	public String getStatusByUserName() {
		if (_statusByUserName == null) {
			return "";
		}
		else {
			return _statusByUserName;
		}
	}

	@Override
	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	@JSON
	@Override
	public Date getStatusDate() {
		return _statusDate;
	}

	@Override
	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	@Override
	public com.liferay.trash.kernel.model.TrashEntry getTrashEntry()
		throws PortalException {
		if (!isInTrash()) {
			return null;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry = com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return trashEntry;
		}

		com.liferay.portal.kernel.trash.TrashHandler trashHandler = getTrashHandler();

		if (Validator.isNotNull(trashHandler.getContainerModelClassName(
						getPrimaryKey()))) {
			ContainerModel containerModel = null;

			try {
				containerModel = trashHandler.getParentContainerModel(this);
			}
			catch (NoSuchModelException nsme) {
				return null;
			}

			while (containerModel != null) {
				if (containerModel instanceof TrashedModel) {
					TrashedModel trashedModel = (TrashedModel)containerModel;

					return trashedModel.getTrashEntry();
				}

				trashHandler = com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil.getTrashHandler(trashHandler.getContainerModelClassName(
							containerModel.getContainerModelId()));

				if (trashHandler == null) {
					return null;
				}

				containerModel = trashHandler.getContainerModel(containerModel.getParentContainerModelId());
			}
		}

		return null;
	}

	@Override
	public long getTrashEntryClassPK() {
		return getPrimaryKey();
	}

	/**
	* @deprecated As of Judson (7.1.x), with no direct replacement
	*/
	@Deprecated
	@Override
	public com.liferay.portal.kernel.trash.TrashHandler getTrashHandler() {
		return com.liferay.portal.kernel.trash.TrashHandlerRegistryUtil.getTrashHandler(getModelClassName());
	}

	@Override
	public boolean isInTrash() {
		if (getStatus() == WorkflowConstants.STATUS_IN_TRASH) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInTrashContainer() {
		com.liferay.portal.kernel.trash.TrashHandler trashHandler = getTrashHandler();

		if ((trashHandler == null) ||
				Validator.isNull(trashHandler.getContainerModelClassName(
						getPrimaryKey()))) {
			return false;
		}

		try {
			ContainerModel containerModel = trashHandler.getParentContainerModel(this);

			if (containerModel == null) {
				return false;
			}

			if (containerModel instanceof TrashedModel) {
				return ((TrashedModel)containerModel).isInTrash();
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	public boolean isInTrashExplicitly() {
		if (!isInTrash()) {
			return false;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry = com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isInTrashImplicitly() {
		if (!isInTrash()) {
			return false;
		}

		com.liferay.trash.kernel.model.TrashEntry trashEntry = com.liferay.trash.kernel.service.TrashEntryLocalServiceUtil.fetchEntry(getModelClassName(),
				getTrashEntryClassPK());

		if (trashEntry != null) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isApproved() {
		if (getStatus() == WorkflowConstants.STATUS_APPROVED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDenied() {
		if (getStatus() == WorkflowConstants.STATUS_DENIED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isDraft() {
		if (getStatus() == WorkflowConstants.STATUS_DRAFT) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isExpired() {
		if (getStatus() == WorkflowConstants.STATUS_EXPIRED) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isInactive() {
		if (getStatus() == WorkflowConstants.STATUS_INACTIVE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isIncomplete() {
		if (getStatus() == WorkflowConstants.STATUS_INCOMPLETE) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isPending() {
		if (getStatus() == WorkflowConstants.STATUS_PENDING) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isScheduled() {
		if (getStatus() == WorkflowConstants.STATUS_SCHEDULED) {
			return true;
		}
		else {
			return false;
		}
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			ExportImportConfiguration.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public ExportImportConfiguration toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (ExportImportConfiguration)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ExportImportConfigurationImpl exportImportConfigurationImpl = new ExportImportConfigurationImpl();

		exportImportConfigurationImpl.setMvccVersion(getMvccVersion());
		exportImportConfigurationImpl.setExportImportConfigurationId(getExportImportConfigurationId());
		exportImportConfigurationImpl.setGroupId(getGroupId());
		exportImportConfigurationImpl.setCompanyId(getCompanyId());
		exportImportConfigurationImpl.setUserId(getUserId());
		exportImportConfigurationImpl.setUserName(getUserName());
		exportImportConfigurationImpl.setCreateDate(getCreateDate());
		exportImportConfigurationImpl.setModifiedDate(getModifiedDate());
		exportImportConfigurationImpl.setName(getName());
		exportImportConfigurationImpl.setDescription(getDescription());
		exportImportConfigurationImpl.setType(getType());
		exportImportConfigurationImpl.setSettings(getSettings());
		exportImportConfigurationImpl.setStatus(getStatus());
		exportImportConfigurationImpl.setStatusByUserId(getStatusByUserId());
		exportImportConfigurationImpl.setStatusByUserName(getStatusByUserName());
		exportImportConfigurationImpl.setStatusDate(getStatusDate());

		exportImportConfigurationImpl.resetOriginalValues();

		return exportImportConfigurationImpl;
	}

	@Override
	public int compareTo(ExportImportConfiguration exportImportConfiguration) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				exportImportConfiguration.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExportImportConfiguration)) {
			return false;
		}

		ExportImportConfiguration exportImportConfiguration = (ExportImportConfiguration)obj;

		long primaryKey = exportImportConfiguration.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		ExportImportConfigurationModelImpl exportImportConfigurationModelImpl = this;

		exportImportConfigurationModelImpl._originalGroupId = exportImportConfigurationModelImpl._groupId;

		exportImportConfigurationModelImpl._setOriginalGroupId = false;

		exportImportConfigurationModelImpl._originalCompanyId = exportImportConfigurationModelImpl._companyId;

		exportImportConfigurationModelImpl._setOriginalCompanyId = false;

		exportImportConfigurationModelImpl._setModifiedDate = false;

		exportImportConfigurationModelImpl._originalType = exportImportConfigurationModelImpl._type;

		exportImportConfigurationModelImpl._setOriginalType = false;

		exportImportConfigurationModelImpl._originalStatus = exportImportConfigurationModelImpl._status;

		exportImportConfigurationModelImpl._setOriginalStatus = false;

		exportImportConfigurationModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<ExportImportConfiguration> toCacheModel() {
		ExportImportConfigurationCacheModel exportImportConfigurationCacheModel = new ExportImportConfigurationCacheModel();

		exportImportConfigurationCacheModel.mvccVersion = getMvccVersion();

		exportImportConfigurationCacheModel.exportImportConfigurationId = getExportImportConfigurationId();

		exportImportConfigurationCacheModel.groupId = getGroupId();

		exportImportConfigurationCacheModel.companyId = getCompanyId();

		exportImportConfigurationCacheModel.userId = getUserId();

		exportImportConfigurationCacheModel.userName = getUserName();

		String userName = exportImportConfigurationCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			exportImportConfigurationCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			exportImportConfigurationCacheModel.createDate = createDate.getTime();
		}
		else {
			exportImportConfigurationCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			exportImportConfigurationCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			exportImportConfigurationCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		exportImportConfigurationCacheModel.name = getName();

		String name = exportImportConfigurationCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			exportImportConfigurationCacheModel.name = null;
		}

		exportImportConfigurationCacheModel.description = getDescription();

		String description = exportImportConfigurationCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			exportImportConfigurationCacheModel.description = null;
		}

		exportImportConfigurationCacheModel.type = getType();

		exportImportConfigurationCacheModel.settings = getSettings();

		String settings = exportImportConfigurationCacheModel.settings;

		if ((settings != null) && (settings.length() == 0)) {
			exportImportConfigurationCacheModel.settings = null;
		}

		exportImportConfigurationCacheModel.status = getStatus();

		exportImportConfigurationCacheModel.statusByUserId = getStatusByUserId();

		exportImportConfigurationCacheModel.statusByUserName = getStatusByUserName();

		String statusByUserName = exportImportConfigurationCacheModel.statusByUserName;

		if ((statusByUserName != null) && (statusByUserName.length() == 0)) {
			exportImportConfigurationCacheModel.statusByUserName = null;
		}

		Date statusDate = getStatusDate();

		if (statusDate != null) {
			exportImportConfigurationCacheModel.statusDate = statusDate.getTime();
		}
		else {
			exportImportConfigurationCacheModel.statusDate = Long.MIN_VALUE;
		}

		return exportImportConfigurationCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<ExportImportConfiguration, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((4 * attributeGetterFunctions.size()) +
				2);

		sb.append("{");

		for (Map.Entry<String, Function<ExportImportConfiguration, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<ExportImportConfiguration, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply(
					(ExportImportConfiguration)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<ExportImportConfiguration, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((5 * attributeGetterFunctions.size()) +
				4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<ExportImportConfiguration, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<ExportImportConfiguration, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply(
					(ExportImportConfiguration)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = ExportImportConfiguration.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			ExportImportConfiguration.class, ModelWrapper.class
		};
	private long _mvccVersion;
	private long _exportImportConfigurationId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _name;
	private String _description;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private String _settings;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
	private long _columnBitmask;
	private ExportImportConfiguration _escapedModel;
}