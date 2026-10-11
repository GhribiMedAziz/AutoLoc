package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.repository.EmployeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeServiceImpl implements IEmployeService {

    private final EmployeRepository employeRepository;

    @Autowired
    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public List<Employe> findAll() {
        return employeRepository.findAll();
    }

    @Override
    public Optional<Employe> findById(Long id) {
        return employeRepository.findById(id);
    }

    @Override
    @Transactional
    public Employe save(Employe employe) {
        return employeRepository.save(employe);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        employeRepository.deleteById(id);
    }

    @Override
    public List<Employe> findByAgenceId(Long idAgence) {
        return employeRepository.findByAgenceIdAgence(idAgence);
    }
}
