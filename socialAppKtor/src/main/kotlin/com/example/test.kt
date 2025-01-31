package com.example

import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)

    // put your code here
    val line = scanner.nextLine()

    while(scanner.hasNext()) {
        println(scanner.next())
    }
}