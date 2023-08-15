package com.luisfagundes.framework.decoder

import android.net.Uri
import javax.inject.Inject

class UriDecoder @Inject constructor() : StringDecoder {
    override fun decode(encodedString: String): String = Uri.decode(encodedString)
}
