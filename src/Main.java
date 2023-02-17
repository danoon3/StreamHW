import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Potter", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long countOfPersons = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(countOfPersons);

        List<String> armySurnames = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getSurname)
                .collect(Collectors.toList());

        System.out.println(armySurnames);

        List<String> workerSurnames = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> (person.getSex()==Sex.MAN && person.getAge() >= 18 && person.getAge() < 65) ||
                        (person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <60))
                .sorted(Comparator.comparing(Person::getSurname))
                .map(Person::getSurname)
                .collect(Collectors.toList());
    }
}