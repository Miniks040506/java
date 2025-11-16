public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i ++) {
            LPAStudent s = new LPAStudent("SE20350" + i,
                    switch (i) {
                        case 1 -> "Mary";
                        case 2 -> "Tim";
                        case 3 -> "Carol";
                        case 4 -> "Harry";
                        case 5 -> "Matt";
                        default -> "Anonymous";
                    },
                    "04/05/2006",
                    "Java Masterclass");

            System.out.println(s);
        }

        StudentData pojoStudent = new StudentData("S923006", "Ann",
                "05/11/1985", "Java Masterclass");

        LPAStudent recordStudent = new LPAStudent("S923007", "Bill",
                "17/10/1985", "Java Masterclass");

        System.out.println(pojoStudent);
        System.out.println(recordStudent);

        pojoStudent.setClassList(pojoStudent.getClassList() + ", Java OCP exam 829!");

        System.out.println(pojoStudent.getName() + " is taking " + pojoStudent.getClassList());
        System.out.println(recordStudent.name() + " is taking " + recordStudent.classList());
    }
}
