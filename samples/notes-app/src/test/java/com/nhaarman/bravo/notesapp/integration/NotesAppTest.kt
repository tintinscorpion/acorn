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

package com.nhaarman.bravo.notesapp.integration

import com.nhaarman.bravo.notesapp.ImmediateMainThreadExtension
import com.nhaarman.bravo.notesapp.NoRxErrorsExtension
import com.nhaarman.bravo.notesapp.integration.presentation.createitem.createItem
import com.nhaarman.bravo.notesapp.integration.presentation.edititem.editItem
import com.nhaarman.bravo.notesapp.integration.presentation.itemlist.itemList
import com.nhaarman.bravo.notesapp.navigation.NotesAppNavigator
import com.nhaarman.bravo.testing.TestContext
import com.nhaarman.bravo.testing.TestContext.Companion.testWith
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(NoRxErrorsExtension::class, ImmediateMainThreadExtension::class)
class NotesAppTest {

    val noteAppComponent = TestNotesAppComponent()
    val navigator = NotesAppNavigator(noteAppComponent, null)
    val context = TestContext.create(navigator, NotesAppTestContainerProvider)

    @Test
    fun `creating an item`() = testWith(context) {
        itemList {
            verifyVisible(emptyList())
            requestCreateItem()
        }

        createItem {
            enterText("Foo")
            create()
        }

        itemList {
            verifyVisible("Foo")
        }
    }

    @Test
    fun `editing an item`() = testWith(context) {
        itemList {
            verifyVisible(emptyList())
            requestCreateItem()
        }

        createItem {
            enterText("Foo")
            create()
        }

        itemList {
            verifyVisible("Foo")
            clickItem(0)
        }

        editItem {
            enterText("Bar")
            save()
        }

        itemList {
            verifyVisible("Bar")
            clickItem(0)
        }
    }

    @Test
    fun `deleting an item`() = testWith(context) {
        itemList {
            verifyVisible(emptyList())
            requestCreateItem()
        }

        createItem {
            enterText("Foo")
            create()
        }

        itemList {
            verifyVisible("Foo")
            delete(0)
            verifyVisible()
        }
    }

    @Test
    fun `creating multiple items`() = testWith(context) {
        itemList {
            verifyVisible(emptyList())
            requestCreateItem()
        }

        createItem {
            enterText("Foo")
            create()
        }

        itemList {
            requestCreateItem()
        }

        createItem {
            enterText("Bar")
            create()
        }

        itemList {
            verifyVisible("Foo", "Bar")
        }
    }
}