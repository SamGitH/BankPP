package com.test.bank.tools

fun String.formatCurrencyString(): String {

    fun getPrefix(string: String, count: Int): String{
        var newStr = ""
        var iter = count
        var iterPref = string.length % 3
        var iterSpace = 0
        var havePref = false

        string.forEachIndexed { index, char ->
            if(iterPref > 0){
                iterPref--
                newStr += char
                if(iterPref == 0) {
                    newStr += " "
                    havePref = true
                }
            }
            else if(!havePref && iterSpace != 0 && iterSpace % 3 == 0 && iter != 0){
                newStr += " $char"
                iter --
                iterSpace = 0
            }
            else {
                iterSpace++
                newStr += char
                havePref = false
            }
        }
        return newStr
    }

    var postfix = this.substringAfter(".")
    postfix = when {
        postfix.length == 1 && postfix == "0" -> ""
        postfix.length == 1 -> postfix + "0"
        postfix.length > 2 -> "${postfix[0]}${postfix[1]}"
        else -> postfix
    }
    postfix = ".$postfix"

    var prefix = this.substringBefore(".")
    if(prefix.length / 3 > 0){
        prefix = if(prefix.length % 3 != 0){
            getPrefix(prefix, prefix.length / 3)
        } else{
            getPrefix(prefix, prefix.length / 3 - 1)
        }
    }
    return prefix + postfix
}