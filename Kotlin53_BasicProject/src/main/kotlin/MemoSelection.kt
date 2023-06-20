import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

class MemoSelection(private val categoryNum: Int) {
    private val scanner = Scanner(System.`in`)
    private val file = File("memo.json")
    private val jsonParser = JSONParser()

    // 메모 카테고리 선택에서 선택된 카테고리별 메모
    fun runMemoSelection(){
        while(true){
            try{
                printMemo()
                print("1. 메모보기, 2. 메모등록, 3. 메모수정, 4. 메모삭제, 5. 이전 : ")
                when(val userInput = scanner.nextInt()){
                    // 1. 메모보기
                    MemoStatus.MEMO_SHOW.userInput -> {
                        memoShowing()
                    }
                    // 2. 메모등록
                    MemoStatus.MEMO_REGISTRATION.userInput -> {
                        memoRegistration()
                    }
                    // 3. 메모수정
                    MemoStatus.MEMO_MODIFICATION.userInput -> {
                        memoModification()
                    }
                    // 4. 메모삭제
                    MemoStatus.MEMO_DELETION.userInput -> {
                        memoDeletion()
                    }
                    // 5. 이전
                    MemoStatus.MEMO_BACK.userInput -> {
                        return
                    }
                    else -> {
                        // 그 외의 번호 입력
                        println("잘못 입력하였습니다\n")
                    }
                }
            } catch (e: Exception){
                scanner.nextLine()
                println("잘못 입력하였습니다\n")
            }
        }
    }

    // 등록된 메모를 출력하는 메서드
    private fun printMemo(){
        val fileReader = FileReader("memo.json")
        val jsonArray = returnJsonArray(fileReader)
        val categoryArray = jsonArray[categoryNum] as JSONArray

        if(categoryArray.size == 1){
            println("\n등록된 메모가 없습니다")
        } else{
            println()
            for(index in 0 until (categoryArray[1] as JSONArray).size){
                println("${index+1} : ${((categoryArray[1] as JSONArray)[index] as JSONObject)["메모 제목"]}")
            }
        }
        println()
    }

