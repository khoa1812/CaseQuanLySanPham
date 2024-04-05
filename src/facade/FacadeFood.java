package facade;

import controller.CookieManager;
import controller.MeatManager;

public class FacadeFood {

    public void display() {
        System.out.println("\n\n--------  BÁNH  ------ \n");
        CookieManager.displayCookies();
        System.out.println("\n\n\n--------  THỊT  -------- \n");
        MeatManager.displayMeats();
    }
}
