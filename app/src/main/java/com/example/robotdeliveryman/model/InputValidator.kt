package com.example.robotdeliveryman.model

class InputValidator {

    fun checkInput(string: String): Boolean{

        if (string.length < 5)
            return false

        val firstCheckString = string.replace("\\s+".toRegex(),"")
        if (firstCheckString[0] != firstCheckString[2])
            return false
        if (firstCheckString[1] != 'x')
            return false
        val max = firstCheckString[0].toString().toInt()

        val secondCheckString = firstCheckString.substring(3)

        if ((secondCheckString.length) % 5 != 0)
            return false

        for (i in secondCheckString.indices){
            when(i % 5) {
                0 -> if (secondCheckString[i] != '(') return false
                1 -> if (!isValidNumber(secondCheckString[i].toString(), max)) return false
                2 -> if (secondCheckString[i] != ',') return false
                3 -> if (!isValidNumber(secondCheckString[i].toString(), max)) return false
                4 -> if (secondCheckString[i] != ')') return false
            }
        }
        return true
    }

    private fun isValidNumber(str: String, max: Int): Boolean{
        val isInt = try {
            str.toInt()
            true
        } catch (e: NumberFormatException){
            false
        }
        if (!isInt) return false
        if (str.toInt() > max || str.toInt() < 0) return false

        return true
    }
}