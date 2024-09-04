package com.example.ll1parser;

import java.util.ArrayList;
import java.util.List;

public class LL1Scanner {

    private static final String[] grammerOperator = {":=", "(", ")", ".", ";", ",", "+", "-", "*", "/", "mod", "div", "<=", ">=", "<>", "=", "<", ">", ":", "|="};

    public static List<Tokens> getTokens(String code) throws Exception {
        List<Tokens> tokens = new ArrayList<>();
        int index = 0;
        int lineNumber = 1;

        while (index < code.length()) {
            char activChar = code.charAt(index);

            if (activChar == '\n') {
                lineNumber++;
                index++;
                continue;
            }

            if (Character.isWhitespace(activChar)) {
                index++;
                continue;
            }

            if (Character.isLetter(activChar)) {
                int start = index;
                while (index < code.length() && (Character.isLetterOrDigit(code.charAt(index)) || code.charAt(index) == '_')) {
                    index++;
                }
                String token = code.substring(start, index);
                tokens.add(new Tokens(token, lineNumber));
                continue;
            }

            if (Character.isDigit(activChar)) {
                int start = index;
                boolean isFloat = false;
                while (index < code.length() && (Character.isDigit(code.charAt(index)) || code.charAt(index) == '.')) {
                    if (code.charAt(index) == '.') {
                        isFloat = true;
                    }
                    index++;
                }
                String token = code.substring(start, index);
                tokens.add(new Tokens(token, lineNumber));
                continue;
            }

            boolean FLAG = false;
            for (String operator : grammerOperator) {
                if (code.startsWith(operator, index)) {
                    tokens.add(new Tokens(operator, lineNumber));
                    index += operator.length();
                    FLAG = true;
                    break;
                }
            }
            if (FLAG) {
                continue;
            }

            throw new Exception("invalid character for this grammar: " + activChar + " at line " + lineNumber);
        }
        return tokens;
    }
}
