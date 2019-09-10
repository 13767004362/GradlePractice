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
