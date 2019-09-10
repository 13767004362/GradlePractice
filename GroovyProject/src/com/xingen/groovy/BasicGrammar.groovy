package com.xingen.groovy

/**
 * author：HeXinGen
 * date: 2019/9/9
 * description:  groovy 的基本语法
 */
class BasicGrammar {

    /**
     *  测试定义方法的动态类型参数
     */
    public static void printMethodArg() {
        def content = "groovy 输出日志"
        printLog(content)
    }
    /**
     *  groovy 支持动态类型，即定义变量时可以不指定类型
     *  通常使用def 定义变量，但也可以省略
     */
    public static void printVariable() {
        def int variable1 = 110; // 使用def时，可以指定具体类型
       def variable2 = "119"  // 使用def 修饰，支持动态类型，不指定具体类型
        int variable3 = 120  // 不使用def 修饰，需要指定具体的参数类型

        printLog(variable1)
        printLog(variable2)
        printLog(variable3)
    }
    /**
     *  测试 def修饰动态类型返回值的方法
     */
    public static void printMethodReturn() {
        String type1 = "110"
        def type2 = 119
        printLog(filterContent(type1))
        printLog(filterContent(type2))
    }
    /**
     * groovy 支持定义方法返回值是动态类型，不指定具体类型,但必须使用def修饰
     * 定义方法中，可以不使用return 语句来返回值，但默认最后一行执行结果是返回值
     */
    static def filterContent(type) {
        // 使用 def 定义动态类型的变量，不指定具体类型
        def content
        switch (type) {
            case "110":
                content = 110
                break
            case 119:
                // 不使用return语句，则最后一行默认是返回值，这里则返回字符类型
                content = "119"
                break
            case "120":
                //  不使用return语句，则最后一行默认是返回值，这里则返回int类型
                content = 120
                break
            default:
                content = "无法匹配"
                break
        }
    }
    /**
     * groovy 对字符串的支持
     *
     *  单引号''中的内容严格对应Java中的String，不对$符号进行转义
     *  双引号""的内容则和脚本语言的处理有点像，如果字符中有$号的话，则它会$表达式先求值
     *  三个引号'''xxx'''中的字符串支持随意换行
     */
    public static void printString() {
        def s = 110
        //单引号的字符直接输出原本内容
        def str1 = '拨打电话$s'
        //双引号的字符串可以对特殊符号转义
        def str2 = "拨打电话$s"
        // 三个引号的字符串换行
        def str3 =
                '''start 
                   第一行
                   第二行
                   第三行....
                   end'''
        printLog(str1)
        printLog(str2)
        // 在groovy 中方法调用 可以不带括号
        printLog str3
    }


    /**
     *  groovy 的注释与java一致
     *
     *  定义方法时，参数可以是动态类型，不指定参数类型
     */
    private static void printLog(content) {
        // groovy 语句可以不用分号结尾
        println(content)
    }
}
