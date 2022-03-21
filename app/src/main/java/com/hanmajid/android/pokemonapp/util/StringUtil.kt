package com.hanmajid.android.pokemonapp.util

import java.util.*

/**
 * String-related utility object.
 */
object StringUtil {
    /**
     * Capitalize the given [text].
     */
    fun capitalizeString(text: String): String {
        return text.replace("-", " ").split(" ").joinToString(" ") {
            capitalizeOneWord(it)
        }
    }

    /**
     * Capitalize the given [word].
     */
    private fun capitalizeOneWord(word: String): String {
        return word.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
    }
}