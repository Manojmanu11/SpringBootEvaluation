package com.example.SpringEvaluation.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail user ;

    private String groupName;


    @ElementCollection // Used to map collections of basic types to db (strings)
    @CollectionTable(
            name = "watchlist_group_symbol",
            joinColumns = @JoinColumn(name = "watchlist_group_id")
    )
    @Column(name = "symbol_name") // Column name for the symbols in the watchlist_symbol table
    private List<String> symbols = new ArrayList<>();

}
