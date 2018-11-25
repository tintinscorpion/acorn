/*
 * Acorn - Decoupling navigation from Android
 * Copyright (C) 2018 Niek Haarman
 *
 * Acorn is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Acorn is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Acorn.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.nhaarman.acorn.samples.hellonavigation

import android.view.View
import android.view.ViewGroup
import com.nhaarman.acorn.android.presentation.RestorableViewController
import com.nhaarman.acorn.android.presentation.ViewController
import com.nhaarman.acorn.android.presentation.ViewProvidingScene
import com.nhaarman.acorn.android.util.inflate
import com.nhaarman.acorn.navigation.Navigator
import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.SavableScene
import kotlinx.android.synthetic.main.second_scene.*

/**
 * A Scene that shows a button to navigate to the first Scene.
 *
 * This Scene exposes the [SecondScene.Events] callback interface to let the
 * [Navigator] know the first Scene is requested. The Navigator implements this
 * interface to decide whether to make a transition.
 *
 * This Scene does not handle any state restoration, since there is no state
 * worth saving.
 * In cases where state _is_ worth saving, your Scene should generally implement
 * [SavableScene].
 */
class SecondScene(
    private val listener: Events
) : ViewProvidingScene<SecondSceneContainer> {

    override fun createViewController(parent: ViewGroup): ViewController {
        return SecondSceneViewController(parent.inflate(R.layout.second_scene))
    }

    override fun attach(v: SecondSceneContainer) {
        v.onFirstSceneClicked { listener.onFirstSceneRequested() }
    }

    interface Events {

        fun onFirstSceneRequested()
    }
}

interface SecondSceneContainer : Container {

    fun onFirstSceneClicked(f: () -> Unit)
}

class SecondSceneViewController(
    override val view: View
) : RestorableViewController, SecondSceneContainer {

    override fun onFirstSceneClicked(f: () -> Unit) {
        firstSceneButton.setOnClickListener { f() }
    }
}