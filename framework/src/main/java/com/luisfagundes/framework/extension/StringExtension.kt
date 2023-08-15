package com.luisfagundes.framework.extension

import android.os.Build
import android.text.Html
import android.text.Spanned
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.core.text.toSpannable

const val EMPTY = ""

fun String?.safe(): String {
    return this ?: EMPTY
}

fun String.Companion.empty() = EMPTY

fun String?.isNotNullOrBlank(): Boolean = !this.isNullOrBlank()

fun String.fromHtml(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toSpannable()
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this).toSpannable()
    }
}

val String.isPhone get() = matches("^[0-9+]*$".toRegex())

fun String.clearTurkishChars(): String {
    var ret = this
    val turkishChars = charArrayOf(
        0x131.toChar(),
        0x130.toChar(),
        0xFC.toChar(),
        0xDC.toChar(),
        0xF6.toChar(),
        0xD6.toChar(),
        0x15F.toChar(),
        0x15E.toChar(),
        0xE7.toChar(),
        0xC7.toChar(),
        0x11F.toChar(),
        0x11E.toChar()
    )
    val englishChars = charArrayOf('i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G')
    for (i in turkishChars.indices) {
        ret = ret.replace(
            String(charArrayOf(turkishChars[i])).toRegex(),
            String(charArrayOf(englishChars[i]))
        )
    }
    return ret
}
