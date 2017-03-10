package javabeat.net.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import javabeat.net.springboot.domain.Activity;

public interface ActivityRepository extends JpaRepository<Activity, String> {

}
