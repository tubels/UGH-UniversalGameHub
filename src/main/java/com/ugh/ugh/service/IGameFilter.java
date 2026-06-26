package com.ugh.ugh.service;

import java.util.ArrayList;

import com.ugh.ugh.model.Game;

public interface IGameFilter {

    public ArrayList<Game> filterGamesByKeyword(String keyword) throws Exception;
    
}
