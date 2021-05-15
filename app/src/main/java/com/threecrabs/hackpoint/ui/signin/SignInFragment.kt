package com.threecrabs.hackpoint.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.threecrabs.hackpoint.changeAuth
import com.threecrabs.hackpoint.databinding.SignInFragmentBinding

class SignInFragment: Fragment() {

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: SignInFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = SignInFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSignIn.setOnClickListener {
            viewModel.signInWithValidation(
                binding.inputEmail.editText?.text.toString(),
                binding.inputPassword.editText?.text.toString(),
            )
        }
        binding.inputEmail.editText?.addTextChangedListener {
            binding.inputEmail.error = null
        }

        binding.inputPassword.editText?.addTextChangedListener {
            binding.inputPassword.error = null
        }
        viewModel.success.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                requireActivity().changeAuth()
            }
        }
        viewModel.inputError.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let {
                it[SignInViewModel.LOGIN]?.let {
                    binding.inputEmail.error = getString(it)
                }
                it[SignInViewModel.PASSWORD]?.let {
                    binding.inputPassword.error = getString(it)
                }
            }
        }
    }
}