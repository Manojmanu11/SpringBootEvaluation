package com.example.SpringEvaluation.entity;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Data
@Entity
@Table(name = "SYMBOL")
public class Symbol {

    @Id
    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name ="SYMBOL_NAME")
    private String symbolName;

    @Column(name = "INDEX_NAME")
    private String indexName;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "INDUSTRY")
    private String industry;

    @Column(name = "SERIES")
    private String series;

    @Column(name = "ISIN_CODE")
    private String isinCode;

    @Column(name = "EXCHANGE")
    private String exchange;

    @Column(name = "CREATED_AT")
    private String createdAt;

    @Column(name = "UPDATED_AT")
    private String updatedAt;

    @Column(name = "SCRIP_CODE")
    private String scripCode;
}