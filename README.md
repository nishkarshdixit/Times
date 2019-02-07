# Times

     * Solves both of our problems:-
     
     1. When we send the order as external parameter along with searchTerm i.e sortOrder=asc or sortOrder=desc
        * we get the search results sorted, Results are sorted as per the count of reviews in the external field file
        * Sample url for first :- 
        http://localhost:8080/search/times/searchproducts?searchTerm=mobile&sortOrder=desc
        
        * O/P :- We will have the results for products with title containing mobile and sorted descending acc to reviews

     
     2.  When we send only the searchTerm as external parameter the results are displayed accordingly
         * If we have search term in our elevate.xml the output will have mentioned documents pinned accordingly
         * Sample url for second :- 
         http://localhost:8080/search/times/searchproducts?searchTerm=samsung
         
         * O/P :- We will have the results according to the search along with the pinned documents at top in order in which they are pinned
         
         
         NOTE:- All the basic solr configuration changes files are provided separately under the folder solr configs.
