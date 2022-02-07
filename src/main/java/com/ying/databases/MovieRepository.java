package com.ying.databases;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ying.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	Movie findById(int id);
	Movie findByTitle(String title);
	List<Movie> findAllByYear(int year);
}
