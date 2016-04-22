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

package com.liferay.chat.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the Entry service. Represents a row in the &quot;Chat_Entry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.chat.model.impl.EntryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.chat.model.impl.EntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Entry
 * @see com.liferay.chat.model.impl.EntryImpl
 * @see com.liferay.chat.model.impl.EntryModelImpl
 * @generated
 */
@ProviderType
public interface EntryModel extends BaseModel<Entry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a entry model instance should use the {@link Entry} interface instead.
	 */

	/**
	 * Returns the primary key of this entry.
	 *
	 * @return the primary key of this entry
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this entry.
	 *
	 * @param primaryKey the primary key of this entry
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the entry ID of this entry.
	 *
	 * @return the entry ID of this entry
	 */
	public long getEntryId();

	/**
	 * Sets the entry ID of this entry.
	 *
	 * @param entryId the entry ID of this entry
	 */
	public void setEntryId(long entryId);

	/**
	 * Returns the create date of this entry.
	 *
	 * @return the create date of this entry
	 */
	public long getCreateDate();

	/**
	 * Sets the create date of this entry.
	 *
	 * @param createDate the create date of this entry
	 */
	public void setCreateDate(long createDate);

	/**
	 * Returns the from user ID of this entry.
	 *
	 * @return the from user ID of this entry
	 */
	public long getFromUserId();

	/**
	 * Sets the from user ID of this entry.
	 *
	 * @param fromUserId the from user ID of this entry
	 */
	public void setFromUserId(long fromUserId);

	/**
	 * Returns the from user uuid of this entry.
	 *
	 * @return the from user uuid of this entry
	 */
	public String getFromUserUuid();

	/**
	 * Sets the from user uuid of this entry.
	 *
	 * @param fromUserUuid the from user uuid of this entry
	 */
	public void setFromUserUuid(String fromUserUuid);

	/**
	 * Returns the to user ID of this entry.
	 *
	 * @return the to user ID of this entry
	 */
	public long getToUserId();

	/**
	 * Sets the to user ID of this entry.
	 *
	 * @param toUserId the to user ID of this entry
	 */
	public void setToUserId(long toUserId);

	/**
	 * Returns the to user uuid of this entry.
	 *
	 * @return the to user uuid of this entry
	 */
	public String getToUserUuid();

	/**
	 * Sets the to user uuid of this entry.
	 *
	 * @param toUserUuid the to user uuid of this entry
	 */
	public void setToUserUuid(String toUserUuid);

	/**
	 * Returns the content of this entry.
	 *
	 * @return the content of this entry
	 */
	@AutoEscape
	public String getContent();

	/**
	 * Sets the content of this entry.
	 *
	 * @param content the content of this entry
	 */
	public void setContent(String content);

	/**
	 * Returns the flag of this entry.
	 *
	 * @return the flag of this entry
	 */
	public int getFlag();

	/**
	 * Sets the flag of this entry.
	 *
	 * @param flag the flag of this entry
	 */
	public void setFlag(int flag);

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
	public int compareTo(com.liferay.chat.model.Entry entry);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.chat.model.Entry> toCacheModel();

	@Override
	public com.liferay.chat.model.Entry toEscapedModel();

	@Override
	public com.liferay.chat.model.Entry toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}