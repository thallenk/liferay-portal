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

package com.liferay.commerce.product.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the CommerceProductDefinitionLocalization service. Represents a row in the &quot;CommerceProductDefinitionLocalization&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.product.model.impl.CommerceProductDefinitionLocalizationModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.product.model.impl.CommerceProductDefinitionLocalizationImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CommerceProductDefinitionLocalization
 * @see com.liferay.commerce.product.model.impl.CommerceProductDefinitionLocalizationImpl
 * @see com.liferay.commerce.product.model.impl.CommerceProductDefinitionLocalizationModelImpl
 * @generated
 */
@ProviderType
public interface CommerceProductDefinitionLocalizationModel extends BaseModel<CommerceProductDefinitionLocalization>,
	MVCCModel, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce product definition localization model instance should use the {@link CommerceProductDefinitionLocalization} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce product definition localization.
	 *
	 * @return the primary key of this commerce product definition localization
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce product definition localization.
	 *
	 * @param primaryKey the primary key of this commerce product definition localization
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this commerce product definition localization.
	 *
	 * @return the mvcc version of this commerce product definition localization
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this commerce product definition localization.
	 *
	 * @param mvccVersion the mvcc version of this commerce product definition localization
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the commerce product definition localization ID of this commerce product definition localization.
	 *
	 * @return the commerce product definition localization ID of this commerce product definition localization
	 */
	public long getCommerceProductDefinitionLocalizationId();

	/**
	 * Sets the commerce product definition localization ID of this commerce product definition localization.
	 *
	 * @param commerceProductDefinitionLocalizationId the commerce product definition localization ID of this commerce product definition localization
	 */
	public void setCommerceProductDefinitionLocalizationId(
		long commerceProductDefinitionLocalizationId);

	/**
	 * Returns the company ID of this commerce product definition localization.
	 *
	 * @return the company ID of this commerce product definition localization
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce product definition localization.
	 *
	 * @param companyId the company ID of this commerce product definition localization
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the commerce product definition pk of this commerce product definition localization.
	 *
	 * @return the commerce product definition pk of this commerce product definition localization
	 */
	public long getCommerceProductDefinitionPK();

	/**
	 * Sets the commerce product definition pk of this commerce product definition localization.
	 *
	 * @param commerceProductDefinitionPK the commerce product definition pk of this commerce product definition localization
	 */
	public void setCommerceProductDefinitionPK(long commerceProductDefinitionPK);

	/**
	 * Returns the language ID of this commerce product definition localization.
	 *
	 * @return the language ID of this commerce product definition localization
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this commerce product definition localization.
	 *
	 * @param languageId the language ID of this commerce product definition localization
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the title of this commerce product definition localization.
	 *
	 * @return the title of this commerce product definition localization
	 */
	@AutoEscape
	public String getTitle();

	/**
	 * Sets the title of this commerce product definition localization.
	 *
	 * @param title the title of this commerce product definition localization
	 */
	public void setTitle(String title);

	/**
	 * Returns the url title of this commerce product definition localization.
	 *
	 * @return the url title of this commerce product definition localization
	 */
	@AutoEscape
	public String getUrlTitle();

	/**
	 * Sets the url title of this commerce product definition localization.
	 *
	 * @param urlTitle the url title of this commerce product definition localization
	 */
	public void setUrlTitle(String urlTitle);

	/**
	 * Returns the description of this commerce product definition localization.
	 *
	 * @return the description of this commerce product definition localization
	 */
	@AutoEscape
	public String getDescription();

	/**
	 * Sets the description of this commerce product definition localization.
	 *
	 * @param description the description of this commerce product definition localization
	 */
	public void setDescription(String description);

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
	public int compareTo(
		CommerceProductDefinitionLocalization commerceProductDefinitionLocalization);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceProductDefinitionLocalization> toCacheModel();

	@Override
	public CommerceProductDefinitionLocalization toEscapedModel();

	@Override
	public CommerceProductDefinitionLocalization toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}