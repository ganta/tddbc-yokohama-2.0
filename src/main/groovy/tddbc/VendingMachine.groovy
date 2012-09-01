package tddbc

/**
 * 自動販売機です。
 */
class VendingMachine {

    /** 許可するお金 */
    static final List ALLOW_MONEY_LIST = [10, 50, 100, 500, 1000]

    /** ジュースの初期状態 */
    static final Juice DEFAULT = new Juice(name: 'コーラ', price: 120, stock: 5)

    /** 投入されたお金 */
    List insertedMoneyList = []

    /** 格納されているジュース */
    Juice storedJuice = DEFAULT


    /**
     * 投入金額の総計を取得します。
     *
     * @return 総計
     */
    def getTotal() {
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
        if (!ALLOW_MONEY_LIST.contains(money)) {
            throw new UnsupportedMoneyInsertedException(insertedMoney: money)
        }
        insertedMoneyList << money
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
