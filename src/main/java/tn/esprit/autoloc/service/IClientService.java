package tn.esprit.autoloc.service;

import tn.esprit.autoloc.web.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    List<ClientDTO> findAll();

    Optional<ClientDTO> findById(Long id);

    ClientDTO save(ClientDTO clientDTO);

    void deleteById(Long id);

    Optional<ClientDTO> findByEmail(String email);

    boolean existsByEmail(String email);
}
