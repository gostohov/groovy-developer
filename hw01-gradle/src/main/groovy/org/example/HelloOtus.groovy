package org.example

import com.google.common.collect.Lists

class HelloOtus {
    static void main(String[] args) {
        def numbers = [1, 2, 3, 4, 5]
        def reversedList = Lists.reverse(numbers)
        println "Исходный список: $numbers"
        println "Развернутый список с помощью guava: $reversedList"
    }
}
