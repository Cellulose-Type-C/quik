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
package dev.cellulose.sendsans.feature.settings.about

import com.uber.autodispose.android.lifecycle.scope
import com.uber.autodispose.autoDisposable
import dev.cellulose.sendsans.R
import dev.cellulose.sendsans.common.Navigator
import dev.cellulose.sendsans.common.base.QkPresenter
import javax.inject.Inject

class AboutPresenter @Inject constructor(
    private val navigator: Navigator
) : QkPresenter<AboutView, Unit>(Unit) {

    override fun bindIntents(view: AboutView) {
        super.bindIntents(view)

        view.preferenceClicks()
                .autoDisposable(view.scope())
                .subscribe { preference ->
                    when (preference.id) {

                        R.id.source -> navigator.showSourceCode()

                        R.id.changelog -> navigator.showChangelog()

                        R.id.contact -> navigator.showSupport()

                        R.id.license -> navigator.showLicense()
                    }
                }
    }

}