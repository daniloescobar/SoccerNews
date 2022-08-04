package com.escobargames.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.escobargames.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Remover mock de noticias
        List<News> news = new ArrayList<>();
        news.add(new News("Ferroviaria tem desfalques importantes", "Time feminino da ferroviaria vem com desfalques para o jogo contra o Corinthians"));
        news.add(new News("São Paulo se classifica para a semifinal da Copa do Brasil", "São Paulo ganha novamente do America-MG e avança para as semifinais da Copa do Brasil"));
        news.add(new News("Atletico-MG empata com Palmeiras, mas se classifica nos penaltis", "Em grande jogo no Allianz Parque, os dois times empatam e o galo ganha nos penaltis"));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}