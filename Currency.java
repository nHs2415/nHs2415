class Currency {
    String currencyName;
    double exchangeRate;
    double foreignCost;

    public Currency(String currencyName, double exchangeRate, double foreignCost) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
        this.foreignCost = foreignCost;
    }

    public void printCurrencyDetails() {
        System.out.println("Currency Name: " + currencyName);
        System.out.println("Exchange Rate: " + exchangeRate);
        System.out.println("Foreign Cost: " + foreignCost);
    }
}

class LocalCostService {
    String costName;
    double localCost;
    double taxedLocalCost;
    double tax;
    Currency currency;
    boolean exempted;

    public LocalCostService(String costName, boolean exempted) {
        this.costName = costName;
        this.exempted = exempted;
        this.localCost = 0.0;
        this.taxedLocalCost = 0.0;
        this.tax = 0.0;
        this.currency = null;
    }

    public double calculateLocalCost(Currency currency) {
        this.currency = currency;
        this.localCost = currency.exchangeRate * currency.foreignCost;
        return this.localCost;
    }

    public double calculateTaxedLocalCost(Currency currency) {
        this.calculateLocalCost(currency);
        if (!exempted) {
            this.tax = this.localCost * 0.2;
            this.taxedLocalCost = this.localCost + this.tax;
        }
        return this.taxedLocalCost;
    }

    public void printLocalCostDetails(Currency currency) {
        double cost = 0.0;
        if (exempted) {
            cost = this.calculateLocalCost(currency);

        } else {
            cost = this.calculateTaxedLocalCost(currency);

        }

        System.out.println("Cost Name: " + costName);
        System.out.println("Local Cost: " + cost);
        System.out.println("Foreign Cost: "+currency.foreignCost);
        System.out.println(" ");

        if(exempted){
            System.out.println("The Medical Service is not expected from tax paying.");
            System.out.println("Untaxed Local Cost: "+ this.localCost);
            System.out.println("Tax: "+this.localCost*0.2);
            System.out.println("Taxed Local cost: "+this.taxedLocalCost);
        }
        else{
            System.out.println("The Medical Service is expected from tax paying.");
            System.out.println("Untaxed Local Cost: "+ this.localCost);

        }

        System.out.println(" ");
        currency.printCurrencyDetails();
    }
}

 class Driver {
    public static void main(String[] args) {
        Currency currency = new Currency("Dollar", 307.22, 100);
        LocalCostService service = new LocalCostService("Medical Service", false);
        service.printLocalCostDetails(currency);
    }
}
