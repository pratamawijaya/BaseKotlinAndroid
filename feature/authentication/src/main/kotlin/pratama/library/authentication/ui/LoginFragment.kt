package pratama.library.authentication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import pratama.library.authentication.databinding.FragmentLoginBinding
import pratama.library.core_android.fragment.BaseFragmentBinding

class LoginFragment : BaseFragmentBinding<FragmentLoginBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun setupView(binding: FragmentLoginBinding) {
    }
}