/*
 * Copyright (c) 2018, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.jeebiz.ftpclient.spring.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import net.jeebiz.ftpclient.FTPClientConfig;
import net.jeebiz.ftpclient.pool.FTPClientPoolConfig;

/**
 * FTPClient 参数配置
 * @author ： <a href="https://github.com/vindell">vindell</a>
 */
@ConfigurationProperties(FTPClientProperties.PREFIX)
public class FTPClientProperties extends FTPClientConfig {

	public static final String PREFIX = "ftpclient";
	
	@NestedConfigurationProperty
	private FTPClientPoolConfig pool = new FTPClientPoolConfig();
	
	@NestedConfigurationProperty
	private FTPSClientProperties ftps = new FTPSClientProperties();
	 
	@NestedConfigurationProperty
	private FTPHttpClientProperties httpProxy = new FTPHttpClientProperties();

	public FTPClientPoolConfig getPool() {
		return pool;
	}

	public void setPool(FTPClientPoolConfig pool) {
		this.pool = pool;
	}

	public FTPSClientProperties getFtps() {
		return ftps;
	}

	public void setFtps(FTPSClientProperties ftps) {
		this.ftps = ftps;
	}

	public FTPHttpClientProperties getHttpProxy() {
		return httpProxy;
	}

	public void setHttpProxy(FTPHttpClientProperties httpProxy) {
		this.httpProxy = httpProxy;
	}
	
	
	
}
