package com.gao.toolbar.recyclerview.http;

public class HttpResponseMeizi<T> {
    private String error;
    private T results;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "HttpResponseMeizi{" +
                "error='" + error + '\'' +
                ", results=" + results +
                '}';
    }
}
