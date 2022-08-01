package ru.ds.materialdesign.espresso

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.ds.materialdesign.view.recycler.RecyclerFragment
import ru.ds.materialdesign.R
import ru.ds.materialdesign.view.recycler.RecyclerAdapter

@RunWith(AndroidJUnit4::class)
class RecyclerFragmentTest {

    private lateinit var scenario: FragmentScenario<RecyclerFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer()
    }

    @Test
    fun recyclerSearch_ScrollTo() {

        Espresso.onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerAdapter.MarsViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("Mars"))
                )
            )

    }
    @Test
    fun activitySearch_PerformClickAtPosition() {

            Espresso.onView(withId(R.id.recyclerView))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerAdapter.MarsViewHolder>(
                        0,
                        ViewActions.click()
                    )
                )

    }

    @Test
    fun activitySearch_PerformClickOnItem() {

            Espresso.onView(withId(R.id.recyclerView))
                .perform(
                    RecyclerViewActions.scrollTo<RecyclerAdapter.MarsViewHolder>(
                        ViewMatchers.hasDescendant(ViewMatchers.withText("Mars"))
                    )
                )

            Espresso.onView(withId(R.id.recyclerView))
                .perform(
                    RecyclerViewActions.actionOnItem<RecyclerAdapter.MarsViewHolder>(
                        ViewMatchers.hasDescendant(ViewMatchers.withText("Mars")),
                        ViewActions.click()
                    )
                )

    }


}