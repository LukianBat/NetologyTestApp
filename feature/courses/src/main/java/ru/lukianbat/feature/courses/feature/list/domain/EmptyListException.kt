package ru.lukianbat.feature.courses.feature.list.domain

import java.lang.Exception

class EmptyListException : Exception(EMPTY_LIST_EXCEPTION) {
    companion object {
        private const val EMPTY_LIST_EXCEPTION = "list can not be empty!"
    }
}
