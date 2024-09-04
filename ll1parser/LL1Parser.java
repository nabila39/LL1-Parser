package com.example.ll1parser;

import java.util.*;

public class LL1Parser {
    //fill the array with the grammer reserved word
    static final List<String> reservedWords = Arrays.asList(
            "module", "end", "begin", "var", "const", "integer", "real", "char",
            "procedure", "mod", "div", "readint", "readreal", "readchar", "readln", "writeint", "writereal",
            "writechar", "writeln", "if", "then", "else", "while", "do", "loop", "until",
            "exit", "call"
    );
    //fill the list with the non-Terminal that has occuer in our grammer
    static final List<String> nonTerminal = Arrays.asList(
            "module-decl",
            "module-heading",
            "block",
            "declarations",
            "const-decl",
            "const-list",
            "var-decl",
            "var-list",
            "var-item",
            "name-list",
            "more-names",
            "data-type",
            "procedure-decl",
            "procedure-heading",
            "stmt-list",
            "statement",
            "ass-stmt",
            "exp",
            "exp-prime",
            "term",
            "term-prime",
            "factor",
            "add-oper",
            "mul-oper",
            "read-stmt",
            "write-stmt",
            "write-list",
            "more-write-value",
            "write-item",
            "if-stmt",
            "else-part",
            "while-stmt",
            "loop-stmt",
            "exit-stmt",
            "call-stmt",
            "condition",
            "relational-oper",
            "name-value",
            "value"
    );
    //fill the production on the hash map , i have used for easy access
    static Map<Integer, String[]> productions = new HashMap<Integer, String[]>();
    // define the parsing table according to the results of first and follow i did
    static Map<String, Map<String, Integer>> LL1parsingTable = new HashMap<>();

