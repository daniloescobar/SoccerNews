package com.escobargames.soccernews.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.escobargames.soccernews.MainActivity;
import com.escobargames.soccernews.databinding.FragmentFavoritesBinding;
import com.escobargames.soccernews.domain.News;
import com.escobargames.soccernews.ui.adapters.NewsAdapter;

import java.util.List;

public class FavoritesFragment extends Fragment {

    //private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        FavoritesViewModel favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        MainActivity activity = (MainActivity) getActivity();
        loadFavoriteNews();


        return binding.getRoot();
    }

    private void loadFavoriteNews() {
        MainActivity activity = (MainActivity) getActivity();
       if (activity != null) {
           List<News> favoriteNews = activity.getDb().newsDao().loadFavoriteNews();
           binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
           binding.rvNews.setAdapter(new NewsAdapter(favoriteNews, updatedNews -> {
               activity.getDb().newsDao().save(updatedNews);
               loadFavoriteNews();
           }));
       }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}