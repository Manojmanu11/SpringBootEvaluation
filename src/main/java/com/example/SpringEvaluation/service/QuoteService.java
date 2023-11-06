package com.example.SpringEvaluation.service;
//
//import com.msil.evaluation.constants.StringConstants;
//import com.msil.evaluation.dto.ResponseMessage;
//import com.msil.evaluation.entity.Symbol;
//import com.msil.evaluation.repository.SymbolRepository;
//import jakarta.transaction.Transactional;
import com.example.SpringEvaluation.constants.StringConstants;
import com.example.SpringEvaluation.dto.ResponseMessage;
import com.example.SpringEvaluation.entity.Symbol;
import com.example.SpringEvaluation.model.Response;
import com.example.SpringEvaluation.repository.SymbolRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
@Service
@Log4j2
@Transactional
public class QuoteService {
    @Autowired
    SymbolRepository symbolsRepository;
    @Value("${api.csvFilePath}")
    public String csvFilePath;


    @Autowired
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    // Method to check if a symbol exists by symbol name
    public boolean existsBySymbol(String symbol) {
        String sql = "SELECT COUNT(*) FROM Symbol WHERE SYMBOL = :symbol";
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

    // Method to find a symbol by symbol name
    public Symbol findSymbol(String symbol) {
        String sql = "SELECT * FROM Symbol WHERE SYMBOL = :symbol";
        Map<String, Object> params = new HashMap<>();
        params.put("symbol", symbol);
        List<Symbol> symbols = namedParameterJdbcTemplate.query(sql, params, symbolRowMapper);
        return symbols.isEmpty() ? null : symbols.get(0);
    }

    // Method to find all symbols
    public List<Symbol> findAllSymbols() {
        String sql = "SELECT * FROM Symbol";
        return namedParameterJdbcTemplate.query(sql, symbolRowMapper);
    }

    // RowMapper to convert a ResultSet row to a Symbol object
    private final RowMapper<Symbol> symbolRowMapper = (resultSet, rowNum) -> {
        Symbol symbol = new Symbol();
        symbol.setSymbol(resultSet.getString("SYMBOL"));
        symbol.setSymbolName(resultSet.getString("SYMBOL_NAME"));
        symbol.setIndexName(resultSet.getString("INDEX_NAME"));
        symbol.setCompanyName(resultSet.getString("COMPANY_NAME")); // Corrected field name
        symbol.setIndustry(resultSet.getString("INDUSTRY"));
        symbol.setSeries(resultSet.getString("SERIES"));
        symbol.setIsinCode(resultSet.getString("ISIN_CODE"));
        symbol.setExchange(resultSet.getString("EXCHANGE"));
        symbol.setCreatedAt(resultSet.getString("CREATED_AT"));
        symbol.setUpdatedAt(resultSet.getString("UPDATED_AT"));
        symbol.setScripCode(resultSet.getString("SCRIP_CODE"));
        return symbol;
    };

    @org.springframework.transaction.annotation.Transactional
    public Response storeCsvInDatabase() throws IOException {
        try {
            Reader reader = new FileReader(csvFilePath);
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withHeader("SYMBOL", "SYMBOL_NAME", "INDEX_NAME", "COMPANY_NAME", "INDUSTRY", "SERIES", "ISIN_CODE", "EXCHANGE", "CREATED_AT", "UPDATED_AT", "SCRIP_CODE")
                    .withIgnoreHeaderCase()
                    .withTrim());

            for (CSVRecord csvRecord : csvParser) {
                Symbol symbol = new Symbol();
                symbol.setSymbol(csvRecord.get("SYMBOL"));
                symbol.setSymbolName(csvRecord.get("SYMBOL_NAME"));
                symbol.setIndexName(csvRecord.get("INDEX_NAME"));
                symbol.setCompanyName(csvRecord.get("COMPANY_NAME")); // Corrected field name
                symbol.setIndustry(csvRecord.get("INDUSTRY"));
                symbol.setSeries(csvRecord.get("SERIES"));
                symbol.setIsinCode(csvRecord.get("ISIN_CODE"));
                symbol.setExchange(csvRecord.get("EXCHANGE"));
                symbol.setCreatedAt(csvRecord.get("CREATED_AT"));
                symbol.setUpdatedAt(csvRecord.get("UPDATED_AT"));
                symbol.setScripCode(csvRecord.get("SCRIP_CODE"));
                symbolsRepository.save(symbol);
            }
            csvParser.close();
            reader.close();
            return new Response(StringConstants.Saved);

        } catch (IOException e) {
            return new Response(StringConstants.Failed);
        }

    }



    /**Method to retrieve all symbols from the database.*/
    public List<Symbol> getAllSymbols() {
        log.info(StringConstants.Quote + "getAllSymbols");
        return findAllSymbols();
    }
    public List<Symbol> getSymbol(String symbol){
        log.info("Returned by quote service method : RetrieveSymbolDetails");
        return Collections.singletonList(findSymbol(symbol));
    }


}
