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
    public void invalid_sum() {
        assertThat(checkout.checkout(null), equalTo(-1));
        assertThat(checkout.checkout(""), equalTo(0));

    }

    @Test
    public void normalOffers() {
        assertThat(checkout.checkout("AAAB"), equalTo(160));
        assertThat(checkout.checkout("AAAAA"), equalTo(200));
        assertThat(checkout.checkout("ABBB"), equalTo(125));
        assertThat(checkout.checkout("HHHHHHH"), equalTo(65));
        assertThat(checkout.checkout("VVV"), equalTo(130));
    }

    @Test
    public void multipleOffers(){
        assertThat(checkout.checkout("AAABB"), equalTo(175));
        assertThat(checkout.checkout("HHHHHKKQQQ"), equalTo(245));
    }

    @Test
    public void freeItemOffer() {
        assertThat(checkout.checkout("EEB"), equalTo(80));
        assertThat(checkout.checkout("EEBB"), equalTo(110));
        assertThat(checkout.checkout("EEEEBB"), equalTo(160));
    }

    @Test
    public void buyNgetOneFreeOffers() {
        assertThat(checkout.checkout("FFF"), equalTo(20));
        assertThat(checkout.checkout("FFFF"), equalTo(30));
        assertThat(checkout.checkout("FFFFFF"), equalTo(40));
    }

    @Test
    public void groupOffers(){
        assertThat(checkout.checkout("SSS"), equalTo(45));
        assertThat(checkout.checkout("SSSZ"), equalTo(65));
        assertThat(checkout.checkout("STX"), equalTo(45));
        assertThat(checkout.checkout("STXYZ"), equalTo(82));
        assertThat(checkout.checkout("SSSZZ"), equalTo(85));
        assertThat(checkout.checkout("SSSSZZZ"), equalTo(110));
    }

    @Test
    public void noOffer() {
        assertThat(checkout.checkout("ABCD"), equalTo(115));
    }
}

