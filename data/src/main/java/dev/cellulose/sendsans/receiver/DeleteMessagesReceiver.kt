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
package dev.cellulose.sendsans.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.android.AndroidInjection
import dev.cellulose.sendsans.interactor.DeleteMessages
import timber.log.Timber
import javax.inject.Inject

class DeleteMessagesReceiver : BroadcastReceiver() {
    @Inject lateinit var deleteMessages: DeleteMessages

    override fun onReceive(context: Context, intent: Intent) {
        AndroidInjection.inject(this, context)

        Timber.v("received")

        intent.getLongExtra("threadId", 0).takeIf { it > 0 }?.let { threadId ->
            intent.getLongArrayExtra("messageIds")?.let { messageIds ->
                val pendingResult = goAsync()
                deleteMessages.execute(DeleteMessages.Params(messageIds.toList(), threadId)) {
                    pendingResult.finish()
                }
            }
        }
    }

}