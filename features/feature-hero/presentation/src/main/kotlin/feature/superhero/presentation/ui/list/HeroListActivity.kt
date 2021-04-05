package feature.superhero.presentation.ui.list

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.corbellini.presentation.R
import com.corbellini.presentation.databinding.ActivityHeroListBinding
import feature.superhero.presentation.models.HeroPresentation
import library.presentation.core.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroListActivity : AppCompatActivity() {
    // region Members

    private val heroListViewModel by viewModel<HeroListViewModel>()

    private lateinit var binding: ActivityHeroListBinding


    private val favoritesAdapter = createHeoresAdapter {
        //todo, on click
    }

    // endregion

    // region Android API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hero_list)

        observeHeroListState()

        handleTextChanges()

        heroListViewModel.dispatchEvent(HeroListEvent.LoadMore)
    }


    // endregion


    // region Private API

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
        heroListViewModel.listState.observe(this, Observer { state ->
            handleLoading(state)

            handleContent(state)

            handleError(state)
        })
    }

    private fun handleError(state: HeroListState) {
        state.error?.run {
            //todo make it better
            Snackbar.make(binding.listHeroRecyclerView, this, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleLoading(state: HeroListState){
        //binding.loading.show()
    }
    private fun handleContent(state: HeroListState){
        state.listHero.let { listHeroes ->
            if (listHeroes.isNotEmpty()) {
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
            adapter = favoritesAdapter.apply {
                submitList(favorites)
            }
        }
    }


    private fun handleNoListHero(){
        //binding.noDataFoundTextView.show()
    }



    // endregion
    companion object{
       val TRESHOLD = 2
    }
}

