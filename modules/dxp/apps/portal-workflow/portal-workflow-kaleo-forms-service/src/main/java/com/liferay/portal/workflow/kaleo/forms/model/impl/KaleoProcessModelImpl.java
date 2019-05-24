/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.kaleo.forms.model.impl;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessModel;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessSoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model implementation for the KaleoProcess service. Represents a row in the &quot;KaleoProcess&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>KaleoProcessModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KaleoProcessImpl}.
 * </p>
 *
 * @author Marcellus Tavares
 * @see KaleoProcessImpl
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class KaleoProcessModelImpl
	extends BaseModelImpl<KaleoProcess> implements KaleoProcessModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a kaleo process model instance should use the <code>KaleoProcess</code> interface instead.
	 */
	public static final String TABLE_NAME = "KaleoProcess";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"kaleoProcessId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"DDLRecordSetId", Types.BIGINT}, {"DDMTemplateId", Types.BIGINT},
		{"workflowDefinitionName", Types.VARCHAR},
		{"workflowDefinitionVersion", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("kaleoProcessId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("DDLRecordSetId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("DDMTemplateId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("workflowDefinitionName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("workflowDefinitionVersion", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table KaleoProcess (uuid_ VARCHAR(75) null,kaleoProcessId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,DDLRecordSetId LONG,DDMTemplateId LONG,workflowDefinitionName VARCHAR(75) null,workflowDefinitionVersion INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table KaleoProcess";

	public static final String ORDER_BY_JPQL =
		" ORDER BY kaleoProcess.kaleoProcessId ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY KaleoProcess.kaleoProcessId ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.forms.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.forms.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.portal.workflow.kaleo.forms.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"),
		true);

	public static final long DDLRECORDSETID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long GROUPID_COLUMN_BITMASK = 4L;

	public static final long UUID_COLUMN_BITMASK = 8L;

	public static final long KALEOPROCESSID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static KaleoProcess toModel(KaleoProcessSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		KaleoProcess model = new KaleoProcessImpl();

		model.setUuid(soapModel.getUuid());
		model.setKaleoProcessId(soapModel.getKaleoProcessId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setDDLRecordSetId(soapModel.getDDLRecordSetId());
		model.setDDMTemplateId(soapModel.getDDMTemplateId());
		model.setWorkflowDefinitionName(soapModel.getWorkflowDefinitionName());
		model.setWorkflowDefinitionVersion(
			soapModel.getWorkflowDefinitionVersion());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<KaleoProcess> toModels(KaleoProcessSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<KaleoProcess> models = new ArrayList<KaleoProcess>(
			soapModels.length);

		for (KaleoProcessSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.portal.workflow.kaleo.forms.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess"));

	public KaleoProcessModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _kaleoProcessId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoProcessId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoProcessId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoProcess.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoProcess.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<KaleoProcess, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<KaleoProcess, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoProcess, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((KaleoProcess)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<KaleoProcess, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<KaleoProcess, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(KaleoProcess)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<KaleoProcess, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<KaleoProcess, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, KaleoProcess>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			KaleoProcess.class.getClassLoader(), KaleoProcess.class,
			ModelWrapper.class);

		try {
			Constructor<KaleoProcess> constructor =
				(Constructor<KaleoProcess>)proxyClass.getConstructor(
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

	private static final Map<String, Function<KaleoProcess, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<KaleoProcess, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<KaleoProcess, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<KaleoProcess, Object>>();
		Map<String, BiConsumer<KaleoProcess, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<KaleoProcess, ?>>();

		attributeGetterFunctions.put("uuid", KaleoProcess::getUuid);
		attributeSetterBiConsumers.put(
			"uuid", (BiConsumer<KaleoProcess, String>)KaleoProcess::setUuid);
		attributeGetterFunctions.put(
			"kaleoProcessId", KaleoProcess::getKaleoProcessId);
		attributeSetterBiConsumers.put(
			"kaleoProcessId",
			(BiConsumer<KaleoProcess, Long>)KaleoProcess::setKaleoProcessId);
		attributeGetterFunctions.put("groupId", KaleoProcess::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<KaleoProcess, Long>)KaleoProcess::setGroupId);
		attributeGetterFunctions.put("companyId", KaleoProcess::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<KaleoProcess, Long>)KaleoProcess::setCompanyId);
		attributeGetterFunctions.put("userId", KaleoProcess::getUserId);
		attributeSetterBiConsumers.put(
			"userId", (BiConsumer<KaleoProcess, Long>)KaleoProcess::setUserId);
		attributeGetterFunctions.put("userName", KaleoProcess::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<KaleoProcess, String>)KaleoProcess::setUserName);
		attributeGetterFunctions.put("createDate", KaleoProcess::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<KaleoProcess, Date>)KaleoProcess::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", KaleoProcess::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<KaleoProcess, Date>)KaleoProcess::setModifiedDate);
		attributeGetterFunctions.put(
			"DDLRecordSetId", KaleoProcess::getDDLRecordSetId);
		attributeSetterBiConsumers.put(
			"DDLRecordSetId",
			(BiConsumer<KaleoProcess, Long>)KaleoProcess::setDDLRecordSetId);
		attributeGetterFunctions.put(
			"DDMTemplateId", KaleoProcess::getDDMTemplateId);
		attributeSetterBiConsumers.put(
			"DDMTemplateId",
			(BiConsumer<KaleoProcess, Long>)KaleoProcess::setDDMTemplateId);
		attributeGetterFunctions.put(
			"workflowDefinitionName", KaleoProcess::getWorkflowDefinitionName);
		attributeSetterBiConsumers.put(
			"workflowDefinitionName",
			(BiConsumer<KaleoProcess, String>)
				KaleoProcess::setWorkflowDefinitionName);
		attributeGetterFunctions.put(
			"workflowDefinitionVersion",
			KaleoProcess::getWorkflowDefinitionVersion);
		attributeSetterBiConsumers.put(
			"workflowDefinitionVersion",
			(BiConsumer<KaleoProcess, Integer>)
				KaleoProcess::setWorkflowDefinitionVersion);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
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
	public long getKaleoProcessId() {
		return _kaleoProcessId;
	}

	@Override
	public void setKaleoProcessId(long kaleoProcessId) {
		_kaleoProcessId = kaleoProcessId;
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

	@JSON
	@Override
	public long getDDLRecordSetId() {
		return _DDLRecordSetId;
	}

	@Override
	public void setDDLRecordSetId(long DDLRecordSetId) {
		_columnBitmask |= DDLRECORDSETID_COLUMN_BITMASK;

		if (!_setOriginalDDLRecordSetId) {
			_setOriginalDDLRecordSetId = true;

			_originalDDLRecordSetId = _DDLRecordSetId;
		}

		_DDLRecordSetId = DDLRecordSetId;
	}

	public long getOriginalDDLRecordSetId() {
		return _originalDDLRecordSetId;
	}

	@JSON
	@Override
	public long getDDMTemplateId() {
		return _DDMTemplateId;
	}

	@Override
	public void setDDMTemplateId(long DDMTemplateId) {
		_DDMTemplateId = DDMTemplateId;
	}

	@JSON
	@Override
	public String getWorkflowDefinitionName() {
		if (_workflowDefinitionName == null) {
			return "";
		}
		else {
			return _workflowDefinitionName;
		}
	}

	@Override
	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		_workflowDefinitionName = workflowDefinitionName;
	}

	@JSON
	@Override
	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	@Override
	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion) {
		_workflowDefinitionVersion = workflowDefinitionVersion;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(KaleoProcess.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), KaleoProcess.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public KaleoProcess toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = _escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		KaleoProcessImpl kaleoProcessImpl = new KaleoProcessImpl();

		kaleoProcessImpl.setUuid(getUuid());
		kaleoProcessImpl.setKaleoProcessId(getKaleoProcessId());
		kaleoProcessImpl.setGroupId(getGroupId());
		kaleoProcessImpl.setCompanyId(getCompanyId());
		kaleoProcessImpl.setUserId(getUserId());
		kaleoProcessImpl.setUserName(getUserName());
		kaleoProcessImpl.setCreateDate(getCreateDate());
		kaleoProcessImpl.setModifiedDate(getModifiedDate());
		kaleoProcessImpl.setDDLRecordSetId(getDDLRecordSetId());
		kaleoProcessImpl.setDDMTemplateId(getDDMTemplateId());
		kaleoProcessImpl.setWorkflowDefinitionName(getWorkflowDefinitionName());
		kaleoProcessImpl.setWorkflowDefinitionVersion(
			getWorkflowDefinitionVersion());

		kaleoProcessImpl.resetOriginalValues();

		return kaleoProcessImpl;
	}

	@Override
	public int compareTo(KaleoProcess kaleoProcess) {
		long primaryKey = kaleoProcess.getPrimaryKey();

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

		if (!(obj instanceof KaleoProcess)) {
			return false;
		}

		KaleoProcess kaleoProcess = (KaleoProcess)obj;

		long primaryKey = kaleoProcess.getPrimaryKey();

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
		KaleoProcessModelImpl kaleoProcessModelImpl = this;

		kaleoProcessModelImpl._originalUuid = kaleoProcessModelImpl._uuid;

		kaleoProcessModelImpl._originalGroupId = kaleoProcessModelImpl._groupId;

		kaleoProcessModelImpl._setOriginalGroupId = false;

		kaleoProcessModelImpl._originalCompanyId =
			kaleoProcessModelImpl._companyId;

		kaleoProcessModelImpl._setOriginalCompanyId = false;

		kaleoProcessModelImpl._setModifiedDate = false;

		kaleoProcessModelImpl._originalDDLRecordSetId =
			kaleoProcessModelImpl._DDLRecordSetId;

		kaleoProcessModelImpl._setOriginalDDLRecordSetId = false;

		kaleoProcessModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<KaleoProcess> toCacheModel() {
		KaleoProcessCacheModel kaleoProcessCacheModel =
			new KaleoProcessCacheModel();

		kaleoProcessCacheModel.uuid = getUuid();

		String uuid = kaleoProcessCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			kaleoProcessCacheModel.uuid = null;
		}

		kaleoProcessCacheModel.kaleoProcessId = getKaleoProcessId();

		kaleoProcessCacheModel.groupId = getGroupId();

		kaleoProcessCacheModel.companyId = getCompanyId();

		kaleoProcessCacheModel.userId = getUserId();

		kaleoProcessCacheModel.userName = getUserName();

		String userName = kaleoProcessCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kaleoProcessCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kaleoProcessCacheModel.createDate = createDate.getTime();
		}
		else {
			kaleoProcessCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kaleoProcessCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kaleoProcessCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kaleoProcessCacheModel.DDLRecordSetId = getDDLRecordSetId();

		kaleoProcessCacheModel.DDMTemplateId = getDDMTemplateId();

		kaleoProcessCacheModel.workflowDefinitionName =
			getWorkflowDefinitionName();

		String workflowDefinitionName =
			kaleoProcessCacheModel.workflowDefinitionName;

		if ((workflowDefinitionName != null) &&
			(workflowDefinitionName.length() == 0)) {

			kaleoProcessCacheModel.workflowDefinitionName = null;
		}

		kaleoProcessCacheModel.workflowDefinitionVersion =
			getWorkflowDefinitionVersion();

		return kaleoProcessCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<KaleoProcess, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<KaleoProcess, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoProcess, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((KaleoProcess)this));
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
		Map<String, Function<KaleoProcess, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<KaleoProcess, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<KaleoProcess, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((KaleoProcess)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static final Function<InvocationHandler, KaleoProcess>
		_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	private String _uuid;
	private String _originalUuid;
	private long _kaleoProcessId;
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
	private long _DDLRecordSetId;
	private long _originalDDLRecordSetId;
	private boolean _setOriginalDDLRecordSetId;
	private long _DDMTemplateId;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private long _columnBitmask;
	private KaleoProcess _escapedModel;

}