package com.cursokotlin.coupons

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cursokotlin.coupons.mainModule.view.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityCreateTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun createCuponTest(){
        val etCoupon = onView(withId(R.id.etCoupon))
        etCoupon.check(matches(withText(""))) //Verifica que (view)(coincida(con el text("")))
        etCoupon.perform(click())
        etCoupon.perform(replaceText("WELCOME_02"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId(R.id.btnCreate))
        btnCreate.check(matches(isDisplayed()))

        val etDescription = onView(withId(R.id.etDescription))
        etDescription.perform(replaceText("Descripcion de prueba"))

        btnCreate.perform(click())

        val snackbar = onView(withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(matches(withText("Cupon creado")))

    }
    /*
        * Corrobora que el boton "crear" no existe y no es valid
        * Test: nuestro etCoupon inicia vacio, luergo haz click sobre el, añade el texto "WELCOME_01"
        * y ahora desde btnConsult haz click sobre el.
        * Verifica que el btnCrear no es visible
        * */
    @Test
    fun consultCoupoExistTest(){
        val etCoupon = onView(withId(R.id.etCoupon))
        etCoupon.check(matches(withText("")))
        etCoupon.perform(click())
        etCoupon.perform(replaceText("WELCOME_01"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId(R.id.btnCreate))
        btnCreate.check(matches(not(isDisplayed()))) // ! = not()

    }

    /*
    *Xomprueba que no s erpuede crear un cupon con codigo repetido.
    * Test:
    * etCoupon inicia vacio, y se reeemplazxa erl textp con "WELCOME_01a" (cupon inexistente)
    * clock en btnConsult
    * Corrobora que btnCreate existe
    * Añade descripcion y edit etCoupon (por un codigo existente, ej: "WELCOME_01")
    * Click btnCreate
    * Comprueba el mensaje de snacknbar(Este cupon ya existe, agrege un codigo diferente)
    * */

    @Test
    fun createCouponWithOldCodeTest(){
        val etCoupon = onView(withId(R.id.etCoupon))
        etCoupon.perform(replaceText("WELCOME_01a"))

        val btnConsult = onView(withId(R.id.btnConsult))
        btnConsult.perform(click())

        val btnCreate = onView(withId(R.id.btnCreate))
        btnCreate.check(matches(isDisplayed()))

        val etDescription = onView(withId(R.id.etDescription))
        etDescription.perform(replaceText("3x2 en chocoloates"))
        etCoupon.perform(replaceText("WELCOME_01"))

        btnCreate.perform(click())

        val snackbar = onView(withId(com.google.android.material.R.id.snackbar_text))
        snackbar.check(matches(withText("Este cupon ya existe, agrege un codigo diferente")))
    }

}