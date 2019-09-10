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
