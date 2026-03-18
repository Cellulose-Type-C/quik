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

import android.view.LayoutInflater
import android.view.ViewGroup
import dev.cellulose.sendsans.R
import dev.cellulose.sendsans.common.base.QkRealmAdapter
import dev.cellulose.sendsans.common.base.QkBindingViewHolder
import dev.cellulose.sendsans.model.BlockedNumber
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import dev.cellulose.sendsans.databinding.BlockedNumberListItemBinding

class BlockedNumbersAdapter : QkRealmAdapter<BlockedNumber, QkBindingViewHolder<BlockedNumberListItemBinding>>() {

    val unblockAddress: Subject<Long> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QkBindingViewHolder<BlockedNumberListItemBinding> {
        val binding = BlockedNumberListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QkBindingViewHolder(binding).apply {
            binding.unblock.setOnClickListener {
                val number = getItem(adapterPosition) ?: return@setOnClickListener
                unblockAddress.onNext(number.id)
            }
        }
    }

    override fun onBindViewHolder(holder: QkBindingViewHolder<BlockedNumberListItemBinding>, position: Int) {
        val item = getItem(position)!!

        holder.binding.number.text = item.address
    }

}
