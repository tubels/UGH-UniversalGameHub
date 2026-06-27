package com.ugh.ugh.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.UserGame;
import com.ugh.ugh.repo.IDeveloperRepo;
import com.ugh.ugh.repo.IGameRepo;
import com.ugh.ugh.repo.IGenreRepo;
import com.ugh.ugh.repo.IPublisherRepo;
import com.ugh.ugh.repo.IUserGameRepo;
import com.ugh.ugh.service.IFilterService;

@Service
public class FilterServiceImpl implements IFilterService {

    private final IGameRepo gameRepo;
    private final IDeveloperRepo developerRepo;
    private final IPublisherRepo publisherRepo;
    private final IGenreRepo genreRepo;
    private final IUserGameRepo userGameRepo;

    FilterServiceImpl(IGameRepo gameRepo, IDeveloperRepo developerRepo,
            IPublisherRepo publisherRepo, IGenreRepo genreRepo, IUserGameRepo userGameRepo) {
        this.gameRepo = gameRepo;
        this.developerRepo = developerRepo;
        this.publisherRepo = publisherRepo;
        this.genreRepo = genreRepo;
        this.userGameRepo = userGameRepo;
    }

    @Override
    public ArrayList<Game> filterGamesByKeyword(String keyword) throws Exception {
        if (keyword == null || keyword.isEmpty()) {
            return (ArrayList<Game>) gameRepo.findAll();
        } else {
            ArrayList<Game> gamesWithKeyword = gameRepo
                    .findByDescriptionContainingOrTitleContainingOrDeveloperNameContainingOrPublisherNameContainingOrGenresNameContaining(
                            keyword, keyword, keyword, keyword, keyword);

            if (gamesWithKeyword.isEmpty()) {
                throw new Exception("Cannot find games with keyword " + keyword);
            }

            return gamesWithKeyword;
        }
    }

    @Override
    public ArrayList<UserGame> filterUserGameByGameStatus(GameStatus gameStatus) throws Exception {
        if (gameStatus == null || !(userGameRepo.existsByGameStatus(gameStatus))) {
            return (ArrayList<UserGame>) userGameRepo.findAll();
        } else {
            return userGameRepo.findAllByGameStatus(gameStatus);
        }
    }

    public ArrayList<Game> filterGameByStartAndEndDate(LocalDate startDate, LocalDate endDate) throws Exception {
        if (startDate == null && endDate == null) {
            return (ArrayList<Game>) gameRepo.findAll();
        } else if (startDate == null) {
            return gameRepo.findAllByReleaseDateBefore(endDate);
        } else if (endDate == null) {
            return gameRepo.findAllByReleaseDateAfter(startDate);
        } else {
            return gameRepo.findAllByReleaseDateBetween(startDate, endDate);
        }
    }
}
