package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDateGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDateGenerator generator = new ContactDateGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactDate> contacts = generateContacts(count);
        save(contacts, new File(file));
    }

    private static void save(List<ContactDate> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactDate contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getAddress()
                    , contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getEmail()
                    , contact.getEmail2(), contact.getEmail3()));
        }
        writer.close();
    }

    private static List<ContactDate> generateContacts(int count) {
        List<ContactDate> contacts = new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactDate()
                    .withFirstname(String.format("Vasya %s", i)).withLastname(String.format("Byblik %s", i))
                    .withAddress(String.format("Lenina, 152, 1%s",i)).withHomePhone(String.format("+1111111%s",i))
                    .withMobilePhone(String.format("+22222222%s",i)).withWorkPhone(String.format("+73333333%s",i))
                    .withEmail(String.format("rherthe%s@gmail.com", i)).withEmail2(String.format("1re%s@yandex.ru", i))
                    .withEmail3(String.format("vv%s@mail.ru", i)));
        }
        return contacts;
    }
}
