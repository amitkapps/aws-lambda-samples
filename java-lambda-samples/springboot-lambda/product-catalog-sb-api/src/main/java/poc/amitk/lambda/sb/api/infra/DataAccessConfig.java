package poc.amitk.lambda.sb.api.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @author amitkapps
 */
@Configuration
public class DataAccessConfig {

    private Logger logger = LoggerFactory.getLogger(DataAccessConfig.class);

    @Autowired
    private SecretsManagerService secretsManagerService;

    @Value("${datasource.secret-id}")
    String datasourceSecretId;

    @Bean
    @Qualifier("productsDS")
    public DataSource productsDS() throws JsonProcessingException {

        Properties props = new Properties();
        //Add other connection pooling properties - min/max/timeouts/min idle
        props.setProperty("poolName", "ProductsConnectionPool");
        props.setProperty("driverClassName", "com.mysql.cj.jdbc.Driver");
        Map<String, String> credentialsMap = productsDSCredentials(datasourceSecretId);
        String jdbcUrl = "jdbc:mysql://"
                + credentialsMap.get("host")
                + ":"
                + String.valueOf(credentialsMap.get("port"))
                + "/products";
        props.setProperty("jdbcUrl", jdbcUrl);
        props.setProperty("username", credentialsMap.get("username"));
        props.setProperty("password", credentialsMap.get("password"));
        logger.info("jdbcUrl: {}", jdbcUrl);
//        props.setProperty("connectionTestQuery", "select 1 from dual"); // only for non-jdbc4-compliant drivers
        props.put("dataSource.logWriter", new PrintWriter(System.out));

        HikariConfig config = new HikariConfig(props);
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }

    @Bean
    @Qualifier("productsJdbcTemplate")
    public JdbcTemplate customerJdbcTemplate(@Qualifier("productsDS") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    public Map<String, String> productsDSCredentials(String secretId) throws JsonProcessingException {
        return secretsManagerService.getSecretsAsMap(secretId);
    }

}