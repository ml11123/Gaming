package com.gaming.baby.payload.request;

import org.springframework.data.domain.Sort;

public class SearchRequest {

    private String s;
    private int perPage;
    private int page;
    private String sortBy = "id";
    private String sort = "DESC";



    public SearchRequest(){}

    public SearchRequest(String s, int perPage, int page) {
        this.s = s;
        this.perPage = perPage;
        this.page = page;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Sort getSortObj(){
        Sort sort = Sort.by(this.getSortBy());
        switch(this.sort){
            case "ASC":
                sort =  sort.ascending();
                break;
            default:
                sort = sort.descending();
                break;
        }

        return sort;

    }

}
