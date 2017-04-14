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

import com.google.common.collect.ObjectArrays;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import java.util.List;

import javax.net.ssl.X509TrustManager;

/**
 * @author Dennis Ju
 */
public class CompositeX509TrustManager implements X509TrustManager {

	public CompositeX509TrustManager(List<X509TrustManager> x509TrustManagers) {
		_x509TrustManagers = x509TrustManagers;
	}

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {

		for (X509TrustManager x509TrustManager : _x509TrustManagers) {
			try {
				x509TrustManager.checkClientTrusted(chain, authType);

				return;
			}
			catch (Exception e) {
			}
		}

		throw new CertificateException();
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {

		for (X509TrustManager x509TrustManager : _x509TrustManagers) {
			try {
				x509TrustManager.checkServerTrusted(chain, authType);

				return;
			}
			catch (Exception e) {
			}
		}

		throw new CertificateException();
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		X509Certificate[] x509Certificates = new X509Certificate[0];

		for (X509TrustManager x509TrustManager : _x509TrustManagers) {
			x509Certificates = ObjectArrays.concat(
				x509Certificates, x509TrustManager.getAcceptedIssuers(),
				X509Certificate.class);
		}

		return x509Certificates;
	}

	private final List<X509TrustManager> _x509TrustManagers;

}