package homework.bank

class SpecialBankAccount: BankAccount() {
    fun specialWithdraw(amount: Int) {
        withdraw(amount)
    }
}