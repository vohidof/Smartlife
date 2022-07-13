package Fragment

import Model.MyVideo
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vohidov.smartlife.R
import com.vohidov.smartlife.databinding.FragmentMyVideoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

/**
 * A simple [Fragment] subclass.
 * Use the [MyVideoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyVideoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: MyVideo? = null
    private var i : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as MyVideo
        }
    }

    lateinit var binding: FragmentMyVideoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMyVideoBinding.inflate(layoutInflater)

        val videoUri =param1?.url
        val title = param1?.title
        val desc = param1?.desc

        binding.textVideoDescription.text = desc
        binding.textVideoTitle.text = title
        binding.videoView.setVideoPath(videoUri)
        binding.videoView.setOnPreparedListener(object : MediaPlayer.OnPreparedListener{
            override fun onPrepared(mp: MediaPlayer) {
                binding.videoProgressBar.visibility = View.GONE
                mp.start()

            }
        })

        binding.videoView.setOnCompletionListener { object : MediaPlayer.OnCompletionListener{
            override fun onCompletion(mp: MediaPlayer) {
                mp.start()
            }
        } }

        binding.videoView.setOnClickListener {
            if (binding.videoView.isPlaying){
                binding.videoView.pause()
            }else{
                binding.videoView.start()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.videoView.setOnClickListener(object : DoubleClickListener() {
            override fun onDoubleClick(v: View?) {
                Toast.makeText(binding.root.context, "twice", Toast.LENGTH_SHORT).show()
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment MyVideoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: MyVideo) =
            MyVideoFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }

    // double click
    abstract class DoubleClickListener : View.OnClickListener{
        private var lastCLickTime: Long = 0
        override fun onClick(v: View?) {
            val clickTime = System.currentTimeMillis()
            if(clickTime - lastCLickTime < DOUBLE_CLICK_TIME_DELTA ){
                onDoubleClick(v)
            }
            lastCLickTime = clickTime
        }

        abstract fun onDoubleClick(v: View?)

        companion object {
            private const val DOUBLE_CLICK_TIME_DELTA: Long = 300
        }
    }
}