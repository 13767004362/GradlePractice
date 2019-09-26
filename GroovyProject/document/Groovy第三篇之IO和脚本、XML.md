### **1. 脚本**

在java源文件中,不能不写class或者interface。Groovy可以写脚本一样，将想做的事情，写在一起。


#### **1.1 脚本导包**

在指定包路径下创建一个Groovy类,名为TestData.groovy,如下：

```
package com.xingen.groovy

/**
 * author：HeXinGen
 * date: 2019/9/10
 * description: 用于测试导入脚本中
 */
class TestData {
    String name
    int  age;
    TestData(String name, int age) {
        this.name = name
        this.age = age
    }

    def  printData(){
        println("name: "+this.name+" \nage :"+this.age)
    }
}
```
然后在创建一个test.groovy的脚本,导包导入TestData这个类,调用其方法：

```
package grovyscript

import com.xingen.groovy.TestData

/**
 * author：HeXinGen
 * date: 2019/9/10
 * description: 一个Groovy脚本，测试导包和脚本定义属性的作用域
 */

// 在groovy 脚本中导包
def  data=new TestData("HeXinGen",26)
data.printData()

```
运行test.groovy这个脚本，结果如下：
```
name: HeXinGen 
age :26
```
#### **2. Groovy脚本中定义属性和方法**

**2.1 定义脚本内部使用的属性和方法**

在脚本内部定义一个动态类型的属性,可以省略def。

接下来,创建一个test的groovy脚本,定义一个属性和一个方法，然后调用。
```

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

```
执行该脚本,输出结果如下：

```
定义一个属性
```

**2.2 定义供外部脚本使用的属性**：

创建一个test的groovy脚本,定义一个属性和一个方法,供外部脚本使用。
```
/**
 *  groovy脚本中定义一个属性，若是需要外部脚本使用，需要添加@Field
 */
@Field attribute_l=110
def  printAttribute2(){
    println("外部脚本访问："+attribute_l)
}
```
新创建一个名为test1的groovy脚本,去使用：
```
package grovyscript
/**
 * author：HeXinGen
 * date: 2019/9/10
 * description: 访问其他脚本中的属性
 */

def  test=new test()
test.printAttribute2()
```
运行test1这个脚本,结果如下：
```
外部脚本访问：110
```
### **2. IO**

Groovy的文件I/O是在原有Java I/O操作上进行更简单方便的封装，且使用Closure闭包来简化代码编写。

通过一个读取配置文件的案例,进一步理解。

在目录下,创建一个配置文件load.propertis:

```
development_language=groovy
```
接下来,创建IOTest.groovy的脚本文件。

在脚本中编写读取配置信息。

```
package grovyscript

/**
 *  读取 properties文件
 */
def readProperties() {
    def properties = new Properties()
    def file = new File("load.properties")
    properties.load(file.newInputStream())
    def enumeration = properties.propertyNames()
    while (enumeration.hasMoreElements()) {
        def key = enumeration.nextElement()
        def value = properties.getProperty(key)
        print("properties中 key= " + key + " , value =" + value)
    }
}

readProperties()
```
运行脚本,输出结果：
```
properties中 key= development_language , value =groovy
```

接下来，体验一下文件拷贝功能：

```
/**
 *  拷贝一个文件
 * @return
 */
def copyFile() {
    def originFile = new File("load.properties")
    def newFile = new File("new.properties")
    originFile.withInputStream {
        inputStream ->
            newFile.withOutputStream {
                outputStream -> outputStream << inputStream
            }
    }
}

copyFile()
```
运行脚本,会在同目录下出现一个名为new.properties的文件。



### **3.XML解析**

Groovy中XML操作很简单,提供了GPath响应的API。

创建一个test.xml的文件：
```
<response version-api="2.0" >
    <value>
        <books>
            <book available="20" id="1">
                <title>Don Xijote</title>
                <author id="1">Manuel De Cervantes</author>
            </book>
            <book available="14" id="2">
                <title>Catcher in the Rye</title>
                <author id="2">JD Salinger</author>
            </book>
        </books>
    </value>
</response>
```
创建一个XmlTest.groovy脚本,用于读取xml中信息,编写代码如下:
```groovy
package grovyscript
/**
 * author：HeXinGen
 * date: 2019/9/10
 * description:  xml 测试
 */

//第一步，创建XmlSlurper类
def  parser=new XmlSlurper()
def  file=new File("test.xml")
// GPathResult代表着xml中最外层的元素，这里是response
def result=parser.parse(file)
// 通过e1.e2.e3能够访问到各级子元素
def  book=result.value.books.book[1]

// 获取元素属性，以元素.@id 或者  元素['@id']
def  version_api=result.@'version-api'
def book_id=book['@id']
//获取元素的内容 text()
def author=book.author.text()
print("解析 test.xml  其中，version-api是 "+version_api+" \n第二本书籍的id是 "+book_id+",作者是 "+author)
```
运行脚本,输出结果如下：

```groovy
解析 test.xml  其中，version-api是 2.0 
第二本书籍的id是 2,作者是 JD Salinger
```
