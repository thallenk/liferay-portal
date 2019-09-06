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

package com.liferay.wsrp.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the WSRPConsumer service. Represents a row in the &quot;WSRP_WSRPConsumer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.wsrp.model.impl.WSRPConsumerModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.wsrp.model.impl.WSRPConsumerImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumer
 * @generated
 */
@ProviderType
public interface WSRPConsumerModel
	extends BaseModel<WSRPConsumer>, ShardedModel, StagedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a wsrp consumer model instance should use the {@link WSRPConsumer} interface instead.
	 */

	/**
	 * Returns the primary key of this wsrp consumer.
	 *
	 * @return the primary key of this wsrp consumer
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this wsrp consumer.
	 *
	 * @param primaryKey the primary key of this wsrp consumer
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the uuid of this wsrp consumer.
	 *
	 * @return the uuid of this wsrp consumer
	 */
	@AutoEscape
	@Override
	public String getUuid();

	/**
	 * Sets the uuid of this wsrp consumer.
	 *
	 * @param uuid the uuid of this wsrp consumer
	 */
	@Override
	public void setUuid(String uuid);

	/**
	 * Returns the wsrp consumer ID of this wsrp consumer.
	 *
	 * @return the wsrp consumer ID of this wsrp consumer
	 */
	public long getWsrpConsumerId();

	/**
	 * Sets the wsrp consumer ID of this wsrp consumer.
	 *
	 * @param wsrpConsumerId the wsrp consumer ID of this wsrp consumer
	 */
	public void setWsrpConsumerId(long wsrpConsumerId);

	/**
	 * Returns the company ID of this wsrp consumer.
	 *
	 * @return the company ID of this wsrp consumer
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this wsrp consumer.
	 *
	 * @param companyId the company ID of this wsrp consumer
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the create date of this wsrp consumer.
	 *
	 * @return the create date of this wsrp consumer
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this wsrp consumer.
	 *
	 * @param createDate the create date of this wsrp consumer
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this wsrp consumer.
	 *
	 * @return the modified date of this wsrp consumer
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this wsrp consumer.
	 *
	 * @param modifiedDate the modified date of this wsrp consumer
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this wsrp consumer.
	 *
	 * @return the name of this wsrp consumer
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this wsrp consumer.
	 *
	 * @param name the name of this wsrp consumer
	 */
	public void setName(String name);

	/**
	 * Returns the url of this wsrp consumer.
	 *
	 * @return the url of this wsrp consumer
	 */
	@AutoEscape
	public String getUrl();

	/**
	 * Sets the url of this wsrp consumer.
	 *
	 * @param url the url of this wsrp consumer
	 */
	public void setUrl(String url);

	/**
	 * Returns the wsdl of this wsrp consumer.
	 *
	 * @return the wsdl of this wsrp consumer
	 */
	@AutoEscape
	public String getWsdl();

	/**
	 * Sets the wsdl of this wsrp consumer.
	 *
	 * @param wsdl the wsdl of this wsrp consumer
	 */
	public void setWsdl(String wsdl);

	/**
	 * Returns the registration context string of this wsrp consumer.
	 *
	 * @return the registration context string of this wsrp consumer
	 */
	@AutoEscape
	public String getRegistrationContextString();

	/**
	 * Sets the registration context string of this wsrp consumer.
	 *
	 * @param registrationContextString the registration context string of this wsrp consumer
	 */
	public void setRegistrationContextString(String registrationContextString);

	/**
	 * Returns the registration properties string of this wsrp consumer.
	 *
	 * @return the registration properties string of this wsrp consumer
	 */
	@AutoEscape
	public String getRegistrationPropertiesString();

	/**
	 * Sets the registration properties string of this wsrp consumer.
	 *
	 * @param registrationPropertiesString the registration properties string of this wsrp consumer
	 */
	public void setRegistrationPropertiesString(
		String registrationPropertiesString);

	/**
	 * Returns the forward cookies of this wsrp consumer.
	 *
	 * @return the forward cookies of this wsrp consumer
	 */
	@AutoEscape
	public String getForwardCookies();

	/**
	 * Sets the forward cookies of this wsrp consumer.
	 *
	 * @param forwardCookies the forward cookies of this wsrp consumer
	 */
	public void setForwardCookies(String forwardCookies);

	/**
	 * Returns the forward headers of this wsrp consumer.
	 *
	 * @return the forward headers of this wsrp consumer
	 */
	@AutoEscape
	public String getForwardHeaders();

	/**
	 * Sets the forward headers of this wsrp consumer.
	 *
	 * @param forwardHeaders the forward headers of this wsrp consumer
	 */
	public void setForwardHeaders(String forwardHeaders);

	/**
	 * Returns the markup character sets of this wsrp consumer.
	 *
	 * @return the markup character sets of this wsrp consumer
	 */
	@AutoEscape
	public String getMarkupCharacterSets();

	/**
	 * Sets the markup character sets of this wsrp consumer.
	 *
	 * @param markupCharacterSets the markup character sets of this wsrp consumer
	 */
	public void setMarkupCharacterSets(String markupCharacterSets);

	/**
	 * Returns the last publish date of this wsrp consumer.
	 *
	 * @return the last publish date of this wsrp consumer
	 */
	public Date getLastPublishDate();

	/**
	 * Sets the last publish date of this wsrp consumer.
	 *
	 * @param lastPublishDate the last publish date of this wsrp consumer
	 */
	public void setLastPublishDate(Date lastPublishDate);

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
	public int compareTo(WSRPConsumer wsrpConsumer);

	@Override
	public int hashCode();

	@Override
	public CacheModel<WSRPConsumer> toCacheModel();

	@Override
	public WSRPConsumer toEscapedModel();

	@Override
	public WSRPConsumer toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();

}