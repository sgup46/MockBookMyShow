package com.sapient.config;

import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {


    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(
                "-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }
}
