package com.jim.io.bio.ssl;

import javax.net.ServerSocketFactory;
import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class SSLCommonContext {

	public static SSLSocket sslSocket;

	SSLServerSocket serverSocket;

	public static void init(String type, String serverKeyPath, String keyManagerFactory, String clientKeyPath,
			String sslProto) {

	}

	/**
	 * 加载server密钥
	 * 
	 * @return
	 * @throws Exception 
	 * @throws KeyStoreException 
	 * @throws UnrecoverableKeyException 
	 */
	public static KeyManagerFactory serverKeyManagerFactory(String algorithm, String password)
			throws  Exception {
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");

		kmf.init(keyStore("", "", ""), SSLConf.SERVER_KEY_STORE_PASSWORD.toCharArray());
		return kmf;
	}

	/**
	 * 加载证书
	 * 
	 * @return
	 * @throws Exception
	 */
	public static KeyStore keyStore(String keystoreAlgorithm, String keystorePath, String password) throws Exception {
		KeyStore ks = KeyStore.getInstance(keystoreAlgorithm/* "JKS" */);
		ks.load(new FileInputStream(keystorePath/* "kserver.keystore" */), password.toCharArray()
		/* SSLConf.SERVER_KEY_STORE_PASSWORD.toCharArray() */);

		return ks;

	}

	/**
	 * 初始化客户端密钥
	 * 
	 * @return
	 */
	public static TrustManagerFactory clientTrustManagerFactory() throws Exception {
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		KeyStore tks = keyStore("", "", "");
//        KeyStore tks = KeyStore.getInstance("JKS");
//        tks.load(new FileInputStream("tclient.keystore"),
//                SSLConf.SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());

		tmf.init(tks);
		return tmf;
	}

//	public ServerSocketFactory getServerSocketFactory() {
//
//	}

	/**
	 * <ul>
	 * <li>ssl连接的重点:</li>
	 * <li>初始化SSLServerSocket</li>
	 * <li>导入服务端私钥KeyStore，导入服务端受信任的KeyStore(客户端的证书)</li>
	 * </ul>
	 */
	public void init() {
		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream("kserver.keystore"), SSLConf.SERVER_KEY_STORE_PASSWORD.toCharArray());
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(ks, SSLConf.SERVER_KEY_STORE_PASSWORD.toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			KeyStore tks = KeyStore.getInstance("JKS");
			tks.load(new FileInputStream("tclient.keystore"), SSLConf.SERVER_TRUST_KEY_STORE_PASSWORD.toCharArray());
			tmf.init(tks);

			SSLContext ctx = SSLContext.getInstance("SSL");
			// ctx.init(kmf.getKeyManagers(), null, null); // 单向验证
			ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

			serverSocket = (SSLServerSocket) ctx.getServerSocketFactory().createServerSocket(SSLConf.DEFAULT_PORT);
			((SSLServerSocket) serverSocket).setNeedClientAuth(true); // false或者不设置则为单向验证
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
