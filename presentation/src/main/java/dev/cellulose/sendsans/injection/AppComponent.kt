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

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import dev.cellulose.sendsans.common.QKApplication
import dev.cellulose.sendsans.common.QkDialog
import dev.cellulose.sendsans.common.util.QkChooserTargetService
import dev.cellulose.sendsans.common.widget.AvatarView
import dev.cellulose.sendsans.common.widget.PagerTitleView
import dev.cellulose.sendsans.common.widget.PreferenceView
import dev.cellulose.sendsans.common.widget.QkEditText
import dev.cellulose.sendsans.common.widget.QkSwitch
import dev.cellulose.sendsans.common.widget.QkTextView
import dev.cellulose.sendsans.common.widget.RadioPreferenceView
import dev.cellulose.sendsans.feature.backup.BackupController
import dev.cellulose.sendsans.feature.blocking.BlockingController
import dev.cellulose.sendsans.feature.blocking.filters.MessageContentFiltersController
import dev.cellulose.sendsans.feature.blocking.manager.BlockingManagerController
import dev.cellulose.sendsans.feature.blocking.messages.BlockedMessagesController
import dev.cellulose.sendsans.feature.blocking.numbers.BlockedNumbersController
import dev.cellulose.sendsans.feature.compose.editing.DetailedChipView
import dev.cellulose.sendsans.feature.conversationinfo.injection.ConversationInfoComponent
import dev.cellulose.sendsans.feature.messageutils.MessageUtilsController
import dev.cellulose.sendsans.feature.settings.SettingsController
import dev.cellulose.sendsans.feature.settings.about.AboutController
import dev.cellulose.sendsans.feature.settings.swipe.SwipeActionsController
import dev.cellulose.sendsans.feature.themepicker.injection.ThemePickerComponent
import dev.cellulose.sendsans.feature.widget.WidgetAdapter
import dev.cellulose.sendsans.injection.android.ActivityBuilderModule
import dev.cellulose.sendsans.injection.android.BroadcastReceiverBuilderModule
import dev.cellulose.sendsans.injection.android.ServiceBuilderModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class,
    BroadcastReceiverBuilderModule::class,
    ServiceBuilderModule::class])
interface AppComponent {

    fun conversationInfoBuilder(): ConversationInfoComponent.Builder
    fun themePickerBuilder(): ThemePickerComponent.Builder

    fun inject(application: QKApplication)

    fun inject(controller: AboutController)
    fun inject(controller: BackupController)
    fun inject(controller: BlockedMessagesController)
    fun inject(controller: BlockedNumbersController)
    fun inject(controller: MessageContentFiltersController)
    fun inject(controller: BlockingController)
    fun inject(controller: BlockingManagerController)
    fun inject(controller: MessageUtilsController)
    fun inject(controller: SettingsController)
    fun inject(controller: SwipeActionsController)

    fun inject(dialog: QkDialog)

    fun inject(service: WidgetAdapter)

    /**
     * This can't use AndroidInjection, or else it will crash on pre-marshmallow devices
     */
    fun inject(service: QkChooserTargetService)

    fun inject(view: AvatarView)
    fun inject(view: DetailedChipView)
    fun inject(view: PagerTitleView)
    fun inject(view: PreferenceView)
    fun inject(view: RadioPreferenceView)
    fun inject(view: QkEditText)
    fun inject(view: QkSwitch)
    fun inject(view: QkTextView)

}
