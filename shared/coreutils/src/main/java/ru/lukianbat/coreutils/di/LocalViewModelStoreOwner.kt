package ru.lukianbat.coreutils.di

/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner

/**
 * The CompositionLocal containing the current [ViewModelStoreOwner].
 */
object LocalViewModelStoreOwner {

    private val _localViewModelStoreOwner =
        compositionLocalOf<ViewModelStoreOwner?> { null }

    /**
     * Returns current composition local value for the owner or `null` if one has not
     * been provided nor is one available via [ViewTreeViewModelStoreOwner.get] on the
     * current [LocalView].
     */
    val current: ViewModelStoreOwner?
        @Composable
        get() = _localViewModelStoreOwner.current
            ?: ViewTreeViewModelStoreOwner.get(LocalView.current)

    /**
     * Associates a [_localViewModelStoreOwner] key to a value in a call to
     * [CompositionLocalProvider].
     */
    infix fun provides(viewModelStoreOwner: ViewModelStoreOwner):
            ProvidedValue<ViewModelStoreOwner?> {
        return _localViewModelStoreOwner.provides(viewModelStoreOwner)
    }
}
