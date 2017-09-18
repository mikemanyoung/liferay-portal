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

package com.liferay.sync.internal.model.listener;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.sync.model.SyncDLObject;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Sherly Liu
 */
@Component(immediate = true, service = ModelListener.class)
public class UserGroupRoleModelListener
	extends SyncBaseModelListener<UserGroupRole> {

	@Override
	public void onAfterCreate(UserGroupRole userGroupRole)
		throws ModelListenerException {

		ActionableDynamicQuery actionableDynamicQuery =
			resourcePermissionLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property roleIdProperty = PropertyFactoryUtil.forName(
						"roleId");

					dynamicQuery.add(
						roleIdProperty.eq(userGroupRole.getRoleId()));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.
				PerformActionMethod<ResourcePermission>() {

				@Override
				public void performAction(
					ResourcePermission resourcePermission) {

					if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
						SyncDLObject syncDLObject = getSyncDLObject(
							resourcePermission);

						if (syncDLObject == null) {
							return;
						}

						updateSyncDLObject(syncDLObject);
					}
				}

			});

		try {
			actionableDynamicQuery.performActions();
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(UserGroupRole userGroupRole)
		throws ModelListenerException {

		ActionableDynamicQuery actionableDynamicQuery =
			resourcePermissionLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					Property roleIdProperty = PropertyFactoryUtil.forName(
						"roleId");

					dynamicQuery.add(
						roleIdProperty.eq(userGroupRole.getRoleId()));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.
				PerformActionMethod<ResourcePermission>() {

				@Override
				public void performAction(
					ResourcePermission resourcePermission) {

					if (resourcePermission.hasActionId(ActionKeys.VIEW)) {
						SyncDLObject syncDLObject = getSyncDLObject(
							resourcePermission);

						if (syncDLObject == null) {
							return;
						}

						Date date = new Date();

						syncDLObject.setModifiedTime(date.getTime());
						syncDLObject.setLastPermissionChangeDate(date);

						syncDLObjectLocalService.updateSyncDLObject(
							syncDLObject);
					}
				}

			});

		try {
			actionableDynamicQuery.performActions();
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

}