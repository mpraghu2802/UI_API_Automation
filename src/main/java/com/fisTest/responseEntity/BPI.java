package com.fisTest.responseEntity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Map;

public class BPI {
    private final Map<String, BPIDetails> bpi;

    public Map<String, BPIDetails> getBpi() {
        return bpi;
    }

    @JsonCreator
    public BPI(Map<String, BPIDetails> bpi) {
        this.bpi = bpi;
    }


    public static class BPIDetails {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        @JsonAlias("rate_float")
        private String rateFloat;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRateFloat() {
            return rateFloat;
        }

        public void setRateFloat(String rateFloat) {
            this.rateFloat = rateFloat;
        }
    }

}
