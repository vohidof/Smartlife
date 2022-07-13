package Adapter

import Database.VideoDB
import Fragment.MyVideoFragment
import Model.MyVideo
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class VideoAdapter  (
    var list: ArrayList<VideoDB>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return MyVideoFragment.newInstance(list[position])
    }
}