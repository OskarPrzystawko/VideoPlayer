package project.video.player.ui.video.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import org.koin.android.viewmodel.ext.android.viewModel
import project.video.player.R
import project.video.player.common.Event
import project.video.player.video.model.Video

class VideoListFragment : Fragment() {

    companion object {
        fun newInstance() = VideoListFragment()
    }

    private lateinit var searchView: SearchView
    private lateinit var videoListView: RecyclerView
    private lateinit var adapter: VideoListAdapter

    private var snackbarError: Snackbar? = null

    private val viewModel: VideoListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.video_list_fragment, container, false)
        initViews(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservables()
    }

    private fun initViews(parentView: View) {
        findViews(parentView)
        configureViews()
    }

    private fun findViews(parentView: View) {
        searchView = parentView.findViewById(R.id.search_view)
        videoListView = parentView.findViewById(R.id.video_list)
    }

    private fun configureViews() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                onSearchPhraseChange(newText)
                return true
            }
        })

        adapter = VideoListAdapter()
        videoListView.adapter = adapter
        videoListView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initObservables() {
        viewModel.configureVideoListSubject()
        viewModel.getVideoListLiveData()
            .observe(viewLifecycleOwner, this::updateVideoList)
        viewModel.getVideoListErrorLiveData()
            .observe(viewLifecycleOwner, this::showError)
    }

    private fun onSearchPhraseChange(searchPhrase: String?) {
        viewModel.onSearchPhraseChanged(searchPhrase)
    }

    private fun updateVideoList(videoList: List<Video>) {
        adapter.data = videoList
    }

    private fun showError(event: Event<Throwable>) {
        event.getContentIfNotHandled()?.let {
            if (isErrorNotVisible()) showError()
        }
    }

    private fun showError() {
        snackbarError = Snackbar.make(
            requireView(),
            resources.getText(R.string.something_went_wrong),
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction(resources.getText(R.string.dismiss)) {
                dismiss()
                snackbarError = null
            }
            show()
        }
    }

    private fun isErrorNotVisible(): Boolean {
        return snackbarError == null
    }

}