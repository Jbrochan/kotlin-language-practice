fun main(){

    // null 을 허용하는 변수 선언
    var a1: String? = null
    // null 을 허용하지 않는 변수 선언
    // var a2: String = null

    testFunction1("안녕하세요")
    // testFun1(null)

    testFunction2("안녕하세요")
    testFunction2(null)

    testFunction3("안녕하세요")
    testFunction3(null)

    val t100 = TestClass1(100, "문자열1")
    testFunction4(t100)

    // testFunction1(null)

    testFunction5(t100)

    println("--------------------------------")

    testFunction6(null)

    println("--------------------------------")

    testFunction6(t100)

    println("--------------------------------")

    testFunction6(null)

}

fun testFunction1(str1: String?){
    // !! 연산자
    // null 을 허용하는 타입의 변수 값을 null 을 허용하지 않는 타입으로 변환하여
    // null 을 허용하지 않는 타입의 변수에 담을 수 있도록 한다.
    val value1: String = str1!!
    println("value : $value1")
}

fun testFunction2(str1: String?){
    // str1 에 null 아닌 객체의 ID가 들어있으면 그 ID를 value1 변수에 담아주고
    // null 이 있으면 지정한 값인 "기본문자열" 객체가 value1 변수에 담긴다.
    val value1: String = str1 ?: "기본문자열"
    println("value1 : $value1")
}

fun testFunction3(str1: String?){
    // 만약 변수의 값이 null 인 경우 코드가 동작하지 않도록 처리해주면 null 안정성을 확보할 수 있다.
    // 이 때, null 을 허용하는 변수를 null 을 허용하지 않는 변수처럼 자유롭게 사용할 수 있다.
    if(str1 != null){
        val value1: String = str1
        println("value1 : $value1")
    }
}

class TestClass1(var str1: Int, var str2: String){
    fun testMethod1(){
        println("TestClass1의 testMethod1")
    }
}

fun testFunction4(t1: TestClass1?){
    // ? 변수를 통해 객체의 멤버에 접근한다.
    // null 안정성을 확보하지 않고 멤버에 접근하겠다면 !!를 붙혀준다.

    // !! 연산자는 null 을 허용하는 변수에 담긴 값을 추출하여
    // null 을 허용하지 않는 타입으로 변환하는 연산자이다. 이에 null 값이 들어있으면 오류가 발생한다.
    // 같은 블록에서 한 번 !!연산자를 사용한 참조 변수는 다시 사용하지 않아도 된다.
    println("t1.str1 : ${t1!!.str1}")
    println("t1.str2 : ${t1.str2}")
    t1.testMethod1()
}

fun testFunction5(t1: TestClass1?){
    // ? 연산자
    // 참조변수?.멤버변수 : 참조변수에 null 값이 들어 있다면 null 이 반환된다.
    // 참조변수?.멤버메서드 : 참조변수에 null 값이 들어 있다면 메서드를 호출하지 않는다.
    println("t1.str1 : ${t1?.str1}")
    println("t1.str2 : ${t1?.str2}")
    t1?.testMethod1()
}

fun testFunction6(t1: TestClass1?){
    // null 이 저장되어 있는지 여부를 확인한다.
    // if 문 내부에서는 null 을 아무런 연산자도 붙히지 않고 멤버 접근이 가능하다.
    if(t1 != null){
        println("t1.str1 : ${t1.str1}")
        println("t1.str2 : ${t1.str2}")
        t1.testMethod1()
    }
}