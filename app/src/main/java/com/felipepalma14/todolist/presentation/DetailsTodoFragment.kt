package com.felipepalma14.todolist.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.felipepalma14.todolist.R
import com.felipepalma14.todolist.databinding.FragmentDetailsTodoBinding
import com.felipepalma14.todolist.databinding.FragmentTodoListBinding

class DetailsTodoFragment : Fragment() {


    private var _binding: FragmentDetailsTodoBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailsTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

}