package net.jeebiz.ftpclient.spring.boot;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.util.TrustManagerUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.jeebiz.ftpclient.FTPSClientBuilder;
import net.jeebiz.ftpclient.FTPSClientConfig;
import net.jeebiz.ftpclient.client.FTPPooledResourceClient;
import net.jeebiz.ftpclient.client.FTPResourceClient;
import net.jeebiz.ftpclient.client.IFTPClient;
import net.jeebiz.ftpclient.pool.FTPClientPool;
import net.jeebiz.ftpclient.pool.FTPPooledClientFactory;
import net.jeebiz.ftpclient.spring.boot.ext.AllowAllHostnameVerifier;

@Configuration
@ConditionalOnClass({ IFTPClient.class, FTPClient.class })
@EnableConfigurationProperties({ FTPClientProperties.class })
public class FTPSClientConfiguration {

	/** 加密Socket创建工厂 */
	@Bean
	@ConditionalOnMissingBean
	public SSLSocketFactory sslSocketFactory() throws Exception {
		return SSLContext.getDefault().getSocketFactory();
	}

	/** 在客户端模式(post-TLS)下的连接使用的域名校验对象，默认为null表示不校验 */
	@Bean
	@ConditionalOnMissingBean
	public HostnameVerifier hostnameVerifier() {
		return new AllowAllHostnameVerifier();
	}

	/**
	 * FTPS的TrustManager实现；默认使用
	 * {@link TrustManagerUtils#getValidateServerCertificateTrustManager()}.
	 */
	@Bean
	@ConditionalOnMissingBean
	public TrustManager trustManager() {
		return TrustManagerUtils.getValidateServerCertificateTrustManager();
	}

	@Bean
	public FTPSClientBuilder ftpsClientBuilder(SSLSocketFactory sslSocketFactory, 
			TrustManager trustManager,
			@Autowired(required = false) KeyManager keyManager, 
			FTPClientProperties properties) {

		FTPSClientConfig clientConfig = new FTPSClientConfig();
		// 同名属性拷贝
		BeanUtils.copyProperties(properties, clientConfig);

		return new FTPSClientBuilder(clientConfig);
	}

	@Bean
	public IFTPClient ftpsClient(FTPSClientBuilder clientBuilder, FTPClientProperties properties) {

		if (properties.getPool().isEnabled()) {

			FTPPooledClientFactory factory = new FTPPooledClientFactory(clientBuilder);

			FTPClientPool clientPool = new FTPClientPool(factory, properties.getPool());

			return new FTPPooledResourceClient(clientPool, properties);
		}

		return new FTPResourceClient(clientBuilder);
	}

}
