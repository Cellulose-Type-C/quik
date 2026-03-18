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
package dev.cellulose.sendsans.feature.changelog

import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import dev.cellulose.sendsans.BuildConfig
import dev.cellulose.sendsans.R
import dev.cellulose.sendsans.databinding.ChangelogDialogBinding
import dev.cellulose.sendsans.feature.main.MainActivity
import dev.cellulose.sendsans.manager.ChangelogManager
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

class ChangelogDialog(activity: MainActivity) {

    val moreClicks: Subject<Unit> = PublishSubject.create()

    private val dialog: AlertDialog
    private val adapter = ChangelogAdapter(activity)

    init {
        val layout = ChangelogDialogBinding.inflate(LayoutInflater.from(activity))

        dialog = AlertDialog.Builder(activity)
                .setCancelable(true)
                .setView(layout.root)
                .create()

        layout.version.text = activity.getString(R.string.changelog_version, BuildConfig.VERSION_NAME)
        layout.changelog.adapter = adapter
        layout.more.setOnClickListener { dialog.dismiss(); moreClicks.onNext(Unit) }
        layout.dismiss.setOnClickListener { dialog.dismiss() }
    }

    fun show(changelog: ChangelogManager.CumulativeChangelog) {
        adapter.setChangelog(changelog)
        dialog.show()
    }

}