    // 메모보기 메서드
    private fun memoShowing(){
        while(true){
            try{
                print("확인할 메모의 번호를 입력해주세요 (0. 이전) : ")
                val showingMemoNum = scanner.nextInt()
                val fileReader = FileReader(file)
                val jsonArray = returnJsonArray(fileReader)
                val categoryArray = jsonArray[categoryNum] as JSONArray

                // 사용자가 0을 입력하면 메서드 종료
                if(showingMemoNum == 0){
                    return
                }

                println("제목 : ${((categoryArray[1] as JSONArray)[showingMemoNum-1] as JSONObject)["메모 제목"]}")
                println("내용 : ${((categoryArray[1] as JSONArray)[showingMemoNum-1] as JSONObject)["메모 내용"]}")
                print("이전으로 돌아가려면 0을 입력하세요 : ")

                // 사용자가 0을 입력하면 메서드 종료
                val userInput = scanner.nextInt()
                if(userInput == 0){
                    return
                }

            } catch (e: Exception){
                scanner.nextLine()
                println("잘못 입력하였습니다\n")
            }
        }

    }
    // 메모등록 메서드
    private fun memoRegistration(){
        try{
            scanner.nextLine()    // nextLine()을 위한 \n 문자 처리
            // 새롭게 등록학 메모 JSON Object
            print("메모 제목 : ")
            val memoHead = scanner.nextLine()
            print("메모 내용 : ")
            val memoBody = scanner.nextLine()
            val memoObject = JSONObject()
            memoObject["메모 제목"] = memoHead
            memoObject["메모 내용"] = memoBody

            val fileReader = FileReader(file)
            val jsonArray = returnJsonArray(fileReader)
            val categoryArray = jsonArray[categoryNum] as JSONArray

            val fileWriter = FileWriter(file)

            // 메모를 새롭게 등록
            if(categoryArray.size == 1){
                val newMemoArray = JSONArray()
                newMemoArray.add(memoObject)
                categoryArray.add(newMemoArray)
                jsonArray[categoryNum] = categoryArray
                fileWriter.write(jsonArray.toJSONString())
                fileWriter.flush()
                fileWriter.close()
            } else{
                (categoryArray[1] as JSONArray).add(memoObject)
                jsonArray[categoryNum] = categoryArray
                fileWriter.write(jsonArray.toJSONString())
                fileWriter.flush()
                fileWriter.close()
            }

        } catch(e: Exception){
            scanner.nextLine()
            println("다시 입력해주세요\n")
        }
    }
    // 메모수정 메서드
    private fun memoModification(){
        while(true){
            try{
                print("수정할 메모의 번호를 입력해주세요 (0. 이전) : ")
                val modificationMemoNum = scanner.nextInt()
                val fileReader = FileReader(file)
                val jsonArray = returnJsonArray(fileReader)
                val categoryArray = jsonArray[categoryNum] as JSONArray


                if(modificationMemoNum in 1 .. (categoryArray[1] as JSONArray).size){
                    scanner.nextLine()    // nextLine()을 위한 \n 문자 처리
                    val fileWriter = FileWriter(file)

                    // 새로운 제목 입력
                    println("제목 : ${((categoryArray[1] as JSONArray)[modificationMemoNum-1] as JSONObject)["메모 제목"]}")

                    print("메모의 새로운 제목을 입력해주세요(0 입력시 무시합니다) : ")
                    val newMemoHead = scanner.nextLine()

                    // 새로운 내용 입력
                    println("내용 : ${((categoryArray[1] as JSONArray)[modificationMemoNum-1] as JSONObject)["메모 내용"]}")
                    print("메모의 새로운 내용을 입력해주세요(0 입력시 무시합니다) : ")
                    val newMemoBody = scanner.nextLine()

                    // 0이 아닌 경우에 메모 제목과 내용을 수정
                    if(newMemoHead != "0"){
                        ((categoryArray[1] as JSONArray)[modificationMemoNum-1] as JSONObject)["메모 제목"] = newMemoHead
                    }
                    if(newMemoBody != "0"){
                        ((categoryArray[1] as JSONArray)[modificationMemoNum-1] as JSONObject)["메모 내용"] = newMemoBody
                    }

                    // 결과 반영
                    jsonArray[categoryNum] = categoryArray
                    fileWriter.write(jsonArray.toJSONString())
                    fileWriter.flush()
                    fileWriter.close()
                } else{
                    println("잘못 입력하였습니다\n")
                }
                return
            }catch (e: Exception){
                scanner.nextLine()
                println("다시 입력해주세요\n")
            }
        }
    }
    // 메모삭제 메서드
    private fun memoDeletion(){
        while(true) {
            try {
                val fileReader = FileReader(file)
                val jsonArray = returnJsonArray(fileReader)
                val categoryArray = jsonArray[categoryNum] as JSONArray

                print("삭제할 메모의 번호를 입력해주세요 (0. 이전) : ")
                val deletionMemoNum = scanner.nextInt()

                if (deletionMemoNum in 1..(categoryArray[1] as JSONArray).size) {
                    val fileWriter = FileWriter(file)

                    // 선택한 번호 삭제
                    (categoryArray[1] as JSONArray).removeAt(deletionMemoNum-1)

                    // 결과 반영
                    jsonArray[categoryNum] = categoryArray
                    fileWriter.write(jsonArray.toJSONString())
                    fileWriter.flush()
                    fileWriter.close()
                    return
                } else if(deletionMemoNum == 0){
                    return
                } else {
                    println("잘못 입력하였습니다\n")
                    continue
                }
            } catch (e: Exception){
                scanner.nextLine()
                println("다시 입력해주세요\n")
            }
        }

    }

    // JSON 파일을 읽어 전체 json array 를 반환하는 메서드
    private fun returnJsonArray(fileReader: FileReader) : JSONArray {
        return jsonParser.parse(fileReader) as JSONArray
    }
}

enum class MemoStatus(val userInput: Int){
    MEMO_SHOW(1), MEMO_REGISTRATION(2), MEMO_MODIFICATION(3), MEMO_DELETION(4), MEMO_BACK(5)
}