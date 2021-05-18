package pratama.feature.onboard

import android.view.LayoutInflater
import android.view.ViewGroup
import pratama.feature.onboard.databinding.FragmentOnboardBinding
import pratama.library.core_android.fragment.BaseFragmentBinding

class OnboardFragment : BaseFragmentBinding<FragmentOnboardBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnboardBinding
        get() = FragmentOnboardBinding::inflate

    override fun setupView(binding: FragmentOnboardBinding) {

    }
}