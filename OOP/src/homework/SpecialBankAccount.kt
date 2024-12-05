package homework

class SpecialBankAccount: BankAccount() {
    fun specialWithdraw(amount: Int) {
        withdraw(amount)
    }
}