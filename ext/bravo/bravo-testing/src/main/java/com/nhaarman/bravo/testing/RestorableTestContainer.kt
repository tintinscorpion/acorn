/*
 * Bravo - Decoupling navigation from Android
 * Copyright (C) 2018 Niek Haarman
 *
 * Bravo is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Bravo is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Bravo.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.nhaarman.bravo.testing

import com.nhaarman.bravo.presentation.Container
import com.nhaarman.bravo.presentation.RestorableContainer
import com.nhaarman.bravo.state.ContainerState

/**
 * An interface with default implementations for [RestorableContainer].
 *
 * Test implementations of [Container] can also implement this interface to
 * avoid writing boilerplate code for state saving.
 */
interface RestorableTestContainer : RestorableContainer {

    override fun restoreInstanceState(bundle: ContainerState) {
    }

    override fun saveInstanceState(): ContainerState {
        return ContainerState()
    }
}