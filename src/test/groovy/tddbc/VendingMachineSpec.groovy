package tddbc

import spock.lang.Specification
import spock.lang.Unroll

class VendingMachineSpec extends Specification {

    VendingMachine vendingMachine

    def setup() {
        vendingMachine = new VendingMachine()
    }

    def "何もしないで総計を取得する"() {
        expect:
        vendingMachine.total == 0
    }

    @Unroll
    def "#coin円を入れて総計を取得すると#total円を取得できる"() {
        when:
        assert vendingMachine.insert(coin) == 0

        then:
        vendingMachine.total == total

        where:
        coin | total
        10   | 10
        50   | 50
        100  | 100
        500  | 500
        1000 | 1000
    }

    @Unroll
    def "硬貨#coinsを入れて総計を取得すると#total円となる"() {
        when:
        coins.each { coin ->
            vendingMachine.insert(coin)
        }

        then:
        vendingMachine.total == total

        where:
        coins                    | total
        [10, 10]                 | 20
        [10, 50, 100]            | 160
        [10, 50, 100, 500]       | 660
        [10, 50, 100, 500, 1000] | 1660
    }


    def "何もしないで払い戻しすると0円を取得できる"() {
        expect:
        vendingMachine.refund() == 0
    }

    @Unroll
    def "#coin円を入れて払い戻しをすると#change円を取得できる"() {
        when:
        vendingMachine.insert(coin)

        then:
        vendingMachine.refund() == change

        where:
        coin | change
        10   | 10
        50   | 50
        100  | 100
        500  | 500
        1000 | 1000
    }

    @Unroll
    def "硬貨#coinsを入れて払い戻しすると#change円を取得できる"() {
        when:
        coins.each { coin ->
            vendingMachine.insert(coin)
        }

        then:
        vendingMachine.refund() == change

        where:
        coins                    | change
        [10, 10]                 | 20
        [10, 50, 100]            | 160
        [10, 50, 100, 500]       | 660
        [10, 50, 100, 500, 1000] | 1660
    }

    def "10円を入れて払い戻しした後は総計が0円になる"() {
        setup:
        vendingMachine.insert(10)

        when:
        vendingMachine.refund()

        then:
        vendingMachine.total == 0
    }


    @Unroll
    def "#coin円を入れると釣り銭としてそのまま返す"() {
        expect:
        vendingMachine.insert(coin)

        where:
        coin << [1, 5, 2000, 5000, 10000]
    }

}
