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
package dev.cellulose.sendsans.feature.gallery

import dev.cellulose.sendsans.common.base.QkView
import dev.cellulose.sendsans.model.MmsPart
import io.reactivex.Observable

interface GalleryView : QkView<GalleryState> {

    fun optionsItemSelected(): Observable<Int>
    fun screenTouched(): Observable<*>
    fun pageChanged(): Observable<MmsPart>

    fun requestStoragePermission()

}