    static {
        LL1parsingTable.put("module-decl", new HashMap<String, Integer>() {{
            put("module", 1);
        }});
        LL1parsingTable.put("module-heading", new HashMap<String, Integer>() {{
            put("module", 2);
        }});
        LL1parsingTable.put("block", new HashMap<String, Integer>() {{
            put("begin", 3);
        }});
        LL1parsingTable.put("declarations", new HashMap<String, Integer>() {{
            put("const", 4);
            put("var", 4);
            put("procedure", 4);
            put("begin", 4);
        }});
        LL1parsingTable.put("const-decl", new HashMap<String, Integer>() {{
            put("const", 5);
            put("var", 6);
            put("procedure", 6);
            put("begin", 6);
        }});
        LL1parsingTable.put("const-list", new HashMap<String, Integer>() {{
            put("name", 7);
            put("var", 8);
            put("procedure", 8);
            put("begin", 8);
        }});
        LL1parsingTable.put("var-decl", new HashMap<String, Integer>() {{
            put("var", 9);
            put("procedure", 10);
            put("begin", 10);
        }});
        LL1parsingTable.put("var-list", new HashMap<String, Integer>() {{
            put("name", 11);
            put("procedure", 12);
            put("begin", 12);
        }});
        LL1parsingTable.put("var-item", new HashMap<String, Integer>() {{
            put("name", 13);
        }});
        LL1parsingTable.put("name-list", new HashMap<String, Integer>() {{
            put("name", 14);
        }});
        LL1parsingTable.put("more-names", new HashMap<String, Integer>() {{
            put(":", 16);
            put(",", 15);
            put(")", 16);
        }});
        LL1parsingTable.put("data-type", new HashMap<String, Integer>() {{
            put("integer", 17);
            put("real", 18);
            put("char", 19);
        }});
        LL1parsingTable.put("procedure-decl", new HashMap<String, Integer>() {{
            put("procedure", 20);
            put("begin", 21);
        }});
        LL1parsingTable.put("procedure-heading", new HashMap<String, Integer>() {{
            put("procedure", 22);
        }});
        LL1parsingTable.put("stmt-list", new HashMap<String, Integer>() {{
            put("name", 23);
            put("readint", 23);
            put("readreal", 23);
            put("readchar", 23);
            put("readln", 23);
            put("writeint", 23);
            put("writereal", 23);
            put("writechar", 23);
            put("writeln", 23);
            put("if", 23);
            put("while", 23);
            put("loop", 23);
            put("exit", 23);
            put("call", 23);
            put("begin", 23);
            put("until", 24);
            put("end", 24);
            put("else", 24);
            put(";", 23);
        }});
        LL1parsingTable.put("statement", new HashMap<String, Integer>() {{
            put("name", 25);
            put("readint", 26);
            put("readreal", 26);
            put("readchar", 26);
            put("readln", 26);
            put("writeint", 27);
            put("writereal", 27);
            put("writechar", 27);
            put("writeln", 27);
            put("if", 28);
            put("while", 29);
            put("loop", 30);
            put("exit", 31);
            put("call", 32);
            put("begin", 33);
            put(";", 34);
        }});
        LL1parsingTable.put("ass-stmt", new HashMap<String, Integer>() {{
            put("name", 35);
        }});
        LL1parsingTable.put("exp", new HashMap<String, Integer>() {{
            put("name", 36);
            put("(", 36);
            put("integer-value", 36);
            put("real-value", 36);
        }});
        LL1parsingTable.put("exp-prime", new HashMap<String, Integer>() {{
            put("+", 37);
            put("-", 37);
            put(";", 38);
            put(")", 38);
        }});
        LL1parsingTable.put("term", new HashMap<String, Integer>() {{
            put("integer-value", 39);
            put("real-value", 39);
            put("name", 39);
            put("(", 39);
        }});
        LL1parsingTable.put("term-prime", new HashMap<String, Integer>() {{
            put("*", 40);
            put("/", 40);
            put("mod", 40);
            put("div", 40);
            put("+", 41);
            put("-", 41);
            put(";", 41);
            put(")", 41);
        }});
        LL1parsingTable.put("factor", new HashMap<String, Integer>() {{
            put("(", 42);
            put("real-value", 43);
            put("integer-value", 43);
            put("name", 43);
        }});
        LL1parsingTable.put("add-oper", new HashMap<String, Integer>() {{
            put("+", 44);
            put("-", 45);
        }});
        LL1parsingTable.put("mul-oper", new HashMap<String, Integer>() {{
            put("*", 46);
            put("/", 47);
            put("mod", 48);
            put("div", 49);
        }});
        LL1parsingTable.put("read-stmt", new HashMap<String, Integer>() {{
            put("readint", 50);
            put("readreal", 51);
            put("readchar", 52);
            put("readln", 53);
        }});
        LL1parsingTable.put("write-stmt", new HashMap<String, Integer>() {{
            put("writeint", 54);
            put("writereal", 55);
            put("writechar", 56);
            put("writeln", 57);
        }});
        LL1parsingTable.put("write-list", new HashMap<String, Integer>() {{
            put("name", 58);
            put("integer-value", 58);
            put("real-value", 58);
        }});
        LL1parsingTable.put("more-write-value", new HashMap<String, Integer>() {{
            put(",", 59);
            put(")", 60);
        }});
        LL1parsingTable.put("write-item", new HashMap<String, Integer>() {{
            put("name", 61);
            put("integer-value", 62);
            put("real-value", 62);

        }});
        LL1parsingTable.put("if-stmt", new HashMap<String, Integer>() {{
            put("if", 63);
        }});
        LL1parsingTable.put("else-part", new HashMap<String, Integer>() {{
            put("else", 64);
            put("end", 65);
        }});
        LL1parsingTable.put("while-stmt", new HashMap<String, Integer>() {{
            put("while", 66);
        }});
        LL1parsingTable.put("loop-stmt", new HashMap<String, Integer>() {{
            put("loop", 67);
        }});
        LL1parsingTable.put("exit-stmt", new HashMap<String, Integer>() {{
            put("exit", 68);
        }});
        LL1parsingTable.put("call-stmt", new HashMap<String, Integer>() {{
            put("call", 69);
        }});
        LL1parsingTable.put("condition", new HashMap<String, Integer>() {{
            put("name", 70);
            put("integer-value", 70);
            put("real-value", 70);
        }});
        LL1parsingTable.put("relational-oper", new HashMap<String, Integer>() {{
            put("=", 71);
            put("|=", 72);
            put("<", 73);
            put("<=", 74);
            put(">", 75);
            put(">=", 76);
        }});
        LL1parsingTable.put("name-value", new HashMap<String, Integer>() {{
            put("name", 77);
            put("integer-value", 79);
            put("real-value", 78);
        }});
        LL1parsingTable.put("value", new HashMap<String, Integer>() {{
            put("integer-value", 79);
            put("real-value", 80);
        }});

    }

