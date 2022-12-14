package com.escobargames.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.escobargames.soccernews.MainActivity;
import com.escobargames.soccernews.databinding.FragmentNewsBinding;
import com.escobargames.soccernews.ui.adapters.NewsAdapter;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;
    private FragmentNewsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, updatedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                if (activity != null) {
                    activity.getDb().newsDao().save(updatedNews);
                }
            }));
        });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
           switch (state){
               case DOING:
                   //TODO Iniciar SwipeRefreshLayout(loading)
                   break;
               case DONE:
                   //TODO Finalizar SwipeRefreshLayout(loading)
                   break;
               case ERROR:
                   //TODO Finalizar SwipeRefreshLayout(loading)
                   //TODO Mostrar um erro genérico
           }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}