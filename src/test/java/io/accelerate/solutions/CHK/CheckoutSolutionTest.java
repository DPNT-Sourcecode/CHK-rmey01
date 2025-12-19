package io.accelerate.solutions.CHK;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CheckoutSolutionTest {
    private CheckoutSolution checkout;

    @BeforeEach
    public void setUp() {
        checkout = new CheckoutSolution();
    }

    @Test
    public void compute_sum() {
        assertThat(checkout.checkout(""), equalTo(0));
    }

    @Test
    public void invalid_sum() {
        assertThat(checkout.checkout("KKK"), equalTo(-1));
    }

    @Test
    public void offerA() {
        assertThat(checkout.checkout("AAAB"), equalTo(160));
        assertThat(checkout.checkout("AAAAA"), equalTo(200));
    
    }

    @Test
    public void offerB() {
        assertThat(checkout.checkout("ABBB"), equalTo(125));
    }

    @Test
    public void bothOffers(){
        assertThat(checkout.checkout("AAABB"), equalTo(175));
    }

    @Test
    public void freeItemOffer() {
        assertThat(checkout.checkout("EEB"), equalTo(80));
        assertThat(checkout.checkout("EEBB"), equalTo(110));
        assertThat(checkout.checkout("EEEEBB"), equalTo(160));
    }

    @Test
    public void offerF() {
        assertThat(checkout.checkout("FFF"), equalTo(20));
        assertThat(checkout.checkout("FFFF"), equalTo(30));
        assertThat(checkout.checkout("FFFFFF"), equalTo(40));
    }

    @Test
    public void noOffer() {
        assertThat(checkout.checkout("ABCD"), equalTo(115));
    }
}



