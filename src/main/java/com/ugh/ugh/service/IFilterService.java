package com.ugh.ugh.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Review;
import com.ugh.ugh.model.UserGame;

public interface IFilterService {

    public abstract ArrayList<Game> filterGamesByKeyword(String keyword) throws Exception;

    public abstract ArrayList<UserGame> filterUserGameByGameStatus(GameStatus gameStatus) throws Exception;

    public abstract ArrayList<Game> filterGameByStartAndEndDate(LocalDate startDate, LocalDate endDate)
            throws Exception;

    public abstract Collection<UserGame> filterUserAllGames(long id) throws Exception;

    public abstract ArrayList<Review> filterReviewByRating(int rating) throws Exception;
}
