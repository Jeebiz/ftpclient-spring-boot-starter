package net.jeebiz.ftpclient.spring.boot;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.jeebiz.ftpclient.FTPClientBuilder;
import net.jeebiz.ftpclient.client.FTPPooledResourceClient;
import net.jeebiz.ftpclient.client.FTPResourceClient;
import net.jeebiz.ftpclient.client.IFTPClient;
import net.jeebiz.ftpclient.pool.FTPClientPool;
import net.jeebiz.ftpclient.pool.FTPPooledClientFactory;

@Configuration
@ConditionalOnClass({ IFTPClient.class, FTPClient.class })
@EnableConfigurationProperties({ FTPClientProperties.class })
public class FTPClientConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
	public FTPClientBuilder ftpClientBuilder( FTPClientProperties properties) {
		return new FTPClientBuilder(properties);
	}
	
	@Bean
	public IFTPClient ftpClient(FTPClientBuilder clientBuilder, FTPClientProperties properties) {
		
		if (properties.getPool().isEnabled()) {

			FTPPooledClientFactory factory = new FTPPooledClientFactory(clientBuilder);

			FTPClientPool clientPool = new FTPClientPool(factory, properties.getPool());

			return new FTPPooledResourceClient(clientPool, properties);
		}
		
		return new FTPResourceClient(clientBuilder);
	}

}
