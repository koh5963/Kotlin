import java.io.File
fun main() {
    val path = "./arrays.csv"
    val file = File(path)
    if (!file.exists() || file.length() == 0L) {
        println("input data {$path} is missing")
        return
    }
    file.forEachLine {
        val tmp = it.split(",")
        if (isCommaSeparatedNumbers(it)) {
            val ary = tmp.map { it.toInt() }.toTypedArray()
            println("ソート前 : ${ary.joinToString()}")
            val result = quickSort(ary)
            println("ソート後 : ${result.joinToString()}")
            println()
        } else {
            println("input data format is wrong ${tmp}")
        }
    }
}

/**
 * ソート処理
 * @param ary
 *          ソート対象
 * @return ソート後配列
 */
fun quickSort(ary: Array<Int>): Array<Int> {
    if (ary.size <= 1) {
        return ary
    }
    val pivot: Int = ary[ary.size - 1]
    // pivotと同値要素ソート
    val pivotCnt = ary.count { it == pivot }

    val left = ary.filter { it < pivot }.toTypedArray()
    val right = ary.filter { it > pivot }.toTypedArray()

    val sortedLeft = quickSort(left)
    val sortedRight = quickSort(right)

    return sortedLeft + Array(pivotCnt) {pivot} + sortedRight
}

/**
 * カンマ区切り数値判定
 */
fun isCommaSeparatedNumbers(input: String): Boolean {
    val regex = "^\\d+(,\\d+)*$".toRegex()
    return regex.matches(input)
}