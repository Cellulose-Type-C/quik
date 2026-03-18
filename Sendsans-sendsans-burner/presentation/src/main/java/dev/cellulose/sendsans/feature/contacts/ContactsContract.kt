/*
 * Copyright (C) 2019 SendSans Contributors <SendSans@protonmail.com>
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
package dev.cellulose.sendsans.feature.contacts

import dev.cellulose.sendsans.common.base.QkView
import dev.cellulose.sendsans.extensions.Optional
import dev.cellulose.sendsans.feature.compose.editing.ComposeItem
import dev.cellulose.sendsans.feature.compose.editing.PhoneNumberAction
import io.reactivex.Observable
import io.reactivex.subjects.Subject

interface ContactsContract : QkView<ContactsState> {

    val queryChangedIntent: Observable<CharSequence>
    val queryClearedIntent: Observable<*>
    val queryEditorActionIntent: Observable<Int>
    val composeItemPressedIntent: Subject<ComposeItem>
    val composeItemLongPressedIntent: Subject<ComposeItem>
    val phoneNumberSelectedIntent: Subject<Optional<Long>>
    val phoneNumberActionIntent: Subject<PhoneNumberAction>

    fun clearQuery()
    fun openKeyboard()
    fun finish(result: HashMap<String, String?>)

}
