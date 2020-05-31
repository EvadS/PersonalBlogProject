package com.se.blog.personalblog.repositories;

import com.se.blog.personalblog.model.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findAll(Pageable pageable);

    Tag   getByName(String tagName);

    Optional<Tag> findById (Long tagID);
}