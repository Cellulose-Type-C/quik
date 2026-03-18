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
package dev.cellulose.sendsans.repository

import dev.cellulose.sendsans.model.MessageContentFilter
import dev.cellulose.sendsans.model.MessageContentFilterData
import io.realm.RealmResults

interface MessageContentFilterRepository {

    fun createFilter(data: MessageContentFilterData)

    fun getMessageContentFilters(): RealmResults<MessageContentFilter>

    fun getMessageContentFilter(id: Long): MessageContentFilter?

    fun isBlocked(messageBody: String, address: String, contactsRepo: ContactRepository): Boolean

    fun removeFilter(id: Long)

}
