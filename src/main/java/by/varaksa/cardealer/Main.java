package by.varaksa.cardealer;

import by.varaksa.cardealer.model.validator.UserValidator;

public class Main {
    public static void main(String[] args) {
//        String test = "varaksssvdsdvsmail.ru";
//        String regexp1 = "[a-zA-Z0-9[а-яА-Я]\\._\\-]{2,15}";
//        String regexp2 = "[a-zA-Z0-9[а-яА-Я]\\._\\-]{5,55}";
//
//        Pattern r = Pattern.compile(regexp2);
//        Matcher m = r.matcher(test);
//
//        if (m.matches()) {
//            System.out.println("good");
//        } else {
//            System.out.println("bad");
//        }

        UserValidator.userValidate(UserValidator.EMAIL_REGEXP, "fsd@fsff@");



    }
}
