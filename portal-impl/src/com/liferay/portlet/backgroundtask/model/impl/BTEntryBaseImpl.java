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

package com.liferay.portlet.backgroundtask.model.impl;

import com.liferay.portal.kernel.exception.SystemException;

import com.liferay.portlet.backgroundtask.model.BTEntry;
import com.liferay.portlet.backgroundtask.service.BTEntryLocalServiceUtil;

/**
 * The extended model base implementation for the BTEntry service. Represents a row in the &quot;BTEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This class exists only as a container for the default extended model level methods generated by ServiceBuilder. Helper methods and all application logic should be put in {@link BTEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BTEntryImpl
 * @see com.liferay.portlet.backgroundtask.model.BTEntry
 * @generated
 */
public abstract class BTEntryBaseImpl extends BTEntryModelImpl
	implements BTEntry {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a b t entry model instance should use the {@link BTEntry} interface instead.
	 */
	public void persist() throws SystemException {
		if (this.isNew()) {
			BTEntryLocalServiceUtil.addBTEntry(this);
		}
		else {
			BTEntryLocalServiceUtil.updateBTEntry(this);
		}
	}
}