package com.liuhaozzu.ddd.auctiondemo.domain;

import java.time.LocalDateTime;

/**
 * @author liuhao01
 * @date 4/16/21 7:33 PM
 */
public class Auction {

    private Bid winningBid;

    public void placeBidFor(Bid bid, LocalDateTime currentTime) {

    }

    private void place(Bid determineWinningBidIncrement) {
    }


    private boolean bidderIsIncreasingMaximumBid(Bid bid) {
        return false;
    }

    private void placeABidForTheFirst(Bid bid) {

    }

    private boolean firstOffer() {
        return false;
    }

    private boolean stillInProcess(LocalDateTime currentTime) {
        return false;
    }
}
