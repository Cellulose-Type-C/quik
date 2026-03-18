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
package dev.cellulose.sendsans.interactor

import dev.cellulose.sendsans.repository.MessageRepository
import io.reactivex.Flowable
import javax.inject.Inject

class MarkDelivered @Inject constructor(private val messageRepo: MessageRepository) : Interactor<Long>() {

    override fun buildObservable(params: Long): Flowable<Unit> {
        return Flowable.just(Unit)
                .doOnNext { messageRepo.markDelivered(params) }
    }

}