import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();

        // 1. Kitap ekle
        library.addBook("Sefiller", "Victor Hugo", "Roman", 1200);
        library.addBook("Suç ve Ceza", "Dostoyevski", "Roman", 800);

        // 2. Üye ekle
        boolean added = library.addMember(1, "Ahmet", "ahmet@mail.com");
        System.out.println("Üye eklendi (true beklenir): " + added);
        added = library.addMember(1, "Ali", "ali@mail.com"); // Aynı ID -> false
        System.out.println("Aynı ID ile üye eklendi (false beklenir): " + added);
        added = library.addMember(2, "Ahmet2", "ahmet@mail.com"); // Aynı email -> false
        System.out.println("Aynı email ile üye eklendi (false beklenir): " + added);

        // 3. Kitap listele
        System.out.println("Kitap sayısı (2 beklenir): " + library.listBooks().size());

        // 4. Kitap bulma - var / yok
        System.out.println("Suç ve Ceza bulundu mu? (true beklenir): " +
            (library.findBookByTitle("Suç ve Ceza").size() == 1));
        System.out.println("Yok kitap bulundu mu? (false beklenir): " +
            (library.findBookByTitle("Yok").size() > 0));

        // 5. Üye bulma - var / yok
        System.out.println("Ahmet üyeleri sayısı (>=1 beklenir): " +
            library.findMemberByName("Ahmet").size());
        System.out.println("Yok üye var mı? (0 beklenir): " +
            library.findMemberByName("Yok").size());

        // 6. Kitap ödünç alma - başarılı
        Book book1 = library.findBookByTitle("Sefiller").get(0);
        Member member1 = library.findMemberByID(1);
        Library.BorrowStatus status = library.borrowBook(book1, member1);
        System.out.println("Kitap ödünç alma başarılı mı? (SUCCESS beklenir): " + status);

        // 7. Aynı kitabı tekrar ödünç almaya çalışma - başarısız
        status = library.borrowBook(book1, member1);
        System.out.println("Aynı kitabı tekrar alma (BOOK_NOT_AVAILABLE beklenir): " + status);

        // 8. Kitap bulunmayan ödünç alma
        Book fakeBook = new Book("Fake", "Fake", "Fake", 10, 99999);
        status = library.borrowBook(fakeBook, member1);
        System.out.println("Olmayan kitap ödünç alma (BOOK_NOT_FOUND beklenir): " + status);

        // 9. Üye bulunmayan ödünç alma
        Member fakeMember = new Member(999, "Fake", "fake@mail.com");
        status = library.borrowBook(book1, fakeMember);
        System.out.println("Olmayan üye ödünç alma (MEMBER_NOT_FOUND beklenir): " + status);

        // 10. Kitap iade etme - başarılı
        Library.ReturnStatus returnStatus = library.returnBook(book1);
        System.out.println("Kitap iade başarılı mı? (SUCCESS beklenir): " + returnStatus);

        // 11. Aynı kitabı tekrar iade etme - başarısız
        returnStatus = library.returnBook(book1);
        System.out.println("İade edilmiş kitabı tekrar iade etme (BOOK_NOT_LOANED beklenir): " + returnStatus);

        // 12. Kitap silme - ödünç verilen kitap iade edilmeden silinse ne olur?
        // Önce kitabı ödünç alalım
        Book book2 = library.findBookByTitle("Suç ve Ceza").get(0);
        status = library.borrowBook(book2, member1);
        System.out.println("Kitap 2 ödünç alma (SUCCESS beklenir): " + status);

        // Kitap sil
        boolean removed = library.removeBook(book2);
        System.out.println("Ödünç verilmiş kitap iade edilmeden silindi mi? (true beklenir): " + removed);

        // Silinen kitabı bulmaya çalışma
        Book deletedBook = library.findBookByID(book2.getID());
        System.out.println("Silinen kitap bulundu mu? (null beklenir): " + deletedBook);

        // 13. ID üreteci test (benzersizlik)
        int id1 = library.generateUniqueId();
        int id2 = library.generateUniqueId();
        System.out.println("ID'ler farklı mı? (true beklenir): " + (id1 != id2));

        // 14. Üye listesi ve kitap listesi güncel mi?
        System.out.println("Üye sayısı (1 beklenir): " + library.listMembers().size());
        System.out.println("Kitap sayısı (1 beklenir): " + library.listBooks().size());
    }
}
