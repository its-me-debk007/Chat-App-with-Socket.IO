package task.internship.app.presentation.screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import task.internship.app.R
import task.internship.app.common.TAG
import task.internship.app.databinding.FragmentChatBinding
import task.internship.app.presentation.controller.ChatEpoxyController
import task.internship.app.presentation.helper.TypeDataClass
import task.internship.app.presentation.viewmodel.MyViewModel

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var binding: FragmentChatBinding
    private val viewModel by viewModels<MyViewModel>()
    private lateinit var localUsername: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localUsername = requireArguments().getString("username")!!
        viewModel.addUser(localUsername)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        var totalParticipants: Int

        val epoxyController = ChatEpoxyController()
        binding.epoxyRecyclerView.setControllerAndBuildModels(epoxyController)


        viewModel.listenOnLogin {
            activity?.runOnUiThread {
                val data = it[0] as JSONObject
                totalParticipants = data.getString("numUsers").toInt()

                epoxyController.addItem(
                    TypeDataClass(
                        isTypeMessage = false,
                        localUsername,
                        hasJoined = true,
                        totalParticipants = totalParticipants
                    )
                )
                Log.d(TAG, "Total participants:- $totalParticipants")
            }
        }

        viewModel.listenUserJoining {
            val data = it[0] as JSONObject
            val username = data.getString("username")
            totalParticipants = data.getString("numUsers").toInt()

            epoxyController.addItem(
                TypeDataClass(
                    isTypeMessage = false,
                    username,
                    hasJoined = true,
                    totalParticipants = totalParticipants
                )
            )

            Log.d(TAG, "$username has joined")
            Log.d(TAG, "Total participants:- $totalParticipants")
        }

        viewModel.listenUserLeaving {
            val data = it[0] as JSONObject
            val username = data.getString("username")
            totalParticipants = data.getString("numUsers").toInt()

            epoxyController.addItem(
                TypeDataClass(
                    isTypeMessage = false,
                    username,
                    hasJoined = false,
                    totalParticipants = totalParticipants
                )
            )

            Log.d(TAG, "$username left")
            Log.d(TAG, "Total participants:- $totalParticipants")
        }

        viewModel.listenNewMessage {
            activity?.runOnUiThread {
                val data = it[0] as JSONObject
                val username = data.getString("username")
                val message = data.getString("message")

                epoxyController.addItem(
                    TypeDataClass(
                        isTypeMessage = true,
                        username,
                        message = message
                    )
                )

                Log.d(TAG, "$username: $message")
            }
        }

        binding.textInputLayout.setEndIconOnClickListener {
            val message = binding.textMessage.text

            if (!message.isNullOrBlank()) {
                viewModel.sendMessage(message.trim().toString())

                Log.d(TAG, message.trim().toString())
                epoxyController.addItem(
                    TypeDataClass(
                        isTypeMessage = true,
                        username = localUsername,
                        message = message.trim().toString()
                    )
                )

                binding.textMessage.text?.clear()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.closeConnection()
    }
}
