/*
 * Copyright (C) 2017 Moez Bhatti <moez.bhatti@gmail.com>
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
package dev.cellulose.sendsans.feature.compose

import dev.cellulose.sendsans.compat.SubscriptionInfoCompat
import dev.cellulose.sendsans.model.Attachment
import dev.cellulose.sendsans.model.Conversation
import dev.cellulose.sendsans.model.Message
import dev.cellulose.sendsans.model.Recipient
import io.realm.RealmResults

data class ComposeState(
    val hasError: Boolean = false,
    val editingMode: Boolean = false,
    val threadId: Long = 0,
    val selectedChips: List<Recipient> = ArrayList(),
    val sendAsGroup: Boolean = true,
    val conversationtitle: String = "",
    val loading: Boolean = false,
    val query: String = "",
    val searchSelectionId: Long = -1,
    val searchSelectionPosition: Int = 0,
    val searchResults: Int = 0,
    val messages: Pair<Conversation, RealmResults<Message>>? = null,
    val selectedMessages: Int = 0,
    val selectedMessagesHaveText: Boolean = false,
    val scheduled: Long = 0,
    val attachments: List<Attachment> = listOf(),
    val attaching: Boolean = false,
    val scheduling: Boolean = false,
    val remaining: String = "",
    val subscription: SubscriptionInfoCompat? = null,
    val canSend: Boolean = false,
    val hasScheduledMessages: Boolean = false,
    val validRecipientNumbers: Int = 1,
    val recipientCount: Int = 1,
    val audioMsgRecording: Boolean = false,
    val saveDraft: Boolean = true,
)