package nef;

import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableConfigurationProperties
public class AppEntryPoint {

    public static void main(String[] args) {
        SpringApplication.run(AppEntryPoint.class, args);
    }
    
    @Bean
    public DataSource getDataSource(
            @Value("${nef.jdbcUrl}") String url, 
            @Value("${nef.user}") String user, 
            @Value("${nef.password}") String password ) {
        return DataSourceBuilder.create()
                .url(url)
                .username(user)
                .password(password)
                .build();
    }
    
    @Bean
    public ObjectMapper getObjectMapper(@Value("${nef.dateFormat}") String dateFormat) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        return mapper;
    }
}
