package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class HelpFragment : Fragment() {
    private val helpUrl = "file:///android_asset/help.html"

    private lateinit var web_view_help: WebView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        web_view_help = view.findViewById(R.id.web_view_help)
        // Load help content in WebView
        web_view_help.loadUrl(helpUrl)
    }
}