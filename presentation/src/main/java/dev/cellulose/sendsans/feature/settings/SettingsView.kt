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
package dev.cellulose.sendsans.feature.settings

import dev.cellulose.sendsans.common.base.QkViewContract
import dev.cellulose.sendsans.common.widget.PreferenceView
import io.reactivex.Observable

interface SettingsView : QkViewContract<SettingsState> {
    fun preferenceClicks(): Observable<PreferenceView>
    fun aboutLongClicks(): Observable<*>
    fun viewQksmsPlusClicks(): Observable<*>
    fun nightModeSelected(): Observable<Int>
    fun nightStartSelected(): Observable<Pair<Int, Int>>
    fun nightEndSelected(): Observable<Pair<Int, Int>>
    fun textSizeSelected(): Observable<Int>
    fun sendDelaySelected(): Observable<Int>
    fun signatureChanged(): Observable<String>
    fun mmsSizeSelected(): Observable<Int>
    fun messageLinkHandlingSelected(): Observable<Int>

    fun showQksmsPlusSnackbar()
    fun showNightModeDialog()
    fun showStartTimePicker(hour: Int, minute: Int)
    fun showEndTimePicker(hour: Int, minute: Int)
    fun showTextSizePicker()
    fun showDelayDurationDialog()
    fun showSignatureDialog(signature: String)
    fun showMmsSizePicker()
    fun showMessageLinkHandlingDialogPicker()
    fun showSwipeActions()
    fun showThemePicker()
    fun showAbout()
}
