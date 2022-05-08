package com.Applifting.Applifting.user;

import com.Applifting.Applifting.monitor.MonitoringResult;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    ModelMapper modelMapper = new ModelMapper();

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<UserResult> get(long id) {

        Optional<User> res = this.userRepository.findById(id);

        UserResult dto = modelMapper.map(res.get(), UserResult.class);

        return ResponseEntity.ok(dto);

    }

    public ResponseEntity<List<UserResult>> getAll(Pageable page) {

        Page<User> res = userRepository.findAll(page);

        List<UserResult> found = res.get().map(u -> modelMapper.map(u, UserResult.class)).collect(Collectors.toList());

        return ResponseEntity.ok(found);

    }

    public ResponseEntity<Void> post(UserResult result) {

        userRepository.save(modelMapper.map(result,User.class));

        return ResponseEntity.ok().build();

    }

    public ResponseEntity<Void> delete(long id) {

        userRepository.deleteById(id);

        return ResponseEntity.ok().build();

    }

}
