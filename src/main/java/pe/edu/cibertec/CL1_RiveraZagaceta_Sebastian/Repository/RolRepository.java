package pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.CL1_RiveraZagaceta_Sebastian.Model.bd.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Rol findByNomrol(String nomrol);
}