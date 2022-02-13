package com.aliz.myapplication

import android.R
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.aliz.myapplication.databinding.FragmentABinding
import eightbitlab.com.blurview.RenderScriptBlur

import android.graphics.drawable.Drawable


class FragmentA : Fragment() {

    private var _binding: FragmentABinding? = null
    private val binding get() = _binding!!
    private var moods: ArrayList<Mood>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitmap = Bitmap.createBitmap(30, 40, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)


// Now, let the view draw itself on this canvas
        val radius = 20f

        val decorView: View? = activity?.window?.decorView
        //ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        //ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        val rootView = decorView?.findViewById<View>(R.id.content) as ViewGroup
        //Set drawable to draw in the beginning of each blurred frame (Optional).
        //Can be used in case your layout has a lot of transparent space and your content
        //gets kinda lost after after blur is applied.
        //Set drawable to draw in the beginning of each blurred frame (Optional).
        //Can be used in case your layout has a lot of transparent space and your content
        //gets kinda lost after after blur is applied.
        val windowBackground = decorView?.background

      /*  binding.blurView.setupWith(rootView)
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(true)*/
// Now, let the view draw itself on this canvas
        binding.apply {
            next.setOnClickListener {
                view.findNavController().navigate(FragmentADirections.actionFragmentAToFragmentB())
            }
            // moods?.let { mood.init(it) }
        }
    }
}