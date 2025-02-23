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

package com.liferay.frontend.data.set.taglib.servlet.taglib;

import com.liferay.frontend.data.set.filter.FrontendDataSetFilterSerializer;
import com.liferay.frontend.data.set.model.FrontendDataSetActionDropdownItem;
import com.liferay.frontend.data.set.model.FrontendDataSetPaginationEntry;
import com.liferay.frontend.data.set.model.FrontendDataSetSortItem;
import com.liferay.frontend.data.set.model.FrontendDataSetSortItemList;
import com.liferay.frontend.data.set.taglib.internal.servlet.ServletContextUtil;
import com.liferay.frontend.data.set.view.FrontendDataSetViewSerializer;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.taglib.util.IncludeTag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class HeadlessDisplayTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		try {
			_appURL =
				PortalUtil.getPortalURL(getRequest()) +
					"/o/frontend-data-set-taglib/app";

			if (_creationMenu == null) {
				_creationMenu = new CreationMenu();
			}

			_setActiveViewSettingsJSON();
			_setFrontendDataSetViewsContext();
			_setFrontendDataSetFiltersContext();
			_setFrontendDataSetPaginationEntries();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return super.doStartTag();
	}

	public String getActionParameterName() {
		return _actionParameterName;
	}

	public String getApiURL() {
		return _apiURL;
	}

	public List<DropdownItem> getBulkActionDropdownItems() {
		return _bulkActionDropdownItems;
	}

	public CreationMenu getCreationMenu() {
		return _creationMenu;
	}

	public String getFormId() {
		return _formId;
	}

	public String getFormName() {
		return _formName;
	}

	public List<FrontendDataSetActionDropdownItem>
		getFrontendDataSetActionDropdownItems() {

		return _frontendDataSetActionDropdownItems;
	}

	public List<FrontendDataSetSortItem> getFrontendDataSetSortItemList() {
		return _frontendDataSetSortItemList;
	}

	public String getId() {
		return _id;
	}

	public int getItemsPerPage() {
		return _itemsPerPage;
	}

	public String getNamespace() {
		return _namespace;
	}

	public String getNestedItemsKey() {
		return _nestedItemsKey;
	}

	public String getNestedItemsReferenceKey() {
		return _nestedItemsReferenceKey;
	}

	public int getPageNumber() {
		return _pageNumber;
	}

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public List<String> getSelectedItems() {
		return _selectedItems;
	}

	public String getSelectedItemsKey() {
		return _selectedItemsKey;
	}

	public String getSelectionType() {
		return _selectionType;
	}

	public String getStyle() {
		return _style;
	}

	public boolean isCustomViewsEnabled() {
		return _customViewsEnabled;
	}

	public boolean isShowManagementBar() {
		return _showManagementBar;
	}

	public boolean isShowPagination() {
		return _showPagination;
	}

	public boolean isShowSearch() {
		return _showSearch;
	}

	public void setActionParameterName(String actionParameterName) {
		_actionParameterName = actionParameterName;
	}

	public void setApiURL(String apiURL) {
		_apiURL = apiURL;
	}

	public void setBulkActionDropdownItems(List<DropdownItem> bulkActions) {
		_bulkActionDropdownItems = bulkActions;
	}

	public void setCreationMenu(CreationMenu creationMenu) {
		_creationMenu = creationMenu;
	}

	public void setCustomViewsEnabled(boolean customViewsEnabled) {
		_customViewsEnabled = customViewsEnabled;
	}

	public void setFormId(String formId) {
		_formId = formId;
	}

	public void setFormName(String formName) {
		_formName = formName;
	}

	public void setFrontendDataSetActionDropdownItems(
		List<FrontendDataSetActionDropdownItem>
			frontendDataSetActionDropdownItems) {

		_frontendDataSetActionDropdownItems =
			frontendDataSetActionDropdownItems;
	}

	public void setFrontendDataSetSortItemList(
		FrontendDataSetSortItemList frontendDataSetSortItemList) {

		_frontendDataSetSortItemList = frontendDataSetSortItemList;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setItemsPerPage(int itemsPerPage) {
		_itemsPerPage = itemsPerPage;
	}

	public void setNamespace(String namespace) {
		_namespace = namespace;
	}

	public void setNestedItemsKey(String nestedItemsKey) {
		_nestedItemsKey = nestedItemsKey;
	}

	public void setNestedItemsReferenceKey(String nestedItemsReferenceKey) {
		_nestedItemsReferenceKey = nestedItemsReferenceKey;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		_frontendDataSetViewSerializer =
			ServletContextUtil.getFrontendDataSetViewSerializer();

		_frontendDataSetFilterSerializer =
			ServletContextUtil.getFrontendDataSetFilterSerializer();

		super.setPageContext(pageContext);

		setServletContext(ServletContextUtil.getServletContext());
	}

	public void setPageNumber(int pageNumber) {
		_pageNumber = pageNumber;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setSelectedItems(List<String> selectedItems) {
		_selectedItems = selectedItems;
	}

	public void setSelectedItemsKey(String selectedItemsKey) {
		_selectedItemsKey = selectedItemsKey;
	}

	public void setSelectionType(String selectionType) {
		_selectionType = selectionType;
	}

	public void setShowManagementBar(boolean showManagementBar) {
		_showManagementBar = showManagementBar;
	}

	public void setShowPagination(boolean showPagination) {
		_showPagination = showPagination;
	}

	public void setShowSearch(boolean showSearch) {
		_showSearch = showSearch;
	}

	public void setStyle(String style) {
		_style = style;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_actionParameterName = null;
		_activeViewSettingsJSON = null;
		_apiURL = null;
		_appURL = null;
		_bulkActionDropdownItems = new ArrayList<>();
		_creationMenu = new CreationMenu();
		_customViewsEnabled = false;
		_formId = null;
		_formName = null;
		_frontendDataSetActionDropdownItems = new ArrayList<>();
		_frontendDataSetFiltersContext = null;
		_frontendDataSetFilterSerializer = null;
		_frontendDataSetPaginationEntries = null;
		_frontendDataSetSortItemList = new FrontendDataSetSortItemList();
		_frontendDataSetViewsContext = null;
		_frontendDataSetViewSerializer = null;
		_id = null;
		_itemsPerPage = 0;
		_namespace = null;
		_nestedItemsKey = null;
		_nestedItemsReferenceKey = null;
		_pageNumber = 0;
		_paginationSelectedEntry = 0;
		_portletURL = null;
		_selectedItems = null;
		_selectedItemsKey = null;
		_selectionType = null;
		_showManagementBar = true;
		_showPagination = true;
		_showSearch = true;
		_style = "default";
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest httpServletRequest) {
		httpServletRequest = getRequest();

		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:actionParameterName",
			_actionParameterName);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:activeViewSettingsJSON",
			_activeViewSettingsJSON);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:apiURL", _apiURL);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:appURL", _appURL);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:bulkActionDropdownItems",
			_bulkActionDropdownItems);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:creationMenu", _creationMenu);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:customViewsEnabled",
			_customViewsEnabled);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:formId", _formId);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:formName", _formName);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:" +
				"frontendDataSetActionDropdownItems",
			_frontendDataSetActionDropdownItems);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:" +
				"frontendDataSetDisplayViewsContext",
			_frontendDataSetViewsContext);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:frontendDataSetFiltersContext",
			_frontendDataSetFiltersContext);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:" +
				"frontendDataSetPaginationEntries",
			_frontendDataSetPaginationEntries);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:frontendDataSetSortItemList",
			_frontendDataSetSortItemList);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:id", _id);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:itemsPerPage", _itemsPerPage);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:namespace", _namespace);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:nestedItemsKey",
			_nestedItemsKey);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:nestedItemsReferenceKey",
			_nestedItemsReferenceKey);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:pageNumber", _pageNumber);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:paginationSelectedEntry",
			_paginationSelectedEntry);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:portletURL", _portletURL);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:selectedItems", _selectedItems);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:selectedItemsKey",
			_selectedItemsKey);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:selectionType", _selectionType);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:showManagementBar",
			_showManagementBar);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:showPagination",
			_showPagination);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:showSearch", _showSearch);
		httpServletRequest.setAttribute(
			"frontend-data-set:headless-display:style", _style);
	}

	private List<FrontendDataSetPaginationEntry>
		_getFrontendDataSetPaginationEntries() {

		List<FrontendDataSetPaginationEntry> frontendDataSetPaginationEntries =
			new ArrayList<>();

		for (int curDelta : PropsValues.SEARCH_CONTAINER_PAGE_DELTA_VALUES) {
			if (curDelta > SearchContainer.MAX_DELTA) {
				continue;
			}

			frontendDataSetPaginationEntries.add(
				new FrontendDataSetPaginationEntry(null, curDelta));
		}

		return frontendDataSetPaginationEntries;
	}

	private void _setActiveViewSettingsJSON() {
		HttpServletRequest httpServletRequest = getRequest();

		PortalPreferences portalPreferences =
			PortletPreferencesFactoryUtil.getPortalPreferences(
				httpServletRequest);

		String frontendDataSetSettingsNamespace =
			ServletContextUtil.getFrontendDataSetSettingsNamespace(
				httpServletRequest, _id);

		_activeViewSettingsJSON = portalPreferences.getValue(
			frontendDataSetSettingsNamespace, "activeViewSettingsJSON");
	}

	private void _setFrontendDataSetFiltersContext() {
		_frontendDataSetFiltersContext =
			_frontendDataSetFilterSerializer.serialize(
				_id, PortalUtil.getLocale(getRequest()));
	}

	private void _setFrontendDataSetPaginationEntries() {
		_frontendDataSetPaginationEntries =
			_getFrontendDataSetPaginationEntries();

		Stream<FrontendDataSetPaginationEntry> stream =
			_frontendDataSetPaginationEntries.stream();

		FrontendDataSetPaginationEntry frontendDataSetPaginationEntry =
			stream.filter(
				entry -> entry.getLabel() == _itemsPerPage
			).findAny(
			).orElse(
				null
			);

		_paginationSelectedEntry = _frontendDataSetPaginationEntries.indexOf(
			frontendDataSetPaginationEntry);
	}

	private void _setFrontendDataSetViewsContext() {
		_frontendDataSetViewsContext = _frontendDataSetViewSerializer.serialize(
			_id, PortalUtil.getLocale(getRequest()));
	}

	private static final String _PAGE = "/headless_display/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		HeadlessDisplayTag.class);

	private String _actionParameterName;
	private String _activeViewSettingsJSON;
	private String _apiURL;
	private String _appURL;
	private List<DropdownItem> _bulkActionDropdownItems = new ArrayList<>();
	private CreationMenu _creationMenu = new CreationMenu();
	private boolean _customViewsEnabled;
	private String _formId;
	private String _formName;
	private List<FrontendDataSetActionDropdownItem>
		_frontendDataSetActionDropdownItems = new ArrayList<>();
	private Object _frontendDataSetFiltersContext;
	private FrontendDataSetFilterSerializer _frontendDataSetFilterSerializer;
	private List<FrontendDataSetPaginationEntry>
		_frontendDataSetPaginationEntries;
	private FrontendDataSetSortItemList _frontendDataSetSortItemList =
		new FrontendDataSetSortItemList();
	private Object _frontendDataSetViewsContext;
	private FrontendDataSetViewSerializer _frontendDataSetViewSerializer;
	private String _id;
	private int _itemsPerPage;
	private String _namespace;
	private String _nestedItemsKey;
	private String _nestedItemsReferenceKey;
	private int _pageNumber;
	private int _paginationSelectedEntry;
	private PortletURL _portletURL;
	private List<String> _selectedItems;
	private String _selectedItemsKey;
	private String _selectionType;
	private boolean _showManagementBar = true;
	private boolean _showPagination = true;
	private boolean _showSearch = true;
	private String _style = "default";

}