package com.example.dotaheroes.core

object Extensions {
    inline  fun <T> T.ifNull(defaultValue: () -> T): T = this ?: defaultValue()
    inline  fun <T> Iterator<T>?.ifNull(defaultValue: () -> Iterator<T>): Iterator<T> = this ?: defaultValue()
}