package dev.luisf.movieflix.service;

import dev.luisf.movieflix.entity.Streaming;
import dev.luisf.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public List<Streaming> findAll(){
        return repository.findAll();
    }

    public Streaming save(Streaming streaming){
        return repository.save(streaming);
    }

    public Optional<Streaming> getById(Long id){
        return repository.findById(id);
    }

    public void deleteStreaming(Long id){
        repository.deleteById(id);
    }
}
