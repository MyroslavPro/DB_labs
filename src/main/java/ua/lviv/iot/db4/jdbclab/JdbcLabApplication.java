package ua.lviv.iot.db4.jdbclab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import ua.lviv.iot.db4.jdbclab.view.MyView;


@SpringBootApplication
public class JdbcLabApplication implements CommandLineRunner {
    @Autowired
    private MyView view;

    public static void main(String[] args) {
        SpringApplication.run(JdbcLabApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        view.show();
    }
}
