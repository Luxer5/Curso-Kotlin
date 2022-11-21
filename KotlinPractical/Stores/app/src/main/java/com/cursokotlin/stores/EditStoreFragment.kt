package com.cursokotlin.stores

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.cursokotlin.stores.databinding.FragmentEditStoreBinding
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.nio.channels.AsynchronousByteChannel


class EditStoreFragment : Fragment() {

    private lateinit var mBinding: FragmentEditStoreBinding
    private var mActivity: MainActivity?= null

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?): View? {
        mBinding= FragmentEditStoreBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mActivity= activity as? MainActivity
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mActivity?.supportActionBar?.title= getString(R.string.edit_store_title_add)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                hideKeyboard()
                mActivity?.onBackPressed()
                true
            }
            R.id.action_save ->{
                val store = StoreEntity(name= mBinding.etName.text.toString().trim(),
                phone = mBinding.etPhone.text.toString().trim(),
                website= mBinding.etWebsite.text.toString().trim())
                doAsync {
                    store.id=StoreApplication.database.storeDao().addStore(store)
                    uiThread {
                        mActivity?.addStore(store)
                        hideKeyboard()
                        Toast.makeText(mActivity, R.string.edit_store_message_save_success, Toast.LENGTH_SHORT).show()
                        mActivity?.onBackPressed()
                    }
                }

                true
            }
            else ->  super.onOptionsItemSelected(item)
        }
    }
    private fun hideKeyboard(){
        val imm= mActivity?.getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    override fun onDestroyView() {
        hideKeyboard()
        super.onDestroyView()
    }

    override fun onDestroy() {
        mActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mActivity?.supportActionBar?.title=getString(R.string.app_name)
        mActivity?.hideFab(true)

        setHasOptionsMenu(false)
        super.onDestroy()
    }
}