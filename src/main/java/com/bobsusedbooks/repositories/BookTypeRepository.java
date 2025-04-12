package com.bobsusedbooks.repositories;

import com.bobsusedbooks.entities.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTypeRepository extends JpaRepository<BookType, Long> {
    BookType findByName(String name);
}
