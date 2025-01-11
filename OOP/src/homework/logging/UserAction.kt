package homework.logging

enum class UserAction {

    LOGIN, LOGOUT, CLICK_BUTTON, VIEW_PAGE;

    override fun toString(): String {
        return "UserAction: ${this.name}"
    }
}