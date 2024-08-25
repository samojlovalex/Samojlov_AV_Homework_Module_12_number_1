class Phone {
    fun phone(): String{
        val firstPhoneNumber = "+7917"
        val secondPhoneNumberGenerate = (0..6).map { ('0'..'9').random() }.toMutableList()
        val secondPhoneNumber = secondPhoneNumberGenerate.toList().fold(""){a, b -> "$a$b"}.toString()
        val phone = firstPhoneNumber + secondPhoneNumber
        return phone
    }
}