/*
 * Copyright (C) 2017 SendSans Contributors <SendSans@protonmail.com>
 *
 * This file is part of SendSans.
 *
 * SendSans is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SendSans is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SendSans.  If not, see <http://www.gnu.org/licenses/>.
 */
package dev.cellulose.sendsans.model

import android.content.ContentResolver
import android.net.Uri
import android.webkit.MimeTypeMap
import io.realm.RealmObject
import io.realm.RealmResults
import io.realm.annotations.Index
import io.realm.annotations.LinkingObjects
import io.realm.annotations.PrimaryKey
import java.io.File

open class MmsPart : RealmObject() {

    @PrimaryKey var id: Long = 0
    @Index var messageId: Long = 0
    var type: String = ""
    var seq: Int = -1
    var name: String? = null
    var text: String? = null

    @LinkingObjects("parts")
    val messages: RealmResults<Message>? = null

    fun getUri(): Uri = Uri
        .Builder()
        .scheme(ContentResolver.SCHEME_CONTENT)
        .authority("mms")
        .encodedPath("part/$id")
        .build()

    fun getBestFilename(): String =
        if (name.isNullOrBlank()) "unknown"
        else if (File(name!!).extension.isNotEmpty()) name!!
        else "$name" +
                if (type.isBlank()) ""
                else ".${MimeTypeMap.getSingleton().getExtensionFromMimeType(type)
                    ?: type.substringAfter("/")}"

    fun getSummary(): String? = when {
        type == "application/smil" -> null
        type == "text/plain" -> text
        type == "text/x-vcard" -> "Contact card"
        type.startsWith("image") -> "Picture"
        type.startsWith("video") -> "Video"
        type.startsWith("audio") -> "Audio"
        else -> type.substring(type.indexOf('/') + 1)
    }

}