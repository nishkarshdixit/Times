package com.times.search.service;

import com.times.search.constant.Constant;
import com.times.search.repo.SolrRepository;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Service responsible to create the solr query according to our request
 * @author Nishkarsh
 * @date 06-Feb-2019
 */
@Service
public class SearchProductsReviewSortedServiceImpl implements SearchProductsReviewSortedService {


    @Autowired
    SolrRepository solrRepository;

    /**
     * Method responsible to create and fetch results from the solr query as per our inputs
     * The inputs decide whether to order the documents or not
     *
     * @param searchTerm
     * @param sortOrder
     * @return
     * @throws SolrServerException
     * @throws IOException
     */
    @Override
    public SolrDocumentList searchProductsReviewSorted(String searchTerm,String sortOrder) throws SolrServerException, IOException {
        SolrQuery query = new SolrQuery();
        query.setRequestHandler("/select");
        query.set("qt","search");
        query.setQuery(searchTerm.toLowerCase());
        if(sortOrder!=null && Constant.SORT_ARRAY.contains(sortOrder.toLowerCase().trim())) {
            query.set("sort", "{!func}review "+sortOrder);
        }
        query.setFields( "displayid,title,datatype,field(review)");

        QueryResponse response = solrRepository.querySolr(query);
        return response.getResults();
    }
}
