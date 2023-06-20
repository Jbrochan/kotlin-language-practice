import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader
import java.util.*

class MemoCategorySelection {
    val scanner = Scanner(System.`in`)
    val file = File("memo.json")
    val jsonParser = JSONParser()

    // 메모 카테고리 선택
    fun runMemoCategorySelection(){
        val fileReader = FileReader("memo.json")
        val jsonArray = returnJsonArray(fileReader)

        while(true){
            try{
                // 등록되어 있는 카테고리 출력
                printCategory()

                // 관리하고 싶은 카테고리 선택
                print("선택할 카테고리 번호를 입력해주세요(0. 이전) : ")
                val selectionCategoryNum = scanner.nextInt()
                if( selectionCategoryNum == 0){
                    return
                } else if (selectionCategoryNum in 1..jsonArray.size){
                    val memoSelection = MemoSelection(selectionCategoryNum)
                    memoSelection.runMemoSelection()
                } else {
                    println("다시 입력해주세요\n")
                }
            } catch (e: Exception){
                scanner.nextLine()
                println("다시 입력해주세요\n")
            }
        }
    }

    // 등록되어있는 카테고리를 출력하는 메서드
    private fun printCategory(){
        val fileReader = FileReader("memo.json")
        val jsonArray = returnJsonArray(fileReader)

        if(jsonArray.size == 1){
            println("\n등록된 카테고리가 없습니다")
        } else{
            println()
            for(index in 1 until jsonArray.size){
                println("$index : ${((jsonArray[index] as JSONArray)[0] as JSONObject)["카테고리 이름"]}")
            }
        }
    }

    // JSON 파일을 읽어 전체 json array 를 반환하는 메서드
    private fun returnJsonArray(fileReader: FileReader) : JSONArray {
        return jsonParser.parse(fileReader) as JSONArray
    }
}