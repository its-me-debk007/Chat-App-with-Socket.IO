package task.internship.app.presentation.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import task.internship.app.R
import task.internship.app.databinding.FragmentChatBinding

class ChatFragment: Fragment(R.layout.fragment_chat) {

    private lateinit var binding: FragmentChatBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)
    }
}
