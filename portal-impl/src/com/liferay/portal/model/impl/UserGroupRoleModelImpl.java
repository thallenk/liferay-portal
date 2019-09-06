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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.UserGroupRoleModel;
import com.liferay.portal.kernel.model.UserGroupRoleSoap;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.UserGroupRolePK;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the UserGroupRole service. Represents a row in the &quot;UserGroupRole&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>UserGroupRoleModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link UserGroupRoleImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserGroupRoleImpl
 * @generated
 */
@JSON(strict = true)
public class UserGroupRoleModelImpl
	extends BaseModelImpl<UserGroupRole> implements UserGroupRoleModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a user group role model instance should use the <code>UserGroupRole</code> interface instead.
	 */
	public static final String TABLE_NAME = "UserGroupRole";

	public static final Object[][] TABLE_COLUMNS = {
		{"mvccVersion", Types.BIGINT}, {"userId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"roleId", Types.BIGINT},
		{"companyId", Types.BIGINT}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("mvccVersion", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("roleId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
	}

	public static final String TABLE_SQL_CREATE =
		"create table UserGroupRole (mvccVersion LONG default 0 not null,userId LONG not null,groupId LONG not null,roleId LONG not null,companyId LONG,primary key (userId, groupId, roleId))";

	public static final String TABLE_SQL_DROP = "drop table UserGroupRole";

	public static final String ORDER_BY_JPQL =
		" ORDER BY userGroupRole.id.userId ASC, userGroupRole.id.groupId ASC, userGroupRole.id.roleId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY UserGroupRole.userId ASC, UserGroupRole.groupId ASC, UserGroupRole.roleId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.entity.cache.enabled.com.liferay.portal.kernel.model.UserGroupRole"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.finder.cache.enabled.com.liferay.portal.kernel.model.UserGroupRole"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.util.PropsUtil.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.UserGroupRole"),
		true);

	public static final long GROUPID_COLUMN_BITMASK = 1L;

	public static final long ROLEID_COLUMN_BITMASK = 2L;

	public static final long USERID_COLUMN_BITMASK = 4L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static UserGroupRole toModel(UserGroupRoleSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		UserGroupRole model = new UserGroupRoleImpl();

		model.setMvccVersion(soapModel.getMvccVersion());
		model.setUserId(soapModel.getUserId());
		model.setGroupId(soapModel.getGroupId());
		model.setRoleId(soapModel.getRoleId());
		model.setCompanyId(soapModel.getCompanyId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<UserGroupRole> toModels(UserGroupRoleSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<UserGroupRole> models = new ArrayList<UserGroupRole>(
			soapModels.length);

		for (UserGroupRoleSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.util.PropsUtil.get(
			"lock.expiration.time.com.liferay.portal.kernel.model.UserGroupRole"));

	public UserGroupRoleModelImpl() {
	}

	@Override
	public UserGroupRolePK getPrimaryKey() {
		return new UserGroupRolePK(_userId, _groupId, _roleId);
	}

	@Override
	public void setPrimaryKey(UserGroupRolePK primaryKey) {
		setUserId(primaryKey.userId);
		setGroupId(primaryKey.groupId);
		setRoleId(primaryKey.roleId);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return new UserGroupRolePK(_userId, _groupId, _roleId);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((UserGroupRolePK)primaryKeyObj);
	}

	@Override
	public Class<?> getModelClass() {
		return UserGroupRole.class;
	}

	@Override
	public String getModelClassName() {
		return UserGroupRole.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<UserGroupRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<UserGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((UserGroupRole)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<UserGroupRole, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<UserGroupRole, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(UserGroupRole)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<UserGroupRole, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<UserGroupRole, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, UserGroupRole>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			UserGroupRole.class.getClassLoader(), UserGroupRole.class,
			ModelWrapper.class);

		try {
			Constructor<UserGroupRole> constructor =
				(Constructor<UserGroupRole>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<UserGroupRole, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<UserGroupRole, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<UserGroupRole, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<UserGroupRole, Object>>();
		Map<String, BiConsumer<UserGroupRole, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<UserGroupRole, ?>>();

		attributeGetterFunctions.put(
			"mvccVersion",
			new Function<UserGroupRole, Object>() {

				@Override
				public Object apply(UserGroupRole userGroupRole) {
					return userGroupRole.getMvccVersion();
				}

			});
		attributeSetterBiConsumers.put(
			"mvccVersion",
			new BiConsumer<UserGroupRole, Object>() {

				@Override
				public void accept(
					UserGroupRole userGroupRole, Object mvccVersion) {

					userGroupRole.setMvccVersion((Long)mvccVersion);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<UserGroupRole, Object>() {

				@Override
				public Object apply(UserGroupRole userGroupRole) {
					return userGroupRole.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<UserGroupRole, Object>() {

				@Override
				public void accept(UserGroupRole userGroupRole, Object userId) {
					userGroupRole.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"groupId",
			new Function<UserGroupRole, Object>() {

				@Override
				public Object apply(UserGroupRole userGroupRole) {
					return userGroupRole.getGroupId();
				}

			});
		attributeSetterBiConsumers.put(
			"groupId",
			new BiConsumer<UserGroupRole, Object>() {

				@Override
				public void accept(
					UserGroupRole userGroupRole, Object groupId) {

					userGroupRole.setGroupId((Long)groupId);
				}

			});
		attributeGetterFunctions.put(
			"roleId",
			new Function<UserGroupRole, Object>() {

				@Override
				public Object apply(UserGroupRole userGroupRole) {
					return userGroupRole.getRoleId();
				}

			});
		attributeSetterBiConsumers.put(
			"roleId",
			new BiConsumer<UserGroupRole, Object>() {

				@Override
				public void accept(UserGroupRole userGroupRole, Object roleId) {
					userGroupRole.setRoleId((Long)roleId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<UserGroupRole, Object>() {

				@Override
				public Object apply(UserGroupRole userGroupRole) {
					return userGroupRole.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<UserGroupRole, Object>() {

				@Override
				public void accept(
					UserGroupRole userGroupRole, Object companyId) {

					userGroupRole.setCompanyId((Long)companyId);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

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

	public long getOriginalUserId() {
		return _originalUserId;
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
	public long getRoleId() {
		return _roleId;
	}

	@Override
	public void setRoleId(long roleId) {
		_columnBitmask |= ROLEID_COLUMN_BITMASK;

		if (!_setOriginalRoleId) {
			_setOriginalRoleId = true;

			_originalRoleId = _roleId;
		}

		_roleId = roleId;
	}

	public long getOriginalRoleId() {
		return _originalRoleId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public UserGroupRole toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, UserGroupRole>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		UserGroupRoleImpl userGroupRoleImpl = new UserGroupRoleImpl();

		userGroupRoleImpl.setMvccVersion(getMvccVersion());
		userGroupRoleImpl.setUserId(getUserId());
		userGroupRoleImpl.setGroupId(getGroupId());
		userGroupRoleImpl.setRoleId(getRoleId());
		userGroupRoleImpl.setCompanyId(getCompanyId());

		userGroupRoleImpl.resetOriginalValues();

		return userGroupRoleImpl;
	}

	@Override
	public int compareTo(UserGroupRole userGroupRole) {
		UserGroupRolePK primaryKey = userGroupRole.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserGroupRole)) {
			return false;
		}

		UserGroupRole userGroupRole = (UserGroupRole)obj;

		UserGroupRolePK primaryKey = userGroupRole.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
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
		UserGroupRoleModelImpl userGroupRoleModelImpl = this;

		userGroupRoleModelImpl._originalUserId = userGroupRoleModelImpl._userId;

		userGroupRoleModelImpl._setOriginalUserId = false;

		userGroupRoleModelImpl._originalGroupId =
			userGroupRoleModelImpl._groupId;

		userGroupRoleModelImpl._setOriginalGroupId = false;

		userGroupRoleModelImpl._originalRoleId = userGroupRoleModelImpl._roleId;

		userGroupRoleModelImpl._setOriginalRoleId = false;

		userGroupRoleModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<UserGroupRole> toCacheModel() {
		UserGroupRoleCacheModel userGroupRoleCacheModel =
			new UserGroupRoleCacheModel();

		userGroupRoleCacheModel.userGroupRolePK = getPrimaryKey();

		userGroupRoleCacheModel.mvccVersion = getMvccVersion();

		userGroupRoleCacheModel.userId = getUserId();

		userGroupRoleCacheModel.groupId = getGroupId();

		userGroupRoleCacheModel.roleId = getRoleId();

		userGroupRoleCacheModel.companyId = getCompanyId();

		return userGroupRoleCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<UserGroupRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<UserGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((UserGroupRole)this));
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
		Map<String, Function<UserGroupRole, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<UserGroupRole, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<UserGroupRole, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((UserGroupRole)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, UserGroupRole>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private long _mvccVersion;
	private long _userId;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _roleId;
	private long _originalRoleId;
	private boolean _setOriginalRoleId;
	private long _companyId;
	private long _columnBitmask;
	private UserGroupRole _escapedModel;

}