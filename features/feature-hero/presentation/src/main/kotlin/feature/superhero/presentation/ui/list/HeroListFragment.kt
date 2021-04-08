package feature.superhero.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.corbellini.presentation.R
import com.corbellini.presentation.databinding.FragmentHeroListBinding
import com.google.android.material.snackbar.Snackbar
import feature.superhero.presentation.models.HeroPresentation
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import library.presentation.core.bindScrollListener
import library.presentation.core.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroListFragment : Fragment() {
    // region Members

    private val heroListViewModel by viewModel<HeroListViewModel>()

    private lateinit var binding: FragmentHeroListBinding

    private val favoritesAdapter = createHeoresAdapter {
        findNavController().navigate(R.id.action_heroListFragment_to_heroDetailFragment)
    }

    private val onScrollHitBottomLoadMore = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                dispatchLoadMore()
            }
        }
    }

    // endregion

    // region Android API

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hero_list, container, false)

        observeHeroListState()

        handleTextChanges()

        dispatchLoadMore()

        return binding.root
    }
    // endregion

    // region Private API
    private fun dispatchLoadMore() {
        heroListViewModel.dispatchEvent(HeroListEvent.LoadMore)
    }

    private fun handleTextChanges() {
        /*binding.searchEditText.doOnTextChanged { text, _, _, _ ->
            text.let { query ->
                if (query.length >= TRESHOLD) {
                    binding.dashboardLayout.transitionToEnd()
                    heroListViewModel.dispatchEvent(HeroListEvent.Search( query))
                }
            }
        }*/
    }

    private fun observeHeroListState() {
        lifecycleScope.launch {
            heroListViewModel.listState.collect { state ->
                handleLoading(state)

                handleContent(state)

                handleError(state)
            }
        }
    }

    private fun handleError(state: HeroListState) {
        state.error?.run {
            //todo make it better
            Snackbar.make(binding.listHeroRecyclerView, this, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleLoading(state: HeroListState) {
        //binding.loading.show()
    }

    private fun handleContent(state: HeroListState) {
        state.listHero.let { listHeroes ->
            if (listHeroes.isNotEmpty()) {
                binding.listHeroRecyclerView.bindScrollListener(
                    state.complete,
                    onScrollHitBottomLoadMore
                )
                handleListHeroes(listHeroes)
            } else {
                handleNoListHero()
            }
        }
    }

    private fun handleListHeroes(favorites: List<HeroPresentation>) {
        //binding.noDataFoundTextView.hide()
        binding.listHeroRecyclerView.show()
        binding.listHeroRecyclerView.apply {
            //to avoid 'blink' and scroll to the top
            adapter ?: run { adapter = favoritesAdapter }
            favoritesAdapter.submitList(favorites)
        }
    }

    private fun handleNoListHero() {
        //binding.noDataFoundTextView.show()
    }

    // endregion
}


