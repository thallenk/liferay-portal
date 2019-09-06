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

package com.liferay.sync.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedAuditedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the SyncDevice service. Represents a row in the &quot;SyncDevice&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.sync.model.impl.SyncDeviceModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.sync.model.impl.SyncDeviceImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDevice
 * @generated
 */
@ProviderType
public interface SyncDeviceModel
	extends BaseModel<SyncDevice>, ShardedModel, StagedAuditedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a sync device model instance should use the {@link SyncDevice} interface instead.
	 */

	/**
	 * Returns the primary key of this sync device.
	 *
	 * @return the primary key of this sync device
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this sync device.
	 *
	 * @param primaryKey the primary key of this sync device
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this sync device.
	 *
	 * @return the uuid of this sync device
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this sync device.
	 *
	 * @param uuid the uuid of this sync device
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the sync device ID of this sync device.
	 *
	 * @return the sync device ID of this sync device
	 */
	public long getSyncDeviceId();

	/**
	 * Sets the sync device ID of this sync device.
	 *
	 * @param syncDeviceId the sync device ID of this sync device
	 */
	public void setSyncDeviceId(long syncDeviceId);

	/**
	 * Returns the company ID of this sync device.
	 *
	 * @return the company ID of this sync device
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this sync device.
	 *
	 * @param companyId the company ID of this sync device
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this sync device.
	 *
	 * @return the user ID of this sync device
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this sync device.
	 *
	 * @param userId the user ID of this sync device
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this sync device.
	 *
	 * @return the user uuid of this sync device
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this sync device.
	 *
	 * @param userUuid the user uuid of this sync device
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this sync device.
	 *
	 * @return the user name of this sync device
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this sync device.
	 *
	 * @param userName the user name of this sync device
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this sync device.
	 *
	 * @return the create date of this sync device
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this sync device.
	 *
	 * @param createDate the create date of this sync device
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this sync device.
	 *
	 * @return the modified date of this sync device
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this sync device.
	 *
	 * @param modifiedDate the modified date of this sync device
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the type of this sync device.
	 *
	 * @return the type of this sync device
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this sync device.
	 *
	 * @param type the type of this sync device
	 */
	public void setType(String type);

	/**
	 * Returns the build number of this sync device.
	 *
	 * @return the build number of this sync device
	 */
	public long getBuildNumber();

	/**
	 * Sets the build number of this sync device.
	 *
	 * @param buildNumber the build number of this sync device
	 */
	public void setBuildNumber(long buildNumber);

	/**
	 * Returns the feature set of this sync device.
	 *
	 * @return the feature set of this sync device
	 */
	public int getFeatureSet();

	/**
	 * Sets the feature set of this sync device.
	 *
	 * @param featureSet the feature set of this sync device
	 */
	public void setFeatureSet(int featureSet);

	/**
	 * Returns the hostname of this sync device.
	 *
	 * @return the hostname of this sync device
	 */
	@AutoEscape
	public String getHostname();

	/**
	 * Sets the hostname of this sync device.
	 *
	 * @param hostname the hostname of this sync device
	 */
	public void setHostname(String hostname);

	/**
	 * Returns the status of this sync device.
	 *
	 * @return the status of this sync device
	 */
	public int getStatus();

	/**
	 * Sets the status of this sync device.
	 *
	 * @param status the status of this sync device
	 */
	public void setStatus(int status);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(SyncDevice syncDevice);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SyncDevice> toCacheModel();

	@Override
	public SyncDevice toEscapedModel();

	@Override
	public SyncDevice toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}