package itu.s5.cloud;

import itu.s5.cloud.connection.ConnectDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class TpWsApplication implements CommandLineRunner {
	private static ConnectDB PostgreSQL;

	public static ConnectDB getPostgreSQL() {
		return PostgreSQL;
	}

	public void setPostgreSQL(ConnectDB postgreSQL) {
		PostgreSQL = postgreSQL;
	}

	public static void main(String[] args) {
		SpringApplication.run(TpWsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE").allowedOrigins("*");
			}
		};
	}

	@Override
	public void run(String... args) throws Exception {
		try
		{
			PostgreSQL=new ConnectDB("jdbc:postgresql://arjuna.db.elephantsql.com:5432/hdtdhvaw","hdtdhvaw","c2TpncI8dTOXskzE89m1fTQsNmR2GWcP");
			if(PostgreSQL!=null){System.out.println("Connected to PostgreSQL - Avion");}
		}
		catch(SQLException sqle){
			System.out.println("TAY_"+sqle.getMessage());
		}
		Connection connection = PostgreSQL.getConnection();
		System.out.println("Web Services is ready ...");
	}
}
