fun main(){
    val t1 = TestClass1()
    val t2 = TestClass2()
    val t3 = TestClass3()

    t1.abstractMethod1()
    t2.abstractMethod2()
    t3.abstractMethod3()

    val t4 = TestClass4()
    t4.interfaceMethod1()
    t4.interfaceMethod2()
    t4.interfaceMethod3()

    testFunction1(t1)
    testFunction2(t2)

    testFunction3(t4)
    testFunction4(t4)
}

fun testFunction1(t100:Abstract1){
    t100.abstractMethod1()
}
fun testFunction2(t200:Abstract2){
    t200.abstractMethod2()
}
fun testFunction3(t300:Inter1){
    t300.interfaceMethod1()
}
fun testFunction4(t400:Inter2){
    t400.interfaceMethod2()
}

// 추상 클래스
abstract class Abstract1{
    abstract fun abstractMethod1()
}

abstract class Abstract2{
    abstract fun abstractMethod2()
}

abstract class Abstract3{
    abstract fun abstractMethod3()
}


// 추상 클래스도 결국에는 클래스이므로 하나의 클래스당 하나의 추상 클래스만 상속이 가능하다
class TestClass1 : Abstract1(){
    override fun abstractMethod1() {
        println("TestClass1의 abstractMethod1")
    }
}

class TestClass2 : Abstract2(){
    override fun abstractMethod2() {
        println("TestClass2의 abstractMethod2")
    }
}

class TestClass3 : Abstract3(){
    override fun abstractMethod3() {
        println("TestClass3의 abstractMethod3")
    }
}

// 인터페이스
interface Inter1{
    fun interfaceMethod1()
}

interface Inter2{
    fun interfaceMethod2()
}

interface Inter3{
    fun interfaceMethod3()
}

// 인터페이스를 정의한 클래스
class TestClass4 : Inter1, Inter2, Inter3{
    override fun interfaceMethod1() {
        println("TestClass4의 interMethod1")
    }

    override fun interfaceMethod2() {
        println("TestClass4의 interfaceMethod2")
    }

    override fun interfaceMethod3() {
        println("TestClass4의 interfaceMethod3")
    }
}