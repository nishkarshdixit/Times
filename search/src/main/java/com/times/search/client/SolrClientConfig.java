package com.times.search.client;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nishkarsh
 * Basic Solr Client configuration file to have its instance and use it for all pre defined methods
 * @date 06-Feb-2019
 */
@Configuration
public class SolrClientConfig extends AbstractFactoryBean<SolrClient> {

    private SolrClient solrClient;

    @Value("http://localhost:8985/solr/product")
    private String urlString;

    @Override
    public Class<?> getObjectType() {
        return SolrClient.class;
    }

    @Override
    protected SolrClient createInstance() throws Exception {
        return buildClient();
    }

    private SolrClient buildClient() {
        solrClient = new HttpSolrClient.Builder(urlString).build();

        return solrClient;
    }

    @Override
    public void destroy() {
        try {
            if (solrClient != null) {
                solrClient.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing Solr client: ", e);
        }
    }
}
