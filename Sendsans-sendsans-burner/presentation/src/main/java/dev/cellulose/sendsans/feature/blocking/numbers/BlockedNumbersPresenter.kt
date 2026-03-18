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
package dev.cellulose.sendsans.feature.blocking.numbers

import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.autoDisposable
import dev.cellulose.sendsans.common.base.QkPresenter
import dev.cellulose.sendsans.interactor.MarkUnblocked
import dev.cellulose.sendsans.repository.BlockingRepository
import dev.cellulose.sendsans.repository.ConversationRepository
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BlockedNumbersPresenter @Inject constructor(
    private val blockingRepo: BlockingRepository,
    private val conversationRepo: ConversationRepository,
    private val markUnblocked: MarkUnblocked
) : QkPresenter<BlockedNumbersView, BlockedNumbersState>(
        BlockedNumbersState(numbers = blockingRepo.getBlockedNumbers())
) {

    override fun bindIntents(view: BlockedNumbersView) {
        super.bindIntents(view)

        view.unblockAddress()
            .observeOn(Schedulers.io())
            .doOnNext { id ->
                blockingRepo.getBlockedNumber(id)?.address
                    ?.let { address -> conversationRepo.getConversation(listOf(address)) }
                    ?.let { conversation -> markUnblocked.execute(listOf(conversation.id)) }
            }
            .doOnNext(blockingRepo::unblockNumber)
            .subscribeOn(Schedulers.io())
            .autoDisposable(view.scope())
            .subscribe()

        view.addAddress()
            .autoDisposable(view.scope())
            .subscribe { view.showAddDialog() }

        view.saveAddress()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .autoDisposable(view.scope())
            .subscribe { address -> blockingRepo.blockNumber(address) }
    }

}
