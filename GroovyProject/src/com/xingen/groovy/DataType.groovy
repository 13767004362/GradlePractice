package com.xingen.groovy

/**
 * author：HeXinGen
 * date: 2019/9/9
 * description:  groovy 中的数据类型
 *
 * 作为动态语言，Groovy世界中的所有事物都是对象。
 *
 * 1. java中的基本数据类型
 * 2. Groovy中的容器类
 * 3. 闭包
 */
class DataType {

    /**
     *  java中基本数据类型，在Groovy中是包装类
     */
    public static void printBasicType() {
        def d = true   // 在groovy中是Boolean类型
        def i = 110  // 在groovy中是 Integer类型
        def s = '120'  // 在groovy中是String类型
        printLog(d.getClass().getSimpleName())
        printLog(i.getClass().getSimpleName())
        printLog(s.getClass().getSimpleName())
    }
    /**
     *  groovy中List容器
     */
    public static void printList() {
        // def 定义一个List对象，可以存放任意类型的数据
        def list = [110, "119", '120']
        def equals = list[1] == '119'
        printLog("List中第二个元素是否等于119:" + equals)
        // 任意角标添加元素
        list[5] = 122
        printLog("List中长度:" + list.size())
    }
    /**
     * groovy中Map
     *
     * Map由[:]定义，注意其中的冒号。冒号左边是key，右边是Value。key必须是字符串，value可以是任何对象。
     */
    public static void printMap() {
        def key1 = "急救电话"
        def key2 = "火警电话"
        def value1 = 119
        // def 定义一个key-value的map
        def map = [
                '公安电话'    : 110,
                (key1)    : "120", // 引用其他变量作为key
                "$key2"   : value1,
                anotherKey: "其他的值" //key可以不加单引号或者双引号，但这里会默认处理为"字符串"
        ]
        // 修改map的key对应的value
        map.anotherKey = true
        printLog(" map中的数据是：-->" + map.toString())
    }

    /**
     *  groovy中range是对list的一种扩展
     *
     *  Range类型的变量 由begin值+两个点+end值表示
     */
    public static void printRange() {
        // def 定义一个range类型的变量，包含a,b,c
        def range1 = "a".."c"
        def range2 = 1..<3 // 不想包含最后一个元素，这里的值是1,2
        def stringBuffer = new StringBuffer();
        for (def item in range1) {
            stringBuffer.append(item + " ")
        }
        printLog("range1 中元素是 --> " + stringBuffer)
        printLog("range2 最后一个元素是:--> " + range2.to)
    }

    /**
     *  在Groovy中，闭包Closure一种数据类型或者是一种概念,一段可执行的代码，用{}花括号包裹。
     */
    public static void printClosure() {
        printLog(closure.call("闭包对象.call(参数)调用"))
        printLog(closure2("闭包对象(参数)调用 + "))
        printLog(closure3())
        // 当函数的最后一个参数为闭包时，调用该方法，可以省略圆括号。
        testClosure 1, 1, { printLog("加法结果:" + it) }
    }
    /**
     *  定义一个有参数的闭包
     *  def xxx = { paramters -> code }*  或者 def xxx = {无参数，纯code} 这种case不需要->符号
     */
    static def closure = {
        String content ->  // 参数
            "有定义参数，存在code的闭包：-->" + content  //最好一行，返回值
    }
    /**
     * 定义一个无参数的闭包，隐含参数
     *
     * 闭包没定义参数的话，则隐含有一个参数，这个参数名字叫it，和this的作用类似。it代表闭包的参数。
     */
    static def closure2 = {
        it + " 有隐含参数it，存code的闭包 "
    }
    /**
     * 无参数的闭包，不包含隐形参数it
     */
    static def closure3 = {
        // 闭包定义时，采用下面这种写法，则表示闭包没有参数！
        -> "无参数，存在code的闭包"
    }

    /**
     *  定义一个包含闭包的方法
     */
    static def testClosure(int a, int b, Closure closure) {
        int total = a + b
        closure(total) // 调用闭包传递参数
    }

    /**
     *  打印日志
     * @param content
     * @return
     */
    private static def printLog(content) {
        println(content)
    }
}
