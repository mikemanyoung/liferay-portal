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

package com.liferay.sync.engine.documentlibrary.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.sync.engine.documentlibrary.event.Event;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.util.FileUtil;

/**
 * @author Shinn Lok
 */
public class MoveFolderHandler extends BaseJSONHandler {

	public MoveFolderHandler(Event event) {
		super(event);
	}

	@Override
	public void processResponse(String response) throws Exception {
		SyncFile localSyncFile = (SyncFile)getParameterValue("syncFile");

		ObjectMapper objectMapper = new ObjectMapper();

		SyncFile remoteSyncFile = objectMapper.readValue(
			response, new TypeReference<SyncFile>() {});

		SyncFile parentLocalSyncFile = SyncFileService.fetchSyncFile(
			remoteSyncFile.getRepositoryId(), getSyncAccountId(),
			remoteSyncFile.getParentFolderId());

		localSyncFile.setFilePathName(
			FileUtil.getFilePathName(
				parentLocalSyncFile.getFilePathName(),
				remoteSyncFile.getName()));

		localSyncFile.setModifiedTime(remoteSyncFile.getModifiedTime());

		SyncFileService.update(localSyncFile);
	}

}