package tddbc

class VendingMachine {

    Integer total = 0
    static final List ALLOW_MONEYS = [10, 50, 100, 500, 1000]

    def insert(Integer coin) {
        if (ALLOW_MONEYS.contains(coin)) {
            total += coin
            return 0
        }
        coin
    }

    def refund() {
        def refund = total
        total = 0
        refund
    }

}
