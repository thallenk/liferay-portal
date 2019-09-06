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

package com.liferay.trash.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the TrashVersion service. Represents a row in the &quot;TrashVersion&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portlet.trash.model.impl.TrashVersionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portlet.trash.model.impl.TrashVersionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrashVersion
 * @generated
 */
@ProviderType
public interface TrashVersionModel
	extends AttachedModel, BaseModel<TrashVersion>, ShardedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a trash version model instance should use the {@link TrashVersion} interface instead.
	 */

	/**
	 * Returns the primary key of this trash version.
	 *
	 * @return the primary key of this trash version
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this trash version.
	 *
	 * @param primaryKey the primary key of this trash version
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the version ID of this trash version.
	 *
	 * @return the version ID of this trash version
	 */
	public long getVersionId();

	/**
	 * Sets the version ID of this trash version.
	 *
	 * @param versionId the version ID of this trash version
	 */
	public void setVersionId(long versionId);

	/**
	 * Returns the company ID of this trash version.
	 *
	 * @return the company ID of this trash version
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this trash version.
	 *
	 * @param companyId the company ID of this trash version
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the entry ID of this trash version.
	 *
	 * @return the entry ID of this trash version
	 */
	public long getEntryId();

	/**
	 * Sets the entry ID of this trash version.
	 *
	 * @param entryId the entry ID of this trash version
	 */
	public void setEntryId(long entryId);

	/**
	 * Returns the fully qualified class name of this trash version.
	 *
	 * @return the fully qualified class name of this trash version
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this trash version.
	 *
	 * @return the class name ID of this trash version
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this trash version.
	 *
	 * @param classNameId the class name ID of this trash version
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this trash version.
	 *
	 * @return the class pk of this trash version
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this trash version.
	 *
	 * @param classPK the class pk of this trash version
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the type settings of this trash version.
	 *
	 * @return the type settings of this trash version
	 */
	@AutoEscape
	public String getTypeSettings();

	/**
	 * Sets the type settings of this trash version.
	 *
	 * @param typeSettings the type settings of this trash version
	 */
	public void setTypeSettings(String typeSettings);

	/**
	 * Returns the status of this trash version.
	 *
	 * @return the status of this trash version
	 */
	public int getStatus();

	/**
	 * Sets the status of this trash version.
	 *
	 * @param status the status of this trash version
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
	public int compareTo(TrashVersion trashVersion);

	@Override
	public int hashCode();

	@Override
	public CacheModel<TrashVersion> toCacheModel();

	@Override
	public TrashVersion toEscapedModel();

	@Override
	public TrashVersion toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}