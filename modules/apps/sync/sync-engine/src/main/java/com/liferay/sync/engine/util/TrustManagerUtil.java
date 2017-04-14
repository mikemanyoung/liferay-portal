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

package com.liferay.sync.engine.util;

import com.sun.deploy.config.ClientConfig;
import com.sun.deploy.config.Config;

import java.io.IOException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 */
public class TrustManagerUtil {

	public static TrustManager[] getTrustManagers() throws Exception {
		if (_trustManagers == null) {
			final List<X509TrustManager> x509TrustManagers = new ArrayList<>();

			x509TrustManagers.add(_getDefaultTrustManager());

			Path certificatesFilePath = FileUtil.getFilePath(
				PropsValues.SYNC_CONFIGURATION_DIRECTORY, "certificates");

			if (FileUtil.exists(certificatesFilePath)) {
				Files.walkFileTree(
					certificatesFilePath,
					new SimpleFileVisitor<Path>() {

						@Override
						public FileVisitResult visitFile(
								Path filePath,
								BasicFileAttributes basicFileAttributes)
							throws IOException {

							try {
								x509TrustManagers.add(
									TrustManagerUtil.
										_getTrustManagerByCertificateFilePath(
											filePath));

								return FileVisitResult.CONTINUE;
							}
							catch (Exception e) {
							}

							try {
								x509TrustManagers.add(
									TrustManagerUtil.
										_getTrustManagerByKeyStoreFilePath(
											filePath));
							}
							catch (Exception e) {
								_logger.error(e.getMessage(), e);
							}

							return FileVisitResult.CONTINUE;
						}

					});
			}

			if (Config.getInstance() == null) {
				Config.setInstance(new ClientConfig());
			}

			Path[] certificateStoreFilePaths = new Path[] {
				Paths.get(Config.getUserSSLRootCertificateFile()),
				Paths.get(Config.getUserTrustedCertificateFile()),
				Paths.get(Config.getUserTrustedHttpsCertificateFile())
			};

			for (Path certificateStoreFilePath : certificateStoreFilePaths) {
				try {
					x509TrustManagers.add(
						_getTrustManagerByKeyStoreFilePath(
							certificateStoreFilePath));
				}
				catch (Exception e) {
					_logger.error(e.getMessage(), e);
				}
			}

			_trustManagers = new TrustManager[] {
				new CompositeX509TrustManager(x509TrustManagers)
			};
		}

		return _trustManagers;
	}

	private static X509TrustManager _getDefaultTrustManager() throws Exception {
		return _getTrustManager(null);
	}

	private static X509TrustManager _getTrustManager(KeyStore keyStore)
		throws Exception {

		TrustManagerFactory trustManagerFactory =
			TrustManagerFactory.getInstance(
				TrustManagerFactory.getDefaultAlgorithm());

		trustManagerFactory.init(keyStore);

		for (TrustManager trustManager :
				trustManagerFactory.getTrustManagers()) {

			if (trustManager instanceof X509TrustManager) {
				return (X509TrustManager)trustManager;
			}
		}

		return null;
	}

	private static X509TrustManager _getTrustManagerByCertificateFilePath(
			Path filePath)
		throws Exception {

		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

		keyStore.load(null, null);

		CertificateFactory certificateFactory = CertificateFactory.getInstance(
			"X.509");

		Certificate certificate = certificateFactory.generateCertificate(
			Files.newInputStream(filePath));

		keyStore.setCertificateEntry(filePath.toString(), certificate);

		return _getTrustManager(keyStore);
	}

	private static X509TrustManager _getTrustManagerByKeyStoreFilePath(
			Path filePath)
		throws Exception {

		KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

		keyStore.load(Files.newInputStream(filePath), null);

		return _getTrustManager(keyStore);
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		TrustManagerUtil.class);

	private static TrustManager[] _trustManagers;

}