    {
        productions.put(1, new String[]{"module-heading", "declarations", "block", "name", "."});//"module-heading declarations block name .", // 0
        productions.put(2, new String[]{"module", "name", ";"});// "module name ;", // 1
        productions.put(3, new String[]{"begin", "stmt-list", "end"}); //"begin stmt-list end", // 2
        productions.put(4, new String[]{"const-decl", "var-decl", "procedure-decl"});// "const-decl var-decl procedure-decl", // 3
        productions.put(5, new String[]{"const", "const-list"});//"const const-list", // 4
        productions.put(6, new String[]{});  // epsilon
        productions.put(7, new String[]{"name", "=", "value", ";", "const-list"});//  "name = value ; const-list", // 6
        productions.put(8, new String[]{});  // epsilon
        productions.put(9, new String[]{"var", "var-list"});// "var var-list", // 8
        productions.put(10, new String[]{});  // epsilon
        productions.put(11, new String[]{"var-item", ";", "var-list"});//   "var-item ; var-list", // 10
        productions.put(12, new String[]{});  // epsilon
        productions.put(13, new String[]{"name-list", ":", "data-type"});//  "name-list : data-type", // 12
        productions.put(14, new String[]{"name", "more-names"});//  "name more-names", // 13
        productions.put(15, new String[]{",", "name-list"});// ", name-list", // 14
        productions.put(16, new String[]{});  // epsilon
        productions.put(17, new String[]{"integer"}); // "integer", // 16
        productions.put(18, new String[]{"real"});//   "real",//17
        productions.put(19, new String[]{"char"});//"char",//18
        productions.put(20, new String[]{"procedure-heading", "declarations", "block", "name", ";", "procedure-decl"});// "procedure-heading declarations block name ; procedure-decl", // 19
        productions.put(21, new String[]{});  // epsilon
        productions.put(22, new String[]{"procedure", "name", ";"});// "procedure name ;", // 21
        productions.put(23, new String[]{"statement", ";", "stmt-list"});//  "statement ; stmt-list", // 22
        productions.put(24, new String[]{});  // epsilon
        productions.put(25, new String[]{"ass-stmt"});//    "ass-stmt", // 24
        productions.put(26, new String[]{"read-stmt"});// "read-stmt",//25
        productions.put(27, new String[]{"write-stmt"});//  "write-stmt",//26
        productions.put(28, new String[]{"if-stmt"});//      "if-stmt",//27
        productions.put(29, new String[]{"while-stmt"});//     "while-stmt",//28
        productions.put(30, new String[]{"loop-stmt"});//   "loop-stmt",//29
        productions.put(31, new String[]{"exit-stmt"});//   "exit-stmt",//30
        productions.put(32, new String[]{"call-stmt"});//  "call-stmt",//31
        productions.put(33, new String[]{"block"});//   "block",//32
        productions.put(34, new String[]{});  // epsilon
        productions.put(35, new String[]{"name", ":=", "exp"});//     "name := exp", // 34
        productions.put(36, new String[]{"term", "exp-prime"});// "term exp-prime", // 35
        productions.put(37, new String[]{"add-oper", "term", "exp-prime"});// "add-oper term exp-prime", // 36
        productions.put(38, new String[]{});  // epsilon
        productions.put(39, new String[]{"factor", "term-prime"});// "factor term-prime", // 38
        productions.put(40, new String[]{"mul-oper", "factor", "term-prime"});// "mul-oper factor term-prime", // 39
        productions.put(41, new String[]{});  // epsilon
        productions.put(42, new String[]{"(", "exp", ")"});//  "( exp )", // 41
        productions.put(43, new String[]{"name-value"});//  "name-value",//42
        productions.put(44, new String[]{"+"});// "+", // 43
        productions.put(45, new String[]{"-"});// "-",//44
        productions.put(46, new String[]{"*"});//  "*", //45
        productions.put(47, new String[]{"/"});//  "/",//46
        productions.put(48, new String[]{"mod"});//   "mod",//47
        productions.put(49, new String[]{"div"});//  "div",//48
        productions.put(50, new String[]{"readint", "(", "name-list", ")"});// "readint ( name-list )", // 49
        productions.put(51, new String[]{"readreal", "(", "name-list", ")"});//  "readreal ( name-list )",//50
        productions.put(52, new String[]{"readchar", "(", "name-list", ")"});//  "readchar ( name-list )",//51
        productions.put(53, new String[]{"readln"});//   "readln",//52
        productions.put(54, new String[]{"writeint", "(", "write-list", ")"});// "writeint ( write-list )", // 53
        productions.put(55, new String[]{"writereal", "(", "write-list", ")"});//  "writereal ( write-list )",//54
        productions.put(56, new String[]{"writechar", "(", "write-list", ")"});// "writechar ( write-list )",//55
        productions.put(57, new String[]{"writeln"});// "writeln",//56
        productions.put(58, new String[]{"write-item", "more-write-value"});// "write-item more-write-value", // 57
        productions.put(59, new String[]{"write-list"});//  ", write-list", // 58
        productions.put(60, new String[]{});  // epsilon
        productions.put(61, new String[]{"name"});//   "name", // 60
        productions.put(62, new String[]{"value"});// "value",//61
        productions.put(63, new String[]{"if", "condition", "then", "stmt-list", "else-part", "end"});////     "if condition then stmt-list else-part end", // 62
        productions.put(64, new String[]{"else", "stmt-list"});//  "else stmt-list", // 63
        productions.put(65, new String[]{});  // epsilon
        productions.put(66, new String[]{"while", "condition", "do", "stmt-list", "end"});//  "while condition do stmt-list end", // 65
        productions.put(67, new String[]{"loop", "stmt-list", "until", "condition"});// "loop stmt-list until condition", // 66
        productions.put(68, new String[]{"exit"});// "exit", // 67
        productions.put(69, new String[]{"call", "name"});//   "call name", // 68
        productions.put(70, new String[]{"name-value", "relational-oper", "name-value"});//  "name-value relational-oper name-value", // 69
        productions.put(71, new String[]{"="});// "=", // 70
        productions.put(72, new String[]{"|="});//  "|=",//71
        productions.put(73, new String[]{"<"});//  "<",//72
        productions.put(74, new String[]{"<="});//  "<=",//73
        productions.put(75, new String[]{">"});//      ">",//74
        productions.put(76, new String[]{">="});//">=",//75
        productions.put(77, new String[]{"name"});//   "name", // 76
        productions.put(78, new String[]{"value"});// "value",//77
        productions.put(79, new String[]{"integer-value"});// "integer-value", // 78
        productions.put(80, new String[]{"real-value"});//    "real-value"//79
    }

