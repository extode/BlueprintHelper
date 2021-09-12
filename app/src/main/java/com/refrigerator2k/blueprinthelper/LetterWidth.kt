package com.refrigerator2k.blueprinthelper

object LetterWidth {
    private lateinit var font: Font

    private val letters = mapOf(
        'А' to  7.0,
        'Б' to  6.0,
        'В' to  6.0,
        'Г' to  5.0,
        'Д' to  7.0,
        'Е' to  5.0,
        'Ж' to  8.0,
        'З' to  5.0,
        'И' to  6.0,
        'Й' to  6.0,
        'К' to  6.0,
        'Л' to  6.0,
        'М' to  7.0,
        'Н' to  6.0,
        'О' to  6.0,
        'П' to  6.0,
        'Р' to  6.0,
        'С' to  5.0,
        'Т' to  6.0,
        'У' to  7.0,
        'Ф' to  8.0,
        'Х' to  7.0,
        'Ц' to  7.0,
        'Ч' to  6.0,
        'Ш' to  8.0,
        'Щ' to  9.0,
        'Ъ' to  8.0,
        'Ы' to  7.0,
        'Ь' to  6.0,
        'Э' to  6.0,
        'Ю' to  7.0,
        'Я' to  6.0,

        '0' to  5.0,
        '1' to  3.0,
        '2' to  5.0,
        '3' to  5.0,
        '4' to  6.0,
        '5' to  5.0,
        '6' to  5.0,
        '7' to  5.0,
        '8' to  5.0,
        '9' to  5.0,

        '-' to  7.0,
        ' ' to  2.0,
    )

    fun init() {
        font = FontProvider.font
    }

    operator fun get(char:  Char): Double? {
        val w = letters[char] ?: return null
        return round2(font.properties.size / 10.0 * w)
    }

}