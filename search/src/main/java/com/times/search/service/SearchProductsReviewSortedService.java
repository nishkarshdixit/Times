package com.times.search.service;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface SearchProductsReviewSortedService {

    SolrDocumentList searchProductsReviewSorted(String searchTerm,String sortOrder) throws SolrServerException, IOException;
}
