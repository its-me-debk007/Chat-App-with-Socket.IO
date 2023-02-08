package task.internship.app.presentation.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import task.internship.app.R
import task.internship.app.common.TAG
import task.internship.app.databinding.FragmentChatBinding
import task.internship.app.presentation.viewmodel.MyViewModel

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<MyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = requireArguments().getString("username")!!
        viewModel.addUser(username)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        var totalParticipants: Int

        viewModel.listenOnLogin {
//            activity?.runOnUiThread {
            val data = it[0] as JSONObject
            totalParticipants = data.getString("numUsers").toInt()

            Log.d(TAG, "Total participants:- $totalParticipants")
//            }
        }

        viewModel.listenUserJoining {
            val data = it[0] as JSONObject
            val username = data.getString("username")
            totalParticipants = data.getString("numUsers").toInt()

            Log.d(TAG, "$username has joined")
            Log.d(TAG, "Total participants:- $totalParticipants")
        }

        viewModel.listenUserLeaving {
            val data = it[0] as JSONObject
            val username = data.getString("username")
            totalParticipants = data.getString("numUsers").toInt()

            Log.d(TAG, "$username left")
            Log.d(TAG, "Total participants:- $totalParticipants")
        }

        viewModel.listenNewMessage {
//            activity?.runOnUiThread {
            val data = it[0] as JSONObject
            val username = data.getString("username")
            val message = data.getString("message")

            Log.d(TAG, "$username: $message")
//            }
        }

        binding.textMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.addTyping()
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG, "after text changed")
            }

        })

        binding.textInputLayout.setEndIconOnClickListener {
            val message = binding.textMessage.text

            if (!message.isNullOrBlank()) {
                viewModel.sendMessage(message.trim().toString())
                binding.textMessage.text?.clear()
            }
        }
    }
}
