package com.akashmjain.ipl;


public class IPLTestDrive {

    public static void main(String[] args) throws Exception {
        FilterInterface filterFacade   = new FilterFacade();
        filterFacade.numberOfMatchesWonOfAllTeamsOverAllYear();
        filterFacade.numberOfMatchesPlayedPerYearForAllYear();
        filterFacade.yearWiseTopEconomicalBowler("2015", 5);
        filterFacade.yearWiseExtraRunConcededPerTeam("2016");
    }

}
