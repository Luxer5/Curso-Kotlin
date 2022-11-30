package com.cursokotlin.snapshots

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.contract.ActivityResultContracts
import com.cursokotlin.snapshots.databinding.FragmentAddBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class AddFragment : Fragment() {

    //private val RC_GALLERY = 18
    private val PATH_SNAPSHOT = "snapshots"

    private lateinit var mBinding: FragmentAddBinding
    private lateinit var mStorageReference: StorageReference
    private lateinit var mDatabaseReference: DatabaseReference

    private var mPhotoSelectedUri: Uri? = null

    private val galleryResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){

                mPhotoSelectedUri = it.data?.data

                with(mBinding){
                    imgPhoto.setImageURI(mPhotoSelectedUri)
                    tilTitle.visibility= View.VISIBLE
                    tvMessage.text = getString(R.string.post_message_valid_title)
                }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAddBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.btnPost.setOnClickListener{ postSnapshot() }

        mBinding.btnSelect.setOnClickListener{ openGallery() }

        mStorageReference = FirebaseStorage.getInstance().reference
        mDatabaseReference = FirebaseDatabase.getInstance().reference.child(PATH_SNAPSHOT)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryResult.launch(intent)
    }

    private fun postSnapshot() {
        mBinding.progressBar.visibility = View.VISIBLE
        val key = mDatabaseReference.push().key!!
        val storageRef = mStorageReference.child(PATH_SNAPSHOT)
            .child(FirebaseAuth.getInstance().currentUser!!.uid).child(key)
        if (mPhotoSelectedUri != null){
            storageRef.putFile(mPhotoSelectedUri!!)
                .addOnProgressListener {
                    val progress = (100 * it.bytesTransferred/it.totalByteCount).toDouble()
                    mBinding.progressBar.progress= progress.toInt()
                    mBinding.tvMessage.text = "$progress%"
                    hideKeyboard()
                }
                .addOnCompleteListener{
                    mBinding.progressBar.visibility = View.INVISIBLE
                }
                .addOnSuccessListener {
                    Snackbar.make(mBinding.root, "Instantanea publicada",
                    Snackbar.LENGTH_SHORT).show()
                    it.storage.downloadUrl.addOnSuccessListener {
                        saveSnapshot(key, it.toString(), mBinding.etTitle.text.toString().trim())
                        mBinding.tilTitle.visibility= View.GONE
                        mBinding.tvMessage.text = getString(R.string.post_message_title)
                    }
                }
                .addOnFailureListener{
                    Snackbar.make(mBinding.root, "No se pudo subir, intente, mas tarde",
                        Snackbar.LENGTH_SHORT).show()
                }
        }
    }

    private fun saveSnapshot(key: String, url: String, title: String){
        val snapshot = Snapshot(title=title, photoUrl = url )
        mDatabaseReference.child(key). setValue(snapshot)
    }

    private fun hideKeyboard(){
        val imm= context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}