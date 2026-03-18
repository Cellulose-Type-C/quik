package dev.cellulose.sendsans.feature.messageutils

import dev.cellulose.sendsans.repository.MessageRepository

data class MessageUtilsState(
    val autoDeduplicateMessages: Boolean = false,
    val deduplicationProgress: MessageRepository.DeduplicationProgress = MessageRepository.DeduplicationProgress.Idle,

    val autoDelete: Int = 0,
)
