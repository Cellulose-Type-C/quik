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
package dev.cellulose.sendsans.feature.scheduled

import dev.cellulose.sendsans.common.base.QkView
import io.reactivex.Observable

interface ScheduledView : QkView<ScheduledState> {

    val composeIntent: Observable<*>
    val upgradeIntent: Observable<*>
    val messagesSelectedIntent: Observable<List<Long>>
    val optionsItemIntent: Observable<Int>
    val deleteScheduledMessages: Observable<List<Long>>
    val sendScheduledMessages: Observable<List<Long>>
    val editScheduledMessage: Observable<Long>
    val backPressedIntent: Observable<Unit>

    fun clearSelection()
    fun toggleSelectAll()
    fun showDeleteDialog(messages: List<Long>)
    fun showSendNowDialog(messages: List<Long>)
    fun showEditMessageDialog(message: Long)
    fun finishActivity()

}
