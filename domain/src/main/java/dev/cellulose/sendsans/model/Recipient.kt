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

import android.telephony.PhoneNumberUtils
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Locale

open class Recipient(
    @PrimaryKey var id: Long = 0,
    var address: String = "",
    var contact: Contact? = null,
    var lastUpdate: Long = 0
) : RealmObject() {

    /**
     * Return a string that can be displayed to represent the name of this contact
     */
    fun getDisplayName(): String = contact?.name?.takeIf { it.isNotBlank() }
            ?: PhoneNumberUtils.formatNumber(address, Locale.getDefault().country) // TODO: Use our own PhoneNumberUtils
            ?: address

}
