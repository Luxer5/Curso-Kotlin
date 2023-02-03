package com.cursokotlin.coupons.mainModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cursokotlin.coupons.R
import com.cursokotlin.coupons.common.entities.CouponEntity
import com.cursokotlin.coupons.common.utils.getMsgErrorByCode
import com.cursokotlin.coupons.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository = MainRepository()

    val coupon =MutableLiveData(CouponEntity())

    private  val hideKeyboard = MutableLiveData<Boolean>()
    fun isHideKeyboard() = hideKeyboard

    private val snackbarMsg = MutableLiveData<Int>()
    fun getSnackbarMsg() = snackbarMsg

    fun consultCouponBycode(){
        coupon.value?.code?.let{ code->
            viewModelScope.launch {
                hideKeyboard.value = true
                coupon.value = repository.consultCouponByCode(code)?: CouponEntity(code=code, isActive = false)
            }
        }
    }

    fun saveCoupon (){
        coupon.value?.let{ couponEntity ->
            viewModelScope.launch {
                hideKeyboard.value = true
                try {
                    couponEntity.isActive = true
                    repository.saveCoupon(couponEntity)
                    consultCouponBycode()
                    snackbarMsg.value = R.string.main_save_success
                }catch (e: Exception){
                    snackbarMsg.value = getMsgErrorByCode(e.message)
                }
            }
        }
    }

}