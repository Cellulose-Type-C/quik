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
package dev.cellulose.sendsans.injection.android

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dev.cellulose.sendsans.feature.backup.BackupActivity
import dev.cellulose.sendsans.feature.blocking.BlockingActivity
import dev.cellulose.sendsans.feature.compose.ComposeActivity
import dev.cellulose.sendsans.feature.compose.ComposeActivityModule
import dev.cellulose.sendsans.feature.contacts.ContactsActivity
import dev.cellulose.sendsans.feature.contacts.ContactsActivityModule
import dev.cellulose.sendsans.feature.conversationinfo.ConversationInfoActivity
import dev.cellulose.sendsans.feature.gallery.GalleryActivity
import dev.cellulose.sendsans.feature.gallery.GalleryActivityModule
import dev.cellulose.sendsans.feature.main.MainActivity
import dev.cellulose.sendsans.feature.main.MainActivityModule
import dev.cellulose.sendsans.feature.messageutils.MessageUtilsActivity
import dev.cellulose.sendsans.feature.notificationprefs.NotificationPrefsActivity
import dev.cellulose.sendsans.feature.notificationprefs.NotificationPrefsActivityModule
import dev.cellulose.sendsans.feature.plus.PlusActivity
import dev.cellulose.sendsans.feature.plus.PlusActivityModule
import dev.cellulose.sendsans.feature.qkreply.QkReplyActivity
import dev.cellulose.sendsans.feature.qkreply.QkReplyActivityModule
import dev.cellulose.sendsans.feature.scheduled.ScheduledActivity
import dev.cellulose.sendsans.feature.scheduled.ScheduledActivityModule
import dev.cellulose.sendsans.feature.settings.SettingsActivity
import dev.cellulose.sendsans.feature.settings.about.AboutActivity
import dev.cellulose.sendsans.injection.scope.ActivityScope

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PlusActivityModule::class])
    abstract fun bindPlusActivity(): PlusActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindBackupActivity(): BackupActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ComposeActivityModule::class])
    abstract fun bindComposeActivity(): ComposeActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ContactsActivityModule::class])
    abstract fun bindContactsActivity(): ContactsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindConversationInfoActivity(): ConversationInfoActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [GalleryActivityModule::class])
    abstract fun bindGalleryActivity(): GalleryActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [NotificationPrefsActivityModule::class])
    abstract fun bindNotificationPrefsActivity(): NotificationPrefsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [QkReplyActivityModule::class])
    abstract fun bindQkReplyActivity(): QkReplyActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [ScheduledActivityModule::class])
    abstract fun bindScheduledActivity(): ScheduledActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindMessageUtilsActivity(): MessageUtilsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindSettingsActivity(): SettingsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindAboutActivity(): AboutActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun bindBlockingActivity(): BlockingActivity

}
