package com.cursokotlin.coupons.common.utils

import org.junit.Assert.*
import org.junit.Test
<<<<<<< HEAD
import com.cursokotlin.coupons.R
import com.cursokotlin.coupons.common.entities.CouponEntity
=======
>>>>>>> 72478c1 (tests)

class CouponUtilsKtTest{
    @Test
    fun validateTextCodeSuccessTest(){
<<<<<<< HEAD
        val code = "Welcome"
=======
        var code = "WELCOME"
>>>>>>> 72478c1 (tests)
        assertTrue(validateTextCode(code))
    }
    @Test
    fun validateTextCodeEmptyFailTest(){
<<<<<<< HEAD
        val code = ""
        assertFalse(validateTextCode(code))
    }
    @Test
    fun validateTextCodeMinLengthFailTest(){
        val code = "hola"
        assertFalse(validateTextCode(code))
    }
    @Test
    fun validateTextCodeMaxLengthFailTest(){
        val code = "hola5678901"
        assertFalse(validateTextCode(code))
    }

    @Test
    fun getMsgErrorByCodeNullTest(){
        val errorCode = null
        val expectedValue = R.string.error_unknow
        val result =  getMsgErrorByCode(errorCode)
        assertEquals("Error al evaluar un null. ",expectedValue, result)
    }
    @Test
    fun getMsgErrorByCodeExistTest(){
        val errorCode = Constants.ERROR_EXIST
        val expectedValue = R.string.error_unique_code
        val result =  getMsgErrorByCode(errorCode)
        assertEquals("Error al evaluar un cupon existente. " ,expectedValue, result)
    }
    @Test
    fun getMsgErrorByCodeLengthTest(){
        val errorCode = Constants.ERROR_LENGTH
        val expectedValue = R.string.error_invalid_length
        val result =  getMsgErrorByCode(errorCode)
        assertEquals("Error al evaluar la longitud valida. " ,expectedValue, result)
        assertNotEquals("El recurso no existe", -1, result)
    }

    @Test
    fun checkNotNullCouponTest(){
        val coupon = CouponEntity(code = "ANDROID", description = "Cursos a $9.99")
        assertNotNull("El cupon no deberia ser null. ", coupon)
    }
    @Test
    fun checkNullCouponTest(){
        val coupon = null
        assertNull("El cupon deberia ser null. ", coupon)
    }
    @Test
    fun checkgroupSuccessTest(){
        val aNames = arrayOf("Alain", "Daniel", "Mary") // Valor actual;
        val bNames = arrayOf("Alain", "Daniel", "Mary") // Valor esperado;
        assertArrayEquals("Los arreglos deberias coincidir, revise sus elementos. ",
            bNames, aNames)
    }
    @Test
    fun checkgroupsFailTest(){
        val aNames = arrayOf("Alain", "Daniel", "Mary") // Valor actual;
        val bNames = arrayOf("Alain", "Daniela", "Mary") // Valor esperado;
        assertNotEquals("Los arreglos no deberian coincidir, revise sus elementos. ",
            bNames, aNames)
=======
        var code = ""
        assertFalse(validateTextCode(code))
    }
    @Test
    fun validateTextCodeMinLengthTest(){
        var code = "hola"
        //val code2 = "hola2"
        assertFalse(validateTextCode(code))
        //assertTrue(validateTextCode(code2))
    }
    @Test
    fun validateTextCodeMaxLengthTest(){
        //var code = "Hola567890"
        val code2 = "Hola5678901"
        assertFalse(validateTextCode(code2))
        //assertTrue(validateTextCode(code))
>>>>>>> 72478c1 (tests)
    }
}