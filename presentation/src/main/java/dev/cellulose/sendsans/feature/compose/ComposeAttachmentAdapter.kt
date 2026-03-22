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

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import dev.cellulose.sendsans.R
import dev.cellulose.sendsans.common.base.QkAdapter
import dev.cellulose.sendsans.common.base.QkViewHolder
import dev.cellulose.sendsans.common.util.extensions.getDisplayName
import dev.cellulose.sendsans.databinding.AttachmentContactListItemBinding
import dev.cellulose.sendsans.databinding.AttachmentFileListItemBinding
import dev.cellulose.sendsans.extensions.getName
import dev.cellulose.sendsans.feature.extensions.LoadBestIconIntoImageView
import dev.cellulose.sendsans.feature.extensions.loadBestIconIntoImageView
import dev.cellulose.sendsans.model.Attachment
import ezvcard.Ezvcard
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject


class ComposeAttachmentAdapter @Inject constructor(
    private val context: Context
) : QkAdapter<Attachment, QkViewHolder>() {

    companion object {
        private const val VIEW_TYPE_FILE = 0
        private const val VIEW_TYPE_CONTACT = 1
    }

    val attachmentDeleted: Subject<Attachment> = PublishSubject.create()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QkViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = if (viewType == VIEW_TYPE_CONTACT) {
            AttachmentContactListItemBinding.inflate(inflater, parent, false).root
        } else {
            AttachmentFileListItemBinding.inflate(inflater, parent, false).root
        }

        return QkViewHolder(view).apply {
            view.setOnClickListener {
                val attachment = getItem(adapterPosition)
                attachmentDeleted.onNext(attachment)
            }
        }
    }

    override fun onBindViewHolder(holder: QkViewHolder, position: Int) {
        val attachment = getItem(position)

        if (attachment.isVCard(context)) {
            val binding = AttachmentContactListItemBinding.bind(holder.itemView)
            try {
                val displayName = Ezvcard.parse(
                    String(attachment.getResourceBytes(context))
                ).first().getDisplayName() ?: ""
                binding.name.text = displayName
                binding.name.isVisible = displayName.isNotEmpty()
            } catch (e: Exception) {
                // npe from Ezvcard first() call above can be thrown if resource bytes cannot
                // be retrieved from contact resource provider
                binding.vCardAvatar.setImageResource(android.R.drawable.ic_delete)
                binding.name.text = context.getString(R.string.attachment_missing)
                binding.name.isVisible = true
            }
            return
        }

        val binding = AttachmentFileListItemBinding.bind(holder.itemView)
        // set best image and text to use for icon
        when (attachment.uri.loadBestIconIntoImageView(context, binding.thumbnail)) {
            LoadBestIconIntoImageView.Missing -> {
                binding.fileName.text = context.getString(R.string.attachment_missing)
                binding.fileName.visibility = View.VISIBLE
            }
            LoadBestIconIntoImageView.ActivityIcon,
            LoadBestIconIntoImageView.DefaultAudioIcon,
            LoadBestIconIntoImageView.GenericIcon -> {
                // generic style icon used, also show name
                binding.fileName.text = attachment.uri.getName(context)
                binding.fileName.visibility = View.VISIBLE
            }
            else -> binding.fileName.visibility = View.GONE
        }
    }

    override fun getItemViewType(position: Int) = when (getItem(position).isVCard(context)) {
        true -> VIEW_TYPE_CONTACT
        else -> VIEW_TYPE_FILE
    }

}
