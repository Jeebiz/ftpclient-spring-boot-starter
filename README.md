# ftpclient-spring-boot-starter
Spring Boot Starter For jeebiz-ftpclient

### 说明

 > 基于 [jeebiz-ftpclient](https://github.com/Jeebiz/jeebiz-ftpclient) 的 Spring Boot Starter 实现

1. 整合 smbclient

### Maven

``` xml
<dependency>
	<groupId>net.jeebiz</groupId>
	<artifactId>ftpclient-spring-boot-starter</artifactId>
	<version>${project.version}</version>
</dependency>
```

### Sample

```java


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.jeebiz.ftpclient.client.IFTPClient;

@EnableFTPClient
@EnableFTPHttpClient
@EnableFTPSClient
@SpringBootApplication
public class Application {
	
	@Autowired
	public IFTPClient ftpClient;
	
	@Autowired
	public IFTPClient ftpsClient;
	
	@Autowired
	public IFTPClient ftpHttpClient;
	
	@PostConstruct
	private void init() {
		
		//ftpClient.upload(localFile, ftpFileName)
		
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}

```
