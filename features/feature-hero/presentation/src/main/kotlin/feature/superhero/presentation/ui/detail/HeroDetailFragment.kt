package feature.superhero.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.corbellini.presentation.R
import com.corbellini.presentation.databinding.FragmentHeroDetailBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HeroDetailFragment : Fragment() {
    // region Members

    private val heroDetailViewModel by viewModel<HeroDetailViewModel>()

    private lateinit var binding: FragmentHeroDetailBinding

    // endregion

    // region Android API

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hero_detail, container, false)

        observeHeroListState()


        dispatchLoadById(1)

        return binding.root
    }
    // endregion

    // region Private API
    private fun dispatchLoadById(id: Int) {
        heroDetailViewModel.dispatchEvent(HeroDetailEvent.Load(id = id))
    }

    private fun observeHeroListState() {
        lifecycleScope.launch {
            heroDetailViewModel.detailState.collect { state ->
                handleLoading(state)

                handleContent(state)

                handleError(state)
            }
        }
    }

    private fun handleError(state: HeroDetailState) {
        state.error?.run {
            //todo make it better
            Snackbar.make(binding.root, this, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun handleLoading(state: HeroDetailState) {
        //binding.loading.show()
    }

    private fun handleContent(state: HeroDetailState) {
        state.hero?.let { hero ->
            binding.hero = hero
        }
    }

    // endregion
}


