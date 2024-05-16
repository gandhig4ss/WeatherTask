package com.example.myapplication.ui

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapter.BookmarkAdapter
import com.example.myapplication.adapter.LocationAdapter
import com.example.myapplication.model.Location
import com.example.myapplication.repository.LocationRepository
import com.example.myapplication.viewmodel.HomeViewModel
import com.example.myapplication.viewmodel.HomeViewModelFactory
import com.example.other.BookmarkManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var locationAdapter: LocationAdapter
    private lateinit var button_search: Button
    private lateinit var edit_text_search: EditText
    private lateinit var bookmarkAdapter: BookmarkAdapter
    private lateinit var fabHelp: FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        val locationRepository = LocationRepository(geocoder)
        val viewModelFactory = HomeViewModelFactory(locationRepository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
        val recycler_view_locations = view.findViewById<RecyclerView>(R.id.recycler_view_locations)
        button_search = view.findViewById(R.id.button_search)
        edit_text_search = view.findViewById(R.id.edit_text_search)


        bookmarkAdapter = BookmarkAdapter(emptyList(),
            onItemClick = { location ->
                // Handle click on bookmark name (navigate to another screen)
                // You can implement this logic as needed
                navigateToCityFragment(location)
            },
            onDeleteClick = { location ->
                // Handle click on delete button (remove bookmark)
                val bookmarkManager = BookmarkManager(requireContext())
                bookmarkManager.removeBookmark(location)
                val updatedBookmarks = bookmarkManager.getAllBookmarks()
                bookmarkAdapter.updateBookmarks(updatedBookmarks.toList())
            })


        recycler_view_locations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bookmarkAdapter
        }

        val bookmarkManager = BookmarkManager(requireContext())
        val bookmarks = bookmarkManager.getAllBookmarks()
        bookmarkAdapter.bookmarks = bookmarks.toList()
        bookmarkAdapter.notifyDataSetChanged()

        locationAdapter = LocationAdapter() { location ->
            navigateToCityFragment(location)
        }

        button_search.setOnClickListener {
            val query = edit_text_search.text.toString().trim()
            if (query.isNotEmpty()) {
                val searchResults = viewModel.searchLocation(query)
                locationAdapter.submitList(searchResults)
                recycler_view_locations.adapter = locationAdapter

            } else {
                Toast.makeText(requireContext(), "Please enter a search query", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        fabHelp = view.findViewById(R.id.fab_help)
        fabHelp.setOnClickListener {
            navigateToHelpFragment()
        }


    }

    private fun navigateToHelpFragment() {

        val helpFragment = HelpFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, helpFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun navigateToCityFragment(location: Location) {
        val cityFragment = CityFragment()
        val bundle = Bundle()
        bundle.putString("location", location.name)
        bundle.putDouble("latitude", location.latitude)
        bundle.putDouble("longitude", location.longitude)
        cityFragment.arguments = bundle
        val bookmarkManager = BookmarkManager(requireContext())
        val location = Location(location.latitude, location.longitude, location.name)
        bookmarkManager.addBookmark(location)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, cityFragment)
            .addToBackStack(null)
            .commit()
    }
}
