package org.esupportail.helpdesk.data.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;

@Configuration
@Lazy
public class JndiDataSourceConfig {

    @Value("${jndi.datasource}")
    private String jndiDatasourceName;

    @Bean(name = {"JNDIDataSource", "JNDI"})
    public DataSource jndiDataSource() {
        JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        return lookup.getDataSource(jndiDatasourceName);
    }
}
