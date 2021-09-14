package com.refrigerator2k.blueprinthelper

object LetterWidth {
    private lateinit var font: Font

    private val letters = mapOf(
        'а' to  6.0,
        'А' to  7.0,

        'б' to  5.0,
        'Б' to  6.0,

        'в' to  5.0,
        'В' to  6.0,

        'г' to  5.0,
        'Г' to  5.0,

        'д' to  5.0,
        'Д' to  7.0,

        'е' to  5.0,
        'Е' to  5.0,

        'ж' to  7.0,
        'Ж' to  8.0,

        'з' to  5.0,
        'З' to  5.0,

        'и' to  5.0,
        'И' to  6.0,

        'й' to  5.0,
        'Й' to  6.0,

        'к' to  5.0,
        'К' to  6.0,

        'л' to  5.0,
        'Л' to  6.0,

        'м' to  6.0,
        'М' to  7.0,

        'н' to  5.0,
        'Н' to  6.0,

        'о' to  5.0,
        'О' to  6.0,

        'п' to  5.0,
        'П' to  6.0,

        'р' to  5.0,
        'Р' to  6.0,

        'с' to  4.0,
        'С' to  5.0,

        'т' to  7.0,
        'Т' to  6.0,

        'у' to  5.0,
        'У' to  6.0,

        'ф' to  7.0,
        'Ф' to  8.0,

        'х' to  5.0,
        'Х' to  7.0,

        'ц' to  6.0,
        'Ц' to  7.0,

        'ч' to  5.0,
        'Ч' to  6.0,

        'ш' to  7.0,
        'Ш' to  8.0,

        'щ' to  8.0,
        'Щ' to  9.0,

        'ъ' to  6.0,
        'Ъ' to  8.0,

        'ы' to  6.0,
        'Ы' to  7.0,

        'ь' to  5.0,
        'Ь' to  6.0,

        'э' to  5.0,
        'Э' to  6.0,

        'ю' to  6.0,
        'Ю' to  7.0,

        'я' to  5.0,
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