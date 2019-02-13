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

package com.liferay.portal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.model.RepositoryModel;
import com.liferay.portal.kernel.model.RepositorySoap;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;

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
 * The base model implementation for the Repository service. Represents a row in the &quot;Repository&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link RepositoryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link RepositoryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RepositoryImpl
 * @see Repository
 * @see RepositoryModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class RepositoryModelImpl extends BaseModelImpl<Repository>
	implements RepositoryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a repository model instance should use the {@link Repository} interface instead.
	 */
	public static final String TABLE_NAME = "Repository";
	public static final Object[][] TABLE_COLUMNS = {
			{ "mvccVersion", Types.BIGINT },
			{ "uuid_", Types.VARCHAR },
			{ "repositoryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "classNameId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "description", Types.VARCHAR },
			{ "portletId", Types.VARCHAR },
			{ "typeSettings", Types.CLOB },
			{ "dlFolderId", Types.BIGINT },
			{ "lastPublishDate", Types.TIMESTAMP }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("repositoryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("portletId", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("dlFolderId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE = "create table Repository (mvccVersion LONG default 0 not null,uuid_ VARCHAR(75) null,repositoryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,name VARCHAR(200) null,description STRING null,portletId VARCHAR(200) null,typeSettings TEXT null,dlFolderId LONG,lastPublishDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table Repository";
	public static final String ORDER_BY_JPQL = " ORDER BY repository.repositoryId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Repository.repositoryId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.Repository"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.Repository"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.Repository"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long GROUPID_COLUMN_BITMASK = 2L;
	public static final long NAME_COLUMN_BITMASK = 4L;
	public static final long PORTLETID_COLUMN_BITMASK = 8L;
	public static final long UUID_COLUMN_BITMASK = 16L;
	public static final long REPOSITORYID_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Repository toModel(RepositorySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Repository model = new RepositoryImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUuid(soapModel.getUuid());
		model.setRepositoryId(soapModel.getRepositoryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setClassNameId(soapModel.getClassNameId());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setPortletId(soapModel.getPortletId());
		model.setTypeSettings(soapModel.getTypeSettings());
		model.setDlFolderId(soapModel.getDlFolderId());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Repository> toModels(RepositorySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Repository> models = new ArrayList<Repository>(soapModels.length);

		for (RepositorySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.kernel.model.Repository"));

	public RepositoryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _repositoryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setRepositoryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _repositoryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Repository.class;
	}

	@Override
	public String getModelClassName() {
		return Repository.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<Repository, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<Repository, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<Repository, Object> attributeGetterFunction = entry.getValue();

			attributes.put(attributeName,
				attributeGetterFunction.apply((Repository)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<Repository, Object>> attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<Repository, Object> attributeSetterBiConsumer = attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept((Repository)this,
					entry.getValue());
			}
		}
	}

	public Map<String, Function<Repository, Object>> getAttributeGetterFunctions() {
		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<Repository, Object>> getAttributeSetterBiConsumers() {
		return _attributeSetterBiConsumers;
	}

	private static final Map<String, Function<Repository, Object>> _attributeGetterFunctions;
	private static final Map<String, BiConsumer<Repository, Object>> _attributeSetterBiConsumers;

	static {
		Map<String, Function<Repository, Object>> attributeGetterFunctions = new LinkedHashMap<String, Function<Repository, Object>>();
		Map<String, BiConsumer<Repository, ?>> attributeSetterBiConsumers = new LinkedHashMap<String, BiConsumer<Repository, ?>>();


		attributeGetterFunctions.put("mvccVersion", Repository::getMvccVersion);
		attributeSetterBiConsumers.put("mvccVersion", (BiConsumer<Repository, Long>)Repository::setMvccVersion);
		attributeGetterFunctions.put("uuid", Repository::getUuid);
		attributeSetterBiConsumers.put("uuid", (BiConsumer<Repository, String>)Repository::setUuid);
		attributeGetterFunctions.put("repositoryId", Repository::getRepositoryId);
		attributeSetterBiConsumers.put("repositoryId", (BiConsumer<Repository, Long>)Repository::setRepositoryId);
		attributeGetterFunctions.put("groupId", Repository::getGroupId);
		attributeSetterBiConsumers.put("groupId", (BiConsumer<Repository, Long>)Repository::setGroupId);
		attributeGetterFunctions.put("companyId", Repository::getCompanyId);
		attributeSetterBiConsumers.put("companyId", (BiConsumer<Repository, Long>)Repository::setCompanyId);
		attributeGetterFunctions.put("userId", Repository::getUserId);
		attributeSetterBiConsumers.put("userId", (BiConsumer<Repository, Long>)Repository::setUserId);
		attributeGetterFunctions.put("userName", Repository::getUserName);
		attributeSetterBiConsumers.put("userName", (BiConsumer<Repository, String>)Repository::setUserName);
		attributeGetterFunctions.put("createDate", Repository::getCreateDate);
		attributeSetterBiConsumers.put("createDate", (BiConsumer<Repository, Date>)Repository::setCreateDate);
		attributeGetterFunctions.put("modifiedDate", Repository::getModifiedDate);
		attributeSetterBiConsumers.put("modifiedDate", (BiConsumer<Repository, Date>)Repository::setModifiedDate);
		attributeGetterFunctions.put("classNameId", Repository::getClassNameId);
		attributeSetterBiConsumers.put("classNameId", (BiConsumer<Repository, Long>)Repository::setClassNameId);
		attributeGetterFunctions.put("name", Repository::getName);
		attributeSetterBiConsumers.put("name", (BiConsumer<Repository, String>)Repository::setName);
		attributeGetterFunctions.put("description", Repository::getDescription);
		attributeSetterBiConsumers.put("description", (BiConsumer<Repository, String>)Repository::setDescription);
		attributeGetterFunctions.put("portletId", Repository::getPortletId);
		attributeSetterBiConsumers.put("portletId", (BiConsumer<Repository, String>)Repository::setPortletId);
		attributeGetterFunctions.put("typeSettings", Repository::getTypeSettings);
		attributeSetterBiConsumers.put("typeSettings", (BiConsumer<Repository, String>)Repository::setTypeSettings);
		attributeGetterFunctions.put("dlFolderId", Repository::getDlFolderId);
		attributeSetterBiConsumers.put("dlFolderId", (BiConsumer<Repository, Long>)Repository::setDlFolderId);
		attributeGetterFunctions.put("lastPublishDate", Repository::getLastPublishDate);
		attributeSetterBiConsumers.put("lastPublishDate", (BiConsumer<Repository, Date>)Repository::setLastPublishDate);


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
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getRepositoryId() {
		return _repositoryId;
	}

	@Override
	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
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

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
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
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
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
	public String getPortletId() {
		if (_portletId == null) {
			return "";
		}
		else {
			return _portletId;
		}
	}

	@Override
	public void setPortletId(String portletId) {
		_columnBitmask |= PORTLETID_COLUMN_BITMASK;

		if (_originalPortletId == null) {
			_originalPortletId = _portletId;
		}

		_portletId = portletId;
	}

	public String getOriginalPortletId() {
		return GetterUtil.getString(_originalPortletId);
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	@JSON
	@Override
	public long getDlFolderId() {
		return _dlFolderId;
	}

	@Override
	public void setDlFolderId(long dlFolderId) {
		_dlFolderId = dlFolderId;
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				Repository.class.getName()), getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			Repository.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Repository toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Repository)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		RepositoryImpl repositoryImpl = new RepositoryImpl();

		repositoryImpl.setMvccVersion(getMvccVersion());
		repositoryImpl.setUuid(getUuid());
		repositoryImpl.setRepositoryId(getRepositoryId());
		repositoryImpl.setGroupId(getGroupId());
		repositoryImpl.setCompanyId(getCompanyId());
		repositoryImpl.setUserId(getUserId());
		repositoryImpl.setUserName(getUserName());
		repositoryImpl.setCreateDate(getCreateDate());
		repositoryImpl.setModifiedDate(getModifiedDate());
		repositoryImpl.setClassNameId(getClassNameId());
		repositoryImpl.setName(getName());
		repositoryImpl.setDescription(getDescription());
		repositoryImpl.setPortletId(getPortletId());
		repositoryImpl.setTypeSettings(getTypeSettings());
		repositoryImpl.setDlFolderId(getDlFolderId());
		repositoryImpl.setLastPublishDate(getLastPublishDate());

		repositoryImpl.resetOriginalValues();

		return repositoryImpl;
	}

	@Override
	public int compareTo(Repository repository) {
		long primaryKey = repository.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Repository)) {
			return false;
		}

		Repository repository = (Repository)obj;

		long primaryKey = repository.getPrimaryKey();

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
		RepositoryModelImpl repositoryModelImpl = this;

		repositoryModelImpl._originalUuid = repositoryModelImpl._uuid;

		repositoryModelImpl._originalGroupId = repositoryModelImpl._groupId;

		repositoryModelImpl._setOriginalGroupId = false;

		repositoryModelImpl._originalCompanyId = repositoryModelImpl._companyId;

		repositoryModelImpl._setOriginalCompanyId = false;

		repositoryModelImpl._setModifiedDate = false;

		repositoryModelImpl._originalName = repositoryModelImpl._name;

		repositoryModelImpl._originalPortletId = repositoryModelImpl._portletId;

		repositoryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Repository> toCacheModel() {
		RepositoryCacheModel repositoryCacheModel = new RepositoryCacheModel();

		repositoryCacheModel.mvccVersion = getMvccVersion();

		repositoryCacheModel.uuid = getUuid();

		String uuid = repositoryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			repositoryCacheModel.uuid = null;
		}

		repositoryCacheModel.repositoryId = getRepositoryId();

		repositoryCacheModel.groupId = getGroupId();

		repositoryCacheModel.companyId = getCompanyId();

		repositoryCacheModel.userId = getUserId();

		repositoryCacheModel.userName = getUserName();

		String userName = repositoryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			repositoryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			repositoryCacheModel.createDate = createDate.getTime();
		}
		else {
			repositoryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			repositoryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			repositoryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		repositoryCacheModel.classNameId = getClassNameId();

		repositoryCacheModel.name = getName();

		String name = repositoryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			repositoryCacheModel.name = null;
		}

		repositoryCacheModel.description = getDescription();

		String description = repositoryCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			repositoryCacheModel.description = null;
		}

		repositoryCacheModel.portletId = getPortletId();

		String portletId = repositoryCacheModel.portletId;

		if ((portletId != null) && (portletId.length() == 0)) {
			repositoryCacheModel.portletId = null;
		}

		repositoryCacheModel.typeSettings = getTypeSettings();

		String typeSettings = repositoryCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			repositoryCacheModel.typeSettings = null;
		}

		repositoryCacheModel.dlFolderId = getDlFolderId();

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			repositoryCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			repositoryCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return repositoryCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<Repository, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((4 * attributeGetterFunctions.size()) +
				2);

		sb.append("{");

		for (Map.Entry<String, Function<Repository, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<Repository, Object> attributeGetterFunction = entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((Repository)this));
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
		Map<String, Function<Repository, Object>> attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler((5 * attributeGetterFunctions.size()) +
				4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<Repository, Object>> entry : attributeGetterFunctions.entrySet()) {
			String attributeName = entry.getKey();
			Function<Repository, Object> attributeGetterFunction = entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((Repository)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = Repository.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			Repository.class, ModelWrapper.class
		};
	private long _mvccVersion;
	private String _uuid;
	private String _originalUuid;
	private long _repositoryId;
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
	private long _classNameId;
	private String _name;
	private String _originalName;
	private String _description;
	private String _portletId;
	private String _originalPortletId;
	private String _typeSettings;
	private long _dlFolderId;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private Repository _escapedModel;
}