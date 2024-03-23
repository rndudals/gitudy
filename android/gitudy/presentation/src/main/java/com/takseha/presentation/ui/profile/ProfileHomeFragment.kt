package com.takseha.presentation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.takseha.presentation.R
import com.takseha.presentation.databinding.FragmentProfileHomeBinding
import com.takseha.presentation.viewmodel.MainHomeUserInfoUiState

class ProfileHomeFragment : Fragment() {
    private var _binding: FragmentProfileHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.BACKGROUND)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userInfo = arguments?.getSerializable("userInfo") as MainHomeUserInfoUiState

        setUserInfo(userInfo)
    }

    private fun setUserInfo(
        userInfo: MainHomeUserInfoUiState
    ) {
        with(binding) {
            nickname.text = userInfo.name
            githubIdText.text = "@${userInfo.githubId}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}