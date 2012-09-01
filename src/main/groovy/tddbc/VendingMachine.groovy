package tddbc

class VendingMachine {

    Integer total = 0

    def insert(Integer coin) {
        total += coin
    }

    def refund() {
        def refund = total
        total = 0
        refund
    }

}
