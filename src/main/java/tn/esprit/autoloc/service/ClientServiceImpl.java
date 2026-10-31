package tn.esprit.autoloc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.mapper.ClientMapper;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.web.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    public Optional<ClientDTO> findById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto);
    }

    @Override
    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Optional<ClientDTO> findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .map(clientMapper::toDto);
    }

    @Override
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }
}
