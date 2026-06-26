package com.ugh.ugh.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugh.ugh.model.Developer;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Genre;
import com.ugh.ugh.model.Publisher;
import com.ugh.ugh.repo.IDeveloperRepo;
import com.ugh.ugh.repo.IGameRepo;
import com.ugh.ugh.repo.IGenreRepo;
import com.ugh.ugh.repo.IPublisherRepo;
import com.ugh.ugh.service.IGameCRUDService;
import com.ugh.ugh.service.IGameFilter;

@Service
public class GameFilterServiceImpl implements IGameFilter {

    private final IGameRepo gameRepo;
    private final IDeveloperRepo developerRepo;
    private final IPublisherRepo publisherRepo;
    private final IGenreRepo genreRepo;

    GameFilterServiceImpl(IGameRepo gameRepo, IDeveloperRepo developerRepo,
            IPublisherRepo publisherRepo, IGenreRepo genreRepo) {
        this.gameRepo = gameRepo;
        this.developerRepo = developerRepo;
        this.publisherRepo = publisherRepo;
        this.genreRepo = genreRepo;
    }

    @Override
    public ArrayList<Game> filterGamesByKeyword(String keyword) throws Exception {
        if (keyword == null || keyword.isEmpty()) {
            throw new Exception("Not a valid input");
        }

        ArrayList<Game> gamesWithKeyword = gameRepo
                .findByDescriptionContainingOrTitleContainingOrDeveloperNameContainingOrPublisherNameContainingOrGenresNameContaining(
                        keyword, keyword, keyword, keyword, keyword);

        if (gamesWithKeyword.isEmpty()) {
            throw new Exception("Cannot find games with keyword " + keyword);
        }

        return gamesWithKeyword;
    }
}
