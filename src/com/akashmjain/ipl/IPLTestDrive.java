package com.akashmjain.ipl;


public class IPLTestDrive {
    public static void main(String[] args) {
        FilterInterface filterFacade = FilterFacade.getInstance();
        filterFacade.numberOfMatchesWonOfAllTeamsOverAllYear();
        filterFacade.numberOfMatchesPlayedPerYearForAllYear();
        filterFacade.yearWiseTopEconomicalBowler("2015", 5);
        filterFacade.yearWiseExtraRunConcededPerTeam("2016");
        filterFacade.topMostCatchesInHistoryPlayers(5);
    }
}
