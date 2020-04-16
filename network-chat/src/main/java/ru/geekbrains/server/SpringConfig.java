package ru.geekbrains.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
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
        return "jdbc:mysql://localhost:3306/network_chat?createDatabaseIfNotExist=true&characterEncoding=UTF-8&serverTimezone=UTC";
    }

    @Bean
    //@Scope("prototype")
    Service1 service1() {
        return new Service1();
    }

    @Bean
    Service2 service21() {
        return new Service2(service1());
    }

    @Bean
    Service2 service22() {
        return new Service2(service1());
    }

    @Bean
    Service2 service23() {
        return new Service2(service1());
    }

    static class Service1 {

        private static int count = 0;

        public Service1() {
            count++;
        }

        public String getName() {
            return "service1 " + count;
        }

    }

    static class Service2 {

        private Service1 service1;

        public Service2(Service1 service1) {
            this.service1 = service1;
            System.out.println("service2 " + service1.getName());
        }
    }
}
