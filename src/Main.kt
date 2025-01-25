fun main() {
    val stroke = "12.3"
    println(stroke)
    stringConversion(stroke)
    println()

    val argumentOne = "Это строка"
    println(argumentOne)
    println(checkingEnteredArgument(argumentOne))
    val argumentTwo = 1
    println(argumentTwo)
    println(checkingEnteredArgument(argumentTwo))
    val argumentThree = 12.3
    println(argumentThree)
    println(checkingEnteredArgument(argumentThree))
    val argumentFour = 'f'
    println(argumentFour)
    println(checkingEnteredArgument(argumentFour))
    val argumentFive = arrayOf(1,2,3,4,5,6)
    println(argumentFive.contentDeepToString())
    println(checkingEnteredArgument(argumentFive))
    val argumentSix = listOf(1,2,3,4,5,6)
    println(argumentSix)
    println(checkingEnteredArgument(argumentSix))
    val argumentSeven = setOf(1,2,3,4,5,6)
    println(argumentSeven)
    println(checkingEnteredArgument(argumentSeven))
    println()

    val paramsOne = 5
    println(paramsOne)
    println(function(paramsOne))
    val paramsTwo = "Это строка"
    println(paramsTwo)
    println(function(paramsTwo))
    val paramsThree = arrayOf(1,2,3,4,5,6)
    println(paramsThree.contentDeepToString())
    println(function(paramsThree))
}

/*
Дана строка, содержащая в себе значение 12.3.
Преобразовать ее в целое число, число с плавающей точкой, число в двоичной системе представления и вывести в консоль.
Для части преобразований, например, в двоичное представление, необходимо сделать несколько преобразований,
чтобы целое число перевести в двоичное.
*/
fun stringConversion(stroke: String){
    try {
        println("целое - ${stroke.toDouble().toInt()}")
        println("с плавающей точкой - ${stroke.toDouble()}")
        println("в двоичной системе - ${stroke.toDouble().toInt().toString(2)}")
    }
    catch (e: NumberFormatException){
        println(e.message)
    }
}

/*
Написать функцию, которая проверяет чем, является вводимый аргумент строкой, числом, числом с плавающей точкой, символом.
Ввод должен быть таким: «Это строка».
*/
fun <T> checkingEnteredArgument(argument: T): String? {
    try {
        argument as Number
        argument as String
    }
    catch (e: ClassCastException){
        return "Это " + when {
            e.message?.substring(0, 16) == "class java.lang." -> {
                e.message?.substring(16)?.substringBefore(" ")
            }
            e.message?.substring(0, 16) == "class java.util." -> {
                e.message?.substring(16)?.substringBefore(" ") + "<" + e.message?.
                substringAfter("cannot be cast to class java.lang.")?.
                substringBefore(" (java.util.") + ">"
            }
            e.message?.substring(0, 18) == "class [Ljava.lang." -> {
                "Arrays<" + e.message?.substring(18)?.substringBefore(";") + ">"
            }
            else -> {
                e.message
            }
        }
    }
    return null
}

/*
Написать функцию, которая на вход принимает типизированный параметр.
Если это будет целое число, то в консоль вывести сумму вводимого числа и единицы,
если строка - сумму длины строки и единицы, если массив целых чисел - сумму элементов массива.
*/
fun <T> function(params: T): Int {
    var result = 0
    try {
        if (params as Int != null) result = params + 1
    } catch (_: ClassCastException) {}
    try {
        if (params as String != null) result = params.length + 1
    } catch (_: ClassCastException) {}
    try {
        if (params as Array<Int> != null) result = params.sum()
    } catch (_: ClassCastException) {}
    return result
}