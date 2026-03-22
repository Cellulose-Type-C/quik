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
package dev.cellulose.sendsans.interactor

import dev.cellulose.sendsans.manager.NotificationManager
import dev.cellulose.sendsans.repository.MessageRepository
import io.reactivex.Flowable
import javax.inject.Inject

class MarkFailed @Inject constructor(
    private val messageRepo: MessageRepository,
    private val notificationManager: NotificationManager
) : Interactor<MarkFailed.Params>() {
    data class Params(val messageId: Long, val resultCode: Int)

    override fun buildObservable(params: Params): Flowable<Unit> =
        Flowable.just(Unit)
            .doOnNext {
                if (messageRepo.markFailed(params.messageId, params.resultCode))
                    notificationManager.notifyFailed(params.messageId)
            }

}