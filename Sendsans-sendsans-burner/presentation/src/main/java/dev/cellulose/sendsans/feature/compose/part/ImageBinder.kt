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
package dev.cellulose.sendsans.feature.compose.part

import android.content.Context
import dev.cellulose.sendsans.R
import dev.cellulose.sendsans.common.base.QkViewHolder
import dev.cellulose.sendsans.common.util.Colors
import dev.cellulose.sendsans.common.util.extensions.setVisible
import dev.cellulose.sendsans.common.widget.BubbleImageView
import dev.cellulose.sendsans.databinding.MmsImagePreviewListItemBinding
import dev.cellulose.sendsans.extensions.isImage
import dev.cellulose.sendsans.extensions.isVideo
import dev.cellulose.sendsans.model.Message
import dev.cellulose.sendsans.model.MmsPart
import dev.cellulose.sendsans.util.GlideApp
import dev.cellulose.sendsans.util.tryOrNull
import javax.inject.Inject

class ImageBinder @Inject constructor(colors: Colors, private val context: Context) : PartBinder() {

    override val partLayout = R.layout.mms_image_preview_list_item
    override var theme = colors.theme()

    override fun canBindPart(part: MmsPart) = part.isImage() || part.isVideo()

    override fun bindPart(
        holder: QkViewHolder,
        part: MmsPart,
        message: Message,
        canGroupWithPrevious: Boolean,
        canGroupWithNext: Boolean
    ) {
        val binding = MmsImagePreviewListItemBinding.bind(holder.itemView)
        binding.video.setVisible(part.isVideo())
        holder.itemView.setOnClickListener { clicks.onNext(part.id) }

        binding.thumbnail.bubbleStyle = when {
            !canGroupWithPrevious && canGroupWithNext -> if (message.isMe()) BubbleImageView.Style.OUT_FIRST else BubbleImageView.Style.IN_FIRST
            canGroupWithPrevious && canGroupWithNext -> if (message.isMe()) BubbleImageView.Style.OUT_MIDDLE else BubbleImageView.Style.IN_MIDDLE
            canGroupWithPrevious && !canGroupWithNext -> if (message.isMe()) BubbleImageView.Style.OUT_LAST else BubbleImageView.Style.IN_LAST
            else -> BubbleImageView.Style.ONLY
        }

        tryOrNull(true) {
            GlideApp.with(context).load(part.getUri()).fitCenter().into(binding.thumbnail)
        }
    }

}