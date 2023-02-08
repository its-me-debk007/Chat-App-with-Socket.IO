package task.internship.app.presentation.screen

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import task.internship.app.R
import task.internship.app.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.button.setOnClickListener {
            checkUsernameAndNavigate()
        }

        binding.username.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                checkUsernameAndNavigate()
            }
            true
        }
    }

    private fun checkUsernameAndNavigate() {
        binding.textInputLayout.error = null

        if (binding.username.text.isNullOrBlank()) {
            binding.textInputLayout.error = "add a valid username"
            return
        }

        goToNextFragment(binding.username.text.toString())
    }

    private fun goToNextFragment(username: String) {
        val chatFragment = ChatFragment()

        val bundle = Bundle()
        bundle.putString("username", username)

        chatFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chatFragment)
            .commit()
    }
}
