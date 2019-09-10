package grovyscript
/**
 * author：HeXinGen
 * date: 2019/9/10
 * description: IO测试,  读取properties和读写IO
 */


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

// 测试 io

copyFile()

readProperties()