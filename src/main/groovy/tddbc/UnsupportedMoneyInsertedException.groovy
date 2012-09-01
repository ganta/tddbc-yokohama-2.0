package tddbc

/**
 * 想定外のお金が投入された時に発生する例外です。
 * */
class UnsupportedMoneyInsertedException extends RuntimeException {

    /** 投入されたお金 */
    Integer insertedMoney

}
