package Fragment

import Adapter.VideoAdapter
import Database.AppDatabase
import Database.VideoDB
import Model.MyVideo
import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.*
import com.vohidov.smartlife.R
import com.vohidov.smartlife.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var reference: DatabaseReference
    lateinit var adapter: VideoAdapter
    lateinit var firebaseDatabase: FirebaseDatabase

    //for checking internet connection
    var connectivity: ConnectivityManager? = null
    var info: NetworkInfo? = null

    // room database
    lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)


        appDatabase = AppDatabase.getInstance(binding.root.context)
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("VideoReal")


        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<MyVideo>()
                val listDB = appDatabase.videoDao().getAllVideo()
                val children = snapshot.children
                for (child in children) {
                    val value = child.getValue(MyVideo::class.java)
                    if (value != null) {
                        list.add(value)
                        if (list != null) {
                            val videoDB = VideoDB()
                            videoDB.title = value.title
                            videoDB.desc = value.title
                            videoDB.url = value.title
                            videoDB.comentOnOff = value.comentOnOff
                            videoDB.saveOnOff = value.saveOnOff
                            appDatabase.videoDao().addVideo(videoDB)
                        }

                    }
                }
                adapter = VideoAdapter(list, childFragmentManager, lifecycle)
                binding.viewPager.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        isNetworkConnected()
    }

    @SuppressLint("InflateParams")
    private fun isNetworkConnected() {
        val context1 = binding.root.context

        connectivity =
            context1.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (connectivity != null) {
            info = connectivity!!.activeNetworkInfo

            if (info != null) {
                if (info!!.state == NetworkInfo.State.CONNECTED) {
                    Toast.makeText(context1, "connected", Toast.LENGTH_SHORT).show()
                }
            } else {
                val bottomSheetDialog = BottomSheetDialog(context1)
                bottomSheetDialog.setContentView(
                    layoutInflater.inflate(
                        R.layout.no_internet_dialog,
                        null,
                        false
                    )
                )
                bottomSheetDialog.show()
            }
        }
    }
}