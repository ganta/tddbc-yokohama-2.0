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
    def "#money円を入れて総計を取得すると#total円を取得できる"() {
        when:
        vendingMachine.insert(money)

        then:
        vendingMachine.total == total

        where:
        money | total
        10    | 10
        50    | 50
        100   | 100
        500   | 500
        1000  | 1000
    }

    @Unroll
    def "硬貨#moneyListを入れて総計を取得すると#total円となる"() {
        when:
        moneyList.each { money ->
            vendingMachine.insert(money)
        }

        then:
        vendingMachine.total == total

        where:
        moneyList                | total
        [10, 10]                 | 20
        [10, 50, 100]            | 160
        [10, 50, 100, 500]       | 660
        [10, 50, 100, 500, 1000] | 1660
    }


    def "何もしないで払い戻しすると空の集合が取得できる"() {
        expect:
        vendingMachine.refund() == []
    }

    @Unroll
    def "#money円を入れて払い戻しをすると#changeを取得できる"() {
        when:
        vendingMachine.insert(money)

        then:
        vendingMachine.refund() == change

        where:
        money | change
        10    | [10]
        50    | [50]
        100   | [100]
        500   | [500]
        1000  | [1000]
    }

    @Unroll
    def "#moneyListを入れて払い戻しすると#changeを取得できる"() {
        when:
        moneyList.each { money ->
            vendingMachine.insert(money)
        }

        then:
        vendingMachine.refund() == change

        where:
        moneyList                | change
        [10, 10]                 | [10, 10]
        [50, 50]                 | [50, 50] // TODO 100円?
        [10, 50, 100]            | [10, 50, 100]
        [10, 50, 100, 500]       | [10, 50, 100, 500]
        [10, 50, 100, 500, 1000] | [10, 50, 100, 500, 1000]
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
    def "#money円を入れると釣り銭としてそのまま返す"() {
        when:
        vendingMachine.insert(money)

        then:
        def e = thrown(UnsupportedMoneyInsertedException)
        e.insertedMoney == money

        where:
        money << [1, 5, 2000, 5000, 10000]
    }

    def "初期状態でジュースの情報を取得すると120円のコーラ5本だ！"() {
        when:
        def actual = vendingMachine.storedJuice

        then:
        actual.name == 'コーラ'
        actual.price == 120
        actual.stock == 5
    }

}
