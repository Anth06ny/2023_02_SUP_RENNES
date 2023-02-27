package com.amonteiro.a2023_02_sup_rennes


fun main() {

    var html = RequestUtils.sendGet("https://www.google.fr")
    println("res=$html")
}