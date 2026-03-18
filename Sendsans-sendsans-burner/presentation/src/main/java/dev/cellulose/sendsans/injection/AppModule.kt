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
package dev.cellulose.sendsans.injection

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkerFactory
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dev.cellulose.sendsans.blocking.BlockingClient
import dev.cellulose.sendsans.blocking.BlockingManager
import dev.cellulose.sendsans.common.ViewModelFactory
import dev.cellulose.sendsans.common.util.BillingManagerImpl
import dev.cellulose.sendsans.common.util.NotificationManagerImpl
import dev.cellulose.sendsans.common.util.ShortcutManagerImpl
import dev.cellulose.sendsans.feature.conversationinfo.injection.ConversationInfoComponent
import dev.cellulose.sendsans.feature.themepicker.injection.ThemePickerComponent
import dev.cellulose.sendsans.listener.ContactAddedListener
import dev.cellulose.sendsans.listener.ContactAddedListenerImpl
import dev.cellulose.sendsans.manager.ActiveConversationManager
import dev.cellulose.sendsans.manager.ActiveConversationManagerImpl
import dev.cellulose.sendsans.manager.AlarmManager
import dev.cellulose.sendsans.manager.AlarmManagerImpl
import dev.cellulose.sendsans.manager.BillingManager
import dev.cellulose.sendsans.manager.ChangelogManager
import dev.cellulose.sendsans.manager.ChangelogManagerImpl
import dev.cellulose.sendsans.manager.KeyManager
import dev.cellulose.sendsans.manager.KeyManagerImpl
import dev.cellulose.sendsans.manager.NotificationManager
import dev.cellulose.sendsans.manager.PermissionManager
import dev.cellulose.sendsans.manager.PermissionManagerImpl
import dev.cellulose.sendsans.manager.RatingManager
import dev.cellulose.sendsans.manager.ReferralManager
import dev.cellulose.sendsans.manager.ReferralManagerImpl
import dev.cellulose.sendsans.manager.ShortcutManager
import dev.cellulose.sendsans.manager.WidgetManager
import dev.cellulose.sendsans.manager.WidgetManagerImpl
import dev.cellulose.sendsans.mapper.CursorToContact
import dev.cellulose.sendsans.mapper.CursorToContactGroup
import dev.cellulose.sendsans.mapper.CursorToContactGroupImpl
import dev.cellulose.sendsans.mapper.CursorToContactGroupMember
import dev.cellulose.sendsans.mapper.CursorToContactGroupMemberImpl
import dev.cellulose.sendsans.mapper.CursorToContactImpl
import dev.cellulose.sendsans.mapper.CursorToConversation
import dev.cellulose.sendsans.mapper.CursorToConversationImpl
import dev.cellulose.sendsans.mapper.CursorToMessage
import dev.cellulose.sendsans.mapper.CursorToMessageImpl
import dev.cellulose.sendsans.mapper.CursorToPart
import dev.cellulose.sendsans.mapper.CursorToPartImpl
import dev.cellulose.sendsans.mapper.CursorToRecipient
import dev.cellulose.sendsans.mapper.CursorToRecipientImpl
import dev.cellulose.sendsans.mapper.RatingManagerImpl
import dev.cellulose.sendsans.repository.BackupRepository
import dev.cellulose.sendsans.repository.BackupRepositoryImpl
import dev.cellulose.sendsans.repository.BlockingRepository
import dev.cellulose.sendsans.repository.BlockingRepositoryImpl
import dev.cellulose.sendsans.repository.ContactRepository
import dev.cellulose.sendsans.repository.ContactRepositoryImpl
import dev.cellulose.sendsans.repository.ConversationRepository
import dev.cellulose.sendsans.repository.ConversationRepositoryImpl
import dev.cellulose.sendsans.repository.EmojiReactionRepository
import dev.cellulose.sendsans.repository.EmojiReactionRepositoryImpl
import dev.cellulose.sendsans.repository.MessageContentFilterRepository
import dev.cellulose.sendsans.repository.MessageContentFilterRepositoryImpl
import dev.cellulose.sendsans.repository.MessageRepository
import dev.cellulose.sendsans.repository.MessageRepositoryImpl
import dev.cellulose.sendsans.repository.ScheduledMessageRepository
import dev.cellulose.sendsans.repository.ScheduledMessageRepositoryImpl
import dev.cellulose.sendsans.repository.SyncRepository
import dev.cellulose.sendsans.repository.SyncRepositoryImpl
import dev.cellulose.sendsans.worker.InjectionWorkerFactory
import javax.inject.Singleton

