package tddbc

/**
 * 自動販売機です。
 */
class VendingMachine {

    /** 許可するお金 */
    static final List ALLOW_MONEY_LIST = [10, 50, 100, 500, 1000]

    /** 投入されたお金 */
    List insertedMoneyList = []

    Integer getTotal() {
        insertedMoneyList.sum() ?: 0
    }

    /**
     * お金を投入します。
     * <p>
     * 許可されないお金が投入された場合はそのまま釣り銭として返します。
     *
     * @param money お金
     * @return 釣り銭
     */
    void insert(Integer money) {
        if (ALLOW_MONEY_LIST.contains(money)) {
            insertedMoneyList << money
            return
        }
        throw new UnusableMoneyUsedException(insertedMoney: money)
    }

    /**
     * 払い戻しをします。
     * <p>
     * 現在投入されている金額を返し、合計金額を0にします。
     *
     * @return 釣り銭
     */
    def refund() {
        def refundMoneyList = insertedMoneyList
        insertedMoneyList = []
        refundMoneyList
    }

}
