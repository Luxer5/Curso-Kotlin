package com.cursokotlin.mdc

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cursokotlin.mdc.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar


class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* findViewById<FloatingActionButton>(R.id.fab).setOnClickListener{
            if (findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode==BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
                findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode= BottomAppBar.FAB_ALIGNMENT_MODE_END
            }else{
                findViewById<BottomAppBar>(R.id.bottom_app_bar).fabAlignmentMode= BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

            }
        }*/

        //Click para mover el pulsar
        binding.fab.setOnClickListener{
            if (binding.bottomAppBar.fabAlignmentMode==BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
                binding.bottomAppBar.fabAlignmentMode= BottomAppBar.FAB_ALIGNMENT_MODE_END
            }else{
                binding.bottomAppBar.fabAlignmentMode= BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

            }
        }

        binding.bottomAppBar.setNavigationOnClickListener {
            Snackbar.make(binding.root, R.string.message_action_succes, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .show()
        }

        binding.content.btnSkip.setOnClickListener { binding.content.cvAdd.visibility= View.GONE }

        binding.content.btnBuy.setOnClickListener {
            Snackbar.make(it, R.string.card_buying, Snackbar.LENGTH_LONG)
                .setAnchorView(binding.fab)
                .setAction(R.string.card_to_go) {
                    Toast.makeText(this, R.string.card_historial, Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        loadImage()

        binding.content.cbEnablePass.setOnClickListener{
            binding.content.tilPassword.isEnabled= !binding.content.tilPassword.isEnabled
        }

        binding.content.etUrl.onFocusChangeListener= View.OnFocusChangeListener { _, focused ->
            //var errorStr: String? = null
            val url = binding.content.etUrl.text.toString()

            if (!focused){
                if (url.isEmpty()){
                    binding.content.tilUrl.error= getString(R.string.card_required)
                }else if (URLUtil.isValidUrl(url)){
                    loadImage(url)
                }else {
                    binding.content.tilUrl.error= getString(R.string.card_invalid_url)
               }
            }

        }
        binding.content.toggleButton.addOnButtonCheckedListener { _, checkedId, _ ->
            binding.content.root.setBackgroundColor(
                when(checkedId){
                    R.id.btnRed-> Color.RED
                    R.id.btnGreen-> Color.GREEN
                    else-> Color.BLUE

                }
            )
        }

    }

    private fun loadImage(url: String= "data:image/jpeg;base64,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")
    {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.content.imgCover)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}