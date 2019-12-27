/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
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

/**
 * FTPHTTPClient 参数配置
 * @author ： <a href="https://github.com/hiwepy">hiwepy</a>
 */
public class FTPHttpClientProperties {

	//===============================================================================
	//=============FTPHTTPClient参数配置================================================
	//===============================================================================

	/** HTTP代理主机IP地址 */
	protected String httpProxyHost;
	/** HTTP代理主机端口 */
	protected int httpProxyPort;
	/** HTTP代理主机账户名 */
	protected String httpProxyUsername;
	/** HTTP代理主机密码 */
	protected String httpProxyPassword;
	 
	public String getHttpProxyHost() {
		return httpProxyHost;
	}

	public void setHttpProxyHost(String httpProxyHost) {
		this.httpProxyHost = httpProxyHost;
	}

	public int getHttpProxyPort() {
		return httpProxyPort;
	}

	public void setHttpProxyPort(int httpProxyPort) {
		this.httpProxyPort = httpProxyPort;
	}

	public String getHttpProxyUsername() {
		return httpProxyUsername;
	}

	public void setHttpProxyUsername(String httpProxyUsername) {
		this.httpProxyUsername = httpProxyUsername;
	}

	public String getHttpProxyPassword() {
		return httpProxyPassword;
	}

	public void setHttpProxyPassword(String httpProxyPassword) {
		this.httpProxyPassword = httpProxyPassword;
	}
	
	
}
