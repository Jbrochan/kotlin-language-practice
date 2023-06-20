import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader
import java.util.*

class MemoCategoryOverview {
    val scanner = Scanner(System.`in`)
    val file = File("memo.json")
    val jsonParser = JSONParser()

    // 메모 내용 전체 보기
    fun runMemoCategoryOverview(){
        val fileReader = FileReader(file)
        val jsonArray = returnJsonArray(fileReader)

        for(index in 1 until jsonArray.size){
            println("\n-----------------------------------------")
            println("${((jsonArray[index] as JSONArray)[0] as JSONObject)["카테고리 이름"]}")
            println("-----------------------------------------")
            for(memoObject in ((jsonArray[index] as JSONArray)[1] as JSONArray)){
                println("\n제목 : ${(memoObject as JSONObject)["메모 제목"]}")
                println("내용 : ${(memoObject as JSONObject)["메모 내용"]}")
            }
        }
    }

    // JSON 파일을 읽어 전체 json array 를 반환하는 메서드
    private fun returnJsonArray(fileReader: FileReader) : JSONArray {
        return jsonParser.parse(fileReader) as JSONArray
    }
}