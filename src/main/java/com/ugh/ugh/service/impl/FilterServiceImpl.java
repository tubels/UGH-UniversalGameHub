package com.ugh.ugh.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Review;
import com.ugh.ugh.model.User;
import com.ugh.ugh.model.UserGame;
import com.ugh.ugh.repo.IDeveloperRepo;
import com.ugh.ugh.repo.IGameRepo;
import com.ugh.ugh.repo.IGenreRepo;
import com.ugh.ugh.repo.IPublisherRepo;
import com.ugh.ugh.repo.IReviewRepo;
import com.ugh.ugh.repo.IUserGameRepo;
import com.ugh.ugh.service.IFilterService;
import com.ugh.ugh.service.IUserCRUDService;
import com.ugh.ugh.service.IUsergameCRUDService;

@Service
public class FilterServiceImpl implements IFilterService {

    private final IGameRepo gameRepo;
    private final IUserGameRepo userGameRepo;
    private final IUserCRUDService userCRUDService;
    private final IReviewRepo reviewRepo;

    FilterServiceImpl(IGameRepo gameRepo, IUserGameRepo userGameRepo,
            IUserCRUDService userCRUDService, IReviewRepo reviewRepo) {
        this.gameRepo = gameRepo;
        this.userGameRepo = userGameRepo;
        this.userCRUDService = userCRUDService;
        this.reviewRepo = reviewRepo;
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
        if (gameStatus == null) {
            return (ArrayList<UserGame>) userGameRepo.findAll();
        } else if (!(userGameRepo.existsByGameStatus(gameStatus))) {
            return new ArrayList<>();
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

    @Override
    public Collection<UserGame> filterUserAllGames(long id) throws Exception {
        if (id == -1) {
            return (Collection<UserGame>) userGameRepo.findAll();
        }
        User userFromDB = userCRUDService.retrieveById(id);
        return userFromDB.getUserGames();
    }

    @Override
    public ArrayList<Review> filterReviewByRating(int rating) throws Exception {
        if (reviewRepo.existsByRating(rating)) {
            return reviewRepo.findAllByRating(rating);
        } else {
            return (ArrayList<Review>) reviewRepo.findAll();
        }
    }
}
