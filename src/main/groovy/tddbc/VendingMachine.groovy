package tddbc

/**
 * 自動販売機です。
 */
class VendingMachine {

    /** 許可するお金 */
    static final List ALLOW_MONEYS = [10, 50, 100, 500, 1000]

    /** 合計金額 */
    Integer total = 0

    /**
     * お金を投入します。
     * <p>
     * 許可されないお金が投入された場合はそのまま釣り銭として返します。
     *
     * @param coin お金
     * @return 釣り銭
     */
    def insert(Integer coin) {
        if (ALLOW_MONEYS.contains(coin)) {
            total += coin
            return 0
        }
        coin
    }

    /**
     * 払い戻しをします。
     * <p>
     * 現在投入されている金額を返し、合計金額を0にします。
     *
     * @return 釣り銭
     */
    def refund() {
        def refund = total
        total = 0
        refund
    }

}
