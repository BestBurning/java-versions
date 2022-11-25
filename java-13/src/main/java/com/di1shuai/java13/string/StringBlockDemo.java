package com.di1shuai.java13.string;

/**
 * @author Shea
 * @date 2021-01-22
 * @description """
 * <p>
 * """
 * 来书写多行文本
 */
public class StringBlockDemo {

    public static void main(String[] args) {
        // < 13
        String htmlBefore13 = "<html>\n" +
                "    <body>\n" +
                "        <p>Hello, Java 13</p>\n" +
                "    </body>\n" +
                "</html>\n";
        System.out.println(htmlBefore13);

        //13
        String html13 = """
                <html>
                    <body>
                        <p>Hello,  Java 13</p>
                    </body>
                </html>
                """;
        System.out.println(html13);
        String html13Var = """
                 <html>
                    <body>
                        <p>Hello,  %s %d</p>
                    </body>
                </html>
                                
                """.formatted("Java", 13);

        System.out.println(html13Var);

    }

}
