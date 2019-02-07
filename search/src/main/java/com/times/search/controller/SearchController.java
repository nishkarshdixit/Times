package com.times.search.controller;

import com.times.search.DAO.ResponseDAO;
import com.times.search.constant.Constant;
import com.times.search.service.SearchProductsReviewSortedService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Main controller for our product search API
 * @author Nishkarsh
 * @date 06-Feb-2019
 */

@RestController
@RequestMapping(value="/times")
public class SearchController {

    @Autowired
    SearchProductsReviewSortedService searchProductsReviewSortedService;

    /**
     * Solves both of our problems:-
     *
     * 1. When we send the order as external parameter along with searchTerm i.e sortOrder=asc or sortOrder=desc
     * we get the search results sorted, Results are sorted as per the count of reviews in the external field file
     * Sample url for first :- http://localhost:8080/search/times/searchproducts?searchTerm=mobile&sortOrder=desc
     * O/P :- We will have the results for products with title containing mobile and sorted descending acc to reviews
     *
     *
     * 2.  When we send only the searchTerm as external parameter the results are displayed accordingly
     * If we have search term in our elevate.xml the output will have mentioned documents pinned accordingly
     * Sample url for second :- http://localhost:8080/search/times/searchproducts?searchTerm=samsung
     * O/P :- We will have the results according to the search along with the pinned documents at top in order in which they are pinned
     *
     * @param searchTerm
     * @param sortOrder
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    @GetMapping(value="/searchproducts", produces = "application/json")
    ResponseDAO<SolrDocumentList> getProducts(
            @RequestParam(required = false) String searchTerm, String sortOrder
    ) throws IOException, SolrServerException {
        if(StringUtils.isEmpty(searchTerm)){
            return ResponseDAO.failure(Constant.EMPTY_STRING);
        }
        SolrDocumentList response = searchProductsReviewSortedService.searchProductsReviewSorted(searchTerm,sortOrder);
        if(response!=null){
            return ResponseDAO.success(Constant.HIT_SUCCESS, response);
        }else{
            return ResponseDAO.failure(Constant.PRODUCT_NOT_FOUND);
        }
    }
}
