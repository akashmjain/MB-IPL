package com.akashmjain.ipl;

import com.akashmjain.ipl.filter.FilterInterface;

import java.util.*;


public class IPLTestDrive {

    public static void main(String[] args) throws Exception {
        FilterInterface filterFacade   = new FilterFacade();
        filterFacade.numberOfMatchesWonOfAllTeamsOverAllYear();
        filterFacade.numberOfMatchesPlayedPerYearForAllYear();
        filterFacade.yearWiseTopEconomicalBoweler("2015", 5);
        filterFacade.yearWiseExtraRunConcededPerTeam("2016");
    }

}
