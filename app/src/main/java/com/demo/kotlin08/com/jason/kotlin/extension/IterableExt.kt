package com.demo.kotlin08.com.jason.kotlin.extension

//不要声明成private
fun <T> Iterable<T>.randomTake(): T = this.shuffled().first()