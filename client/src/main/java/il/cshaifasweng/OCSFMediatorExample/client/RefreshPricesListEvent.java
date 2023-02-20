package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.NewPrice;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;

import java.util.List;

public class RefreshPricesListEvent {
    private List<Price> priceList;
    private List<NewPrice> newPriceList;
    public RefreshPricesListEvent(Message msg) {
        priceList= (List<Price>) msg.getObject();
        newPriceList= (List<NewPrice>) msg.getObject2();
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public List<NewPrice> getNewPriceList() {
        return newPriceList;
    }

    public void setNewPriceList(List<NewPrice> newPriceList) {
        this.newPriceList = newPriceList;
    }
}
