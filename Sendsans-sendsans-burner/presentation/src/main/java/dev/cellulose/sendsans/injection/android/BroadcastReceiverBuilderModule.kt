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
import dev.cellulose.sendsans.feature.widget.WidgetProvider
import dev.cellulose.sendsans.injection.scope.ActivityScope
import dev.cellulose.sendsans.receiver.BlockThreadReceiver
import dev.cellulose.sendsans.receiver.BootReceiver
import dev.cellulose.sendsans.receiver.DefaultSmsChangedReceiver
import dev.cellulose.sendsans.receiver.DeleteMessagesReceiver
import dev.cellulose.sendsans.receiver.MmsReceivedReceiver
import dev.cellulose.sendsans.receiver.MmsWapPushReceiver
import dev.cellulose.sendsans.receiver.NightModeReceiver
import dev.cellulose.sendsans.receiver.RemoteMessagingReceiver
import dev.cellulose.sendsans.receiver.SendScheduledMessageReceiver
import dev.cellulose.sendsans.receiver.MessageDeliveredReceiver
import dev.cellulose.sendsans.receiver.SmsProviderChangedReceiver
import dev.cellulose.sendsans.receiver.SmsReceivedReceiver
import dev.cellulose.sendsans.receiver.MessageMarkReceiver
import dev.cellulose.sendsans.receiver.MessageSentReceiver
import dev.cellulose.sendsans.receiver.ResendMessageReceiver
import dev.cellulose.sendsans.receiver.SendDelayedMessageReceiver
import dev.cellulose.sendsans.receiver.SpeakThreadsReceiver
import dev.cellulose.sendsans.receiver.StartActivityFromWidgetReceiver

@Module
abstract class BroadcastReceiverBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindBlockThreadReceiver(): BlockThreadReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindBootReceiver(): BootReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDefaultSmsChangedReceiver(): DefaultSmsChangedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindDeleteMessagesReceiver(): DeleteMessagesReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSpeakThreadsReceiver(): SpeakThreadsReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindStartActivityFromWidgetReceiver(): StartActivityFromWidgetReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMmsReceivedReceiver(): MmsReceivedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMmsWapPushReceiver(): MmsWapPushReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindNightModeReceiver(): NightModeReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindRemoteMessagingReceiver(): RemoteMessagingReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindResendMessageReceiver(): ResendMessageReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSendScheduledMessageReceiver(): SendScheduledMessageReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSendDelayedMessageReceiver(): SendDelayedMessageReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMessageDeliveredReceiver(): MessageDeliveredReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSmsProviderChangedReceiver(): SmsProviderChangedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSmsReceivedReceiver(): SmsReceivedReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMessageSentReceiver(): MessageSentReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMessageMarkReceiver(): MessageMarkReceiver

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindWidgetProvider(): WidgetProvider

}