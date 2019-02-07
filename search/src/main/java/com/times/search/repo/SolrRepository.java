package com.times.search.repo;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Main class for performing all the SOLR operations
 * @author Nishkarsh
 * @date 06-Feb-2019
 *
 */
@Repository
public class SolrRepository {
    private SolrClient solrClient;

    public SolrRepository(SolrClient solrClient)
    {
        this.solrClient=solrClient;
    }

    public QueryResponse querySolr(SolrQuery query) throws SolrServerException, IOException
    {
        QueryResponse queryResponse =null;

        queryResponse= solrClient.query(query);

        return queryResponse;

    }
}