@Module(subcomponents = [
    ConversationInfoComponent::class,
    ThemePickerComponent::class])
class AppModule(private var application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    fun provideContentResolver(context: Context): ContentResolver = context.contentResolver

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideRxPreferences(preferences: SharedPreferences): RxSharedPreferences {
        return RxSharedPreferences.create(preferences)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    @Provides
    fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory = factory

    // Listener

    @Provides
    fun provideContactAddedListener(listener: ContactAddedListenerImpl): ContactAddedListener = listener

    // Manager

    @Provides
    fun provideBillingManager(manager: BillingManagerImpl): BillingManager = manager

    @Provides
    fun provideActiveConversationManager(manager: ActiveConversationManagerImpl): ActiveConversationManager = manager

    @Provides
    fun provideAlarmManager(manager: AlarmManagerImpl): AlarmManager = manager

    @Provides
    fun blockingClient(manager: BlockingManager): BlockingClient = manager

    @Provides
    fun changelogManager(manager: ChangelogManagerImpl): ChangelogManager = manager

    @Provides
    fun provideKeyManager(manager: KeyManagerImpl): KeyManager = manager

    @Provides
    fun provideNotificationsManager(manager: NotificationManagerImpl): NotificationManager = manager

    @Provides
    fun providePermissionsManager(manager: PermissionManagerImpl): PermissionManager = manager

    @Provides
    fun provideRatingManager(manager: RatingManagerImpl): RatingManager = manager

    @Provides
    fun provideShortcutManager(manager: ShortcutManagerImpl): ShortcutManager = manager

    @Provides
    fun provideReferralManager(manager: ReferralManagerImpl): ReferralManager = manager

    @Provides
    fun provideWidgetManager(manager: WidgetManagerImpl): WidgetManager = manager

    // Mapper

    @Provides
    fun provideCursorToContact(mapper: CursorToContactImpl): CursorToContact = mapper

    @Provides
    fun provideCursorToContactGroup(mapper: CursorToContactGroupImpl): CursorToContactGroup = mapper

    @Provides
    fun provideCursorToContactGroupMember(mapper: CursorToContactGroupMemberImpl): CursorToContactGroupMember = mapper

    @Provides
    fun provideCursorToConversation(mapper: CursorToConversationImpl): CursorToConversation = mapper

    @Provides
    fun provideCursorToMessage(mapper: CursorToMessageImpl): CursorToMessage = mapper

    @Provides
    fun provideCursorToPart(mapper: CursorToPartImpl): CursorToPart = mapper

    @Provides
    fun provideCursorToRecipient(mapper: CursorToRecipientImpl): CursorToRecipient = mapper

    // Repository

    @Provides
    fun provideBackupRepository(repository: BackupRepositoryImpl): BackupRepository = repository

    @Provides
    fun provideBlockingRepository(repository: BlockingRepositoryImpl): BlockingRepository = repository

    @Provides
    fun provideMessageContentFilterRepository(repository: MessageContentFilterRepositoryImpl): MessageContentFilterRepository = repository

    @Provides
    fun provideContactRepository(repository: ContactRepositoryImpl): ContactRepository = repository

    @Provides
    fun provideConversationRepository(repository: ConversationRepositoryImpl): ConversationRepository = repository

    @Provides
    fun provideMessageRepository(repository: MessageRepositoryImpl): MessageRepository = repository

    @Provides
    fun provideScheduledMessagesRepository(repository: ScheduledMessageRepositoryImpl): ScheduledMessageRepository = repository

    @Provides
    fun provideSyncRepository(repository: SyncRepositoryImpl): SyncRepository = repository

    @Provides
    fun provideEmojiReactionRepository(repository: EmojiReactionRepositoryImpl): EmojiReactionRepository = repository

    // worker factory
    @Provides
    fun provideWorkerFactory(workerFactory: InjectionWorkerFactory): WorkerFactory = workerFactory
}