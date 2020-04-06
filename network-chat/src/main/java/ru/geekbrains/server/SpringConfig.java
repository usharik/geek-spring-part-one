package ru.geekbrains.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.geekbrains.server.auth.AuthService;
import ru.geekbrains.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.server.persistance.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SpringConfig {

    @Bean
    public ChatServer chatServer(AuthService authService) {
        return new ChatServer(authService);
    }

    @Bean
    public AuthService authService(UserRepository userRepository) {
        return new AuthServiceJdbcImpl(userRepository);
    }

    @Bean
    public UserRepository userRepository(DataSource dataSource) throws SQLException {
        return new UserRepository(dataSource);
    }

    @Bean
    public DataSource dataSource(String jdbcUrl) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(jdbcUrl);
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return ds;
    }

    @Bean
    public String jdbcUrl() {
        return "jdbc:mysql://localhost:3306/network_chat";
    }
}
