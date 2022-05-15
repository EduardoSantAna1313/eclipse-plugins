package com.edu.postman.service.postman;

/**
 * Class to build a PostmanService.
 *
 * @author Eduardo
 */
public class PostmanServiceBuilder {

    /**
     * PostmanService - service.
     */
    private final PostmanService service;

    /**
     * New instance of PostmanServiceBuilder
     *
     * @param apiKey
     */
    public PostmanServiceBuilder(final String apiKey) {
        service = new PostmanService(apiKey);
    }

    /**
     * build.
     *
     * @return
     */
    public PostmanService build() {
        return service;
    }

}