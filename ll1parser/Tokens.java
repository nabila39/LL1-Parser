package com.example.ll1parser;



public class Tokens {
    private String token;
    private int line;

    public Tokens(String value, int lineNumber) {
        this.token = value;
        this.line = lineNumber;
    }

    public String getToken() {
        return token;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "Token{" + "value='" + token + '\'' + ", lineNumber=" + line + '}';
    }
}
