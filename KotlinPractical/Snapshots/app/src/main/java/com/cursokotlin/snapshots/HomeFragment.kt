package com.cursokotlin.snapshots

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cursokotlin.snapshots.databinding.FragmentHomeBinding
import com.cursokotlin.snapshots.databinding.ItemSnapshotBinding
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.SnapshotParser
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class HomeFragment : Fragment() , HomeAux{

    private lateinit var mBinding: FragmentHomeBinding

    private lateinit var mFirebaseAdapter: FirebaseRecyclerAdapter<Snapshot, SnapshotHolder>
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       mBinding= FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query = FirebaseDatabase.getInstance().reference.child("snapshots")

        val options =
        FirebaseRecyclerOptions.Builder<Snapshot>().setQuery(query){
            val snapshot = it.getValue(Snapshot::class.java)
            snapshot!!.id = it.key!!
            snapshot
        }.build()


        mFirebaseAdapter = object : FirebaseRecyclerAdapter<Snapshot, SnapshotHolder>(options){
            private lateinit var mContext: Context

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapshotHolder {
                mContext = parent.context

                val view= LayoutInflater.from(mContext)
                    .inflate(R.layout.item_snapshot, parent, false)
                return SnapshotHolder(view)
            }

            override fun onBindViewHolder(holder: SnapshotHolder, position: Int, model: Snapshot) {
                val snapshot = getItem(position)

                with (holder){
                    setListener(snapshot)

                    binding.tvTitle.text = snapshot.title
                    binding.cbLike.text = snapshot.likeList.keys.size.toString()
                    FirebaseAuth.getInstance().currentUser?.let {
                        binding.cbLike.isChecked = snapshot.likeList.containsKey(it.uid)
                    }
                    Glide.with(mContext)
                        .load(snapshot.photoUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .into(binding.imgPhoto)
                }
            }

            @SuppressLint("NotifyDataSetChanged") //error interno firebase ui version 8.0.2
            override fun onDataChanged() {
                super.onDataChanged()
                mBinding.progressBar.visibility = View.GONE
                notifyDataSetChanged()
            }

            override fun onError(error: DatabaseError) {
                super.onError(error)
                Toast.makeText(mContext, error.message, Toast.LENGTH_SHORT).show()
            }
        }

        mLayoutManager = LinearLayoutManager(context)

        mBinding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mLayoutManager
            adapter = mFirebaseAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        mFirebaseAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mFirebaseAdapter.stopListening()
    }

    override fun gotoTop() {
        mBinding.recyclerView.smoothScrollToPosition(0)
    }

    private fun deleteSnapshot(snapshot: Snapshot){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialog_delete_title)
            .setPositiveButton(R.string.dialog_delete_confirm) { _, _ ->
                val storageSnapshotsRef = FirebaseStorage.getInstance().reference
                    .child("snapshots")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid)
                    .child(snapshot.id)
                val databaseReference = FirebaseDatabase.getInstance().reference.child("snapshots")
                storageSnapshotsRef.delete().addOnCompleteListener {
                    if (it.isSuccessful){
                        databaseReference.child(snapshot.id).removeValue()
                    } else{
                        Snackbar.make(mBinding.root, getString(R.string.home_delete_photo_error),
                            Snackbar.LENGTH_LONG).show()
                    }
                }

            }
            .setNegativeButton(R.string.dialog_delete_cancel, null)
            .show()

    }

    private fun setLike(snapshot: Snapshot, checked: Boolean){
        val databaseReference = FirebaseDatabase.getInstance().reference.child("snapshots")
        if (checked) {
            databaseReference.child(snapshot.id).child("likeList")
                .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(checked)
        } else{
            databaseReference.child(snapshot.id).child("likeList")
                .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(null)
        }
    }

    inner class SnapshotHolder (view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemSnapshotBinding.bind(view)

        fun setListener (snapshot: Snapshot){

            binding.btnDelete.setOnClickListener{ deleteSnapshot(snapshot) }

            binding.cbLike.setOnCheckedChangeListener { compoundButton, checked ->
                setLike(snapshot, checked)
            }
        }
    }
}