package test

import extensions.myLet

var age: Int? = 15

fun main() {
    age
        ?.myLet {
            if (it >= 18) {
                "You are an adult"
            } else {
                "You will be an adult in ${18 - it} years"
            }
        }
        ?.myLet { println(it) }
}

