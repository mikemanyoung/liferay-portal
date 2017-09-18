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

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.UserGroupRole;
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
					Property nameProperty = PropertyFactoryUtil.forName("name");
					Property roleIdProperty = PropertyFactoryUtil.forName(
						"roleId");
					Property viewActionIdProperty = PropertyFactoryUtil.forName(
						"viewActionId");

					Disjunction disjunction =
						RestrictionsFactoryUtil.disjunction();

					disjunction.add(
						nameProperty.eq(DLFileEntry.class.getName()));
					disjunction.add(nameProperty.eq(DLFolder.class.getName()));

					dynamicQuery.add(disjunction);

					dynamicQuery.add(
						roleIdProperty.eq(userGroupRole.getRoleId()));
					dynamicQuery.add(viewActionIdProperty.eq(true));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.
				PerformActionMethod<ResourcePermission>() {

				@Override
				public void performAction(
					ResourcePermission resourcePermission) {

					SyncDLObject syncDLObject = getSyncDLObject(
						resourcePermission);

					if (syncDLObject == null) {
						return;
					}

					updateSyncDLObject(syncDLObject);
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
					Property nameProperty = PropertyFactoryUtil.forName("name");
					Property roleIdProperty = PropertyFactoryUtil.forName(
						"roleId");
					Property viewActionIdProperty = PropertyFactoryUtil.forName(
						"viewActionId");

					Disjunction disjunction =
						RestrictionsFactoryUtil.disjunction();

					disjunction.add(
						nameProperty.eq(DLFileEntry.class.getName()));
					disjunction.add(nameProperty.eq(DLFolder.class.getName()));

					dynamicQuery.add(disjunction);

					dynamicQuery.add(
						roleIdProperty.eq(userGroupRole.getRoleId()));
					dynamicQuery.add(viewActionIdProperty.eq(true));
				}

			});
		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.
				PerformActionMethod<ResourcePermission>() {

				@Override
				public void performAction(
					ResourcePermission resourcePermission) {

					SyncDLObject syncDLObject = getSyncDLObject(
						resourcePermission);

					if (syncDLObject == null) {
						return;
					}

					Date date = new Date();

					syncDLObject.setModifiedTime(date.getTime());
					syncDLObject.setLastPermissionChangeDate(date);

					syncDLObjectLocalService.updateSyncDLObject(syncDLObject);
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