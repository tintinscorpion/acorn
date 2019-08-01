/*
 *    Copyright 2018 Niek Haarman
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.nhaarman.acorn.notesapp.presentation.createitem

import com.nhaarman.acorn.presentation.Container
import com.nhaarman.acorn.presentation.RestorableContainer
import io.reactivex.Observable

interface CreateItemContainer : Container, RestorableContainer {

    fun setInitialText(text: String?)

    val textChanges: Observable<String>
    val createClicks: Observable<Unit>
}
