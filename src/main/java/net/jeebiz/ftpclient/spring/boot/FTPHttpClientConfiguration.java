package net.jeebiz.ftpclient.spring.boot;

import org.apache.commons.net.ftp.FTPHTTPClient;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.jeebiz.ftpclient.FTPHttpClientBuilder;
import net.jeebiz.ftpclient.FTPHttpClientConfig;
import net.jeebiz.ftpclient.client.FTPPooledResourceClient;
import net.jeebiz.ftpclient.client.FTPResourceClient;
import net.jeebiz.ftpclient.client.IFTPClient;
import net.jeebiz.ftpclient.pool.FTPClientPool;
import net.jeebiz.ftpclient.pool.FTPPooledClientFactory;

@Configuration
@ConditionalOnClass({ FTPHTTPClient.class })
@EnableConfigurationProperties({ FTPClientProperties.class })
public class FTPHttpClientConfiguration {

	@Bean
	public FTPHttpClientBuilder ftpHttpClientBuilder( FTPClientProperties properties) {
		
		FTPHttpClientConfig clientConfig = new FTPHttpClientConfig();
		// 同名属性拷贝
		BeanUtils.copyProperties(properties, clientConfig);
		// http
		FTPHttpClientProperties httpProxy = properties.getHttpProxy();
		
		clientConfig.setHttpProxyHost(httpProxy.getHttpProxyHost());
		clientConfig.setHttpProxyPassword(httpProxy.getHttpProxyPassword());
		clientConfig.setHttpProxyPort(httpProxy.getHttpProxyPort());
		clientConfig.setHttpProxyUsername(httpProxy.getHttpProxyUsername());
		
		return new FTPHttpClientBuilder(clientConfig);
	}
	
	@Bean
	public IFTPClient ftpHttpClient(FTPHttpClientBuilder clientBuilder, FTPClientProperties properties) {

		if (properties.getPool().isEnabled()) {

			FTPPooledClientFactory factory = new FTPPooledClientFactory(clientBuilder);

			FTPClientPool clientPool = new FTPClientPool(factory, properties.getPool());

			return new FTPPooledResourceClient(clientPool, properties);
		}

		return new FTPResourceClient(clientBuilder);
	}

}
