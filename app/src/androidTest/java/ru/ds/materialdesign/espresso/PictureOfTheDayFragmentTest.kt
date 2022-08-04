package ru.ds.materialdesign.espresso

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.ds.materialdesign.R
import ru.ds.materialdesign.view.main.PictureOfTheDayFragment

@RunWith(AndroidJUnit4::class)
class PictureOfTheDayFragmentTest {

    private lateinit var scenario: FragmentScenario<PictureOfTheDayFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer(themeResId = R.style.MyBlueOriginalTheme)
    }

    @Test
    fun fragment_Button_BlackThemeIsDisplayed() {
        Espresso.onView(withId(R.id.btBlack))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun fragment_Button_WhiteThemeIsDisplayed() {
        Espresso.onView(withId(R.id.btWhite))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun searchTextView_HasHint() {
        val assertion = ViewAssertions.matches(withHint("Search"))
        Espresso.onView(withId(R.id.inputLayout)).check(assertion)
    }

    @Test
    fun fragmentStateResumetTest() {
        scenario.onFragment { fragment: PictureOfTheDayFragment ->
            scenario.moveToState(Lifecycle.State.RESUMED)
        }


    }
}