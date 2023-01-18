package com.cursokotlin.coupons.mainModule.model

import com.cursokotlin.coupons.CouponsApplication
import com.cursokotlin.coupons.common.dataAccess.CouponDao

class RoomDatabase {
    private val dao: CouponDao by lazy {CouponsApplication.database.couponDao() }

    suspend fun consultCouponByCode(code: String) = dao.consultCouponByCode(code)
}