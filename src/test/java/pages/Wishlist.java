package pages;

import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Wishlist {

    public Wishlist goToWishList() {
        $("[data-item=courses-wishlist]").click();
        return this;
    }

    public Wishlist courseMoreInfo() {
        $(" .menu-more_icon").click();
        return this;
    }

    public Wishlist removeFromWishlist() {
        $("[data-qa=menu-item-remove-from-wishlist]").click();
        return this;
    }

    public Wishlist confirmChanges() {
        $(" .modal-popup__container").shouldBe(visible);
        $(" .danger[type=button]").click();
        return this;
    }

    public Wishlist checkConfirmed() {
        $(" .modal-popup__container").shouldBe(not(visible));
        return this;
    }
}