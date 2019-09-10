package grovyscript

import com.xingen.groovy.TestData
import groovy.transform.Field

/**
 * author：HeXinGen
 * date: 2019/9/10
 * description: 一个Groovy脚本，测试导包和脚本定义属性的作用域
 */

// 在groovy 脚本中导包
def  data=new TestData("HeXinGen",26)
data.printData()


/**
 * groovy 脚本中定义一个属性，
 * 在脚本本身使用，不需要添加def 或者指定具体类型
 */
attribute_s="定义一个属性"

private  def printAttribute(){
    println( attribute_s)
}
// 直接调用脚本本身定义的方法
printAttribute()


/**
 *  groovy脚本中定义一个属性，若是需要外部脚本使用，需要添加@Field
 */
@Field attribute_l=110
def  printAttribute2(){
    println("外部脚本访问："+attribute_l)
}