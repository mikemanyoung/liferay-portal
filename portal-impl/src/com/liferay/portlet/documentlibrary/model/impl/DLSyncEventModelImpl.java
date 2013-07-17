/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.documentlibrary.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.documentlibrary.model.DLSyncEvent;
import com.liferay.portlet.documentlibrary.model.DLSyncEventModel;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the DLSyncEvent service. Represents a row in the &quot;DLSyncEvent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portlet.documentlibrary.model.DLSyncEventModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link DLSyncEventImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLSyncEventImpl
 * @see com.liferay.portlet.documentlibrary.model.DLSyncEvent
 * @see com.liferay.portlet.documentlibrary.model.DLSyncEventModel
 * @generated
 */
public class DLSyncEventModelImpl extends BaseModelImpl<DLSyncEvent>
	implements DLSyncEventModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a d l sync event model instance should use the {@link com.liferay.portlet.documentlibrary.model.DLSyncEvent} interface instead.
	 */
	public static final String TABLE_NAME = "DLSyncEvent";
	public static final Object[][] TABLE_COLUMNS = {
			{ "syncEventId", Types.BIGINT },
			{ "modifiedDate", Types.BIGINT },
			{ "typeId", Types.BIGINT },
			{ "type_", Types.VARCHAR },
			{ "event", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table DLSyncEvent (syncEventId LONG not null primary key,modifiedDate LONG,typeId LONG,type_ VARCHAR(75) null,event VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table DLSyncEvent";
	public static final String ORDER_BY_JPQL = " ORDER BY dlSyncEvent.modifiedDate ASC";
	public static final String ORDER_BY_SQL = " ORDER BY DLSyncEvent.modifiedDate ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portlet.documentlibrary.model.DLSyncEvent"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portlet.documentlibrary.model.DLSyncEvent"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.column.bitmask.enabled.com.liferay.portlet.documentlibrary.model.DLSyncEvent"),
			true);
	public static long MODIFIEDDATE_COLUMN_BITMASK = 1L;
	public static long TYPEID_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portlet.documentlibrary.model.DLSyncEvent"));

	public DLSyncEventModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _syncEventId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSyncEventId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncEventId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return DLSyncEvent.class;
	}

	@Override
	public String getModelClassName() {
		return DLSyncEvent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("syncEventId", getSyncEventId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("typeId", getTypeId());
		attributes.put("type", getType());
		attributes.put("event", getEvent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long syncEventId = (Long)attributes.get("syncEventId");

		if (syncEventId != null) {
			setSyncEventId(syncEventId);
		}

		Long modifiedDate = (Long)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long typeId = (Long)attributes.get("typeId");

		if (typeId != null) {
			setTypeId(typeId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String event = (String)attributes.get("event");

		if (event != null) {
			setEvent(event);
		}
	}

	@Override
	public long getSyncEventId() {
		return _syncEventId;
	}

	@Override
	public void setSyncEventId(long syncEventId) {
		_syncEventId = syncEventId;
	}

	@Override
	public long getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(long modifiedDate) {
		_columnBitmask = -1L;

		if (!_setOriginalModifiedDate) {
			_setOriginalModifiedDate = true;

			_originalModifiedDate = _modifiedDate;
		}

		_modifiedDate = modifiedDate;
	}

	public long getOriginalModifiedDate() {
		return _originalModifiedDate;
	}

	@Override
	public long getTypeId() {
		return _typeId;
	}

	@Override
	public void setTypeId(long typeId) {
		_columnBitmask |= TYPEID_COLUMN_BITMASK;

		if (!_setOriginalTypeId) {
			_setOriginalTypeId = true;

			_originalTypeId = _typeId;
		}

		_typeId = typeId;
	}

	public long getOriginalTypeId() {
		return _originalTypeId;
	}

	@Override
	public String getType() {
		if (_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@Override
	public String getEvent() {
		if (_event == null) {
			return StringPool.BLANK;
		}
		else {
			return _event;
		}
	}

	@Override
	public void setEvent(String event) {
		_event = event;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			DLSyncEvent.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public DLSyncEvent toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (DLSyncEvent)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		DLSyncEventImpl dlSyncEventImpl = new DLSyncEventImpl();

		dlSyncEventImpl.setSyncEventId(getSyncEventId());
		dlSyncEventImpl.setModifiedDate(getModifiedDate());
		dlSyncEventImpl.setTypeId(getTypeId());
		dlSyncEventImpl.setType(getType());
		dlSyncEventImpl.setEvent(getEvent());

		dlSyncEventImpl.resetOriginalValues();

		return dlSyncEventImpl;
	}

	@Override
	public int compareTo(DLSyncEvent dlSyncEvent) {
		int value = 0;

		if (getModifiedDate() < dlSyncEvent.getModifiedDate()) {
			value = -1;
		}
		else if (getModifiedDate() > dlSyncEvent.getModifiedDate()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DLSyncEvent)) {
			return false;
		}

		DLSyncEvent dlSyncEvent = (DLSyncEvent)obj;

		long primaryKey = dlSyncEvent.getPrimaryKey();

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
	public void resetOriginalValues() {
		DLSyncEventModelImpl dlSyncEventModelImpl = this;

		dlSyncEventModelImpl._originalModifiedDate = dlSyncEventModelImpl._modifiedDate;

		dlSyncEventModelImpl._setOriginalModifiedDate = false;

		dlSyncEventModelImpl._originalTypeId = dlSyncEventModelImpl._typeId;

		dlSyncEventModelImpl._setOriginalTypeId = false;

		dlSyncEventModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<DLSyncEvent> toCacheModel() {
		DLSyncEventCacheModel dlSyncEventCacheModel = new DLSyncEventCacheModel();

		dlSyncEventCacheModel.syncEventId = getSyncEventId();

		dlSyncEventCacheModel.modifiedDate = getModifiedDate();

		dlSyncEventCacheModel.typeId = getTypeId();

		dlSyncEventCacheModel.type = getType();

		String type = dlSyncEventCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			dlSyncEventCacheModel.type = null;
		}

		dlSyncEventCacheModel.event = getEvent();

		String event = dlSyncEventCacheModel.event;

		if ((event != null) && (event.length() == 0)) {
			dlSyncEventCacheModel.event = null;
		}

		return dlSyncEventCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{syncEventId=");
		sb.append(getSyncEventId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", typeId=");
		sb.append(getTypeId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", event=");
		sb.append(getEvent());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portlet.documentlibrary.model.DLSyncEvent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>syncEventId</column-name><column-value><![CDATA[");
		sb.append(getSyncEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeId</column-name><column-value><![CDATA[");
		sb.append(getTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>event</column-name><column-value><![CDATA[");
		sb.append(getEvent());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = DLSyncEvent.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			DLSyncEvent.class
		};
	private long _syncEventId;
	private long _modifiedDate;
	private long _originalModifiedDate;
	private boolean _setOriginalModifiedDate;
	private long _typeId;
	private long _originalTypeId;
	private boolean _setOriginalTypeId;
	private String _type;
	private String _event;
	private long _columnBitmask;
	private DLSyncEvent _escapedModel;
}