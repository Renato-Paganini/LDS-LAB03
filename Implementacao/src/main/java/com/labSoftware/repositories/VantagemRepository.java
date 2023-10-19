package com.labSoftware.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.labSoftware.models.Vantagem;

@Repository
public interface VantagemRepository extends JpaRepository<Vantagem, Long> {


}
