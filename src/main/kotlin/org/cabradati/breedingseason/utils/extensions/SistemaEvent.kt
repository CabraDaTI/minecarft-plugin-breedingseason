package org.cabradati.breedingseason.utils.extensions

interface SistemaEvent<T> {

    fun on(event: T)

}