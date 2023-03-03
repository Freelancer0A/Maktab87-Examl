package com.example.maktab87_quiz_android_1_q1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.maktab87_quiz_android_1_q1.databinding.FragmentDoneTasksBinding


class DoneTasksFragment : Fragment() {

    private val viewModel: TaskViewModel by activityViewModels()
    private lateinit var binding: FragmentDoneTasksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDoneTasksBinding.inflate(inflater, container, false)
        return binding.root
    }
}