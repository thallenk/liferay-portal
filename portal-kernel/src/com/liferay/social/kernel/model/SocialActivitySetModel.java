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

package com.liferay.social.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.AttachedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the SocialActivitySet service. Represents a row in the &quot;SocialActivitySet&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portlet.social.model.impl.SocialActivitySetModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portlet.social.model.impl.SocialActivitySetImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySet
 * @generated
 */
@ProviderType
public interface SocialActivitySetModel
	extends AttachedModel, BaseModel<SocialActivitySet>, ShardedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a social activity set model instance should use the {@link SocialActivitySet} interface instead.
	 */

	/**
	 * Returns the primary key of this social activity set.
	 *
	 * @return the primary key of this social activity set
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this social activity set.
	 *
	 * @param primaryKey the primary key of this social activity set
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the activity set ID of this social activity set.
	 *
	 * @return the activity set ID of this social activity set
	 */
	public long getActivitySetId();

	/**
	 * Sets the activity set ID of this social activity set.
	 *
	 * @param activitySetId the activity set ID of this social activity set
	 */
	public void setActivitySetId(long activitySetId);

	/**
	 * Returns the group ID of this social activity set.
	 *
	 * @return the group ID of this social activity set
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this social activity set.
	 *
	 * @param groupId the group ID of this social activity set
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this social activity set.
	 *
	 * @return the company ID of this social activity set
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this social activity set.
	 *
	 * @param companyId the company ID of this social activity set
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this social activity set.
	 *
	 * @return the user ID of this social activity set
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this social activity set.
	 *
	 * @param userId the user ID of this social activity set
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this social activity set.
	 *
	 * @return the user uuid of this social activity set
	 */
	public String getUserUuid();

	/**
	 * Sets the user uuid of this social activity set.
	 *
	 * @param userUuid the user uuid of this social activity set
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the create date of this social activity set.
	 *
	 * @return the create date of this social activity set
	 */
	public long getCreateDate();

	/**
	 * Sets the create date of this social activity set.
	 *
	 * @param createDate the create date of this social activity set
	 */
	public void setCreateDate(long createDate);

	/**
	 * Returns the modified date of this social activity set.
	 *
	 * @return the modified date of this social activity set
	 */
	public long getModifiedDate();

	/**
	 * Sets the modified date of this social activity set.
	 *
	 * @param modifiedDate the modified date of this social activity set
	 */
	public void setModifiedDate(long modifiedDate);

	/**
	 * Returns the fully qualified class name of this social activity set.
	 *
	 * @return the fully qualified class name of this social activity set
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this social activity set.
	 *
	 * @return the class name ID of this social activity set
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this social activity set.
	 *
	 * @param classNameId the class name ID of this social activity set
	 */
	@Override
	public void setClassNameId(long classNameId);

	/**
	 * Returns the class pk of this social activity set.
	 *
	 * @return the class pk of this social activity set
	 */
	@Override
	public long getClassPK();

	/**
	 * Sets the class pk of this social activity set.
	 *
	 * @param classPK the class pk of this social activity set
	 */
	@Override
	public void setClassPK(long classPK);

	/**
	 * Returns the type of this social activity set.
	 *
	 * @return the type of this social activity set
	 */
	public int getType();

	/**
	 * Sets the type of this social activity set.
	 *
	 * @param type the type of this social activity set
	 */
	public void setType(int type);

	/**
	 * Returns the extra data of this social activity set.
	 *
	 * @return the extra data of this social activity set
	 */
	public String getExtraData();

	/**
	 * Sets the extra data of this social activity set.
	 *
	 * @param extraData the extra data of this social activity set
	 */
	public void setExtraData(String extraData);

	/**
	 * Returns the activity count of this social activity set.
	 *
	 * @return the activity count of this social activity set
	 */
	public int getActivityCount();

	/**
	 * Sets the activity count of this social activity set.
	 *
	 * @param activityCount the activity count of this social activity set
	 */
	public void setActivityCount(int activityCount);

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
	public int compareTo(SocialActivitySet socialActivitySet);

	@Override
	public int hashCode();

	@Override
	public CacheModel<SocialActivitySet> toCacheModel();

	@Override
	public SocialActivitySet toEscapedModel();

	@Override
	public SocialActivitySet toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}