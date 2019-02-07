package com.times.search.exception;

import com.times.search.DAO.ErrorDAO;
import com.times.search.DAO.ResponseDAO;
import com.times.search.constant.Constant;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nishkarsh
 * Global exception Handler
 * @date 07-Feb-2019
 */
@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {

    /**
     * When user not found
     *
     * @param request
     *            Request
     * @param httpResponse
     *            Http Response
     * @param ex
     *            Exception
     * @return Response to the client
     */
    @ExceptionHandler(value = SolrServerException.class)
    @ResponseBody
    public ResponseDAO<ErrorDAO> userNotFound(HttpServletRequest request, HttpServletResponse httpResponse,
                                              Exception ex) {
        return buildErrorResponse("SOLR_EXCEPTION", ex.getMessage());
    }

    /**
     * Parent exception
     *
     * @param request
     *            Request
     * @param httpResponse
     *            Http Response
     * @param ex
     *            Exception
     * @return Response to the client
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseDAO<ErrorDAO> parentException(HttpServletRequest request, HttpServletResponse httpResponse,
                                                 Exception ex) {
        ex.printStackTrace();
        return buildErrorResponse(Constant.UNKOWN_EXCEPTION, Constant.UNKOWN_EXCEPTION);
    }

    /**
     * Build Error Response based on the status and message.
     *
     * @param status
     *            internal error code based on the Exception
     * @param message
     *            Exception message
     * @return
     */
    private ResponseDAO<ErrorDAO> buildErrorResponse(String status, String message) {
        ResponseDAO<ErrorDAO> response = new ResponseDAO<ErrorDAO>(false);
        ErrorDAO error = new ErrorDAO();
        error.setStatus(status);
        error.setMessage(message);
        response.setData(error);
        return response;
    }
}