    //this method to check if the value is  integer or not
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //this method to check if the value is  float or not

    public static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //this method is used to know if the value is a  Name , any string must start with letter and could have digit
    public static boolean isName(String str) {
        return str.matches("[a-zA-Z][a-zA-Z0-9]*");
    }

    // heres the method that parse the code
//    public static boolean parse(List<Tokens> tokens) {
//        Stack<String> stack = new Stack<>();// define a stack to push the non terminal start with the starting non terminal which is module-decl
//        stack.push("module-decl");
//        int pointer = 0;// this is to point at the index of the current token
//        while (!stack.isEmpty()) {// check if the stack is empty or not
//            String root = stack.peek();// get the root from the stak (اللي على القمة)
//            String activeToken = tokens.get(pointer);// get the token
//
//            System.out.println("Stack: " + stack);
//            System.out.println("Active token: " + activeToken);
//            if(isName(activeToken) && !reservedWords.contains(activeToken)){ // this method to check if the active token is name but not a reserved word
//                activeToken="name";
//            }
//            if(isInteger(activeToken)){// check if it is integer or not
//                activeToken="integer-value";
//            }
//            if(isFloat(activeToken)){// chek if it is float
//                activeToken="real-value";
//            }
//            if (nonTerminal.contains(root)) { // check root of stack is a non-terminal , if yes replace with the coresponded production rule
//                Integer ruleNum = LL1parsingTable.get(root).get(activeToken);//get the rule number
//                if (ruleNum == null) {// ==null mean that theres no production rule coresponds with the given root and active token
//                    return false; // rule not found !!
//                }
//                stack.pop(); // remove the non terminal from the stack
//                String[] production = productions.get(ruleNum);// get the production to put in the stack
//                for (int i = production.length - 1; i >= 0; i--) {// put the non terminal and terminal of the production on the stack
//                    if (!production[i].isEmpty()) {
//                        stack.push(production[i]);
//                    }
//                }
//            } else if (root.equals(activeToken)) { // If root of stack is a terminal pop the root of the stack , match the top wih terminal
//                stack.pop();
//                pointer++;
//            } else {
//                return false;// no match
//            }
//        }
//        return true;// every thing is ok
//    }
    public static String parse(List<Tokens> tokens) {
        String str = "";
        Stack<String> stack = new Stack<>(); // define a stack to push the non terminal start with the starting non terminal which is module-decl
        stack.push("module-decl");
        int pointer = 0; // this is to point at the index of the current token

        while (!stack.isEmpty()) {// check if the stack is empty or not
            String root = stack.peek();// get the root from the stak (اللي على القمة)
            if (pointer >= tokens.size()) {
                str += "unexpected end of input at line " + (tokens.isEmpty() ? 0 : tokens.get(tokens.size() - 1).getLine());
                return str;
            }
            Tokens token = tokens.get(pointer);// get the token
            String activeToken = token.getToken();

            System.out.println("Stack: " + stack);
//            System.out.println("Active token: " + activeToken + " at line " + activeTokenObj.getLine());

            if (isName(activeToken) && !reservedWords.contains(activeToken)) {// this method to check if the active token is name but not a reserved word
                activeToken = "name";
            }
            if (isInteger(activeToken)) {// check if it is integer or not
                activeToken = "integer-value";
            }
            if (isFloat(activeToken)) {// chek if it is float
                activeToken = "real-value";
            }

            if (nonTerminal.contains(root)) {// check root of stack is a non-terminal , if yes replace with the coresponded production rule
                Integer ruleNum = LL1parsingTable.get(root).get(activeToken);//get the rule number
                if (ruleNum == null) {// ==null mean that theres no production rule coresponds with the given root and active token
                    str += "Error at line " + token.getLine() + ": no production for [" + root + ", " + activeToken + "]";
                    return str;
                }
                stack.pop(); // remove the non terminal from the stack
                String[] production = productions.get(ruleNum);// get the production to put in the stack
                for (int i = production.length - 1; i >= 0; i--) {// put the non terminal and terminal of the production on the stack
                    if (!production[i].isEmpty()) {
                        stack.push(production[i]);
                    }
                }
            } else if (root.equals(activeToken)) {// If root of stack is a terminal pop the root of the stack , match the top wih terminal
                stack.pop();
                pointer++;
            } else {
                str += "Error at line " + token.getLine() + ": expected " + root + " but found " + activeToken;// no match
                return str;
            }
        }
        if (pointer != tokens.size()) {
            str += "error";
            return str;
        }
        return "parsing compleated successfully";// every thing is ok
    }


}

